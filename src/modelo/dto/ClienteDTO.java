package modelo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para la tabla clientes
 * Representa los datos de un cliente en memoria
 */
public class ClienteDTO implements Serializable {
    
    // Atributos (deben coincidir con las columnas de la tabla)
    private int idCliente;
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private Date fechaNacimiento;
    private int edad;
    private String email;
    private Date fechaRegistro;
    
    // Constructor vacío (recomendado por el profesor)
    public ClienteDTO() {
    }
    
    // Constructor con parámetros (opcional)
    public ClienteDTO(int idCliente, String tipoIdentificacion, String identificacion, 
                     String nombre, Date fechaNacimiento, int edad, String email) {
        this.idCliente = idCliente;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.email = email;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " (" + identificacion + ")";
    }
}
