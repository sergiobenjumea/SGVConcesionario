package Controladores;

import modelo.dao.VentaDAO;
import modelo.dto.VentaDTO;
import vistas.UIVentasCreditoConcesionario;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentasCreditoConcesionarioController implements ActionListener {

    private UIVentasCreditoConcesionario vista;
    private VentaDAO ventaDAO;
    private DecimalFormat formatoMoneda;
    private SimpleDateFormat formatoFecha;

    public VentasCreditoConcesionarioController(UIVentasCreditoConcesionario vista) {
        this.vista = vista;
        this.ventaDAO = new VentaDAO();
        this.formatoMoneda = new DecimalFormat("$#,##0.00");
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        this.vista.btnCargarVentas.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCargarVentas) {
            cargarVentasCredito();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void cargarVentasCredito() {
        List<VentaDTO> ventas = ventaDAO.obtenerVentasCreditoConcesionario();

        if (ventas.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "No se encontraron ventas con crédito del concesionario",
                    "Sin ventas",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            vista.txtTotalVtasCredConcesionario.setText("$0.00");
            vista.txtTotalCantOperaciones.setText("0");
            return;
        }

        // Llenar la tabla y calcular totales
        llenarTablaVentas(ventas);

        JOptionPane.showMessageDialog(vista,
                "Se cargaron " + ventas.size() + " ventas con crédito del concesionario",
                "Ventas cargadas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void llenarTablaVentas(List<VentaDTO> ventas) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVtasCreditoConcesionario.getModel();
        limpiarTabla();

        double totalVentas = 0;

        for (VentaDTO venta : ventas) {
            String fecha = formatoFecha.format(venta.getFechaVenta());
            String total = formatoMoneda.format(venta.getTotalPagar());

            Object[] fila = {
                venta.getNumeroFactura(),
                fecha,
                venta.getNombreVendedor(),
                venta.getMarcaAuto(),   // Marca por separado
                venta.getLineaAuto(),   // Línea por separado
                venta.getAnioAuto(),    // Año por separado
                venta.getNombreCliente(),
                total
            };
            modelo.addRow(fila);
            totalVentas += venta.getTotalPagar();
        }

        vista.txtTotalVtasCredConcesionario.setText(formatoMoneda.format(totalVentas));
        vista.txtTotalCantOperaciones.setText(String.valueOf(ventas.size()));
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVtasCreditoConcesionario.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarCampos() {
        vista.txtTotalVtasCredConcesionario.setText("");
        vista.txtTotalCantOperaciones.setText("");
        limpiarTabla();
    }
}
