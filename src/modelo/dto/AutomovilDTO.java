package modelo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para la tabla automoviles
 */
public class AutomovilDTO implements Serializable {
    
    private int idAuto;
    private String codigo;
    private String marca;
    private String modelo;
    private String color;
    private double precioBase;
    private int idTipoMotor;
    private String nombreTipoMotor; // Para mostrar en ComboBox
    private double impuestoVenta;
    private double iva;
    private double precioTotal;
    private String estado;
    private Date fechaIngreso;
    
    public AutomovilDTO() {
    }

    public AutomovilDTO(int idAuto, String codigo, String marca, String modelo, 
                       String color, double precioBase, int idTipoMotor) {
        this.idAuto = idAuto;
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioBase = precioBase;
        this.idTipoMotor = idTipoMotor;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getIdTipoMotor() {
        return idTipoMotor;
    }

    public void setIdTipoMotor(int idTipoMotor) {
        this.idTipoMotor = idTipoMotor;
    }

    public String getNombreTipoMotor() {
        return nombreTipoMotor;
    }

    public void setNombreTipoMotor(String nombreTipoMotor) {
        this.nombreTipoMotor = nombreTipoMotor;
    }

    public double getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(double impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return codigo + " - " + marca + " " + modelo + " (" + color + ")";
    }
}
