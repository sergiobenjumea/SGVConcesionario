package modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class AutomovilDTO implements Serializable {

    private int idAuto;
    private String codigo;
    private String marca;
    private int idMarca;
    private int idLinea;
    private int anio;
    private String color;
    private double precioBase;
    private double impuestoVenta;
    private double iva;
    private double precioTotal;
    private int idTipoMotor;
    private String estado;
    private Date fechaIngreso;

    // Info para mostrar, via JOIN
    private String nombreMarca;
    private String nombreLinea;
    private String nombreTipoMotor;
    private double porcentajeImpuesto;
    

    public AutomovilDTO() {}

    public int getIdAuto() { return idAuto; }
    public void setIdAuto(int idAuto) { this.idAuto = idAuto; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdLinea() { return idLinea; }
    public void setIdLinea(int idLinea) { this.idLinea = idLinea; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public int getIdTipoMotor() { return idTipoMotor; }
    public void setIdTipoMotor(int idTipoMotor) { this.idTipoMotor = idTipoMotor; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }

    public String getNombreLinea() { return nombreLinea; }
    public void setNombreLinea(String nombreLinea) { this.nombreLinea = nombreLinea; }

    public String getNombreTipoMotor() { return nombreTipoMotor; }
    public void setNombreTipoMotor(String nombreTipoMotor) { this.nombreTipoMotor = nombreTipoMotor; }

    public double getPorcentajeImpuesto() { return porcentajeImpuesto; }
    public void setPorcentajeImpuesto(double porcentajeImpuesto) { this.porcentajeImpuesto = porcentajeImpuesto; }

    @Override
    public String toString() {
        return codigo + " - " + nombreMarca + " " + nombreLinea + " " + anio + " (" + color + ")";
    }
    // 1. Agrega estos atributos nuevos arriba, junto con los demás:

    // 2. Agrega estos métodos set y get en la sección de métodos públicos:
    public void setImpuestoVenta(double impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public double getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getIva() {
        return iva;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

}
