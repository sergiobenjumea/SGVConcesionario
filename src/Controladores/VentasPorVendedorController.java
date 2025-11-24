package Controladores;

import modelo.dao.VendedorDAO;
import modelo.dao.VentaDAO;
import modelo.dto.VendedorDTO;
import modelo.dto.VentaDTO;
import modelo.util.Formato;
import vistas.UIVentasporVendedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentasPorVendedorController implements ActionListener {

    private UIVentasporVendedor view;
    private VentaDAO ventaDAO;
    private VendedorDAO vendedorDAO;

    public VentasPorVendedorController(UIVentasporVendedor view) {
        this.view = view;
        this.ventaDAO = new VentaDAO();
        this.vendedorDAO = new VendedorDAO();

        this.view.setLocationRelativeTo(null);
        
        // Listeners
        this.view.btnBuscarVentasVendedor.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnBuscarVentasVendedor) {
            buscarVentas();
        } else if (e.getSource() == view.btnLimpiar) {
            limpiar();
        }
    }

    private void buscarVentas() {
        // 1. Obtener Cédula
        String cedula = view.txtIDVendedor.getText();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Ingrese el ID (Cédula) del Vendedor");
            return;
        }

        // 2. Buscar datos del Vendedor
        VendedorDTO vendedor = vendedorDAO.buscarPorIdentificacion(cedula);
        
        if (vendedor != null) {
            // Llenar datos de cabecera
            view.txtNombreVendedor.setText(vendedor.getNombre());
            view.txtProfesionVendedor.setText(vendedor.getProfesion());

            // 3. Buscar Ventas usando el ID interno (PK) del vendedor
            llenarTablaYTotales(vendedor.getId());
            
        } else {
            JOptionPane.showMessageDialog(view, "Vendedor no encontrado");
            limpiarResultados();
        }
    }

    private void llenarTablaYTotales(int idVendedor) {
        List<VentaDTO> lista = ventaDAO.listarPorVendedor(idVendedor);
        DefaultTableModel model = new DefaultTableModel();
        
        // Columnas según tu Vista
        model.addColumn("No. Factura");
        model.addColumn("Fecha");
        model.addColumn("Marca");
        model.addColumn("Línea");
        model.addColumn("Año");
        model.addColumn("Cliente");
        model.addColumn("Total");

        double sumaTotal = 0;

        for (VentaDTO v : lista) {
            Object[] fila = new Object[8];
            fila[0] = v.getNumeroFactura();
            fila[1] = v.getFechaVenta();
            fila[3] = v.getNombreMarca();
            fila[4] = v.getNombreLinea();
            fila[5] = v.getAnioAuto();
            fila[6] = v.getNombreCliente();
            fila[7] = Formato.moneda(v.getTotalPagar()); // Formato visual
            
            model.addRow(fila);
            
            // Acumular total
            sumaTotal += v.getTotalPagar();
        }
        view.tblVentasRealizadasVendedor.setModel(model);

        // 4. Llenar Totales en la parte inferior
        view.txtCantidadVentas.setText(String.valueOf(lista.size()));
        view.txtTotalVentasVendedor.setText(Formato.moneda(sumaTotal));
        
        if(lista.isEmpty()){
            JOptionPane.showMessageDialog(view, "Este vendedor no tiene ventas registradas.");
        }
    }

    private void limpiar() {
        view.txtIDVendedor.setText("");
        limpiarResultados();
    }

    private void limpiarResultados() {
        view.txtNombreVendedor.setText("");
        view.txtProfesionVendedor.setText("");
        view.txtCantidadVentas.setText("");
        view.txtTotalVentasVendedor.setText("");
        // Limpiar tabla
        view.tblVentasRealizadasVendedor.setModel(new DefaultTableModel());
    }
}