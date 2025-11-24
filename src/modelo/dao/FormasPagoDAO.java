package modelo.dao;

import config.ConexionDB;
import modelo.dto.FormasPagoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormasPagoDAO {
    
    Connection acceso;
    PreparedStatement ps;
    ResultSet rs;

    // --- C: REGISTRAR ---
    public boolean registrar(FormasPagoDTO fp) {
        String sql = "INSERT INTO formas_pago (nombre) VALUES (?)";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, fp.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrar FP: " + e.toString());
            return false;
        }
    }

    // --- R: LISTAR ---
    public List<FormasPagoDTO> listar() {
        List<FormasPagoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM formas_pago ORDER BY id_forma_pago ASC";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FormasPagoDTO fp = new FormasPagoDTO();
                fp.setId(rs.getInt("id_forma_pago"));
                fp.setNombre(rs.getString("nombre"));
                lista.add(fp);
            }
        } catch (SQLException e) {
            System.err.println("Error listar FP: " + e.toString());
        }
        return lista;
    }

    // --- U: ACTUALIZAR ---
    public boolean actualizar(FormasPagoDTO fp) {
        String sql = "UPDATE formas_pago SET nombre=? WHERE id_forma_pago=?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, fp.getNombre());
            ps.setInt(2, fp.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error actualizar FP: " + e.toString());
            return false;
        }
    }

    // --- D: ELIMINAR ---
    public boolean eliminar(int id) {
        String sql = "DELETE FROM formas_pago WHERE id_forma_pago=?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error eliminar FP: " + e.toString());
            return false;
        }
    }
}