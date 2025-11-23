package modelo.dao;

import config.ConexionDB;
import modelo.dto.FormaPagoDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagoDAO {
    
    public List<FormaPagoDTO> leerTodos() {
        String sql = "SELECT * FROM formas_pago ORDER BY codigo_forma";
        List<FormaPagoDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                FormaPagoDTO forma = new FormaPagoDTO();
                forma.setCodigo(rs.getString("codigo_forma"));
                forma.setDescripcion(rs.getString("nombre_forma"));
                lista.add(forma);
            }
            
            System.out.println("✅ Se encontraron " + lista.size() + " formas de pago");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer formas de pago");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public FormaPagoDTO leerPorCodigo(String codigo) {
        String sql = "SELECT * FROM formas_pago WHERE codigo_forma = ?";
        FormaPagoDTO forma = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                forma = new FormaPagoDTO();
                forma.setCodigo(rs.getString("codigo_forma"));
                forma.setDescripcion(rs.getString("nombre_forma"));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer forma de pago");
            e.printStackTrace();
        }
        
        return forma;
    }
}
