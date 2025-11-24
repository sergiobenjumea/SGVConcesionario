package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.ClienteDTO;
import config.ConexionDB;

public class ClienteDAO {
    
    // Crear nuevo cliente
    public int crear(ClienteDTO cliente) {
        String sql = "INSERT INTO clientes (id_tipo_identificacion, identificacion, nombre, fecha_nacimiento, edad, email) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdTipoIdentificacion());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getNombre());
            ps.setDate(4, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setInt(5, cliente.getEdad());
            ps.setString(6, cliente.getEmail());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Buscar cliente por identificación (documento)
    public ClienteDTO leerPorIdentificacion(String identificacion) {
        String sql = "SELECT c.*, t.codigo_tipo, t.nombre_tipo "
                   + "FROM clientes c "
                   + "INNER JOIN tipos_identificacion t ON c.id_tipo_identificacion = t.id_tipo_identificacion "
                   + "WHERE c.identificacion = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, identificacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                c.setCodigoTipoIdentificacion(rs.getString("codigo_tipo"));
                c.setNombreTipoIdentificacion(rs.getString("nombre_tipo"));
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombre(rs.getString("nombre"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setEdad(rs.getInt("edad"));
                c.setEmail(rs.getString("email"));
                c.setFechaRegistro(rs.getDate("fecha_registro"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Actualizar cliente existente
    public int actualizar(ClienteDTO cliente) {
        String sql = "UPDATE clientes SET id_tipo_identificacion = ?, identificacion = ?, nombre = ?, "
                   + "fecha_nacimiento = ?, edad = ?, email = ? WHERE id_cliente = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdTipoIdentificacion());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getNombre());
            ps.setDate(4, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setInt(5, cliente.getEdad());
            ps.setString(6, cliente.getEmail());
            ps.setInt(7, cliente.getIdCliente());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Eliminar cliente por ID
    public int eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Listar todos los clientes
    public List<ClienteDTO> leerTodos() {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT c.*, t.codigo_tipo, t.nombre_tipo "
                   + "FROM clientes c "
                   + "INNER JOIN tipos_identificacion t ON c.id_tipo_identificacion = t.id_tipo_identificacion";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                c.setCodigoTipoIdentificacion(rs.getString("codigo_tipo"));
                c.setNombreTipoIdentificacion(rs.getString("nombre_tipo"));
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombre(rs.getString("nombre"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setEdad(rs.getInt("edad"));
                c.setEmail(rs.getString("email"));
                c.setFechaRegistro(rs.getDate("fecha_registro"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Buscar cliente por ID (método auxiliar opcional)
    public ClienteDTO leerPorId(int idCliente) {
        String sql = "SELECT c.*, t.codigo_tipo, t.nombre_tipo "
                   + "FROM clientes c "
                   + "INNER JOIN tipos_identificacion t ON c.id_tipo_identificacion = t.id_tipo_identificacion "
                   + "WHERE c.id_cliente = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion"));
                c.setCodigoTipoIdentificacion(rs.getString("codigo_tipo"));
                c.setNombreTipoIdentificacion(rs.getString("nombre_tipo"));
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombre(rs.getString("nombre"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setEdad(rs.getInt("edad"));
                c.setEmail(rs.getString("email"));
                c.setFechaRegistro(rs.getDate("fecha_registro"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
