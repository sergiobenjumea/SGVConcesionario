package modelo.dao;

import config.ConexionDB;
import modelo.dto.TipoIdentificacionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoIdentificacionDAO {
    
    public List<TipoIdentificacionDTO> leerActivos() {
        String sql = "SELECT * FROM tipos_identificacion WHERE activo = TRUE ORDER BY nombre";
        List<TipoIdentificacionDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                TipoIdentificacionDTO tipo = new TipoIdentificacionDTO();
                tipo.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                tipo.setCodigo(rs.getString("codigo"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipo.setActivo(rs.getBoolean("activo"));
                
                lista.add(tipo);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al leer tipos de identificación");
            e.printStackTrace();
        }
        
        return lista;
    }
}
