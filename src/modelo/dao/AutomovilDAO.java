package modelo.dao;

import config.ConexionDB;
import modelo.dto.AutomovilDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomovilDAO {
    
    // ========== CREATE ==========
    public int crear(AutomovilDTO auto) {
        String sql = "INSERT INTO automoviles (codigo, id_marca, id_linea, anio, color, id_tipo_motor, precio_base, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, auto.getCodigo());
            ps.setInt(2, auto.getIdMarca());
            ps.setInt(3, auto.getIdLinea());
            ps.setInt(4, auto.getAnio());
            ps.setString(5, auto.getColor());
            ps.setInt(6, auto.getIdTipoMotor());
            ps.setDouble(7, auto.getPrecioBase());
            ps.setString(8, auto.getEstado());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear automóvil");
            e.printStackTrace();
            return 0;
        }
    }

    // ========== READ por ID ==========
    public AutomovilDTO leer(int idAuto) {
        String sql = "SELECT a.*, m.nombre_marca, l.nombre_linea, tm.nombre_tipo as nombre_tipo_motor, tm.porcentaje_impuesto " +
                     "FROM automoviles a " +
                     "INNER JOIN marcas m ON a.id_marca = m.id_marca " +
                     "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.id_auto = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAuto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return cargarDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al leer automóvil por ID");
            e.printStackTrace();
        }
        return null;
    }

    // ========== READ por Código ==========
    public AutomovilDTO leerPorCodigo(String codigo) {
        String sql = "SELECT a.*, m.nombre_marca, l.nombre_linea, tm.nombre_tipo as nombre_tipo_motor, tm.porcentaje_impuesto " +
                     "FROM automoviles a " +
                     "INNER JOIN marcas m ON a.id_marca = m.id_marca " +
                     "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.codigo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return cargarDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al leer automóvil por código");
            e.printStackTrace();
        }
        return null;
    }

    // ========== BUSCAR POR CÓDIGO (Alias de leerPorCodigo) ==========
    public AutomovilDTO buscarPorCodigo(String codigo) {
        return leerPorCodigo(codigo);
    }

    // ========== UPDATE ==========
    public int actualizar(AutomovilDTO auto) {
        String sql = "UPDATE automoviles SET codigo = ?, id_marca = ?, id_linea = ?, anio = ?, color = ?, id_tipo_motor = ?, precio_base = ?, estado = ? WHERE id_auto = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, auto.getCodigo());
            ps.setInt(2, auto.getIdMarca());
            ps.setInt(3, auto.getIdLinea());
            ps.setInt(4, auto.getAnio());
            ps.setString(5, auto.getColor());
            ps.setInt(6, auto.getIdTipoMotor());
            ps.setDouble(7, auto.getPrecioBase());
            ps.setString(8, auto.getEstado());
            ps.setInt(9, auto.getIdAuto());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar automóvil");
            e.printStackTrace();
            return 0;
        }
    }

    // ========== DELETE ==========
    public int eliminar(int idAuto) {
        String sql = "DELETE FROM automoviles WHERE id_auto = ? AND estado = 'Disponible'";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAuto);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar automóvil");
            e.printStackTrace();
            return 0;
        }
    }

    // ========== READ ALL ==========
    public List<AutomovilDTO> leerTodos() {
        String sql = "SELECT a.*, m.nombre_marca, l.nombre_linea, tm.nombre_tipo as nombre_tipo_motor, tm.porcentaje_impuesto " +
                     "FROM automoviles a " +
                     "INNER JOIN marcas m ON a.id_marca = m.id_marca " +
                     "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "ORDER BY m.nombre_marca, l.nombre_linea, a.anio";
        List<AutomovilDTO> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(cargarDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al leer todos los automóviles");
            e.printStackTrace();
        }
        return lista;
    }

    // ========== READ Disponibles ==========
    public List<AutomovilDTO> leerDisponibles() {
        String sql = "SELECT a.*, m.nombre_marca, l.nombre_linea, tm.nombre_tipo as nombre_tipo_motor, tm.porcentaje_impuesto " +
                     "FROM automoviles a " +
                     "INNER JOIN marcas m ON a.id_marca = m.id_marca " +
                     "INNER JOIN lineas_vehiculos l ON a.id_linea = l.id_linea " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.estado = 'Disponible' " +
                     "ORDER BY m.nombre_marca, l.nombre_linea, a.anio";
        List<AutomovilDTO> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(cargarDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al leer automóviles disponibles");
            e.printStackTrace();
        }
        return lista;
    }

    // ========== METODO PRIVADO: Cargar desde ResultSet ==========
    private AutomovilDTO cargarDesdeResultSet(ResultSet rs) throws SQLException {
        AutomovilDTO auto = new AutomovilDTO();
        auto.setIdAuto(rs.getInt("id_auto"));
        auto.setCodigo(rs.getString("codigo"));
        auto.setIdMarca(rs.getInt("id_marca"));
        auto.setIdLinea(rs.getInt("id_linea"));
        auto.setAnio(rs.getInt("anio"));
        auto.setColor(rs.getString("color"));
        auto.setIdTipoMotor(rs.getInt("id_tipo_motor"));
        auto.setPrecioBase(rs.getDouble("precio_base"));
        auto.setEstado(rs.getString("estado"));
        auto.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        // JOIN info
        auto.setNombreMarca(rs.getString("nombre_marca"));
        auto.setNombreLinea(rs.getString("nombre_linea"));
        auto.setNombreTipoMotor(rs.getString("nombre_tipo_motor"));
        auto.setPorcentajeImpuesto(rs.getDouble("porcentaje_impuesto"));
        // Estos campos asume triggers o la BD los calcula
        try { auto.setImpuestoVenta(rs.getDouble("impuesto_venta")); } catch(Exception ignored) {}
        try { auto.setIva(rs.getDouble("iva")); } catch(Exception ignored) {}
        try { auto.setPrecioTotal(rs.getDouble("precio_total")); } catch(Exception ignored) {}
        return auto;
    }
}
