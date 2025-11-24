package modelo.dto;

import java.io.Serializable;

public class MarcaDTO implements Serializable {
    private int idMarca;
    private String nombreMarca;
    private int activo;

    public MarcaDTO() {}
    public MarcaDTO(int idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }

    @Override
    public String toString() { return nombreMarca; }
}
