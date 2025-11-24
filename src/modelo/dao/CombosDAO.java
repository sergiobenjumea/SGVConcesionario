package modelo.dao;

import config.ConexionDB;
import modelo.dto.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO utilitario para llenar los ComboBoxes
public class CombosDAO {

    public List<MarcaDTO> leerMarcas() {
        List<MarcaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM marcas WHERE activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MarcaDTO m = new MarcaDTO();
                m.setIdMarca(rs.getInt("id_marca"));
                m.setNombre(rs.getString("nombre"));
                lista.add(m);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public List<LineaVehiculoDTO> leerLineasPorMarca(int idMarca) {
        List<LineaVehiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM lineas_vehiculos WHERE id_marca = ? AND activo = 1";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMarca);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LineaVehiculoDTO l = new LineaVehiculoDTO();
                    l.setIdLinea(rs.getInt("id_linea"));
                    l.setNombre(rs.getString("nombre"));
                    lista.add(l);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public List<TipoMotorDTO> leerMotores() {
        List<TipoMotorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipos_motor";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoMotorDTO t = new TipoMotorDTO();
                t.setIdTipoMotor(rs.getInt("id_tipo_motor"));
                t.setNombre(rs.getString("nombre"));
                t.setPorcentajeImpuesto(rs.getBigDecimal("porcentaje_impuesto"));
                lista.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
    
    public List<FormaPagoDTO> leerFormasPago() {
        List<FormaPagoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM formas_pago";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FormaPagoDTO f = new FormaPagoDTO();
                f.setIdFormaPago(rs.getInt("id_forma_pago"));
                f.setNombre(rs.getString("nombre"));
                lista.add(f);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}