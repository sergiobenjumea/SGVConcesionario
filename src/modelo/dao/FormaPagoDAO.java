package modelo.dao;

import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.FormaPagoDTO;

public class FormaPagoDAO {

    // Lista solo formas de pago activas
    public List<FormaPagoDTO> listarFormasPago() {
        List<FormaPagoDTO> lista = new ArrayList<>();
        String sql = "SELECT id_forma_pago, codigo_forma, nombre_forma, descripcion, activo FROM formas_pago WHERE activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FormaPagoDTO f = new FormaPagoDTO();
                f.setIdFormaPago(rs.getInt("id_forma_pago"));
                f.setCodigoForma(rs.getString("codigo_forma"));
                f.setNombreForma(rs.getString("nombre_forma"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setActivo(rs.getInt("activo"));
                lista.add(f);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // Guarda nueva forma de pago
    public int guardar(FormaPagoDTO forma) {
        int resultado = 0;
        String sql = "INSERT INTO formas_pago (codigo_forma, nombre_forma, descripcion, activo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, forma.getCodigoForma());
            ps.setString(2, forma.getNombreForma());
            ps.setString(3, forma.getDescripcion());
            ps.setInt(4, forma.getActivo());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Consulta una forma de pago por código
    public FormaPagoDTO buscarPorCodigo(String codigo) {
        FormaPagoDTO forma = null;
        String sql = "SELECT id_forma_pago, codigo_forma, nombre_forma, descripcion, activo FROM formas_pago WHERE codigo_forma = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    forma = new FormaPagoDTO();
                    forma.setIdFormaPago(rs.getInt("id_forma_pago"));
                    forma.setCodigoForma(rs.getString("codigo_forma"));
                    forma.setNombreForma(rs.getString("nombre_forma"));
                    forma.setDescripcion(rs.getString("descripcion"));
                    forma.setActivo(rs.getInt("activo"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forma;
    }

    // Actualiza una forma de pago existente
    public int actualizar(FormaPagoDTO forma) {
        int resultado = 0;
        String sql = "UPDATE formas_pago SET codigo_forma = ?, nombre_forma = ?, descripcion = ?, activo = ? WHERE id_forma_pago = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, forma.getCodigoForma());
            ps.setString(2, forma.getNombreForma());
            ps.setString(3, forma.getDescripcion());
            ps.setInt(4, forma.getActivo());
            ps.setInt(5, forma.getIdFormaPago());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Elimina una forma de pago por ID
    public int eliminar(int idFormaPago) {
        int resultado = 0;
        String sql = "DELETE FROM formas_pago WHERE id_forma_pago = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFormaPago);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Trae todas las formas de pago (activas e inactivas)
    public List<FormaPagoDTO> leerTodos() {
        List<FormaPagoDTO> lista = new ArrayList<>();
        String sql = "SELECT id_forma_pago, codigo_forma, nombre_forma, descripcion, activo FROM formas_pago";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FormaPagoDTO f = new FormaPagoDTO();
                f.setIdFormaPago(rs.getInt("id_forma_pago"));
                f.setCodigoForma(rs.getString("codigo_forma"));
                f.setNombreForma(rs.getString("nombre_forma"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setActivo(rs.getInt("activo"));
                lista.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
