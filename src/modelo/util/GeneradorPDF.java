package modelo.util;

import modelo.dto.VentaDTO;
import vistas.UIRegistrarventa; // Importamos la vista para leer datos extra
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Desktop;

public class GeneradorPDF {
    
    private String rutaDescargas;
    
    public GeneradorPDF() {
        this.rutaDescargas = System.getProperty("user.home") + File.separator + "Desktop" + File.separator;
    }
    
    // AHORA RECIBE EL DTO Y LA VISTA
    public void generarFactura(VentaDTO venta, UIRegistrarventa view) {
        try {
            String rutaCarpeta = rutaDescargas + "Facturas";
            File carpeta = new File(rutaCarpeta);
            if (!carpeta.exists()) carpeta.mkdirs();
            
            String nombreArchivo = "Factura_" + venta.getNumeroFactura() + ".pdf";
            String rutaCompleta = rutaCarpeta + File.separator + nombreArchivo;
            
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
            
            // --- TABLA 1: DATOS GENERALES ---
            PdfPTable tablaInfo = new PdfPTable(2);
            tablaInfo.setWidthPercentage(100);
            
            addCelda(tablaInfo, "Fecha de Emisión:", subFont);
            addCelda(tablaInfo, view.txtFecha.getText(), normalFont);
            
            addCelda(tablaInfo, "Forma de Pago:", subFont);
            addCelda(tablaInfo, view.cboxFormasPago.getSelectedItem().toString(), normalFont);
            
            document.add(tablaInfo);
            document.add(new Paragraph("\n"));

            // --- TABLA 2: CLIENTE Y VENDEDOR ---
            PdfPTable tablaPersonas = new PdfPTable(2);
            tablaPersonas.setWidthPercentage(100);
            
            // Columna Izquierda: Vendedor
            PdfPCell celdaVendedor = new PdfPCell();
            celdaVendedor.setBorder(Rectangle.BOX);
            celdaVendedor.addElement(new Paragraph("DATOS DEL VENDEDOR", subFont));
            celdaVendedor.addElement(new Paragraph("ID: " + view.txtIDVendedor.getText(), normalFont));
            celdaVendedor.addElement(new Paragraph("Nombre: " + view.txtNombreVendedor.getText(), normalFont));
            celdaVendedor.addElement(new Paragraph("Profesión: " + view.txtProfesionVendedor.getText(), normalFont));
            tablaPersonas.addCell(celdaVendedor);
            
            // Columna Derecha: Cliente
            PdfPCell celdaCliente = new PdfPCell();
            celdaCliente.setBorder(Rectangle.BOX);
            celdaCliente.addElement(new Paragraph("DATOS DEL CLIENTE", subFont));
            celdaCliente.addElement(new Paragraph("ID: " + view.txtIDCliente.getText(), normalFont));
            celdaCliente.addElement(new Paragraph("Nombre: " + view.txtNombreCliente.getText(), normalFont));
            celdaCliente.addElement(new Paragraph("Edad: " + view.txtEdadCliente.getText(), normalFont));
            celdaCliente.addElement(new Paragraph("Email: " + view.txteMailCliente.getText(), normalFont));
            tablaPersonas.addCell(celdaCliente);
            
            document.add(tablaPersonas);
            document.add(new Paragraph("\n"));
            
            // --- TABLA 3: DETALLE DEL VEHÍCULO ---
            document.add(new Paragraph("DETALLE DEL VEHÍCULO", subFont));
            
            PdfPTable tablaAuto = new PdfPTable(4);
            tablaAuto.setWidthPercentage(100);
            tablaAuto.setSpacingBefore(5);
            
            // Encabezados
            addCeldaGris(tablaAuto, "Código", subFont);
            addCeldaGris(tablaAuto, "Marca / Línea", subFont);
            addCeldaGris(tablaAuto, "Detalles", subFont);
            addCeldaGris(tablaAuto, "Precio Base", subFont);
            
            // Datos
            addCelda(tablaAuto, view.txtCodigoAuto.getText(), normalFont);
            addCelda(tablaAuto, view.txtMarca.getText() + " " + view.txtLinea.getText(), normalFont);
            
            String detalles = "Año: " + view.txtAnio.getText() + "\n" +
                              "Color: " + view.txtColorAuto.getText() + "\n" +
                              "Motor: " + view.txtTipoMotor.getText();
            addCelda(tablaAuto, detalles, normalFont);
            
            // Usamos el valor formateado de la nueva caja txtPrecioBase1
            addCelda(tablaAuto, view.txtPrecioBase1.getText(), normalFont);
            
            document.add(tablaAuto);
            
            // --- TABLA 4: TOTALES ---
            PdfPTable tablaTotales = new PdfPTable(2);
            tablaTotales.setWidthPercentage(40);
            tablaTotales.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTotales.setSpacingBefore(10);
            
            addCelda(tablaTotales, "Subtotal:", normalFont);
            addCelda(tablaTotales, view.txtPrecioBase1.getText(), normalFont);
            
            addCelda(tablaTotales, "Impuesto Venta:", normalFont);
            addCelda(tablaTotales, view.txtImpoVenta.getText(), normalFont);
            
            addCelda(tablaTotales, "IVA:", normalFont);
            addCelda(tablaTotales, view.txtIVA.getText(), normalFont);
            
            PdfPCell totalLbl = new PdfPCell(new Phrase("TOTAL A PAGAR:", subFont));
            totalLbl.setBorder(Rectangle.TOP);
            tablaTotales.addCell(totalLbl);
            
            PdfPCell totalVal = new PdfPCell(new Phrase(view.txtTotalPagar.getText(), subFont));
            totalVal.setBorder(Rectangle.TOP);
            tablaTotales.addCell(totalVal);
            
            document.add(tablaTotales);
            
            // --- PIE DE PAGINA ---
            Paragraph fin = new Paragraph("\nGracias por su compra en nuestro concesionario.", normalFont);
            fin.setAlignment(Element.ALIGN_CENTER);
            document.add(fin);
            
            document.close();
            abrirArchivo(rutaCompleta);
            
        } catch (Exception e) {
            System.err.println("Error PDF: " + e.getMessage());
        }
    }
    
    private void addCelda(PdfPTable tabla, String texto, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuente));
        cell.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(cell);
    }
    
    private void addCeldaGris(PdfPTable tabla, String texto, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuente));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(cell);
    }
    
    private void abrirArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            if (archivo.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (Exception e) {}
    }
}