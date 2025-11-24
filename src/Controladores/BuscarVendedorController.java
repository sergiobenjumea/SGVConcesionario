package Controladores;

import modelo.dao.VendedorDAO;
import modelo.dao.VentaDAO;
import modelo.dto.VendedorDTO;
import modelo.dto.VentaDTO;
import modelo.util.Formato;
import vistas.UIBuscarporVendedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BuscarVendedorController implements ActionListener {

    private UIBuscarporVendedor view;
    private VendedorDAO vendedorDAO;
    private VentaDAO ventaDAO;
    private ButtonGroup grupoRadio;

    public BuscarVendedorController(UIBuscarporVendedor view) {
        this.view = view;
        this.vendedorDAO = new VendedorDAO();
        this.ventaDAO = new VentaDAO();

        this.view.setLocationRelativeTo(null);
        
        // 1. Agrupar RadioButtons
        grupoRadio = new ButtonGroup();
        grupoRadio.add(view.rbbuscarporID);
        grupoRadio.add(view.rbbuscarporNombre);
        
        // Selección por defecto
        view.rbbuscarporID.setSelected(true);

        // Listeners de Botones
        this.view.btnbuscar.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
        
        // Nota: Ya no necesitamos listeners en los radiobuttons para bloquear cajas
        // porque ahora solo hay una caja unificada.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnbuscar) {
            realizarBusqueda();
        } else if (e.getSource() == view.btnLimpiar) {
            limpiar();
        }
    }

    private void realizarBusqueda() {
        // 1. LEER LA ÚNICA CAJA DE BÚSQUEDA
        String textoBusqueda = view.txtidonombre.getText().trim();
        
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, ingrese el dato a buscar.");
            return;
        }

        VendedorDTO vendedorEncontrado = null;

        // 2. DECIDIR EL TIPO DE BÚSQUEDA
        if (view.rbbuscarporID.isSelected()) {
            // Búsqueda Exacta por ID
            vendedorEncontrado = vendedorDAO.buscarPorIdentificacion(textoBusqueda);
        } else {
            // Búsqueda Flexible por Nombre
            vendedorEncontrado = vendedorDAO.buscarPorNombre(textoBusqueda);
        }

        // 3. MOSTRAR RESULTADOS
        if (vendedorEncontrado != null) {
            // Llenar Panel de Información (Resultados)
            view.txtIDVendedor.setText(vendedorEncontrado.getIdentificacion()); 
            view.txtNombreVendedor.setText(vendedorEncontrado.getNombre());
            view.txtProfesionVendedor.setText(vendedorEncontrado.getProfesion());

            // Llenar Tabla de Ventas
            llenarTablaVentas(vendedorEncontrado.getId());
            
        } else {
            JOptionPane.showMessageDialog(view, "No se encontró ningún vendedor con ese criterio.");
            limpiarResultados();
        }
    }

    private void llenarTablaVentas(int idVendedor) {
        List<VentaDTO> lista = ventaDAO.listarPorVendedor(idVendedor);
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("No. Factura");
        model.addColumn("Fecha");
        model.addColumn("Marca");
        model.addColumn("Línea");
        model.addColumn("Año");
        model.addColumn("Cliente");
        model.addColumn("Total");

        double sumaTotal = 0;

        for (VentaDTO v : lista) {
            Object[] fila = new Object[7];
            fila[0] = v.getNumeroFactura();
            fila[1] = v.getFechaVenta();
            fila[2] = v.getNombreMarca();
            fila[3] = v.getNombreLinea();
            fila[4] = v.getAnioAuto();
            fila[5] = v.getNombreCliente();
            fila[6] = Formato.moneda(v.getTotalPagar());
            
            model.addRow(fila);
            sumaTotal += v.getTotalPagar();
        }
        view.tblVentasRealizadas.setModel(model);

        // Estadísticas
        view.txtCantVentas.setText(String.valueOf(lista.size()));
        view.txtTotalVentas.setText(Formato.moneda(sumaTotal));
    }

    private void limpiar() {
        view.txtidonombre.setText(""); // Limpiamos la única caja de búsqueda
        view.rbbuscarporID.setSelected(true);
        limpiarResultados();
    }

    private void limpiarResultados() {
        view.txtIDVendedor.setText(""); 
        view.txtNombreVendedor.setText("");
        view.txtProfesionVendedor.setText("");
        view.txtCantVentas.setText("");
        view.txtTotalVentas.setText("");
        view.tblVentasRealizadas.setModel(new DefaultTableModel());
    }
}