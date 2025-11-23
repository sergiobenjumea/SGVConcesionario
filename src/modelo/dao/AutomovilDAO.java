package modelo.dao;

import config.ConexionDB;
import modelo.dto.AutomovilDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla automoviles
 * CRUD completo con cálculo automático de precios (trigger)
 */
public class AutomovilDAO {
    
    // ========== CREATE (Insertar) ==========
    // Nota: El trigger calculará automáticamente impuesto, IVA y precio_total
    public int crear(AutomovilDTO auto) {
        String sql = "INSERT INTO automoviles (codigo, marca, modelo, color, " +
                     "precio_base, id_tipo_motor) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, auto.getCodigo());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModelo());
            ps.setString(4, auto.getColor());
            ps.setDouble(5, auto.getPrecioBase());
            ps.setInt(6, auto.getIdTipoMotor());
            
            int resultado = ps.executeUpdate();
            
            System.out.println("✅ Automóvil creado: " + auto.getMarca() + " " + auto.getModelo());
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al crear automóvil");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== READ (Leer por ID) ==========
    public AutomovilDTO leer(int idAuto) {
        String sql = "SELECT a.*, tm.nombre_tipo as nombre_tipo_motor " +
                     "FROM automoviles a " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.id_auto = ?";
        AutomovilDTO auto = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAuto);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                auto = new AutomovilDTO();
                auto.setIdAuto(rs.getInt("id_auto"));
                auto.setCodigo(rs.getString("codigo"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getString("modelo"));
                auto.setColor(rs.getString("color"));
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setIdTipoMotor(rs.getInt("id_tipo_motor"));
                auto.setNombreTipoMotor(rs.getString("nombre_tipo_motor"));
                auto.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                auto.setIva(rs.getDouble("iva"));
                auto.setPrecioTotal(rs.getDouble("precio_total"));
                auto.setEstado(rs.getString("estado"));
                auto.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                
                System.out.println("✅ Automóvil encontrado: " + auto.getMarca() + " " + auto.getModelo());
            } else {
                System.out.println("⚠️ Automóvil no encontrado con ID: " + idAuto);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer automóvil");
            e.printStackTrace();
        }
        
        return auto;
    }
    
    // ========== READ (Leer por Código) ==========
    public AutomovilDTO leerPorCodigo(String codigo) {
        String sql = "SELECT a.*, tm.nombre_tipo as nombre_tipo_motor " +
                     "FROM automoviles a " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.codigo = ?";
        AutomovilDTO auto = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                auto = new AutomovilDTO();
                auto.setIdAuto(rs.getInt("id_auto"));
                auto.setCodigo(rs.getString("codigo"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getString("modelo"));
                auto.setColor(rs.getString("color"));
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setIdTipoMotor(rs.getInt("id_tipo_motor"));
                auto.setNombreTipoMotor(rs.getString("nombre_tipo_motor"));
                auto.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                auto.setIva(rs.getDouble("iva"));
                auto.setPrecioTotal(rs.getDouble("precio_total"));
                auto.setEstado(rs.getString("estado"));
                auto.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                
                System.out.println("✅ Automóvil encontrado: " + auto.getMarca() + " " + auto.getModelo());
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer automóvil por código");
            e.printStackTrace();
        }
        
        return auto;
    }
    
    // ========== UPDATE (Actualizar) ==========
    public int actualizar(AutomovilDTO auto) {
        String sql = "UPDATE automoviles SET codigo = ?, marca = ?, modelo = ?, " +
                     "color = ?, precio_base = ?, id_tipo_motor = ? " +
                     "WHERE id_auto = ?";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, auto.getCodigo());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModelo());
            ps.setString(4, auto.getColor());
            ps.setDouble(5, auto.getPrecioBase());
            ps.setInt(6, auto.getIdTipoMotor());
            ps.setInt(7, auto.getIdAuto());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Automóvil actualizado: " + auto.getMarca() + " " + auto.getModelo());
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar automóvil");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== DELETE (Eliminar) ==========
    // Solo se pueden eliminar autos que NO estén vendidos
    public int eliminar(int idAuto) {
        String sql = "DELETE FROM automoviles WHERE id_auto = ? AND estado = 'Disponible'";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAuto);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Automóvil eliminado con ID: " + idAuto);
            } else {
                System.out.println("⚠️ No se puede eliminar: auto vendido o no existe");
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar automóvil");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== READ ALL (Leer todos) ==========
    public List<AutomovilDTO> leerTodos() {
        String sql = "SELECT a.*, tm.nombre_tipo as nombre_tipo_motor " +
                     "FROM automoviles a " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "ORDER BY a.marca, a.modelo";
        List<AutomovilDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                AutomovilDTO auto = new AutomovilDTO();
                auto.setIdAuto(rs.getInt("id_auto"));
                auto.setCodigo(rs.getString("codigo"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getString("modelo"));
                auto.setColor(rs.getString("color"));
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setIdTipoMotor(rs.getInt("id_tipo_motor"));
                auto.setNombreTipoMotor(rs.getString("nombre_tipo_motor"));
                auto.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                auto.setIva(rs.getDouble("iva"));
                auto.setPrecioTotal(rs.getDouble("precio_total"));
                auto.setEstado(rs.getString("estado"));
                auto.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                
                lista.add(auto);
            }
            
            System.out.println("✅ Se encontraron " + lista.size() + " automóviles");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer todos los automóviles");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    // ========== MÉTODO EXTRA: Leer solo disponibles para venta ==========
    public List<AutomovilDTO> leerDisponibles() {
        String sql = "SELECT a.*, tm.nombre_tipo as nombre_tipo_motor " +
                     "FROM automoviles a " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.estado = 'Disponible' " +
                     "ORDER BY a.marca, a.modelo";
        List<AutomovilDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                AutomovilDTO auto = new AutomovilDTO();
                auto.setIdAuto(rs.getInt("id_auto"));
                auto.setCodigo(rs.getString("codigo"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getString("modelo"));
                auto.setColor(rs.getString("color"));
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setIdTipoMotor(rs.getInt("id_tipo_motor"));
                auto.setNombreTipoMotor(rs.getString("nombre_tipo_motor"));
                auto.setImpuestoVenta(rs.getDouble("impuesto_venta"));
                auto.setIva(rs.getDouble("iva"));
                auto.setPrecioTotal(rs.getDouble("precio_total"));
                auto.setEstado(rs.getString("estado"));
                auto.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                
                lista.add(auto);
            }
            
            System.out.println("✅ Se encontraron " + lista.size() + " automóviles disponibles");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer automóviles disponibles");
            e.printStackTrace();
        }
        
        return lista;
    }
}
