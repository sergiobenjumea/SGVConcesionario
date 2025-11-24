package modelo.dao;

import config.ConexionDB;      // <--- CAMBIO IMPORTANTE: Usamos tu clase ConexionDB
import modelo.dto.AutomovilDTO;
import modelo.util.ItemCombo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

public class AutomovilDAO {
    
    // Ya no necesitamos instanciar "Conexion con = new Conexion()"
    Connection acceso;
    PreparedStatement ps;
    ResultSet rs;

    // --- MÉTODO REGISTRAR ---
    public boolean registrar(AutomovilDTO auto) {
        String sql = "INSERT INTO automoviles (id_linea, modelo_anio, color, id_tipo_motor, precio_base) VALUES (?,?,?,?,?)";
        
        try {
            // LLAMADA CORREGIDA: Usamos el método estático de tu clase
            acceso = ConexionDB.getConexion(); 
            
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, auto.getIdLinea());
            ps.setInt(2, auto.getAnio());
            ps.setString(3, auto.getColor());
            ps.setInt(4, auto.getIdTipoMotor());
            ps.setDouble(5, auto.getPrecioBase());
            
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error en DAO Registrar: " + e.toString());
            return false;
        }
        // No cerramos la conexión aquí para mantenerla viva para otras consultas
    }

    // --- OBTENER MARCAS ---
    public Vector<ItemCombo> obtenerMarcas() {
        Vector<ItemCombo> items = new Vector<>();
        String sql = "SELECT id_marca, nombre FROM marcas";
        
        try {
            acceso = ConexionDB.getConexion(); // LLAMADA CORREGIDA
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            
            items.add(new ItemCombo(0, "Seleccione Marca"));
            
            while (rs.next()) {
                items.add(new ItemCombo(rs.getInt("id_marca"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error cargando marcas: " + e.toString());
        }
        return items;
    }

    // --- OBTENER LINEAS POR MARCA ---
    public Vector<ItemCombo> obtenerLineasPorMarca(int idMarca) {
        Vector<ItemCombo> items = new Vector<>();
        String sql = "SELECT id_linea, nombre FROM lineas WHERE id_marca = ?";
        
        try {
            acceso = ConexionDB.getConexion(); // LLAMADA CORREGIDA
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idMarca);
            rs = ps.executeQuery();
            
            items.add(new ItemCombo(0, "Seleccione Línea"));
            
            while (rs.next()) {
                items.add(new ItemCombo(rs.getInt("id_linea"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error cargando líneas: " + e.toString());
        }
        return items;
    }
    
    // --- OBTENER TIPOS DE MOTOR ---
    public Vector<ItemCombo> obtenerTiposMotor() {
        Vector<ItemCombo> items = new Vector<>();
        String sql = "SELECT id_tipo_motor, nombre FROM tipos_motor";
        
        try {
            acceso = ConexionDB.getConexion(); // LLAMADA CORREGIDA
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            
            items.add(new ItemCombo(0, "Seleccione Motor"));
            
            while (rs.next()) {
                items.add(new ItemCombo(rs.getInt("id_tipo_motor"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error cargando motores: " + e.toString());
        }
        return items;
    }
    // --- MÉTODO PARA LISTAR TODOS (CON NOMBRES) ---
    public List<AutomovilDTO> listar() {
        List<AutomovilDTO> lista = new ArrayList<>();
        // Unimos las tablas para obtener los nombres reales
        String sql = "SELECT a.id_auto, m.nombre AS marca, l.nombre AS linea, a.modelo_anio, a.color, a.precio_base, tm.nombre AS motor " +
                     "FROM automoviles a " +
                     "INNER JOIN lineas l ON a.id_linea = l.id_linea " +
                     "INNER JOIN marcas m ON l.id_marca = m.id_marca " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "ORDER BY a.id_auto DESC";
        
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                AutomovilDTO auto = new AutomovilDTO();
                // Guardamos el ID por si necesitamos editar/eliminar luego
                auto.setId(rs.getInt("id_auto"));
                
                // Guardamos los datos visuales en los campos auxiliares
                auto.setNombreMarca(rs.getString("marca"));
                auto.setNombreLinea(rs.getString("linea"));
                auto.setAnio(rs.getInt("modelo_anio"));
                auto.setColor(rs.getString("color"));
                auto.setPrecioBase(rs.getDouble("precio_base"));
                auto.setNombreMotor(rs.getString("motor"));
                
                lista.add(auto);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.toString());
        }
        return lista;
    }
    // Buscar auto por ID para traer precio y datos
    public AutomovilDTO buscarPorId(int id) {
        AutomovilDTO a = null;
        String sql = "SELECT a.*, l.nombre as linea, m.nombre as marca, tm.nombre as motor " +
                     "FROM automoviles a " +
                     "INNER JOIN lineas l ON a.id_linea = l.id_linea " +
                     "INNER JOIN marcas m ON l.id_marca = m.id_marca " +
                     "INNER JOIN tipos_motor tm ON a.id_tipo_motor = tm.id_tipo_motor " +
                     "WHERE a.id_auto = ?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = new AutomovilDTO();
                a.setId(rs.getInt("id_auto"));
                a.setNombreMarca(rs.getString("marca"));
                a.setNombreLinea(rs.getString("linea"));
                a.setAnio(rs.getInt("modelo_anio"));
                a.setColor(rs.getString("color"));
                a.setPrecioBase(rs.getDouble("precio_base"));
                a.setNombreMotor(rs.getString("motor"));
                a.setEstado(rs.getString("estado"));
                // Importante: Guardar el estado para saber si ya se vendió
                // (Necesitarás agregar private String estado; en tu DTO si quieres validarlo estricto)
            }
        } catch (Exception e) { e.printStackTrace(); }
        return a;
    }
}