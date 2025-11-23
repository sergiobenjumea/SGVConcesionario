package modelo.dao;

import config.ConexionDB;
import modelo.dto.ClienteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla clientes
 * Implementa CRUD completo según el patrón del profesor
 */
public class ClienteDAO {
    
    // ========== CREATE (Insertar) ==========
    public int crear(ClienteDTO cliente) {
        String sql = "INSERT INTO clientes (tipo_identificacion, identificacion, nombre, "
                   + "fecha_nacimiento, edad, email) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            // Llenar los parámetros
            ps.setString(1, cliente.getTipoIdentificacion());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getNombre());
            ps.setDate(4, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setInt(5, cliente.getEdad());
            ps.setString(6, cliente.getEmail());
            
            // Ejecutar
            int resultado = ps.executeUpdate();
            
            System.out.println("✅ Cliente creado: " + cliente.getNombre());
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al crear cliente");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== READ (Leer por ID) ==========
    public ClienteDTO leer(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        ClienteDTO cliente = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente = new ClienteDTO();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                System.out.println("✅ Cliente encontrado: " + cliente.getNombre());
            } else {
                System.out.println("⚠️ Cliente no encontrado con ID: " + idCliente);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer cliente");
            e.printStackTrace();
        }
        
        return cliente;
    }
    
    // ========== READ (Leer por Identificación) ==========
    public ClienteDTO leerPorIdentificacion(String identificacion) {
        String sql = "SELECT * FROM clientes WHERE identificacion = ?";
        ClienteDTO cliente = null;
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, identificacion);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente = new ClienteDTO();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                System.out.println("✅ Cliente encontrado: " + cliente.getNombre());
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer cliente por identificación");
            e.printStackTrace();
        }
        
        return cliente;
    }
    
    // ========== UPDATE (Actualizar) ==========
    public int actualizar(ClienteDTO cliente) {
        String sql = "UPDATE clientes SET tipo_identificacion = ?, identificacion = ?, "
                   + "nombre = ?, fecha_nacimiento = ?, edad = ?, email = ? "
                   + "WHERE id_cliente = ?";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, cliente.getTipoIdentificacion());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getNombre());
            ps.setDate(4, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setInt(5, cliente.getEdad());
            ps.setString(6, cliente.getEmail());
            ps.setInt(7, cliente.getIdCliente());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Cliente actualizado: " + cliente.getNombre());
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cliente");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== DELETE (Eliminar) ==========
    public int eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                System.out.println("✅ Cliente eliminado con ID: " + idCliente);
            }
            
            return resultado;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente");
            e.printStackTrace();
            return 0;
        }
    }
    
    // ========== READ ALL (Leer todos) ==========
    public List<ClienteDTO> leerTodos() {
        String sql = "SELECT * FROM clientes ORDER BY nombre";
        List<ClienteDTO> lista = new ArrayList<>();
        
        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                lista.add(cliente);
            }
            
            System.out.println("✅ Se encontraron " + lista.size() + " clientes");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al leer todos los clientes");
            e.printStackTrace();
        }
        
        return lista;
    }
}
