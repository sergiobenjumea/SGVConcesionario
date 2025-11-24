package modelo.dto;

import java.io.Serializable;

public class TipoMotorDTO implements Serializable {
    private int idTipoMotor;
    private String nombreTipo;
    private double porcentajeImpuesto;
    private int activo;

    public TipoMotorDTO() {}
    public TipoMotorDTO(int idTipoMotor, String nombreTipo, double porcentajeImpuesto) {
        this.idTipoMotor = idTipoMotor;
        this.nombreTipo = nombreTipo;
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public int getIdTipoMotor() { return idTipoMotor; }
    public void setIdTipoMotor(int idTipoMotor) { this.idTipoMotor = idTipoMotor; }

    public String getNombreTipo() { return nombreTipo; }
    public void setNombreTipo(String nombreTipo) { this.nombreTipo = nombreTipo; }

    public double getPorcentajeImpuesto() { return porcentajeImpuesto; }
    public void setPorcentajeImpuesto(double porcentajeImpuesto) { this.porcentajeImpuesto = porcentajeImpuesto; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }

    @Override
    public String toString() { return nombreTipo; }
}
