package modelo.dto;

public class LineaVehiculoDTO {
    private int idLinea;
    private int idMarca;
    private String nombre;
    private boolean activo;

    public LineaVehiculoDTO() {}

    public int getIdLinea() { return idLinea; }
    public void setIdLinea(int idLinea) { this.idLinea = idLinea; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return nombre;
    }
}