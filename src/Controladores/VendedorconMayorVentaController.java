package Controladores;

import modelo.dao.VentaDAO;
import modelo.dto.VentaDTO;
import vistas.UIVendedorconMayorVenta;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class VendedorconMayorVentaController implements ActionListener {

    private UIVendedorconMayorVenta vista;
    private VentaDAO ventaDAO;
    private DecimalFormat formatoMoneda;
    private SimpleDateFormat formatoFecha;

    public VendedorconMayorVentaController(UIVendedorconMayorVenta vista) {
        this.vista = vista;
        this.ventaDAO = new VentaDAO();
        this.formatoMoneda = new DecimalFormat("$#,##0.00");
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        this.vista.btnBuscarMayorVenta.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarMayorVenta) {
            buscarVendedorMayorVenta();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void buscarVendedorMayorVenta() {
        VentaDTO mayorVenta = ventaDAO.obtenerVentaConMayorMonto();

        if (mayorVenta == null) {
            JOptionPane.showMessageDialog(vista,
                    "No se encontraron ventas registradas en el sistema",
                    "Sin ventas",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            return;
        }

        vista.txtIDVendedor.setText(String.valueOf(mayorVenta.getIdVendedor()));
        vista.txtNombreVendedor.setText(mayorVenta.getNombreVendedor());
        vista.txtProfesionVendedor.setText(mayorVenta.getProfesionVendedor());

        vista.txtNoFactura.setText(mayorVenta.getNumeroFactura());
        vista.txtFechaVenta.setText(formatoFecha.format(mayorVenta.getFechaVenta()));
        vista.txtIDCliente.setText(mayorVenta.getNombreCliente());

        // Mostrar marca, línea y año SEPARADOS
        vista.txtMarca.setText(mayorVenta.getMarcaAuto());
        vista.txtLinea.setText(mayorVenta.getLineaAuto());
        vista.txtAnio.setText(String.valueOf(mayorVenta.getAnioAuto()));

        vista.txtPrecioBase.setText(formatoMoneda.format(mayorVenta.getPrecioBase()));
        vista.txtImpoventa.setText(formatoMoneda.format(mayorVenta.getImpuestoVenta()));
        vista.txtIVA.setText(formatoMoneda.format(mayorVenta.getIva()));
        vista.txtTotalPagar.setText(formatoMoneda.format(mayorVenta.getTotalPagar()));

        JOptionPane.showMessageDialog(vista,
                "El vendedor con la mayor venta es:\n" +
                mayorVenta.getNombreVendedor() +
                "\nTotal de la venta: " + formatoMoneda.format(mayorVenta.getTotalPagar()),
                "Mayor Venta Encontrada",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        vista.txtIDVendedor.setText("");
        vista.txtNombreVendedor.setText("");
        vista.txtProfesionVendedor.setText("");
        vista.txtNoFactura.setText("");
        vista.txtFechaVenta.setText("");
        vista.txtIDCliente.setText("");
        vista.txtMarca.setText("");
        vista.txtLinea.setText("");
        vista.txtAnio.setText("");
        vista.txtPrecioBase.setText("");
        vista.txtImpoventa.setText("");
        vista.txtIVA.setText("");
        vista.txtTotalPagar.setText("");
    }
}
