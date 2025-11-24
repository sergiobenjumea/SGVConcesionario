package modelo.util;

public class ItemCombo {
    private int id;
    private String descripcion;

    public ItemCombo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    
    // Este método es el que usa el ComboBox para mostrar el texto
    @Override
    public String toString() {
        return descripcion;
    }
}