package modelo.dto;

import java.io.Serializable;

public class FormaPagoDTO implements Serializable {
    
    private String codigo;
    private String descripcion;
    
    public FormaPagoDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return codigo + " - " + descripcion;
    }
}
