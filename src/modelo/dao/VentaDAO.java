package modelo.dao;

import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.VentaDTO;

public class VentaDAO {

    public List<VentaDTO> listarVentas() {
        List<VentaDTO> lista = new ArrayList<>();
        String sql = "SELECT v.*, "
                   + "c.nombre as nombre_cliente, "
                   + "vd.nombre as nombre_vendedor, "
                   + "vd.profesion as profesion_vendedor, "
                   + "a.codigo as codigo_auto, m.nombre_marca as marca_auto, l.nombre_linea as linea_auto, a.anio as anio_auto, "
                   + "fp.codigo_forma as codigo_forma_pago, fp.descripcion as descripcion_forma_pago "
                   + "FROM ventas v "
                   + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
                   + "INNER JOIN vendedores vd ON v.id_vendedor = vd.id_vendedor "
                   + "INNER JOIN automoviles a ON v.id_auto = a.id_auto "
                   + "INNER JOIN marcas m ON a.id_marca = m.id_marca "
                   + "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea "
                   + "INNER JOIN formas_pago fp ON v.id_forma_pago = fp.id_forma_pago";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VentaDTO v = new VentaDTO();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setNumeroFactura(rs.getString("numero_factura"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setIdAuto(rs.getInt("id_auto"));
                v.setIdFormaPago(rs.getInt("id_forma_pago"));
                v.setPrecioBase(rs.getDouble("precio_base"));
                v.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                v.setIva(rs.getDouble("iva"));
                v.setTotalPagar(rs.getDouble("total_pagar"));
                v.setEstado(rs.getString("estado"));
                v.setFechaAnulacion(rs.getDate("fecha_anulacion"));
                v.setNombreCliente(rs.getString("nombre_cliente"));
                v.setNombreVendedor(rs.getString("nombre_vendedor"));
                v.setProfesionVendedor(rs.getString("profesion_vendedor"));
                v.setCodigoAuto(rs.getString("codigo_auto"));
                v.setMarcaAuto(rs.getString("marca_auto"));
                v.setLineaAuto(rs.getString("linea_auto"));
                v.setAnioAuto(rs.getInt("anio_auto"));
                v.setCodigoFormaPago(rs.getString("codigo_forma_pago"));
                v.setDescripcionFormaPago(rs.getString("descripcion_forma_pago"));
                lista.add(v);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista; 
    }

    public List<VentaDTO> obtenerVentasPorVendedor(int idVendedor) {
        List<VentaDTO> ventas = new ArrayList<>();
        String sql = "SELECT v.*, "
                   + "a.codigo AS codigo_auto, m.nombre_marca AS marca_auto, l.nombre_linea AS linea_auto, a.anio AS anio_auto, "
                   + "c.nombre AS nombre_cliente "
                   + "FROM ventas v "
                   + "INNER JOIN automoviles a ON v.id_auto = a.id_auto "
                   + "INNER JOIN marcas m ON a.id_marca = m.id_marca "
                   + "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea "
                   + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
                   + "WHERE v.id_vendedor = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVendedor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VentaDTO venta = new VentaDTO();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setNumeroFactura(rs.getString("numero_factura"));
                venta.setFechaVenta(rs.getDate("fecha_venta"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdVendedor(rs.getInt("id_vendedor"));
                venta.setIdAuto(rs.getInt("id_auto"));
                venta.setPrecioBase(rs.getDouble("precio_base"));
                venta.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                venta.setIva(rs.getDouble("iva"));
                venta.setTotalPagar(rs.getDouble("total_pagar"));
                venta.setEstado(rs.getString("estado"));
                venta.setFechaAnulacion(rs.getDate("fecha_anulacion"));
                venta.setCodigoAuto(rs.getString("codigo_auto"));
                venta.setMarcaAuto(rs.getString("marca_auto"));
                venta.setLineaAuto(rs.getString("linea_auto"));
                venta.setAnioAuto(rs.getInt("anio_auto"));
                venta.setNombreCliente(rs.getString("nombre_cliente"));
                ventas.add(venta);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ventas;
    }

    public VentaDTO obtenerVentaConMayorMonto() {
        String sql = "SELECT v.*, "
                   + "c.nombre AS nombre_cliente, "
                   + "vd.nombre AS nombre_vendedor, "
                   + "vd.profesion AS profesion_vendedor, "
                   + "a.codigo AS codigo_auto, "
                   + "m.nombre_marca AS marca_auto, "
                   + "l.nombre_linea AS linea_auto, "
                   + "a.anio AS anio_auto "
                   + "FROM ventas v "
                   + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
                   + "INNER JOIN vendedores vd ON v.id_vendedor = vd.id_vendedor "
                   + "INNER JOIN automoviles a ON v.id_auto = a.id_auto "
                   + "INNER JOIN marcas m ON a.id_marca = m.id_marca "
                   + "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea "
                   + "ORDER BY v.total_pagar DESC LIMIT 1";
        VentaDTO venta = null;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                venta = new VentaDTO();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setNumeroFactura(rs.getString("numero_factura"));
                venta.setFechaVenta(rs.getDate("fecha_venta"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdVendedor(rs.getInt("id_vendedor"));
                venta.setIdAuto(rs.getInt("id_auto"));
                venta.setPrecioBase(rs.getDouble("precio_base"));
                venta.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                venta.setIva(rs.getDouble("iva"));
                venta.setTotalPagar(rs.getDouble("total_pagar"));
                venta.setEstado(rs.getString("estado"));
                venta.setFechaAnulacion(rs.getDate("fecha_anulacion"));
                venta.setCodigoAuto(rs.getString("codigo_auto"));
                venta.setMarcaAuto(rs.getString("marca_auto"));
                venta.setLineaAuto(rs.getString("linea_auto")); // ---- Línea aquí
                venta.setAnioAuto(rs.getInt("anio_auto"));
                venta.setNombreCliente(rs.getString("nombre_cliente"));
                venta.setNombreVendedor(rs.getString("nombre_vendedor"));
                venta.setProfesionVendedor(rs.getString("profesion_vendedor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public List<VentaDTO> obtenerVentasCreditoConcesionario() {
        List<VentaDTO> ventas = new ArrayList<>();
        String sql = "SELECT v.*, "
            + "c.nombre as nombre_cliente, "
            + "vd.nombre as nombre_vendedor, "
            + "a.codigo as codigo_auto, m.nombre_marca as marca_auto, l.nombre_linea as linea_auto, a.anio as anio_auto "
            + "FROM ventas v "
            + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
            + "INNER JOIN vendedores vd ON v.id_vendedor = vd.id_vendedor "
            + "INNER JOIN automoviles a ON v.id_auto = a.id_auto "
            + "INNER JOIN marcas m ON a.id_marca = m.id_marca "
            + "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea "
            + "WHERE v.codigo_forma_pago = 'CR'"; // O ajusta a tu código de forma de pago "Crédito Concesionario"
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VentaDTO v = new VentaDTO();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setNumeroFactura(rs.getString("numero_factura"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setIdAuto(rs.getInt("id_auto"));
                v.setPrecioBase(rs.getDouble("precio_base"));
                v.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                v.setIva(rs.getDouble("iva"));
                v.setTotalPagar(rs.getDouble("total_pagar"));
                v.setEstado(rs.getString("estado"));
                v.setFechaAnulacion(rs.getDate("fecha_anulacion"));
                v.setCodigoAuto(rs.getString("codigo_auto"));
                v.setMarcaAuto(rs.getString("marca_auto"));
                v.setLineaAuto(rs.getString("linea_auto")); // <-- LÍNEA
                v.setAnioAuto(rs.getInt("anio_auto"));      // <-- AÑO
                v.setNombreCliente(rs.getString("nombre_cliente"));
                v.setNombreVendedor(rs.getString("nombre_vendedor"));
                ventas.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ventas;
    }
    public String generarNumeroFactura() {
        String nuevoNumero = null;
        String sql = "SELECT MAX(numero_factura) AS max_factura FROM ventas";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            int numActual = 0;
            if (rs.next()) {
                // Si la columna es varchar, convierte a int
                String maxFac = rs.getString("max_factura");
                if (maxFac != null) {
                    try {
                        numActual = Integer.parseInt(maxFac.trim());
                    } catch (Exception ignored) { numActual = 0; }
                }
            }
            nuevoNumero = String.format("%06d", numActual + 1); // Ejemplo: "000123"
        } catch (Exception e) {
            e.printStackTrace();
            nuevoNumero = "000001"; // Si falla, genera el número inicial
        }
        return nuevoNumero;
    }

    public int crear(VentaDTO venta) {
        int resultado = 0;
        String sql = "INSERT INTO ventas (numero_factura, fecha_venta, id_cliente, id_vendedor, id_auto, codigo_forma_pago, precio_base, impuesto_venta, iva, total_pagar, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
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
            ps.setString(11, venta.getEstado());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public List<VentaDTO> leerTodos() {
        List<VentaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas"; // Ajusta columnas/join si necesitas mostrar más datos
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VentaDTO venta = new VentaDTO();
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
                // Puedes setear datos extra si tu DTO tiene atributos como nombreCliente, nombreVendedor, etc. usando JOINS en el SQL.
                lista.add(venta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
