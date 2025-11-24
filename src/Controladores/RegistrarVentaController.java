package Controladores;

import modelo.dao.*;
import modelo.dto.*;
import modelo.util.Formato;
import modelo.util.GeneradorPDF;
import modelo.util.ItemCombo;
import vistas.UIRegistrarventa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter; // Importante para detectar cambios
import java.awt.event.FocusEvent;   // Importante para detectar cambios
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistrarVentaController implements ActionListener {

    private UIRegistrarventa view;
    
    private VentaDAO ventaDAO;
    private VendedorDAO vendedorDAO;
    private ClienteDAO clienteDAO;
    private AutomovilDAO autoDAO;
    private FormasPagoDAO pagoDAO;

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
        
        cargarFechaYFactura();
        cargarFormasPago();
        listarVentas();

        // --- LISTENERS (BOTONES) ---
        this.view.btnConsultarVendedor.addActionListener(this);
        this.view.btnConsultarCliente.addActionListener(this);
        this.view.btnConsultarAuto.addActionListener(this);
        // Ya no existe btnCalcularTotal, lo borramos de aquí
        this.view.btnRegistrarVenta.addActionListener(this);
        
        // --- LISTENER AUTOMÁTICO DE PRECIO ---
        // Esto reemplaza al botón: Al salir de la caja de texto, calcula solo.
        this.view.txtPrecioBase.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calcularTotal();
            }
        });
        
        // También podemos hacer que calcule al dar Enter en la caja
        this.view.txtPrecioBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });
    }

    private void cargarFechaYFactura() {
        this.view.txtFecha.setText(LocalDate.now().toString());
        this.view.txtNoFactura.setText(ventaDAO.obtenerSiguienteFactura());
    }

    private void cargarFormasPago() {
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
        } 
        // Eliminamos el 'else if' del botón calcular
        else if (e.getSource() == view.btnRegistrarVenta) {
            registrarVenta();
        }
    }

    // --- BÚSQUEDAS ---

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
        try {
            int id = Integer.parseInt(view.txtCodigoAuto.getText());
            AutomovilDTO a = autoDAO.buscarPorId(id);
            
            if (a != null) {
                // --- VALIDACIÓN DE ESTADO (EL CANDADO) ---
                if (a.getEstado().equalsIgnoreCase("Vendido")) {
                    JOptionPane.showMessageDialog(view, "⛔ ¡ERROR! Este vehículo ya figura como VENDIDO.", "Vehículo no disponible", JOptionPane.ERROR_MESSAGE);
                    limpiarCamposAuto(); // Método opcional para limpiar si había algo escrito
                    return; // DETIENE LA EJECUCIÓN AQUÍ
                }
                // -----------------------------------------
                view.txtMarca.setText(a.getNombreMarca());
                view.txtLinea.setText(a.getNombreLinea());
                view.txtAnio.setText(String.valueOf(a.getAnio()));
                view.txtColorAuto.setText(a.getColor());
                view.txtTipoMotor.setText(a.getNombreMotor());
                
                // Llenar precio CRUDO para editar
                view.txtPrecioBase.setText(String.valueOf(a.getPrecioBase())); 
                
                idAutoEncontrado = a.getId();
                
                
                // Calcular y llenar los formateados automáticamente
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
            // Leemos el precio crudo que el usuario puede editar
            double precio = Double.parseDouble(view.txtPrecioBase.getText());
            
            // Actualizamos la caja "espejo" con formato bonito
            view.txtPrecioBase1.setText(Formato.moneda(precio));
            
            double impuesto = precio * 0.15; 
            double iva = precio * 0.19;      
            double total = precio + impuesto + iva;

            view.txtImpoVenta.setText(Formato.moneda(impuesto));
            view.txtIVA.setText(Formato.moneda(iva));
            view.txtTotalPagar.setText(Formato.moneda(total));
        } catch (Exception e) {
            // Si el campo está vacío o inválido, no hacemos nada
        }
    }

    private void registrarVenta() {
        // Aseguramos que los cálculos estén frescos antes de guardar
        calcularTotal();

        if (idVendedorEncontrado == 0 || idClienteEncontrado == 0 || idAutoEncontrado == 0) {
            JOptionPane.showMessageDialog(view, "Debe buscar y seleccionar Vendedor, Cliente y Auto.");
            return;
        }
        ItemCombo pagoSel = (ItemCombo) view.cboxFormasPago.getSelectedItem();
        if (pagoSel == null || pagoSel.getId() == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione Forma de Pago");
            return;
        }

        VentaDTO venta = new VentaDTO();
        venta.setNumeroFactura(view.txtNoFactura.getText());
        venta.setFechaVenta(Date.valueOf(LocalDate.now()));
        venta.setIdVendedor(idVendedorEncontrado);
        venta.setIdCliente(idClienteEncontrado);
        venta.setIdAuto(idAutoEncontrado);
        venta.setIdFormaPago(pagoSel.getId());
        
        try {
            // Leemos valores numéricos (usando remover para los formateados)
            venta.setPrecioBase(Double.parseDouble(view.txtPrecioBase.getText()));
            venta.setImpuesto(Formato.remover(view.txtImpoVenta.getText()));
            venta.setIva(Formato.remover(view.txtIVA.getText()));
            venta.setTotalPagar(Formato.remover(view.txtTotalPagar.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error en los valores numéricos. Verifique el precio.");
            return;
        }

        if (ventaDAO.registrar(venta)) {
            JOptionPane.showMessageDialog(view, "¡Venta Registrada!");
            
            // --- PREPARAR PDF ---
            venta.setNombreVendedor(view.txtNombreVendedor.getText());
            venta.setNombreCliente(view.txtNombreCliente.getText());
            venta.setNombreFormaPago(pagoSel.toString());
            String descAuto = view.txtMarca.getText() + " " + view.txtLinea.getText() + " " + 
                              view.txtAnio.getText() + " - " + view.txtColorAuto.getText();
            venta.setDescripcionAuto(descAuto);
            
            // Generar PDF pasando la vista completa
            GeneradorPDF pdf = new GeneradorPDF();
            pdf.generarFactura(venta, view);
            
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
            fila[5] = Formato.moneda(v.getTotalPagar());
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
        view.txtPrecioBase1.setText("");
        
        view.txtImpoVenta.setText("");
        view.txtIVA.setText("");
        view.txtTotalPagar.setText("");
        view.cboxFormasPago.setSelectedIndex(0);
    }
    private void limpiarCamposAuto() {
        view.txtMarca.setText("");
        view.txtLinea.setText("");
        view.txtAnio.setText("");
        view.txtColorAuto.setText("");
        view.txtTipoMotor.setText("");
        view.txtPrecioBase.setText("");
        view.txtPrecioBase1.setText("");
        idAutoEncontrado = 0;
    }
}