package modelo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para la tabla ventas
 */
public class VentaDTO implements Serializable {
    private int idVenta;
    private String numeroFactura;
    private Date fechaVenta;
    private int idCliente;
    private int idVendedor;
    private int idAuto;
    private int idFormaPago;
    private double precioBase;
    private double impuestoVenta;
    private double iva;
    private double totalPagar;
    private String estado;
    private Date fechaAnulacion;

    // Campos adicionales para mostrar/JOINs
    private String nombreCliente;
    private String nombreVendedor;
    private String profesionVendedor;
    private String codigoAuto;         // Código único de automovil en la BD

    // Para mostrar correctamente los datos del auto SEPARADOS
    private String marcaAuto;          // Nombre de la marca (JOIN)
    private String lineaAuto;          // Nombre de la línea (JOIN)
    private int anioAuto;              // Año del auto

    // Para mostrar la forma de pago correctamente
    private String codigoFormaPago;
    private String descripcionFormaPago;

    public VentaDTO() {}

    // ==== Getters & Setters ====
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }

    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }

    public int getIdAuto() { return idAuto; }
    public void setIdAuto(int idAuto) { this.idAuto = idAuto; }

    public int getIdFormaPago() { return idFormaPago; }
    public void setIdFormaPago(int idFormaPago) { this.idFormaPago = idFormaPago; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public double getImpuestoVenta() { return impuestoVenta; }
    public void setImpuestoVenta(double impuestoVenta) { this.impuestoVenta = impuestoVenta; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    public double getTotalPagar() { return totalPagar; }
    public void setTotalPagar(double totalPagar) { this.totalPagar = totalPagar; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaAnulacion() { return fechaAnulacion; }
    public void setFechaAnulacion(Date fechaAnulacion) { this.fechaAnulacion = fechaAnulacion; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getNombreVendedor() { return nombreVendedor; }
    public void setNombreVendedor(String nombreVendedor) { this.nombreVendedor = nombreVendedor; }

    public String getProfesionVendedor() { return profesionVendedor; }
    public void setProfesionVendedor(String profesionVendedor) { this.profesionVendedor = profesionVendedor; }

    public String getCodigoAuto() { return codigoAuto; }
    public void setCodigoAuto(String codigoAuto) { this.codigoAuto = codigoAuto; }

    public String getMarcaAuto() { return marcaAuto; }
    public void setMarcaAuto(String marcaAuto) { this.marcaAuto = marcaAuto; }

    public String getLineaAuto() { return lineaAuto; }
    public void setLineaAuto(String lineaAuto) { this.lineaAuto = lineaAuto; }

    public int getAnioAuto() { return anioAuto; }
    public void setAnioAuto(int anioAuto) { this.anioAuto = anioAuto; }

    public String getCodigoFormaPago() { return codigoFormaPago; }
    public void setCodigoFormaPago(String codigoFormaPago) { this.codigoFormaPago = codigoFormaPago; }

    public String getDescripcionFormaPago() { return descripcionFormaPago; }
    public void setDescripcionFormaPago(String descripcionFormaPago) { this.descripcionFormaPago = descripcionFormaPago; }

    @Override
    public String toString() {
        return "Venta " + numeroFactura + " - " + nombreCliente;
    }
}
