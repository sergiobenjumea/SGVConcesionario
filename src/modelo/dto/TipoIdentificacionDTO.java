package modelo.dto;

import java.io.Serializable;

public class TipoIdentificacionDTO implements Serializable {

    private int idTipoIdentificacion;
    private String codigoTipo;
    private String nombreTipo;
    private String descripcion;
    private int activo;

    // Getters y setters
    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(String codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    // Método para mostrar el nombre en el JComboBox
    @Override
    public String toString() {
        return nombreTipo; // El texto mostrado en el JComboBox
    }
}
