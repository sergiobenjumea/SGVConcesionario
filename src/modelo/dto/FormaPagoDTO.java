package modelo.dto;

import java.io.Serializable;

public class FormaPagoDTO implements Serializable {
    private int idFormaPago;
    private String codigoForma;
    private String nombreForma;
    private String descripcion;
    private int activo;

    public FormaPagoDTO() {}

    public int getIdFormaPago() { return idFormaPago; }
    public void setIdFormaPago(int idFormaPago) { this.idFormaPago = idFormaPago; }

    public String getCodigoForma() { return codigoForma; }
    public void setCodigoForma(String codigoForma) { this.codigoForma = codigoForma; }

    public String getNombreForma() { return nombreForma; }
    public void setNombreForma(String nombreForma) { this.nombreForma = nombreForma; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
    
    @Override
    public String toString() {
        return this.getDescripcion();
    }
}
