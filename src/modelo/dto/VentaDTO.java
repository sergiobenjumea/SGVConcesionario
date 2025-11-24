package modelo.dto;

import java.sql.Date;

public class VentaDTO {
    // Atributos Principales
    private int id;
    private String numeroFactura;
    private Date fechaVenta;
    
    // IDs (Para guardar)
    private int idCliente;
    private int idVendedor;
    private int idAuto;
    private int idFormaPago;
    
    // Valores
    private double precioBase;
    private double impuesto;
    private double iva;
    private double totalPagar;
    
    // --- CAMPOS AUXILIARES PARA REPORTES (Solo visualización) ---
    private String nombreVendedor;
    private String profesionVendedor;
    private String nombreCliente;
    private String nombreMarca;    // Nuevo
    private String nombreLinea;    // Nuevo
    private int anioAuto;          // Nuevo
    private String descripcionAuto; // (Marca + Linea combinados si se requiere)
    private String nombreFormaPago;

    public VentaDTO() {}

    // --- GETTERS Y SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
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
    
    public double getImpuesto() { return impuesto; }
    public void setImpuesto(double impuesto) { this.impuesto = impuesto; }
    
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    
    public double getTotalPagar() { return totalPagar; }
    public void setTotalPagar(double totalPagar) { this.totalPagar = totalPagar; }

    // Auxiliares
    public String getNombreVendedor() { return nombreVendedor; }
    public void setNombreVendedor(String nombreVendedor) { this.nombreVendedor = nombreVendedor; }
    
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    
    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String nombreMarca) { this.nombreMarca = nombreMarca; }
    
    public String getNombreLinea() { return nombreLinea; }
    public void setNombreLinea(String nombreLinea) { this.nombreLinea = nombreLinea; }
    
    public int getAnioAuto() { return anioAuto; }
    public void setAnioAuto(int anioAuto) { this.anioAuto = anioAuto; }
    
    public String getDescripcionAuto() { return descripcionAuto; }
    public void setDescripcionAuto(String descripcionAuto) { this.descripcionAuto = descripcionAuto; }
    
    public String getNombreFormaPago() { return nombreFormaPago; }
    public void setNombreFormaPago(String nombreFormaPago) { this.nombreFormaPago = nombreFormaPago; }
    
    public String getProfesionVendedor() { return profesionVendedor; }
    public void setProfesionVendedor(String profesionVendedor) { this.profesionVendedor = profesionVendedor; }
}