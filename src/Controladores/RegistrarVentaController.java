package Controladores;

import modelo.dao.*; // Importamos todos los DAOs
import modelo.dto.*; // Importamos todos los DTOs
import modelo.util.ItemCombo;
import vistas.UIRegistrarventa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import modelo.util.Formato;
import modelo.util.GeneradorPDF;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistrarVentaController implements ActionListener {

    private UIRegistrarventa view;
    
    // Instanciamos todos los DAOs necesarios
    private VentaDAO ventaDAO;
    private VendedorDAO vendedorDAO;
    private ClienteDAO clienteDAO;
    private AutomovilDAO autoDAO;
    private FormasPagoDAO pagoDAO;

    // Variables temporales para guardar los IDs encontrados
    private int idVendedorEncontrado = 0;
    private int idClienteEncontrado = 0;
    private int idAutoEncontrado = 0;

    public RegistrarVentaController(UIRegistrarventa view) {
        this.view = view;
        this.ventaDAO = new VentaDAO();
        this.vendedorDAO = new VendedorDAO();
        this.clienteDAO = new ClienteDAO();
        this.autoDAO = new AutomovilDAO();
        this.pagoDAO = new FormasPagoDAO();

        this.view.setLocationRelativeTo(null);
        
        // Carga Inicial
        cargarFechaYFactura();
        cargarFormasPago();
        listarVentas();

        // Listeners
        this.view.btnConsultarVendedor.addActionListener(this);
        this.view.btnConsultarCliente.addActionListener(this);
        this.view.btnConsultarAuto.addActionListener(this);
        this.view.btnCalcularTotal.addActionListener(this);
        this.view.btnRegistrarVenta.addActionListener(this);
    }

    private void cargarFechaYFactura() {
        this.view.txtFecha.setText(LocalDate.now().toString());
        this.view.txtNoFactura.setText(ventaDAO.obtenerSiguienteFactura());
    }

    private void cargarFormasPago() {
        // Obtenemos formas de pago y las metemos al combo
        List<FormasPagoDTO> lista = pagoDAO.listar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(new ItemCombo(0, "Seleccione..."));
        for (FormasPagoDTO fp : lista) {
            model.addElement(new ItemCombo(fp.getId(), fp.getNombre()));
        }
        view.cboxFormasPago.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnConsultarVendedor) {
            buscarVendedor();
        } else if (e.getSource() == view.btnConsultarCliente) {
            buscarCliente();
        } else if (e.getSource() == view.btnConsultarAuto) {
            buscarAuto();
        } else if (e.getSource() == view.btnCalcularTotal) {
            calcularTotal();
        } else if (e.getSource() == view.btnRegistrarVenta) {
            registrarVenta();
        }
    }

    // --- MÉTODOS DE BÚSQUEDA ---

    private void buscarVendedor() {
        String id = view.txtIDVendedor.getText();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(view, "Ingrese ID Vendedor"); return; }
        
        VendedorDTO v = vendedorDAO.buscarPorIdentificacion(id);
        if (v != null) {
            view.txtNombreVendedor.setText(v.getNombre());
            view.txtProfesionVendedor.setText(v.getProfesion());
            idVendedorEncontrado = v.getId();
        } else {
            JOptionPane.showMessageDialog(view, "Vendedor no encontrado");
        }
    }

    private void buscarCliente() {
        String id = view.txtIDCliente.getText();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(view, "Ingrese ID Cliente"); return; }
        
        ClienteDTO c = clienteDAO.buscarPorIdentificacion(id);
        if (c != null) {
            view.txtNombreCliente.setText(c.getNombre());
            view.txtEdadCliente.setText(String.valueOf(c.getEdad()));
            view.txteMailCliente.setText(c.getEmail());
            idClienteEncontrado = c.getId();
        } else {
            JOptionPane.showMessageDialog(view, "Cliente no encontrado");
        }
    }

    private void buscarAuto() {
        // En la vista dice "Código Auto", pero usaremos el ID numérico por ahora
        try {
            int id = Integer.parseInt(view.txtCodigoAuto.getText());
            AutomovilDTO a = autoDAO.buscarPorId(id);
            if (a != null) {
                view.txtMarca.setText(a.getNombreMarca());
                view.txtLinea.setText(a.getNombreLinea());
                view.txtAnio.setText(String.valueOf(a.getAnio()));
                view.txtColorAuto.setText(a.getColor());
                view.txtTipoMotor.setText(a.getNombreMotor());
                view.txtPrecioBase.setText(String.valueOf(a.getPrecioBase()));
                idAutoEncontrado = a.getId();
                
                // Calculamos totales automáticamente al encontrar el auto
                calcularTotal();
            } else {
                JOptionPane.showMessageDialog(view, "Automóvil no encontrado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Ingrese un ID numérico válido para el auto");
        }
    }

    private void calcularTotal() {
        try {
            // El precio base suele ser ingresado manualmente sin formato, lo leemos normal
            double precio = Double.parseDouble(view.txtPrecioBase.getText());
            
            double impuesto = precio * 0.15; 
            double iva = precio * 0.19;      
            double total = precio + impuesto + iva;

            // --- CAMBIO: Mostramos con formato en los campos no editables ---
            view.txtImpoVenta.setText(Formato.moneda(impuesto));
            view.txtIVA.setText(Formato.moneda(iva));
            view.txtTotalPagar.setText(Formato.moneda(total));
            // ---------------------------------------------------------------
        } catch (Exception e) {
            // Ignorar
        }
    }

    private void registrarVenta() {
        // ... (Tus validaciones iniciales de IDs y Combos las dejas igual) ...
        // ...
        if (idVendedorEncontrado == 0 || idClienteEncontrado == 0 || idAutoEncontrado == 0) {
             JOptionPane.showMessageDialog(view, "Debe buscar y seleccionar Vendedor, Cliente y Auto.");
             return;
        }
        ItemCombo pagoSel = (ItemCombo) view.cboxFormasPago.getSelectedItem();
        if (pagoSel == null || pagoSel.getId() == 0) {
             JOptionPane.showMessageDialog(view, "Seleccione Forma de Pago");
             return;
        }

        // Crear Objeto
        VentaDTO venta = new VentaDTO();
        venta.setNumeroFactura(view.txtNoFactura.getText());
        venta.setFechaVenta(Date.valueOf(LocalDate.now()));
        venta.setIdVendedor(idVendedorEncontrado);
        venta.setIdCliente(idClienteEncontrado);
        venta.setIdAuto(idAutoEncontrado);
        venta.setIdFormaPago(pagoSel.getId());
        
        // --- CAMBIO CRÍTICO: Usamos Formato.remover() para guardar ---
        // Leemos el precio base normal (usuario lo escribe)
        venta.setPrecioBase(Double.parseDouble(view.txtPrecioBase.getText())); 
        
        // Leemos los campos calculados (que tienen signo $) usando remover()
        venta.setImpuesto(Formato.remover(view.txtImpoVenta.getText()));
        venta.setIva(Formato.remover(view.txtIVA.getText()));
        venta.setTotalPagar(Formato.remover(view.txtTotalPagar.getText()));
        // -------------------------------------------------------------

        if (ventaDAO.registrar(venta)) {
            JOptionPane.showMessageDialog(view, "¡Venta Registrada!");
            // --- AQUÍ GENERAMOS EL PDF ---
            // 1. Completamos los datos visuales que faltan en el DTO para el PDF
            venta.setNombreVendedor(view.txtNombreVendedor.getText());
            venta.setNombreCliente(view.txtNombreCliente.getText());
            venta.setNombreFormaPago(pagoSel.toString()); // ItemCombo devuelve nombre en toString
            
            // Armamos la descripción del auto (Marca + Linea + Color)
            String descAuto = view.txtMarca.getText() + " " + view.txtLinea.getText() + " " + view.txtColorAuto.getText();
            venta.setDescripcionAuto(descAuto);
            
            // 2. Llamamos al generador
            GeneradorPDF pdf = new GeneradorPDF();
            pdf.generarFactura(venta);
            // -----------------------------
            listarVentas();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(view, "Error al registrar venta");
        }
    }
    private void listarVentas() {
        List<VentaDTO> lista = ventaDAO.listar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No. Factura");
        model.addColumn("Fecha");
        model.addColumn("Vendedor");
        model.addColumn("Auto");
        model.addColumn("Cliente");
        model.addColumn("Total");
        
        for (VentaDTO v : lista) {
            Object[] fila = new Object[6];
            fila[0] = v.getNumeroFactura();
            fila[1] = v.getFechaVenta();
            fila[2] = v.getNombreVendedor();
            fila[3] = v.getDescripcionAuto();
            fila[4] = v.getNombreCliente();
            
            // --- CAMBIO: Formato visual para la tabla ---
            fila[5] = Formato.moneda(v.getTotalPagar());
            // --------------------------------------------
            
            model.addRow(fila);
        }
        view.tblVentasRealizadas.setModel(model);
    }

    private void limpiar() {
        cargarFechaYFactura();
        idVendedorEncontrado = 0;
        idClienteEncontrado = 0;
        idAutoEncontrado = 0;
        
        view.txtIDVendedor.setText("");
        view.txtNombreVendedor.setText("");
        view.txtProfesionVendedor.setText("");
        
        view.txtIDCliente.setText("");
        view.txtNombreCliente.setText("");
        view.txtEdadCliente.setText("");
        view.txteMailCliente.setText("");
        
        view.txtCodigoAuto.setText("");
        view.txtMarca.setText("");
        view.txtLinea.setText("");
        view.txtAnio.setText("");
        view.txtColorAuto.setText("");
        view.txtTipoMotor.setText("");
        view.txtPrecioBase.setText("");
        
        view.txtImpoVenta.setText("");
        view.txtIVA.setText("");
        view.txtTotalPagar.setText("");
        view.cboxFormasPago.setSelectedIndex(0);
    }
}