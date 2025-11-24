package modelo.dao;

import config.ConexionDB;
import modelo.dto.VendedorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    
    Connection acceso;
    PreparedStatement ps;
    ResultSet rs;

    // --- REGISTRAR ---
    public boolean registrar(VendedorDTO vend) {
        String sql = "INSERT INTO vendedores (identificacion, nombre, profesion, fecha_contratacion) VALUES (?,?,?,?)";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, vend.getIdentificacion());
            ps.setString(2, vend.getNombre());
            ps.setString(3, vend.getProfesion());
            ps.setDate(4, vend.getFechaContratacion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrar vendedor: " + e.toString());
            return false;
        }
    }

    // --- LISTAR ---
    public List<VendedorDTO> listar() {
        List<VendedorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedores ORDER BY id_vendedor DESC";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendedorDTO v = new VendedorDTO();
                v.setId(rs.getInt("id_vendedor"));
                v.setIdentificacion(rs.getString("identificacion"));
                v.setNombre(rs.getString("nombre"));
                v.setProfesion(rs.getString("profesion"));
                v.setFechaContratacion(rs.getDate("fecha_contratacion"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error listar vendedores: " + e.toString());
        }
        return lista;
    }

    // --- ACTUALIZAR ---
    public boolean actualizar(VendedorDTO vend) {
        String sql = "UPDATE vendedores SET identificacion=?, nombre=?, profesion=? WHERE id_vendedor=?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, vend.getIdentificacion());
            ps.setString(2, vend.getNombre());
            ps.setString(3, vend.getProfesion());
            ps.setInt(4, vend.getId()); // WHERE id_vendedor
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error actualizar vendedor: " + e.toString());
            return false;
        }
    }

    // --- ELIMINAR ---
    public boolean eliminar(int id) {
        String sql = "DELETE FROM vendedores WHERE id_vendedor=?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error eliminar vendedor: " + e.toString());
            return false;
        }
    }
    
    // --- BUSCAR POR IDENTIFICACIÓN (Para el botón Consultar) ---
    public VendedorDTO buscarPorIdentificacion(String identificacion) {
        VendedorDTO v = null;
        String sql = "SELECT * FROM vendedores WHERE identificacion = ?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, identificacion);
            rs = ps.executeQuery();
            if (rs.next()) {
                v = new VendedorDTO();
                v.setId(rs.getInt("id_vendedor"));
                v.setIdentificacion(rs.getString("identificacion"));
                v.setNombre(rs.getString("nombre"));
                v.setProfesion(rs.getString("profesion"));
                v.setFechaContratacion(rs.getDate("fecha_contratacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error buscar vendedor: " + e.toString());
        }
        return v;
    }
    // ... (Métodos anteriores) ...

    // --- BUSCAR POR NOMBRE (FLEXIBLE) ---
    public VendedorDTO buscarPorNombre(String busqueda) {
        VendedorDTO v = null;
        // LIKE usa % para buscar coincidencias parciales
        String sql = "SELECT * FROM vendedores WHERE nombre LIKE ? LIMIT 1";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            // Agregamos los comodines % alrededor del texto para que busque en cualquier parte
            ps.setString(1, "%" + busqueda + "%"); 
            rs = ps.executeQuery();
            if (rs.next()) {
                v = new VendedorDTO();
                v.setId(rs.getInt("id_vendedor"));
                v.setIdentificacion(rs.getString("identificacion"));
                v.setNombre(rs.getString("nombre"));
                v.setProfesion(rs.getString("profesion"));
                v.setFechaContratacion(rs.getDate("fecha_contratacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error buscar por nombre: " + e.toString());
        }
        return v;
    }
}