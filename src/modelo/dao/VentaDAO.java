package modelo.dao;

import config.ConexionDB;
import modelo.dto.VentaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    
    // ========== CREATE (Registrar Venta) ==========
    public int crear(VentaDTO venta) {
        String sql = "INSERT INTO ventas (numero_factura, fecha_venta, id_cliente, " +
                     "id_vendedor, id_auto, codigo_forma_pago, precio_base, " +
                     "impuesto_venta, iva, total_pagar, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, venta.getNumeroFactura());
            ps.setDate(2, new java.sql.Date(venta.getFechaVenta().getTime()));
            ps.setInt(3, venta.getIdCliente());
            ps.setInt(4, venta.getIdVendedor());
            ps.setInt(5, venta.getIdAuto());
            ps.setString(6, venta.getCodigoFormaPago());
            ps.setDouble(7, venta.getPrecioBase());
            ps.setDouble(8, venta.getImpuestoVenta());
            ps.setDouble(9, venta.getIva());
            ps.setDouble(10, venta.getTotalPagar());
            ps.setString(11, venta.getEstado() != null ? venta.getEstado() : "Activa");
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Venta registrada: " + venta.getNumeroFactura());
                
                // Actualizar estado del automóvil a "Vendido"
                actualizarEstadoAutomovil(venta.getIdAuto(), "Vendido");
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al crear venta");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== ACTUALIZAR ESTADO DEL AUTOMÓVIL ==========
    private void actualizarEstadoAutomovil(int idAuto, String nuevoEstado) {
        String sql = "UPDATE automoviles SET estado = ? WHERE id_auto = ?";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idAuto);
            ps.executeUpdate();
            
            System.out.println("✅ Estado del automóvil actualizado a: " + nuevoEstado);
            
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar estado del automóvil");
            e.printStackTrace();
        }
    }
    
    // ========== READ ALL (Leer todas las ventas) ==========
    public List<VentaDTO> leerTodos() {
        String sql = "SELECT v.*, " +
                     "c.nombre as nombre_cliente, " +
                     "ve.nombre as nombre_vendedor, " +
                     "a.codigo as codigo_auto, " +
                     "fp.nombre_forma as descripcion_forma_pago " +
                     "FROM ventas v " +
                     "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                     "INNER JOIN vendedores ve ON v.id_vendedor = ve.id_vendedor " +
                     "INNER JOIN automoviles a ON v.id_auto = a.id_auto " +
                     "LEFT JOIN formas_pago fp ON v.codigo_forma_pago = fp.codigo_forma " +
                     "WHERE v.estado = 'Activa' " +
                     "ORDER BY v.fecha_venta DESC, v.numero_factura DESC";
        
        List<VentaDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                VentaDTO venta = new VentaDTO();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setNumeroFactura(rs.getString("numero_factura"));
                venta.setFechaVenta(rs.getDate("fecha_venta"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdVendedor(rs.getInt("id_vendedor"));
                venta.setIdAuto(rs.getInt("id_auto"));
                venta.setCodigoFormaPago(rs.getString("codigo_forma_pago"));
                venta.setPrecioBase(rs.getDouble("precio_base"));
                venta.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                venta.setIva(rs.getDouble("iva"));
                venta.setTotalPagar(rs.getDouble("total_pagar"));
                venta.setEstado(rs.getString("estado"));
                
                // Campos adicionales
                venta.setNombreCliente(rs.getString("nombre_cliente"));
                venta.setNombreVendedor(rs.getString("nombre_vendedor"));
                venta.setCodigoAuto(rs.getString("codigo_auto"));
                venta.setDescripcionFormaPago(rs.getString("descripcion_forma_pago"));
                
                lista.add(venta);
            }
            
            System.out.println("✅ Se encontraron " + lista.size() + " ventas");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer ventas");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    // ========== GENERAR NÚMERO DE FACTURA ==========
    public String generarNumeroFactura() {
        String sql = "SELECT MAX(CAST(SUBSTRING(numero_factura, 2) AS UNSIGNED)) as ultimo " +
                     "FROM ventas WHERE numero_factura LIKE 'F%'";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            int siguiente = 1;
            if (rs.next() && rs.getObject("ultimo") != null) {
                siguiente = rs.getInt("ultimo") + 1;
            }
            
            return String.format("F%05d", siguiente); // F00001, F00002, etc.
            
        } catch (SQLException e) {
            System.err.println("❌ Error al generar número de factura");
            e.printStackTrace();
            // Si hay error, generar con timestamp
            return "F" + System.currentTimeMillis();
        }
    }
    
    // ========== LEER POR NÚMERO DE FACTURA ==========
    public VentaDTO leerPorNumeroFactura(String numeroFactura) {
        String sql = "SELECT v.*, " +
                     "c.nombre as nombre_cliente, " +
                     "ve.nombre as nombre_vendedor, " +
                     "a.codigo as codigo_auto, " +
                     "fp.nombre_forma as descripcion_forma_pago " +
                     "FROM ventas v " +
                     "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                     "INNER JOIN vendedores ve ON v.id_vendedor = ve.id_vendedor " +
                     "INNER JOIN automoviles a ON v.id_auto = a.id_auto " +
                     "LEFT JOIN formas_pago fp ON v.codigo_forma_pago = fp.codigo_forma " +
                     "WHERE v.numero_factura = ?";
        
        VentaDTO venta = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, numeroFactura);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                venta = new VentaDTO();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setNumeroFactura(rs.getString("numero_factura"));
                venta.setFechaVenta(rs.getDate("fecha_venta"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdVendedor(rs.getInt("id_vendedor"));
                venta.setIdAuto(rs.getInt("id_auto"));
                venta.setCodigoFormaPago(rs.getString("codigo_forma_pago"));
                venta.setPrecioBase(rs.getDouble("precio_base"));
                venta.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                venta.setIva(rs.getDouble("iva"));
                venta.setTotalPagar(rs.getDouble("total_pagar"));
                venta.setEstado(rs.getString("estado"));
                
                venta.setNombreCliente(rs.getString("nombre_cliente"));
                venta.setNombreVendedor(rs.getString("nombre_vendedor"));
                venta.setCodigoAuto(rs.getString("codigo_auto"));
                venta.setDescripcionFormaPago(rs.getString("descripcion_forma_pago"));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer venta");
            e.printStackTrace();
        }
        
        return venta;
    }
}
