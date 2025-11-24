package modelo.dto;

public class TipoIdentificacionDTO {
    private int idTipoIdentificacion;
    private String codigo;
    private String nombre;
    private boolean activo;

    public TipoIdentificacionDTO() {}

    public TipoIdentificacionDTO(int id, String codigo, String nombre) {
        this.idTipoIdentificacion = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getIdTipoIdentificacion() { return idTipoIdentificacion; }
    public void setIdTipoIdentificacion(int idTipoIdentificacion) { this.idTipoIdentificacion = idTipoIdentificacion; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return nombre; // Para mostrar correctamente en el ComboBox
    }
}