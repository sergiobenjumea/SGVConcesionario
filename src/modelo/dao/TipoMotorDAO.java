package modelo.dao;

import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.TipoMotorDTO;

public class TipoMotorDAO {
    public List<TipoMotorDTO> listarTiposMotor() {
        List<TipoMotorDTO> lista = new ArrayList<>();
        String sql = "SELECT id_tipo_motor, nombre_tipo, porcentaje_impuesto, activo FROM tipos_motor WHERE activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoMotorDTO t = new TipoMotorDTO();
                t.setIdTipoMotor(rs.getInt("id_tipo_motor"));
                t.setNombreTipo(rs.getString("nombre_tipo"));
                t.setPorcentajeImpuesto(rs.getDouble("porcentaje_impuesto"));
                t.setActivo(rs.getInt("activo"));
                lista.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}
