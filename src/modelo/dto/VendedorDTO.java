package modelo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para la tabla vendedores
 */
public class VendedorDTO implements Serializable {
    private int idVendedor;
    private String identificacion;
    private String nombre;
    private String profesion;
    private Date fechaContratacion;
    private String estado;

    // Campos adicionales útiles para vistas (opcional)
    private int totalVentas; // Usado para reportes o estadísticas

    public VendedorDTO() {}

    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getTotalVentas() { return totalVentas; }
    public void setTotalVentas(int totalVentas) { this.totalVentas = totalVentas; }

    @Override
    public String toString() {
        return "Vendedor: " + nombre + " (" + identificacion + ")";
    }
}
