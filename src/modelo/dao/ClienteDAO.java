package modelo.dao;

import config.ConexionDB;
import modelo.dto.ClienteDTO;
import modelo.util.ItemCombo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClienteDAO {
    
    Connection acceso;
    PreparedStatement ps;
    ResultSet rs;

    // --- C: REGISTRAR ---
    public boolean registrar(ClienteDTO cliente) {
        String sql = "INSERT INTO clientes (id_tipo_identificacion, identificacion, nombre, fecha_nacimiento, edad, email) VALUES (?,?,?,?,?,?)";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, cliente.getIdTipoIdentificacion());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getNombre());
            ps.setDate(4, cliente.getFechaNacimiento());
            ps.setInt(5, cliente.getEdad());
            ps.setString(6, cliente.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrar: " + e.toString());
            return false;
        }
    }

    // --- R: LISTAR ---
    public List<ClienteDTO> listar() {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT c.*, t.codigo AS codigo_id FROM clientes c " +
                     "INNER JOIN tipos_identificacion t ON c.id_tipo_identificacion = t.id_tipo_identificacion " +
                     "ORDER BY c.id_cliente DESC";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setId(rs.getInt("id_cliente"));
                c.setIdTipoIdentificacion(rs.getInt("id_tipo_identificacion")); // Importante para editar
                c.setNombreTipoId(rs.getString("codigo_id"));
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombre(rs.getString("nombre"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setEdad(rs.getInt("edad"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error listar: " + e.toString());
        }
        return lista;
    }

    // --- U: ACTUALIZAR ---
    public boolean actualizar(ClienteDTO cliente) {
        String sql = "UPDATE clientes SET id_tipo_identificacion=?, identificacion=?, nombre=?, fecha_nacimiento=?, edad=?, email=? WHERE id_cliente=?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, cliente.getIdTipoIdentificacion());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getNombre());
            ps.setDate(4, cliente.getFechaNacimiento());
            ps.setInt(5, cliente.getEdad());
            ps.setString(6, cliente.getEmail());
            ps.setInt(7, cliente.getId()); // El ID es fundamental para el WHERE
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error actualizar: " + e.toString());
            return false;
        }
    }

    // --- D: ELIMINAR ---
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente=?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error eliminar: " + e.toString());
            return false;
        }
    }

    // --- AUXILIAR: COMBO ---
    public Vector<ItemCombo> obtenerTiposID() {
        Vector<ItemCombo> items = new Vector<>();
        String sql = "SELECT id_tipo_identificacion, nombre, codigo FROM tipos_identificacion WHERE activo = TRUE";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            items.add(new ItemCombo(0, "Seleccione Tipo"));
            while (rs.next()) {
                String texto = rs.getString("codigo") + " - " + rs.getString("nombre");
                items.add(new ItemCombo(rs.getInt("id_tipo_identificacion"), texto));
            }
        } catch (SQLException e) {
            System.err.println("Error tipos ID: " + e.toString());
        }
        return items;
    }
        public ClienteDTO buscarPorIdentificacion(String identificacion) {
        ClienteDTO c = null;
        String sql = "SELECT * FROM clientes WHERE identificacion = ?";
        try {
            acceso = ConexionDB.getConexion();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, identificacion);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new ClienteDTO();
                c.setId(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setEdad(rs.getInt("edad"));
                c.setEmail(rs.getString("email"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return c;
    }
}