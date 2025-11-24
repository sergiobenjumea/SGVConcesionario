package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.MarcaDTO;
import config.ConexionDB;

public class MarcaDAO {
    public List<MarcaDTO> listarMarcas() {
        List<MarcaDTO> lista = new ArrayList<>();
        String sql = "SELECT id_marca, nombre_marca, activo FROM marcas WHERE activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MarcaDTO m = new MarcaDTO();
                m.setIdMarca(rs.getInt("id_marca"));
                m.setNombreMarca(rs.getString("nombre_marca"));
                m.setActivo(rs.getInt("activo"));
                lista.add(m);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
    // Métodos agregar/actualizar/eliminar según tus RF...
}
