package modelo.dao;

import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.VendedorDTO;

public class VendedorDAO {
    public List<VendedorDTO> listarVendedores() {
        List<VendedorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VendedorDTO v = new VendedorDTO();
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setIdentificacion(rs.getString("identificacion"));
                v.setNombre(rs.getString("nombre"));
                v.setProfesion(rs.getString("profesion"));
                v.setFechaContratacion(rs.getDate("fecha_contratacion"));
                v.setEstado(rs.getString("estado"));
                lista.add(v);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
    public VendedorDTO buscarPorId(int idVendedor) {
    VendedorDTO vendedor = null;
    String sql = "SELECT * FROM vendedores WHERE id_vendedor = ?";
    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idVendedor);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            vendedor = new VendedorDTO();
            vendedor.setIdVendedor(rs.getInt("id_vendedor"));
            vendedor.setNombre(rs.getString("nombre"));
            // ... agrega aquí otros campos según tu DTO
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return vendedor;
}

    public VendedorDTO buscarPorNombre(String nombre) {
        VendedorDTO vendedor = null;
        String sql = "SELECT * FROM vendedores WHERE nombre = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vendedor = new VendedorDTO();
                vendedor.setIdVendedor(rs.getInt("id_vendedor"));
                vendedor.setNombre(rs.getString("nombre"));
                // ... agrega aquí otros campos según tu DTO
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vendedor;
    }
    public VendedorDTO leerPorIdentificacion(String identificacion) {
        VendedorDTO vendedor = null;
        String sql = "SELECT * FROM vendedores WHERE identificacion = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, identificacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vendedor = new VendedorDTO();
                    vendedor.setIdVendedor(rs.getInt("id_vendedor"));
                    vendedor.setIdentificacion(rs.getString("identificacion"));
                    vendedor.setNombre(rs.getString("nombre"));
                    vendedor.setProfesion(rs.getString("profesion"));
                    vendedor.setFechaContratacion(rs.getDate("fecha_contratacion"));
                    vendedor.setEstado(rs.getString("estado"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vendedor;
    }    
    public int crear(VendedorDTO vendedor) {
        int resultado = 0;
        String sql = "INSERT INTO vendedores (identificacion, nombre, profesion, fecha_contratacion, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vendedor.getIdentificacion());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getProfesion());
            ps.setDate(4, new java.sql.Date(vendedor.getFechaContratacion().getTime()));
            ps.setString(5, vendedor.getEstado());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public int actualizar(VendedorDTO vendedor) {
        int resultado = 0;
        String sql = "UPDATE vendedores SET identificacion = ?, nombre = ?, profesion = ?, fecha_contratacion = ?, estado = ? WHERE id_vendedor = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vendedor.getIdentificacion());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getProfesion());
            ps.setDate(4, new java.sql.Date(vendedor.getFechaContratacion().getTime()));
            ps.setString(5, vendedor.getEstado());
            ps.setInt(6, vendedor.getIdVendedor());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public int eliminar(int idVendedor) {
        int resultado = 0;
        String sql = "DELETE FROM vendedores WHERE id_vendedor = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVendedor);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public List<VendedorDTO> leerTodos() {
        List<VendedorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VendedorDTO vendedor = new VendedorDTO();
                vendedor.setIdVendedor(rs.getInt("id_vendedor"));
                vendedor.setIdentificacion(rs.getString("identificacion"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setProfesion(rs.getString("profesion"));
                vendedor.setFechaContratacion(rs.getDate("fecha_contratacion"));
                vendedor.setEstado(rs.getString("estado"));
                lista.add(vendedor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
