package Controladores;

import modelo.dao.*;
import modelo.dto.*;
import vistas.UIRegistrarventa;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modelo.util.GeneradorPDF;


/**
 * Controlador para UIRegistrarventa
 * Maneja el proceso completo de registro de ventas
 */
public class RegistrarVentaController implements ActionListener {
    
    private UIRegistrarventa vista;
    private VentaDAO ventaDAO;
    private ClienteDAO clienteDAO;
    private VendedorDAO vendedorDAO;
    private AutomovilDAO automovilDAO;
    private FormaPagoDAO formaPagoDAO;
    private DefaultTableModel modeloTabla;
    
    // Variables temporales para almacenar los datos consultados
    private ClienteDTO clienteSeleccionado;
    private VendedorDTO vendedorSeleccionado;
    private AutomovilDTO automovilSeleccionado;
    
    public RegistrarVentaController(UIRegistrarventa vista) {
        this.vista = vista;
        this.ventaDAO = new VentaDAO();
        this.clienteDAO = new ClienteDAO();
        this.vendedorDAO = new VendedorDAO();
        this.automovilDAO = new AutomovilDAO();
        this.formaPagoDAO = new FormaPagoDAO();
        
        // Inicializar la vista
        inicializarVista();
        
        // Agregar listeners
        this.vista.btnConsultarVendedor.addActionListener(this);
        this.vista.btnConsultarAuto.addActionListener(this);
        this.vista.btnConsultarCliente.addActionListener(this);
        this.vista.btnCalcularTotal.addActionListener(this);
        this.vista.btnRegistrarVenta.addActionListener(this);
    }
    
    private void inicializarVista() {
        // Configurar fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        vista.txtFecha.setText(sdf.format(new Date()));
        
        // Generar número de factura
        String numeroFactura = ventaDAO.generarNumeroFactura();
        vista.txtNoFactura.setText(numeroFactura);
        
        // Cargar formas de pago
        cargarFormasPago();
        
        // Configurar tabla
        modeloTabla = (DefaultTableModel) vista.tblVentasRealizadas.getModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new String[]{
            "No. Factura", "Fecha", "Vendedor", "Vehículo", "Cliente", "Total"
        });
        
        // Cargar ventas existentes
        cargarVentasRealizadas();
        
        System.out.println("✅ Vista de ventas inicializada correctamente");
    }
    
    private void cargarFormasPago() {
        List<FormaPagoDTO> formas = formaPagoDAO.leerTodos();
        
        vista.cboxFormasPago.removeAllItems();
        vista.cboxFormasPago.addItem(""); // Opción vacía
        
        for (FormaPagoDTO forma : formas) {
            vista.cboxFormasPago.addItem(forma.getCodigoForma());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnConsultarVendedor) {
            consultarVendedor();
        } else if (e.getSource() == vista.btnConsultarAuto) {
            consultarAutomovil();
        } else if (e.getSource() == vista.btnConsultarCliente) {
            consultarCliente();
        } else if (e.getSource() == vista.btnCalcularTotal) {
            calcularTotal();
        } else if (e.getSource() == vista.btnRegistrarVenta) {
            registrarVenta();
        }
    }
    
    // ========== CONSULTAR VENDEDOR ==========
    private void consultarVendedor() {
        String idVendedor = vista.txtIDVendedor.getText().trim();
        
        if (idVendedor.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Ingrese el ID del vendedor",
                "Campo vacío",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        VendedorDTO vendedor = vendedorDAO.leerPorIdentificacion(idVendedor);
        
        if (vendedor != null) {
            vendedorSeleccionado = vendedor;
            vista.txtNombreVendedor.setText(vendedor.getNombre());
            vista.txtProfesionVendedor.setText(vendedor.getProfesion());
            
            JOptionPane.showMessageDialog(vista,
                "Vendedor encontrado",
                "Consulta exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista,
                "Vendedor no encontrado",
                "Sin resultados",
                JOptionPane.WARNING_MESSAGE);
            limpiarVendedor();
        }
    }
    
    // ========== CONSULTAR AUTOMÓVIL ==========
    private void consultarAutomovil() {
        String codigo = vista.txtCodigoAuto.getText().trim();
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Ingrese el código del automóvil",
                "Campo vacío",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        AutomovilDTO auto = automovilDAO.leerPorCodigo(codigo);
        
        if (auto != null) {
            // Verificar que el automóvil esté disponible
            if (!"Disponible".equals(auto.getEstado())) {
                JOptionPane.showMessageDialog(vista,
                    "Este automóvil NO está disponible.\nEstado actual: " + auto.getEstado(),
                    "Automóvil no disponible",
                    JOptionPane.WARNING_MESSAGE);
                limpiarAutomovil();
                return;
            }
            
            automovilSeleccionado = auto;
            vista.txtMarca.setText(auto.getMarca());
            vista.txtColorAuto.setText(auto.getColor());
            vista.txtPrecioBase.setText(String.format("$%,.2f", auto.getPrecioBase()));
            vista.txtTipoMotor.setText(auto.getNombreTipoMotor());
            
            JOptionPane.showMessageDialog(vista,
                "Automóvil encontrado\nPrecio Total: $" + String.format("%,.2f", auto.getPrecioTotal()),
                "Consulta exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista,
                "Automóvil no encontrado",
                "Sin resultados",
                JOptionPane.WARNING_MESSAGE);
            limpiarAutomovil();
        }
    }
    
    // ========== CONSULTAR CLIENTE ==========
    private void consultarCliente() {
        String idCliente = vista.txtIDCliente.getText().trim();
        
        if (idCliente.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Ingrese el ID del cliente",
                "Campo vacío",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ClienteDTO cliente = clienteDAO.leerPorIdentificacion(idCliente);
        
        if (cliente != null) {
            clienteSeleccionado = cliente;
            vista.txtNombreCliente.setText(cliente.getNombre());
            vista.txtEdadCliente.setText(String.valueOf(cliente.getEdad()));
            vista.txteMailCliente.setText(cliente.getEmail());
            
            JOptionPane.showMessageDialog(vista,
                "Cliente encontrado",
                "Consulta exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista,
                "Cliente no encontrado",
                "Sin resultados",
                JOptionPane.WARNING_MESSAGE);
            limpiarCliente();
        }
    }
    
    // ========== CALCULAR TOTAL ==========
    private void calcularTotal() {
    // Validar que haya un automóvil seleccionado
    if (automovilSeleccionado == null) {
        JOptionPane.showMessageDialog(vista,
            "Primero debe consultar un automóvil",
            "Automóvil requerido",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Validar forma de pago
    String codigoFormaPago = (String) vista.cboxFormasPago.getSelectedItem();
    if (codigoFormaPago == null || codigoFormaPago.trim().isEmpty()) {
        JOptionPane.showMessageDialog(vista,
            "Seleccione una forma de pago",
            "Forma de pago requerida",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Ya NO necesitas buscar ni mostrar la descripción en un campo de texto
    // El usuario ve la forma de pago directamente en el combo
    
    // Los valores ya están calculados en el automóvil
    double impuesto = automovilSeleccionado.getImpuestoVenta();
    double iva = automovilSeleccionado.getIva();
    double total = automovilSeleccionado.getPrecioTotal();
    
    vista.txtImpoVenta.setText(String.format("$%,.2f", impuesto));
    vista.txtIVA.setText(String.format("$%,.2f", iva));
    vista.txtTotalPagar.setText(String.format("$%,.2f", total));
    
    JOptionPane.showMessageDialog(vista,
        "Total calculado correctamente\n\n" +
        "Precio Base: $" + String.format("%,.2f", automovilSeleccionado.getPrecioBase()) + "\n" +
        "Impuesto: $" + String.format("%,.2f", impuesto) + "\n" +
        "IVA: $" + String.format("%,.2f", iva) + "\n" +
        "TOTAL: $" + String.format("%,.2f", total),
        "Cálculo exitoso",
        JOptionPane.INFORMATION_MESSAGE);
}

    
    // ========== REGISTRAR VENTA ==========
    private void registrarVenta() {
        try {
            // Validaciones
            if (vendedorSeleccionado == null) {
                JOptionPane.showMessageDialog(vista,
                    "Debe consultar un vendedor",
                    "Vendedor requerido",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (automovilSeleccionado == null) {
                JOptionPane.showMessageDialog(vista,
                    "Debe consultar un automóvil",
                    "Automóvil requerido",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (clienteSeleccionado == null) {
                JOptionPane.showMessageDialog(vista,
                    "Debe consultar un cliente",
                    "Cliente requerido",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String codigoFormaPago = (String) vista.cboxFormasPago.getSelectedItem();
            if (codigoFormaPago == null || codigoFormaPago.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                    "Seleccione una forma de pago",
                    "Forma de pago requerida",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que se haya calculado el total
            if (vista.txtTotalPagar.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                    "Debe calcular el total antes de registrar",
                    "Total requerido",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Crear objeto VentaDTO
            VentaDTO venta = new VentaDTO();
            venta.setNumeroFactura(vista.txtNoFactura.getText().trim());
            venta.setFechaVenta(new Date());
            venta.setIdCliente(clienteSeleccionado.getIdCliente());
            venta.setIdVendedor(vendedorSeleccionado.getIdVendedor());
            venta.setIdAuto(automovilSeleccionado.getIdAuto());
            venta.setCodigoFormaPago(codigoFormaPago);
            venta.setPrecioBase(automovilSeleccionado.getPrecioBase());
            venta.setImpuestoVenta(automovilSeleccionado.getImpuestoVenta());
            venta.setIva(automovilSeleccionado.getIva());
            venta.setTotalPagar(automovilSeleccionado.getPrecioTotal());
            venta.setEstado("Activa");
            
            // Confirmar
            int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Confirmar registro de venta?\n\n" +
                "Factura: " + venta.getNumeroFactura() + "\n" +
                "Cliente: " + clienteSeleccionado.getNombre() + "\n" +
                "Vendedor: " + vendedorSeleccionado.getNombre() + "\n" +
                "Vehículo: " + automovilSeleccionado.getMarca() + " - " + automovilSeleccionado.getCodigo() + "\n" +
                "Total: $" + String.format("%,.2f", venta.getTotalPagar()),
                "Confirmar Venta",
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                int resultado = ventaDAO.crear(venta);
                
                if (resultado > 0) {
                   // Generar PDF automáticamente
                    GeneradorPDF generador = new GeneradorPDF();
                    String rutaPDF =generador.generarFactura(venta);
                    String mensaje = "✅ Venta registrada exitosamente\n\n" +
                    "Factura No: " + venta.getNumeroFactura() + "\n" +
                    "Total: $" + String.format("%,.2f", venta.getTotalPagar()) + "\n\n";
    
                    if (rutaPDF != null) {
                        mensaje += "✅ PDF generado en:\n" + rutaPDF;

                        // Preguntar si desea abrir el PDF
                        int respuesta = JOptionPane.showConfirmDialog(vista,
                            mensaje + "\n\n¿Desea abrir el PDF?",
                            "Venta Exitosa",
                            JOptionPane.YES_NO_OPTION);

                        if (respuesta == JOptionPane.YES_OPTION) {
                            generador.abrirPDF(rutaPDF);
                        }
                    } else {
                            mensaje += "⚠️ Error: No se pudo generar el PDF";
                            JOptionPane.showMessageDialog(vista, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                    limpiarTodo();
                    cargarVentasRealizadas();
                }else {
                    JOptionPane.showMessageDialog(vista,
                        "Error al registrar la venta",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                "Error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    // ========== CARGAR VENTAS REALIZADAS ==========
    private void cargarVentasRealizadas() {
        List<VentaDTO> ventas = ventaDAO.leerTodos();
        
        modeloTabla.setRowCount(0);
        
        for (VentaDTO venta : ventas) {
            Object[] fila = {
                venta.getNumeroFactura(),
                new SimpleDateFormat("dd/MM/yyyy").format(venta.getFechaVenta()),
                venta.getNombreVendedor(),
                venta.getCodigoAuto(),
                venta.getNombreCliente(),
                String.format("$%,.2f", venta.getTotalPagar())
            };
            modeloTabla.addRow(fila);
        }
    }
    
    // ========== MÉTODOS AUXILIARES DE LIMPIEZA ==========
    private void limpiarVendedor() {
        vista.txtIDVendedor.setText("");
        vista.txtNombreVendedor.setText("");
        vista.txtProfesionVendedor.setText("");
        vendedorSeleccionado = null;
    }
    
    private void limpiarAutomovil() {
        vista.txtCodigoAuto.setText("");
        vista.txtMarca.setText("");
        vista.txtColorAuto.setText("");
        vista.txtPrecioBase.setText("");
        vista.txtTipoMotor.setText("");
        automovilSeleccionado = null;
    }
    
    private void limpiarCliente() {
        vista.txtIDCliente.setText("");
        vista.txtNombreCliente.setText("");
        vista.txtEdadCliente.setText("");
        vista.txteMailCliente.setText("");
        clienteSeleccionado = null;
    }
    
    private void limpiarTodo() {
        limpiarVendedor();
        limpiarAutomovil();
        limpiarCliente();
        
        vista.cboxFormasPago.setSelectedIndex(0);
        vista.txtImpoVenta.setText("");
        vista.txtIVA.setText("");
        vista.txtTotalPagar.setText("");
        
        // Generar nuevo número de factura
        String nuevoNumero = ventaDAO.generarNumeroFactura();
        vista.txtNoFactura.setText(nuevoNumero);
    }
}
