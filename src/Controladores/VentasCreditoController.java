package Controladores;

import modelo.dao.VentaDAO;
import modelo.dto.VentaDTO;
import modelo.util.Formato; // Tu clase para el signo pesos ($)
import vistas.UIVentasCreditoConcesionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentasCreditoController implements ActionListener {

    private UIVentasCreditoConcesionario view;
    private VentaDAO ventaDAO;

    public VentasCreditoController(UIVentasCreditoConcesionario view) {
        this.view = view;
        this.ventaDAO = new VentaDAO();

        this.view.setLocationRelativeTo(null);
        
        // Listeners de los botones
        this.view.btnCargarVentas.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCargarVentas) {
            cargarVentasCredito();
        } else if (e.getSource() == view.btnLimpiar) {
            limpiar();
        }
    }

    private void cargarVentasCredito() {
        List<VentaDTO> lista = ventaDAO.listarVentasCreditoConcesionario();
        
        // Configurar tabla con las columnas del diseño
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No. Factura");
        model.addColumn("Fecha");
        model.addColumn("Vendedor");
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
            fila[2] = v.getNombreVendedor();
            fila[3] = v.getNombreMarca();
            fila[4] = v.getNombreLinea();
            fila[5] = v.getAnioAuto();
            fila[6] = v.getNombreCliente();
            fila[7] = Formato.moneda(v.getTotalPagar());
            
            model.addRow(fila);
            
            sumaTotal += v.getTotalPagar();
        }
        view.tblVtasCreditoConcesionario.setModel(model);

        // Actualizar Estadísticas
        view.txtTotalCantOperaciones.setText(String.valueOf(lista.size()));
        view.txtTotalVtasCredConcesionario.setText(Formato.moneda(sumaTotal));
        
        if(lista.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay ventas registradas bajo modalidad Crédito Directo.");
        }
    }

    private void limpiar() {
        view.tblVtasCreditoConcesionario.setModel(new DefaultTableModel());
        view.txtTotalCantOperaciones.setText("");
        view.txtTotalVtasCredConcesionario.setText("");
    }
}