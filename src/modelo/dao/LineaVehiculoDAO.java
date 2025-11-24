package modelo.dao;

import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.LineaVehiculoDTO;

public class LineaVehiculoDAO {
    public List<LineaVehiculoDTO> listarLineasPorMarca(int idMarca) {
        List<LineaVehiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT id_linea, id_marca, nombre_linea, activo FROM lineas_vehiculos WHERE id_marca = ? AND activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMarca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LineaVehiculoDTO l = new LineaVehiculoDTO();
                l.setIdLinea(rs.getInt("id_linea"));
                l.setIdMarca(rs.getInt("id_marca"));
                l.setNombreLinea(rs.getString("nombre_linea"));
                l.setActivo(rs.getInt("activo"));
                lista.add(l);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
    // Métodos CRUD si los necesitas para líneas...
}
