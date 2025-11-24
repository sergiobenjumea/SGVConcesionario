package modelo.dao;

import config.ConexionDB;
import modelo.dto.VentaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    Connection acceso;
    PreparedStatement ps;
    ResultSet rs;

    // --- REGISTRAR VENTA ---
    public boolean registrar(VentaDTO v) {
        String sql = "INSERT INTO ventas (numero_factura, fecha_venta, id_cliente, id_vendedor, id_auto, id_forma_pago, precio_base, impuesto_venta, iva, total_pagar) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, v.getNumeroFactura());
            ps.setDate(2, v.getFechaVenta());
            ps.setInt(3, v.getIdCliente());
            ps.setInt(4, v.getIdVendedor());
            ps.setInt(5, v.getIdAuto());
            ps.setInt(6, v.getIdFormaPago());
            ps.setDouble(7, v.getPrecioBase());
            ps.setDouble(8, v.getImpuesto());
            ps.setDouble(9, v.getIva());
            ps.setDouble(10, v.getTotalPagar());
            ps.executeUpdate();
            
            // Opcional: Actualizar estado del auto a 'Vendido'
            String sqlAuto = "UPDATE automoviles SET estado = 'Vendido' WHERE id_auto = ?";
            PreparedStatement psAuto = acceso.prepareStatement(sqlAuto);
            psAuto.setInt(1, v.getIdAuto());
            psAuto.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println("Error registro venta: " + e.toString());
            return false;
        }
    }

    // --- LISTAR VENTAS (JOIN GIGANTE PARA VER NOMBRES) ---
    public List<VentaDTO> listar() {
        List<VentaDTO> lista = new ArrayList<>();
        String sql = "SELECT v.*, c.nombre as cli_nom, ven.nombre as ven_nom, " +
                     "m.nombre as marca, l.nombre as linea, fp.nombre as fp_nom " +
                     "FROM ventas v " +
                     "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                     "INNER JOIN vendedores ven ON v.id_vendedor = ven.id_vendedor " +
                     "INNER JOIN automoviles a ON v.id_auto = a.id_auto " +
                     "INNER JOIN lineas l ON a.id_linea = l.id_linea " +
                     "INNER JOIN marcas m ON l.id_marca = m.id_marca " +
                     "INNER JOIN formas_pago fp ON v.id_forma_pago = fp.id_forma_pago " +
                     "ORDER BY v.id_venta DESC";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VentaDTO v = new VentaDTO();
                v.setNumeroFactura(rs.getString("numero_factura"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                v.setNombreVendedor(rs.getString("ven_nom"));
                v.setDescripcionAuto(rs.getString("marca") + " " + rs.getString("linea"));
                // El campo "Año" en la tabla no está en el DTO, pero puedes concatenarlo si quieres
                v.setNombreCliente(rs.getString("cli_nom"));
                v.setTotalPagar(rs.getDouble("total_pagar"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error listar ventas: " + e.toString());
        }
        return lista;
    }

    // --- GENERAR NÚMERO DE FACTURA AUTOMÁTICO ---
    public String obtenerSiguienteFactura() {
        String num = "FAC-001";
        String sql = "SELECT MAX(id_venta) FROM ventas";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int siguiente = rs.getInt(1) + 1;
                num = "FAC-" + String.format("%03d", siguiente);
            }
        } catch (SQLException e) {}
        return num;
    }
    // --- NUEVO MÉTODO PARA EL REPORTE DE VENDEDOR ---
    public List<VentaDTO> listarPorVendedor(int idVendedor) {
        List<VentaDTO> lista = new ArrayList<>();
        // Consulta Multitabla (JOIN) para obtener nombres descriptivos
        String sql = "SELECT v.numero_factura, v.fecha_venta, " +
                     "m.nombre AS marca, l.nombre AS linea, a.modelo_anio, " +
                     "c.nombre AS cliente, v.total_pagar " +
                     "FROM ventas v " +
                     "INNER JOIN automoviles a ON v.id_auto = a.id_auto " +
                     "INNER JOIN lineas l ON a.id_linea = l.id_linea " +
                     "INNER JOIN marcas m ON l.id_marca = m.id_marca " +
                     "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                     "WHERE v.id_vendedor = ? " +
                     "ORDER BY v.fecha_venta DESC";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idVendedor); // Filtramos por el ID interno del vendedor
            rs = ps.executeQuery();
            
            while (rs.next()) {
                VentaDTO v = new VentaDTO();
                v.setNumeroFactura(rs.getString("numero_factura"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                v.setNombreMarca(rs.getString("marca"));
                v.setNombreLinea(rs.getString("linea"));
                v.setAnioAuto(rs.getInt("modelo_anio"));
                v.setNombreCliente(rs.getString("cliente"));
                v.setTotalPagar(rs.getDouble("total_pagar"));
                
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error listar por vendedor: " + e.toString());
        }
        return lista;
    }
    // --- OBTENER LA VENTA MÁS ALTA (GLOBAL O POR VENDEDOR) ---
    public VentaDTO obtenerMayorVenta(String cedulaVendedor) {
        VentaDTO v = null;
        String sql = "";
        
        // Construcción dinámica de la consulta
        String baseSql = "SELECT v.numero_factura, v.fecha_venta, v.total_pagar, v.precio_base, v.impuesto_venta, v.iva, " +
                         "ven.identificacion AS id_vendedor, ven.nombre AS nom_vend, ven.profesion, " +
                         "c.nombre AS nom_cli, " +
                         "m.nombre AS marca, l.nombre AS linea, a.modelo_anio " +
                         "FROM ventas v " +
                         "INNER JOIN vendedores ven ON v.id_vendedor = ven.id_vendedor " +
                         "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                         "INNER JOIN automoviles a ON v.id_auto = a.id_auto " +
                         "INNER JOIN lineas l ON a.id_linea = l.id_linea " +
                         "INNER JOIN marcas m ON l.id_marca = m.id_marca ";

        // Lógica: Si hay cédula filtra por ella, si no, busca en todo
        if (cedulaVendedor != null && !cedulaVendedor.isEmpty()) {
            sql = baseSql + "WHERE ven.identificacion = ? ORDER BY v.total_pagar DESC LIMIT 1";
        } else {
            sql = baseSql + "ORDER BY v.total_pagar DESC LIMIT 1";
        }

        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            
            if (cedulaVendedor != null && !cedulaVendedor.isEmpty()) {
                ps.setString(1, cedulaVendedor);
            }
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                v = new VentaDTO();
                // Datos Venta
                v.setNumeroFactura(rs.getString("numero_factura"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                v.setPrecioBase(rs.getDouble("precio_base"));
                v.setImpuesto(rs.getDouble("impuesto_venta"));
                v.setIva(rs.getDouble("iva"));
                v.setTotalPagar(rs.getDouble("total_pagar"));
                
                // Datos Vendedor (Para cabecera)
                // Usamos una propiedad temporal en el DTO o reciclamos 'idVendedor' si es int
                // Para visualización rápida usaremos los campos Strings del DTO
                v.setNombreVendedor(rs.getString("nom_vend"));
                v.setProfesionVendedor(rs.getString("profesion"));
                
                // Datos Cliente
                v.setNombreCliente(rs.getString("nom_cli"));
                
                // Datos Auto
                v.setNombreMarca(rs.getString("marca"));
                v.setNombreLinea(rs.getString("linea"));
                v.setAnioAuto(rs.getInt("modelo_anio"));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo mayor venta: " + e.toString());
        }
        return v;
    }
    // --- LISTAR SOLO VENTAS CON CRÉDITO DEL CONCESIONARIO (ID 2) ---
    public List<VentaDTO> listarVentasCreditoConcesionario() {
        List<VentaDTO> lista = new ArrayList<>();
        // ID 2 = Crédito Directo (Según nuestro script semilla)
        String sql = "SELECT v.numero_factura, v.fecha_venta, " +
                     "ven.nombre AS nom_vend, " +
                     "m.nombre AS marca, l.nombre AS linea, a.modelo_anio, " +
                     "c.nombre AS cliente, v.total_pagar " +
                     "FROM ventas v " +
                     "INNER JOIN vendedores ven ON v.id_vendedor = ven.id_vendedor " +
                     "INNER JOIN automoviles a ON v.id_auto = a.id_auto " +
                     "INNER JOIN lineas l ON a.id_linea = l.id_linea " +
                     "INNER JOIN marcas m ON l.id_marca = m.id_marca " +
                     "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                     "WHERE v.id_forma_pago = 2 " + // <--- FILTRO CLAVE
                     "ORDER BY v.fecha_venta DESC";
        
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                VentaDTO v = new VentaDTO();
                v.setNumeroFactura(rs.getString("numero_factura"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                v.setNombreVendedor(rs.getString("nom_vend"));
                v.setNombreMarca(rs.getString("marca"));
                v.setNombreLinea(rs.getString("linea"));
                v.setAnioAuto(rs.getInt("modelo_anio"));
                v.setNombreCliente(rs.getString("cliente"));
                v.setTotalPagar(rs.getDouble("total_pagar"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error listar crédito concesionario: " + e.toString());
        }
        return lista;
    }
}