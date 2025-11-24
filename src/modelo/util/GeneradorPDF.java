package modelo.util;

import modelo.dto.VentaDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.awt.Desktop; // Para abrir el archivo automáticamente

public class GeneradorPDF {
    
    private String rutaDescargas;
    
    public GeneradorPDF() {
        // Guardará en el Escritorio carpeta "Facturas"
        this.rutaDescargas = System.getProperty("user.home") + File.separator + "Desktop" + File.separator;
    }
    
    public void generarFactura(VentaDTO venta) {
        try {
            // 1. Crear carpeta si no existe
            String rutaCarpeta = rutaDescargas + "Facturas";
            File carpeta = new File(rutaCarpeta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            
            // 2. Definir nombre del archivo (Ej: Factura_FAC-001.pdf)
            String nombreArchivo = "Factura_" + venta.getNumeroFactura() + ".pdf";
            String rutaCompleta = rutaCarpeta + File.separator + nombreArchivo;
            
            // 3. Crear Documento PDF
            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
            document.open();
            
            // --- FUENTES ---
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font subFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
            // --- ENCABEZADO ---
            Paragraph titulo = new Paragraph("CONCESIONARIO DE AUTOMÓVILES", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            Paragraph subtitulo = new Paragraph("FACTURA DE VENTA N° " + venta.getNumeroFactura(), subFont);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            document.add(subtitulo);
            
            // --- DATOS GENERALES (Tabla invisible para organizar) ---
            PdfPTable tablaDatos = new PdfPTable(2);
            tablaDatos.setWidthPercentage(100);
            tablaDatos.setSpacingAfter(20);
            
            addCelda(tablaDatos, "Fecha de Venta: " + venta.getFechaVenta().toString(), normalFont);
            addCelda(tablaDatos, "Forma de Pago: " + venta.getNombreFormaPago(), normalFont);
            addCelda(tablaDatos, "Vendedor: " + venta.getNombreVendedor(), normalFont);
            addCelda(tablaDatos, "Cliente: " + venta.getNombreCliente(), normalFont);
            
            document.add(tablaDatos);
            
            // --- DETALLE DEL PRODUCTO ---
            Paragraph prodTitulo = new Paragraph("DETALLE DEL VEHÍCULO", subFont);
            document.add(prodTitulo);
            
            PdfPTable tablaProd = new PdfPTable(2); // Descripción y Valor
            tablaProd.setWidthPercentage(100);
            tablaProd.setSpacingBefore(10);
            tablaProd.setSpacingAfter(20);
            
            // Encabezados de tabla
            PdfPCell c1 = new PdfPCell(new Phrase("Descripción", subFont));
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaProd.addCell(c1);
            
            PdfPCell c2 = new PdfPCell(new Phrase("Valor", subFont));
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaProd.addCell(c2);
            
            // Fila del Auto
            tablaProd.addCell(new Phrase(venta.getDescripcionAuto(), normalFont));
            
            // Usamos tu clase Formato para que se vea bonito en el PDF también
            tablaProd.addCell(new Phrase(Formato.moneda(venta.getPrecioBase()), normalFont));
            
            document.add(tablaProd);
            
            // --- TOTALES ---
            PdfPTable tablaTotales = new PdfPTable(2);
            tablaTotales.setWidthPercentage(40);
            tablaTotales.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            addCelda(tablaTotales, "Subtotal:", normalFont);
            addCelda(tablaTotales, Formato.moneda(venta.getPrecioBase()), normalFont);
            
            addCelda(tablaTotales, "Impuesto:", normalFont);
            addCelda(tablaTotales, Formato.moneda(venta.getImpuesto()), normalFont);
            
            addCelda(tablaTotales, "IVA:", normalFont);
            addCelda(tablaTotales, Formato.moneda(venta.getIva()), normalFont);
            
            PdfPCell celdaTotalLabel = new PdfPCell(new Phrase("TOTAL:", subFont));
            celdaTotalLabel.setBorder(Rectangle.TOP);
            tablaTotales.addCell(celdaTotalLabel);
            
            PdfPCell celdaTotalValue = new PdfPCell(new Phrase(Formato.moneda(venta.getTotalPagar()), subFont));
            celdaTotalValue.setBorder(Rectangle.TOP);
            tablaTotales.addCell(celdaTotalValue);
            
            document.add(tablaTotales);
            
            // --- PIE DE PAGINA ---
            Paragraph fin = new Paragraph("\n\nGracias por su compra.", normalFont);
            fin.setAlignment(Element.ALIGN_CENTER);
            document.add(fin);
            
            document.close();
            System.out.println("PDF Generado: " + rutaCompleta);
            
            // Intentar abrir el archivo automáticamente
            abrirArchivo(rutaCompleta);
            
        } catch (Exception e) {
            System.err.println("Error generando PDF: " + e.getMessage());
        }
    }
    
    private void addCelda(PdfPTable tabla, String texto, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuente));
        cell.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(cell);
    }
    
    private void abrirArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivo);
                }
            }
        } catch (Exception e) {
            // No pasa nada si falla abrirlo, el archivo ya está creado
        }
    }
}