package Controladores;

import modelo.dao.VendedorDAO;
import modelo.dao.VentaDAO;
import modelo.dto.VendedorDTO;
import modelo.dto.VentaDTO;
import vistas.UIBuscarporVendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BusquedaVendedorController implements ActionListener {

    private UIBuscarporVendedor vista;
    private VendedorDAO vendedorDAO;
    private VentaDAO ventaDAO;
    private ButtonGroup grupoBusqueda;
    private DecimalFormat formatoMoneda;
    private SimpleDateFormat formatoFecha;

    public BusquedaVendedorController(UIBuscarporVendedor vista) {
        this.vista = vista;
        this.vendedorDAO = new VendedorDAO();
        this.ventaDAO = new VentaDAO();
        this.formatoMoneda = new DecimalFormat("$#,##0.00");
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        // Configurar ButtonGroup para radio buttons
        grupoBusqueda = new ButtonGroup();
        grupoBusqueda.add(vista.rbbuscarporID);
        grupoBusqueda.add(vista.rbbuscarporNombre);
        vista.rbbuscarporID.setSelected(true); // Por defecto

        // Listeners
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnbuscar) {
            buscarVendedor();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void buscarVendedor() {
        String criterio = vista.txtidonombre.getText().trim();
        if (criterio.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese un criterio de búsqueda", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            vista.txtidonombre.requestFocus();
            return;
        }

        VendedorDTO vendedor = null;
        if (vista.rbbuscarporID.isSelected()) {
            try {
                int idVendedor = Integer.parseInt(criterio);
                vendedor = vendedorDAO.buscarPorId(idVendedor);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El ID debe ser numérico", "Error de formato", JOptionPane.ERROR_MESSAGE);
                vista.txtidonombre.requestFocus();
                return;
            }
        } else if (vista.rbbuscarporNombre.isSelected()) {
            vendedor = vendedorDAO.buscarPorNombre(criterio);
        }

        if (vendedor == null) {
            String c = vista.rbbuscarporID.isSelected() ? "ID" : "nombre";
            JOptionPane.showMessageDialog(vista, "No se encontró un vendedor con " + c + ": " + criterio, "No encontrado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            return;
        }

        vista.txtIDVendedor.setText(String.valueOf(vendedor.getIdVendedor()));
        vista.txtNombreVendedor.setText(vendedor.getNombre());
        vista.txtProfesionVendedor.setText(vendedor.getProfesion());

        List<VentaDTO> ventas = ventaDAO.obtenerVentasPorVendedor(vendedor.getIdVendedor());
        if (ventas == null || ventas.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El vendedor no tiene ventas registradas.", "Sin ventas", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            vista.txtTotalVentas.setText("$0.00");
            vista.txtCantVentas.setText("0");
            return;
        }

        llenarTablaVentas(ventas);
    }

    private void llenarTablaVentas(List<VentaDTO> ventas) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVentasRealizadas.getModel();
        limpiarTabla();
        double totalVentas = 0;
        for (VentaDTO venta : ventas) {
            String fecha = formatoFecha.format(venta.getFechaVenta());
            String total = formatoMoneda.format(venta.getTotalPagar());

            Object[] fila = {
                venta.getNumeroFactura(),
                fecha,
                venta.getMarcaAuto(),        // Marca por separado
                venta.getLineaAuto(),        // Línea por separado
                venta.getAnioAuto(),         // Año por separado
                venta.getNombreCliente(),
                total
            };
            modelo.addRow(fila);
            totalVentas += venta.getTotalPagar();
        }
        vista.txtTotalVentas.setText(formatoMoneda.format(totalVentas));
        vista.txtCantVentas.setText(String.valueOf(ventas.size()));
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVentasRealizadas.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarCampos() {
        vista.txtidonombre.setText("");
        vista.txtIDVendedor.setText("");
        vista.txtNombreVendedor.setText("");
        vista.txtProfesionVendedor.setText("");
        vista.txtTotalVentas.setText("");
        vista.txtCantVentas.setText("");
        limpiarTabla();
        vista.rbbuscarporID.setSelected(true);
        vista.txtidonombre.requestFocus();
    }
}
