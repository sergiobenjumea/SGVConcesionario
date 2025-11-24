package modelo.dto;

import java.math.BigDecimal;

public class TipoMotorDTO {
    private int idTipoMotor;
    private String nombre;
    private BigDecimal porcentajeImpuesto;

    public TipoMotorDTO() {}

    public int getIdTipoMotor() { return idTipoMotor; }
    public void setIdTipoMotor(int idTipoMotor) { this.idTipoMotor = idTipoMotor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPorcentajeImpuesto() { return porcentajeImpuesto; }
    public void setPorcentajeImpuesto(BigDecimal porcentajeImpuesto) { this.porcentajeImpuesto = porcentajeImpuesto; }

    @Override
    public String toString() {
        return nombre;
    }
}