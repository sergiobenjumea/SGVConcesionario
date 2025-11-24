package modelo.dto;

import java.sql.Date;

public class VendedorDTO {
    private int id;
    private String identificacion; // Corresponde al txtidVendedor
    private String nombre;
    private String profesion;
    private Date fechaContratacion;

    public VendedorDTO() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }
}