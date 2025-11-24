package modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class ClienteDTO implements Serializable {
    private int idCliente;
    private int idTipoIdentificacion;
    private String identificacion;
    private String nombre;
    private Date fechaNacimiento;
    private int edad;
    private String email;
    private Date fechaRegistro;

    // Para mostrar correctamente en ComboBox/vistas
    private String codigoTipoIdentificacion;
    private String nombreTipoIdentificacion;

    public ClienteDTO() {}

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

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

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getCodigoTipoIdentificacion() { return codigoTipoIdentificacion; }
    public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) { this.codigoTipoIdentificacion = codigoTipoIdentificacion; }

    public String getNombreTipoIdentificacion() { return nombreTipoIdentificacion; }
    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) { this.nombreTipoIdentificacion = nombreTipoIdentificacion; }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " (" + codigoTipoIdentificacion + " " + identificacion + ")";
    }
}
