package Controladores;

import modelo.dao.VentaDAO;
import modelo.dto.VentaDTO;
import modelo.util.Formato;
import vistas.UIVendedorconMayorVenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VendedorMayorVentaController implements ActionListener {

    private UIVendedorconMayorVenta view;
    private VentaDAO ventaDAO;

    public VendedorMayorVentaController(UIVendedorconMayorVenta view) {
        this.view = view;
        this.ventaDAO = new VentaDAO();
        
        this.view.setLocationRelativeTo(null);
        this.view.txtIDVendedor.setEditable(true); // Permitimos escribir para filtrar si se desea

        // Listeners
        this.view.btnBuscarMayorVenta.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscarMayorVenta) {
            buscarMayorVenta();
        } else if (e.getSource() == view.btnLimpiar) {
            limpiar();
        }
    }

    private void buscarMayorVenta() {
        // Obtenemos el ID del campo (puede estar vacío)
        String filtroCedula = view.txtIDVendedor.getText().trim();
        
        // Llamamos al DAO
        VentaDTO venta = ventaDAO.obtenerMayorVenta(filtroCedula);
        
        if (venta != null) {
            // 1. Llenar Panel Superior (Datos Vendedor)
            // Nota: Si buscamos global, aquí aparecerá el vendedor que logró esa venta
            if (filtroCedula.isEmpty()) {
                view.txtIDVendedor.setText("Global (Todos)"); // Indicador visual
            }
            view.txtNombreVendedor.setText(venta.getNombreVendedor());
            view.txtProfesionVendedor.setText(venta.getProfesionVendedor());
            
            // 2. Llenar Panel Inferior (Detalle Venta)
            view.txtNoFactura.setText(venta.getNumeroFactura());
            view.txtFechaVenta.setText(venta.getFechaVenta().toString());
            view.txtIDCliente.setText(venta.getNombreCliente()); // Mostramos Nombre en vez de ID
            
            view.txtMarca.setText(venta.getNombreMarca());
            view.txtLinea.setText(venta.getNombreLinea());
            view.txtAnio.setText(String.valueOf(venta.getAnioAuto()));
            
            // 3. Llenar Valores Monetarios
            view.txtPrecioBase.setText(Formato.moneda(venta.getPrecioBase()));
            view.txtImpoventa.setText(Formato.moneda(venta.getImpuesto()));
            view.txtIVA.setText(Formato.moneda(venta.getIva()));
            view.txtTotalPagar.setText(Formato.moneda(venta.getTotalPagar()));
            
        } else {
            JOptionPane.showMessageDialog(view, "No se encontraron ventas registradas.");
            limpiar();
        }
    }

    private void limpiar() {
        view.txtIDVendedor.setText("");
        view.txtNombreVendedor.setText("");
        view.txtProfesionVendedor.setText("");
        
        view.txtNoFactura.setText("");
        view.txtFechaVenta.setText("");
        view.txtIDCliente.setText("");
        view.txtMarca.setText("");
        view.txtLinea.setText("");
        view.txtAnio.setText("");
        
        view.txtPrecioBase.setText("");
        view.txtImpoventa.setText("");
        view.txtIVA.setText("");
        view.txtTotalPagar.setText("");
    }
}