package modelo.dto;

public class AutomovilDTO {
    private int id;
    private int idLinea;       // Guardamos el ID de la linea seleccionada
    private int anio;          // El año del modelo
    private String color;
    private int idTipoMotor;
    private double precioBase;

    // --- NUEVOS CAMPOS AUXILIARES PARA LA TABLA ---
    private String nombreMarca;
    private String nombreLinea;
    private String nombreMotor;

    public AutomovilDTO() {
    }

    public AutomovilDTO(int idLinea, int anio, String color, int idTipoMotor, double precioBase) {
        this.idLinea = idLinea;
        this.anio = anio;
        this.color = color;
        this.idTipoMotor = idTipoMotor;
        this.precioBase = precioBase;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdLinea() { return idLinea; }
    public void setIdLinea(int idLinea) { this.idLinea = idLinea; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getIdTipoMotor() { return idTipoMotor; }
    public void setIdTipoMotor(int idTipoMotor) { this.idTipoMotor = idTipoMotor; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }

    public String getNombreLinea() { return nombreLinea; }
    public void setNombreLinea(String nombreLinea) { this.nombreLinea = nombreLinea; }

    public String getNombreMotor() { return nombreMotor; }
    public void setNombreMotor(String nombreMotor) { this.nombreMotor = nombreMotor; }
}