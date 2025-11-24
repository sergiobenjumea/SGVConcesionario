package modelo.dao;

import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.TipoIdentificacionDTO;

public class TipoIdentificacionDAO {
    
    // Trae todos los tipos de identificación activos (para ComboBox)
    public List<TipoIdentificacionDTO> leerActivos() {
        List<TipoIdentificacionDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipos_identificacion WHERE activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoIdentificacionDTO t = new TipoIdentificacionDTO();
                t.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                t.setCodigoTipo(rs.getString("codigo_tipo"));
                t.setNombreTipo(rs.getString("nombre_tipo"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setActivo(rs.getInt("activo"));
                lista.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Busca por id (útil para consultas directas y validaciones)
    public TipoIdentificacionDTO leerPorId(int idTipo) {
        TipoIdentificacionDTO t = null;
        String sql = "SELECT * FROM tipos_identificacion WHERE id_tipo_identificacion = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTipo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new TipoIdentificacionDTO();
                t.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                t.setCodigoTipo(rs.getString("codigo_tipo"));
                t.setNombreTipo(rs.getString("nombre_tipo"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setActivo(rs.getInt("activo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    
    // Busca por código (ejemplo: "CC", "TI", etc.)
    public TipoIdentificacionDTO leerPorCodigo(String codigo) {
        TipoIdentificacionDTO t = null;
        String sql = "SELECT * FROM tipos_identificacion WHERE codigo_tipo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new TipoIdentificacionDTO();
                t.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                t.setCodigoTipo(rs.getString("codigo_tipo"));
                t.setNombreTipo(rs.getString("nombre_tipo"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setActivo(rs.getInt("activo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    // Opcional: Trae todos (activos e inactivos)
    public List<TipoIdentificacionDTO> leerTodos() {
        List<TipoIdentificacionDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipos_identificacion";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoIdentificacionDTO t = new TipoIdentificacionDTO();
                t.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                t.setCodigoTipo(rs.getString("codigo_tipo"));
                t.setNombreTipo(rs.getString("nombre_tipo"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setActivo(rs.getInt("activo"));
                lista.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
