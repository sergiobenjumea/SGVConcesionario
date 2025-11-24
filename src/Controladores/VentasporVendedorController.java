package Controladores;

import modelo.dao.VendedorDAO;
import modelo.dao.VentaDAO;
import modelo.dto.VendedorDTO;
import modelo.dto.VentaDTO;
import vistas.UIVentasporVendedor;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentasporVendedorController implements ActionListener {

    private UIVentasporVendedor vista;
    private VendedorDAO vendedorDAO;
    private VentaDAO ventaDAO;
    private DecimalFormat formatoMoneda;
    private SimpleDateFormat formatoFecha;

    public VentasporVendedorController(UIVentasporVendedor vista) {
        this.vista = vista;
        this.vendedorDAO = new VendedorDAO();
        this.ventaDAO = new VentaDAO();
        this.formatoMoneda = new DecimalFormat("$#,##0.00");
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        this.vista.btnBuscarVentasVendedor.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarVentasVendedor) {
            buscarVentasVendedor();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void buscarVentasVendedor() {
        String idTexto = vista.txtIDVendedor.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Por favor ingrese el ID del vendedor",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE);
            vista.txtIDVendedor.requestFocus();
            return;
        }

        try {
            int idVendedor = Integer.parseInt(idTexto);

            VendedorDTO vendedor = vendedorDAO.buscarPorId(idVendedor);

            if (vendedor == null) {
                JOptionPane.showMessageDialog(vista,
                    "No se encontró un vendedor con el ID: " + idVendedor,
                    "Vendedor no encontrado",
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                vista.txtIDVendedor.requestFocus();
                return;
            }

            vista.txtNombreVendedor.setText(vendedor.getNombre());
            vista.txtProfesionVendedor.setText(vendedor.getProfesion());

            List<VentaDTO> ventas = ventaDAO.obtenerVentasPorVendedor(idVendedor);

            if (ventas.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                    "El vendedor " + vendedor.getNombre() + " no tiene ventas registradas",
                    "Sin ventas",
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                vista.txtTotalVentasVendedor.setText("$0.00");
                vista.txtCantidadVentas.setText("0");
                return;
            }

            llenarTablaVentas(ventas);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista,
                "El ID del vendedor debe ser un número válido",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);
            vista.txtIDVendedor.requestFocus();
        }
    }

    private void llenarTablaVentas(List<VentaDTO> ventas) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVentasRealizadasVendedor.getModel();
        limpiarTabla();

        double totalVentas = 0;

        for (VentaDTO venta : ventas) {
            String automovil = venta.getMarcaAuto() + " " + venta.getLineaAuto() + " " + venta.getAnioAuto();
            String fecha = formatoFecha.format(venta.getFechaVenta());
            String total = formatoMoneda.format(venta.getTotalPagar());

            Object[] fila = {
                venta.getNumeroFactura(),
                fecha,
                venta.getCodigoAuto(),
                automovil,
                venta.getNombreCliente(),
                total
            };

            modelo.addRow(fila);
            totalVentas += venta.getTotalPagar();
        }

        vista.txtTotalVentasVendedor.setText(formatoMoneda.format(totalVentas));
        vista.txtCantidadVentas.setText(String.valueOf(ventas.size()));
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVentasRealizadasVendedor.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarCampos() {
        vista.txtIDVendedor.setText("");
        vista.txtNombreVendedor.setText("");
        vista.txtProfesionVendedor.setText("");
        vista.txtTotalVentasVendedor.setText("");
        vista.txtCantidadVentas.setText("");
        limpiarTabla();
        vista.txtIDVendedor.requestFocus();
    }
}
