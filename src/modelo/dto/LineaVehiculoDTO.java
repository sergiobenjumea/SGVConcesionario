package modelo.dto;

import java.io.Serializable;

public class LineaVehiculoDTO implements Serializable {
    private int idLinea;
    private int idMarca;
    private String nombreLinea;
    private int activo;

    // Solo para mostrar
    private String nombreMarca;

    public LineaVehiculoDTO() {}

    public int getIdLinea() { return idLinea; }
    public void setIdLinea(int idLinea) { this.idLinea = idLinea; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public String getNombreLinea() { return nombreLinea; }
    public void setNombreLinea(String nombreLinea) { this.nombreLinea = nombreLinea; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }

    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }

    @Override
    public String toString() { return nombreLinea; }
}
