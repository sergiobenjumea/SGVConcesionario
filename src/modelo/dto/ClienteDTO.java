package modelo.dto;

import java.sql.Date; // Usaremos java.sql.Date para la base de datos

public class ClienteDTO {
    private int id;
    private int idTipoIdentificacion;
    private String identificacion;
    private String nombre;
    private Date fechaNacimiento;
    private int edad;
    private String email;
    
    // Auxiliar para mostrar en la tabla
    private String nombreTipoId;

    public ClienteDTO() {
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdTipoIdentificacion() { return idTipoIdentificacion; }
    public void setIdTipoIdentificacion(int idTipoIdentificacion) { this.idTipoIdentificacion = idTipoIdentificacion; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombreTipoId() { return nombreTipoId; }
    public void setNombreTipoId(String nombreTipoId) { this.nombreTipoId = nombreTipoId; }
}