package modelo.dao;

import config.ConexionDB;
import modelo.dto.VendedorDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla vendedores
 * CRUD completo según patrón del profesor
 */
public class VendedorDAO {
    
    // ========== CREATE (Insertar) ==========
    public int crear(VendedorDTO vendedor) {
        String sql = "INSERT INTO vendedores (identificacion, nombre, profesion, " +
                     "fecha_contratacion, estado) VALUES (?, ?, ?, ?, ?)";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, vendedor.getIdentificacion());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getProfesion());
            ps.setDate(4, vendedor.getFechaContratacion() != null ? 
                       new java.sql.Date(vendedor.getFechaContratacion().getTime()) : 
                       new java.sql.Date(System.currentTimeMillis()));
            ps.setString(5, vendedor.getEstado() != null ? vendedor.getEstado() : "Activo");
            
            int resultado = ps.executeUpdate();
            
            System.out.println("✅ Vendedor creado: " + vendedor.getNombre());
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al crear vendedor");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== READ (Leer por ID) ==========
    public VendedorDTO leer(int idVendedor) {
        String sql = "SELECT * FROM vendedores WHERE id_vendedor = ?";
        VendedorDTO vendedor = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                vendedor = new VendedorDTO();
                vendedor.setIdVendedor(rs.getInt("id_vendedor"));
                vendedor.setIdentificacion(rs.getString("identificacion"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setProfesion(rs.getString("profesion"));
                vendedor.setFechaContratacion(rs.getDate("fecha_contratacion"));
                vendedor.setEstado(rs.getString("estado"));
                
                System.out.println("✅ Vendedor encontrado: " + vendedor.getNombre());
            } else {
                System.out.println("⚠️ Vendedor no encontrado con ID: " + idVendedor);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer vendedor");
            e.printStackTrace();
        }
        
        return vendedor;
    }
    
    // ========== READ (Leer por Identificación) ==========
    public VendedorDTO leerPorIdentificacion(String identificacion) {
        String sql = "SELECT * FROM vendedores WHERE identificacion = ?";
        VendedorDTO vendedor = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, identificacion);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                vendedor = new VendedorDTO();
                vendedor.setIdVendedor(rs.getInt("id_vendedor"));
                vendedor.setIdentificacion(rs.getString("identificacion"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setProfesion(rs.getString("profesion"));
                vendedor.setFechaContratacion(rs.getDate("fecha_contratacion"));
                vendedor.setEstado(rs.getString("estado"));
                
                System.out.println("✅ Vendedor encontrado: " + vendedor.getNombre());
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer vendedor por identificación");
            e.printStackTrace();
        }
        
        return vendedor;
    }
    
    // ========== UPDATE (Actualizar) ==========
    public int actualizar(VendedorDTO vendedor) {
        String sql = "UPDATE vendedores SET identificacion = ?, nombre = ?, " +
                     "profesion = ?, fecha_contratacion = ?, estado = ? " +
                     "WHERE id_vendedor = ?";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, vendedor.getIdentificacion());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getProfesion());
            ps.setDate(4, new java.sql.Date(vendedor.getFechaContratacion().getTime()));
            ps.setString(5, vendedor.getEstado());
            ps.setInt(6, vendedor.getIdVendedor());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Vendedor actualizado: " + vendedor.getNombre());
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar vendedor");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== DELETE (Eliminar) ==========
    public int eliminar(int idVendedor) {
        String sql = "DELETE FROM vendedores WHERE id_vendedor = ?";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Vendedor eliminado con ID: " + idVendedor);
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar vendedor");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== READ ALL (Leer todos) ==========
    public List<VendedorDTO> leerTodos() {
        String sql = "SELECT * FROM vendedores ORDER BY nombre";
        List<VendedorDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
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
            
            System.out.println("✅ Se encontraron " + lista.size() + " vendedores");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer todos los vendedores");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    // ========== MÉTODO EXTRA: Leer solo vendedores activos ==========
    public List<VendedorDTO> leerActivos() {
        String sql = "SELECT * FROM vendedores WHERE estado = 'Activo' ORDER BY nombre";
        List<VendedorDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
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
            
            System.out.println("✅ Se encontraron " + lista.size() + " vendedores activos");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer vendedores activos");
            e.printStackTrace();
        }
        
        return lista;
    }
}
