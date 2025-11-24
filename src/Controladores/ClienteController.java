package Controladores;

import modelo.dto.ClienteDTO;
import modelo.dao.ClienteDAO;
import modelo.util.ItemCombo;
import vistas.UICliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteController implements ActionListener {

    private UICliente view;
    private ClienteDAO dao;
    private int idClienteSeleccionado = 0; // Guardamos el ID del cliente que se clickeó en la tabla

    public ClienteController(UICliente view) {
        this.view = view;
        this.dao = new ClienteDAO();

        // Configuración visual
        this.view.setLocationRelativeTo(null);
        this.view.txtedadCliente.setEditable(false);

        // Cargar datos
        cargarTiposID();
        listarClientes();

        // Listeners de Botones
        this.view.btnGuardarCliente.addActionListener(this);
        this.view.btnConsultarTodosCliente.addActionListener(this);
        this.view.btnModificarCliente.addActionListener(this);
        this.view.btnEliminarCliente.addActionListener(this);
        
        // Listener de la Tabla (Para seleccionar fila al hacer clic)
        this.view.tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = view.tblClientes.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    // Recuperamos el ID oculto en la columna 0
                    idClienteSeleccionado = (int) view.tblClientes.getValueAt(fila, 0);
                    
                    // Llenamos las cajas de texto
                    view.txtIdentificacionCliente.setText(view.tblClientes.getValueAt(fila, 2).toString());
                    view.txtnombreCliente.setText(view.tblClientes.getValueAt(fila, 3).toString());
                    view.txtedadCliente.setText(view.tblClientes.getValueAt(fila, 4).toString());
                    view.txtemailCliente.setText(view.tblClientes.getValueAt(fila, 5).toString());
                    
                    // Intentamos poner la fecha de nacimiento (si está en la tabla oculta o visible)
                    // Nota: Para simplificar, la tabla muestra String, aquí asumimos formato
                    // En un caso real idealmente guardaríamos el objeto completo en el modelo
                }
            }
        });
    }

    private void cargarTiposID() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(dao.obtenerTiposID());
        view.cboxTipoIDCliente.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardarCliente) {
            guardarCliente();
        } else if (e.getSource() == view.btnConsultarTodosCliente) {
            listarClientes();
        } else if (e.getSource() == view.btnModificarCliente) {
            modificarCliente();
        } else if (e.getSource() == view.btnEliminarCliente) {
            eliminarCliente();
        }
    }

    // --- GUARDAR ---
    private void guardarCliente() {
        if (validarCampos()) {
            ClienteDTO cliente = armarClienteDesdeFormulario();
            if (cliente != null && dao.registrar(cliente)) {
                JOptionPane.showMessageDialog(view, "Registrado exitosamente");
                listarClientes();
                limpiar();
            }
        }
    }

    // --- MODIFICAR ---
    private void modificarCliente() {
        if (idClienteSeleccionado == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione un cliente de la tabla primero");
            return;
        }
        if (validarCampos()) {
            ClienteDTO cliente = armarClienteDesdeFormulario();
            if (cliente != null) {
                cliente.setId(idClienteSeleccionado); // Asignamos el ID que seleccionamos
                if (dao.actualizar(cliente)) {
                    JOptionPane.showMessageDialog(view, "Modificado exitosamente");
                    listarClientes();
                    limpiar();
                }
            }
        }
    }

    // --- ELIMINAR ---
    private void eliminarCliente() {
        if (idClienteSeleccionado == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione un cliente de la tabla primero");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "¿Está seguro de eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminar(idClienteSeleccionado)) {
                JOptionPane.showMessageDialog(view, "Eliminado exitosamente");
                listarClientes();
                limpiar();
            }
        }
    }

    // --- MÉTODOS AUXILIARES ---
    
    private boolean validarCampos() {
        if (view.txtIdentificacionCliente.getText().isEmpty() || view.txtnombreCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete los campos obligatorios");
            return false;
        }
        return true;
    }

    private ClienteDTO armarClienteDesdeFormulario() {
        try {
            ClienteDTO cliente = new ClienteDTO();
            ItemCombo tipoSel = (ItemCombo) view.cboxTipoIDCliente.getSelectedItem();
            
            // Fechas y Edad
            String fechaTexto = view.txtfechadeNacimientoCliente.getText(); 
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaJava = formato.parse(fechaTexto);
            java.sql.Date fechaSQL = new java.sql.Date(fechaJava.getTime());
            
            LocalDate nacimiento = fechaJava.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int edadCalculada = Period.between(nacimiento, LocalDate.now()).getYears();

            cliente.setIdTipoIdentificacion(tipoSel.getId());
            cliente.setIdentificacion(view.txtIdentificacionCliente.getText());
            cliente.setNombre(view.txtnombreCliente.getText());
            cliente.setFechaNacimiento(fechaSQL);
            cliente.setEdad(edadCalculada);
            cliente.setEmail(view.txtemailCliente.getText());
            
            return cliente;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(view, "Fecha inválida. Use formato dd/MM/yyyy");
            return null;
        }
    }

    private void listarClientes() {
        List<ClienteDTO> lista = dao.listar();
        DefaultTableModel model = new DefaultTableModel();
        
        // Estructura de la tabla (Incluimos ID oculto o visible para poder seleccionar)
        model.addColumn("ID"); // Columna 0
        model.addColumn("Tipo");
        model.addColumn("Identificación"); // Columna 2
        model.addColumn("Nombre"); // Columna 3
        model.addColumn("Edad"); // Columna 4
        model.addColumn("Email"); // Columna 5

        for (ClienteDTO c : lista) {
            Object[] fila = new Object[6];
            fila[0] = c.getId();
            fila[1] = c.getNombreTipoId();
            fila[2] = c.getIdentificacion();
            fila[3] = c.getNombre();
            fila[4] = c.getEdad();
            fila[5] = c.getEmail();
            model.addRow(fila);
        }
        view.tblClientes.setModel(model);
        
        // Opcional: Ocultar la columna ID si no quieres que se vea
        // view.tblClientes.getColumnModel().getColumn(0).setMinWidth(0);
        // view.tblClientes.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    private void limpiar() {
        idClienteSeleccionado = 0; // Reiniciamos selección
        view.txtIdentificacionCliente.setText("");
        view.txtnombreCliente.setText("");
        view.txtfechadeNacimientoCliente.setText("");
        view.txtedadCliente.setText("");
        view.txtemailCliente.setText("");
        view.cboxTipoIDCliente.setSelectedIndex(0);
    }
}