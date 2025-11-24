package modelo.dto;

public class FormasPagoDTO {
    private int id;
    private String nombre;

    public FormasPagoDTO() {
    }

    public FormasPagoDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // Método útil para mostrar en ComboBoxes si se requiere en el futuro
    @Override
    public String toString() {
        return nombre;
    }
}