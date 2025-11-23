package modelo.util;

import modelo.dto.VentaDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class GeneradorPDF {
    
    private String rutaDescargas;
    
    public GeneradorPDF() {
        // Ruta del escritorio
        this.rutaDescargas = System.getProperty("user.home") + "/Desktop/";
    }
    
    /**
     * Genera un PDF de factura para una venta
     */
    public String generarFactura(VentaDTO venta) {
        try {
            // Crear carpeta de facturas si no existe
            String rutaFacturas = rutaDescargas + "Facturas/";
            File carpeta = new File(rutaFacturas);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            
            // Nombre del archivo
            String nombreArchivo = "Factura_" + venta.getNumeroFactura() + ".pdf";
            String rutaCompleta = rutaFacturas + nombreArchivo;
            
            // Crear documento
            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
            document.open();
            
            // ===== FUENTES =====
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font seccionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
            // ===== ENCABEZADO =====
            Paragraph titulo = new Paragraph("CONCESIONARIO DE AUTOMÓVILES", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            Paragraph subtitulo = new Paragraph("FACTURA DE VENTA", subtituloFont);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            document.add(subtitulo);
            
            // ===== INFORMACIÓN GENERAL =====
            PdfPTable headerTable = new PdfPTable(4);
            headerTable.setWidthPercentage(100);
            headerTable.setSpacingAfter(15);
            
            addCell(headerTable, "No. Factura: " + venta.getNumeroFactura(), normalFont);
            addCell(headerTable, "Fecha: " + formatearFecha(venta.getFechaVenta()), normalFont);
            addCell(headerTable, "Estado: " + venta.getEstado(), normalFont);
            addCell(headerTable, "Forma Pago: " + venta.getDescripcionFormaPago(), normalFont);
            
            document.add(headerTable);
            
            // ===== DATOS DEL CLIENTE =====
            Paragraph clienteTitulo = new Paragraph("DATOS DEL CLIENTE", seccionFont);
            clienteTitulo.setSpacingBefore(10);
            document.add(clienteTitulo);
            
            PdfPTable clienteTable = new PdfPTable(2);
            clienteTable.setWidthPercentage(100);
            clienteTable.setSpacingAfter(15);
            
            addCell(clienteTable, "Nombre:", normalFont);
            addCell(clienteTable, venta.getNombreCliente(), normalFont);
            addCell(clienteTable, "ID Cliente:", normalFont);
            addCell(clienteTable, String.valueOf(venta.getIdCliente()), normalFont);
            
            document.add(clienteTable);
            
            // ===== DATOS DEL VENDEDOR =====
            Paragraph vendedorTitulo = new Paragraph("DATOS DEL VENDEDOR", seccionFont);
            document.add(vendedorTitulo);
            
            PdfPTable vendedorTable = new PdfPTable(2);
            vendedorTable.setWidthPercentage(100);
            vendedorTable.setSpacingAfter(15);
            
            addCell(vendedorTable, "Nombre:", normalFont);
            addCell(vendedorTable, venta.getNombreVendedor(), normalFont);
            addCell(vendedorTable, "ID Vendedor:", normalFont);
            addCell(vendedorTable, String.valueOf(venta.getIdVendedor()), normalFont);
            
            document.add(vendedorTable);
            
            // ===== DATOS DEL VEHÍCULO =====
            Paragraph vehiculoTitulo = new Paragraph("DATOS DEL VEHÍCULO", seccionFont);
            document.add(vehiculoTitulo);
            
            PdfPTable vehiculoTable = new PdfPTable(2);
            vehiculoTable.setWidthPercentage(100);
            vehiculoTable.setSpacingAfter(15);
            
            addCell(vehiculoTable, "Código:", normalFont);
            addCell(vehiculoTable, venta.getCodigoAuto(), normalFont);
            addCell(vehiculoTable, "ID Automóvil:", normalFont);
            addCell(vehiculoTable, String.valueOf(venta.getIdAuto()), normalFont);
            
            document.add(vehiculoTable);
            
            // ===== RESUMEN DE FACTURA =====
            Paragraph resumenTitulo = new Paragraph("RESUMEN DE FACTURA", seccionFont);
            document.add(resumenTitulo);
            
            PdfPTable resumenTable = new PdfPTable(2);
            resumenTable.setWidthPercentage(60);
            resumenTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            resumenTable.setSpacingAfter(20);
            
            addCell(resumenTable, "Precio Base:", normalFont);
            addCell(resumenTable, String.format("$%,.2f", venta.getPrecioBase()), normalFont);
            
            addCell(resumenTable, "Impuesto de Venta (15%):", normalFont);
            addCell(resumenTable, String.format("$%,.2f", venta.getImpuestoVenta()), normalFont);
            
            addCell(resumenTable, "IVA (19%):", normalFont);
            addCell(resumenTable, String.format("$%,.2f", venta.getIva()), normalFont);
            
            PdfPCell totalLabel = new PdfPCell(new Phrase("TOTAL A PAGAR:", seccionFont));
            totalLabel.setBorder(PdfPCell.NO_BORDER);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            resumenTable.addCell(totalLabel);
            
            PdfPCell totalValue = new PdfPCell(new Phrase(String.format("$%,.2f", venta.getTotalPagar()), seccionFont));
            totalValue.setBorder(PdfPCell.NO_BORDER);
            totalValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
            resumenTable.addCell(totalValue);
            
            document.add(resumenTable);
            
            // ===== PIE DE PÁGINA =====
            Paragraph gracias = new Paragraph("Gracias por su compra", normalFont);
            gracias.setAlignment(Element.ALIGN_CENTER);
            gracias.setSpacingBefore(30);
            document.add(gracias);
            
            Paragraph sistema = new Paragraph("Sistema de Gestión de Concesionario", new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC));
            sistema.setAlignment(Element.ALIGN_CENTER);
            document.add(sistema);
            
            // Cerrar documento
            document.close();
            
            System.out.println("✅ PDF generado exitosamente: " + rutaCompleta);
            return rutaCompleta;
            
        } catch (Exception e) {
            System.err.println("❌ Error al generar PDF");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método auxiliar para agregar celdas a la tabla
     */
    private void addCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(5);
        table.addCell(cell);
    }
    
    /**
     * Formatea una fecha al formato dd/MM/yyyy
     */
    private String formatearFecha(java.util.Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }
    
    /**
     * Abre el PDF automáticamente después de generarlo
     */
    public void abrirPDF(String rutaArchivo) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", rutaArchivo});
            } else if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", rutaArchivo});
            } else {
                // Linux
                Runtime.getRuntime().exec(new String[]{"xdg-open", rutaArchivo});
            }
            System.out.println("✅ Abriendo PDF...");
        } catch (Exception e) {
            System.err.println("❌ Error al abrir PDF");
            e.printStackTrace();
        }
    }
}
