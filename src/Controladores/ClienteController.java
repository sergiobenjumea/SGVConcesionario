package Controladores;

import modelo.dao.ClienteDAO;
import modelo.dao.TipoIdentificacionDAO;
import modelo.dto.ClienteDTO;
import modelo.dto.TipoIdentificacionDTO;
import vistas.UICliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ClienteController implements ActionListener {

    private UICliente vista;
    private ClienteDAO clienteDAO;
    private TipoIdentificacionDAO tipoIdDAO;
    private ClienteDTO clienteActual;
    private DefaultTableModel modeloTabla;

    public ClienteController(UICliente vista) {
        this.vista = vista;
        this.clienteDAO = new ClienteDAO();
        this.tipoIdDAO = new TipoIdentificacionDAO();

        cargarTiposIdentificacion();

        modeloTabla = (DefaultTableModel) vista.tblClientes.getModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new String[]{
            "ID", "Tipo ID", "Identificación", "Nombre", "Edad", "Email"
        });

        // Listeners
        this.vista.btnGuardarCliente.addActionListener(this);
        this.vista.btnConsultarCliente.addActionListener(this);
        this.vista.btnModificarCliente.addActionListener(this);
        this.vista.btnEliminarCliente.addActionListener(this);
        this.vista.btnConsultarTodosCliente.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarCliente) registrarCliente();
        else if (e.getSource() == vista.btnConsultarCliente) consultarCliente();
        else if (e.getSource() == vista.btnModificarCliente) modificarCliente();
        else if (e.getSource() == vista.btnEliminarCliente) eliminarCliente();
        else if (e.getSource() == vista.btnConsultarTodosCliente) consultarTodos();
    }

    private void cargarTiposIdentificacion() {
        List<TipoIdentificacionDTO> tipos = tipoIdDAO.leerActivos();
        vista.cboxTipoIDCliente.removeAllItems();
        vista.cboxTipoIDCliente.addItem(null); // Opción vacía
        for (TipoIdentificacionDTO tipo : tipos) {
            vista.cboxTipoIDCliente.addItem(tipo);
        }
    }

    private void registrarCliente() {
        try {
            ClienteDTO cliente = new ClienteDTO();
            TipoIdentificacionDTO tipoSel = (TipoIdentificacionDTO) vista.cboxTipoIDCliente.getSelectedItem();
            if (tipoSel == null) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un tipo de identificación", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            cliente.setIdTipoIdentificacion(tipoSel.getIdTipoIdentificacion());
            cliente.setCodigoTipoIdentificacion(tipoSel.getCodigoTipo());

            cliente.setIdentificacion(vista.txtIdentificacionCliente.getText().trim());
            cliente.setNombre(vista.txtnombreCliente.getText().trim());
            cliente.setEmail(vista.txtemailCliente.getText().trim());

            // Fecha nacimiento
            String fechaText = vista.txtfechadeNacimientoCliente.getText().trim();
            if (fechaText.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese la fecha de nacimiento", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNac = sdf.parse(fechaText);
            cliente.setFechaNacimiento(fechaNac);

            int edad = calcularEdad(fechaNac);
            cliente.setEdad(edad);
            vista.txtedadCliente.setText(String.valueOf(edad));

            // Validación obligatorios
            if (cliente.getIdentificacion().isEmpty() || cliente.getNombre().isEmpty() || cliente.getEmail().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos obligatorios", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int resultado = clienteDAO.crear(cliente);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, "Cliente registrado exitosamente\nEdad: " + edad + " años", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(vista, 
                "La fecha debe estar en formato: dd/MM/yyyy\nVerifique que la fecha sea válida", 
                "Error de fecha", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarCliente() {
        String identificacion = vista.txtIdentificacionCliente.getText().trim();
        if (identificacion.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese la identificación a consultar", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ClienteDTO cliente = clienteDAO.leerPorIdentificacion(identificacion);

        if (cliente != null) {
            clienteActual = cliente;
            seleccionarTipoEnCombo(cliente.getIdTipoIdentificacion());
            vista.txtnombreCliente.setText(cliente.getNombre());
            vista.txtedadCliente.setText(String.valueOf(cliente.getEdad()));
            vista.txtemailCliente.setText(cliente.getEmail());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            vista.txtfechadeNacimientoCliente.setText(sdf.format(cliente.getFechaNacimiento()));

            JOptionPane.showMessageDialog(vista, "Cliente encontrado", "Consulta exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado con identificación: " + identificacion, "No encontrado", JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
        }
    }

    private void modificarCliente() {
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(vista, "Primero consulte un cliente para modificarlo", "Consulta requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de modificar este cliente?", "Confirmar modificación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                TipoIdentificacionDTO tipoSel = (TipoIdentificacionDTO) vista.cboxTipoIDCliente.getSelectedItem();
                clienteActual.setIdTipoIdentificacion(tipoSel.getIdTipoIdentificacion());
                clienteActual.setCodigoTipoIdentificacion(tipoSel.getCodigoTipo());
                clienteActual.setIdentificacion(vista.txtIdentificacionCliente.getText().trim());
                clienteActual.setNombre(vista.txtnombreCliente.getText().trim());
                clienteActual.setEmail(vista.txtemailCliente.getText().trim());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaNac = sdf.parse(vista.txtfechadeNacimientoCliente.getText().trim());
                clienteActual.setFechaNacimiento(fechaNac);

                int edad = calcularEdad(fechaNac);
                clienteActual.setEdad(edad);
                vista.txtedadCliente.setText(String.valueOf(edad));

                int resultado = clienteDAO.actualizar(clienteActual);
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(vista, "Cliente modificado exitosamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    clienteActual = null;
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al modificar cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error en los datos ingresados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarCliente() {
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(vista, "Primero consulte un cliente para eliminarlo", "Consulta requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar este cliente?\nNombre: " + clienteActual.getNombre() + "\nIdentificación: " + clienteActual.getIdentificacion(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            int resultado = clienteDAO.eliminar(clienteActual.getIdCliente());
            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, "Cliente eliminado exitosamente", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                clienteActual = null;
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void consultarTodos() {
        List<ClienteDTO> listaClientes = clienteDAO.leerTodos();
        modeloTabla.setRowCount(0);
        for (ClienteDTO cliente : listaClientes) {
            Object[] fila = {
                cliente.getIdCliente(),
                cliente.getCodigoTipoIdentificacion(),
                cliente.getIdentificacion(),
                cliente.getNombre(),
                cliente.getEdad(),
                cliente.getEmail()
            };
            modeloTabla.addRow(fila);
        }
        JOptionPane.showMessageDialog(vista, "Se encontraron " + listaClientes.size() + " clientes", "Consulta exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private int calcularEdad(Date fechaNacimiento) {
        java.util.Calendar fechaNac = java.util.Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);
        java.util.Calendar hoy = java.util.Calendar.getInstance();
        int edad = hoy.get(java.util.Calendar.YEAR) - fechaNac.get(java.util.Calendar.YEAR);
        int mesActual = hoy.get(java.util.Calendar.MONTH);
        int mesNacimiento = fechaNac.get(java.util.Calendar.MONTH);
        if (mesNacimiento > mesActual || (mesNacimiento == mesActual && fechaNac.get(java.util.Calendar.DAY_OF_MONTH) > hoy.get(java.util.Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }

    private void limpiarCampos() {
        vista.txtIdentificacionCliente.setText("");
        vista.txtnombreCliente.setText("");
        vista.txtedadCliente.setText("");
        vista.txtemailCliente.setText("");
        vista.txtfechadeNacimientoCliente.setText("");
        vista.cboxTipoIDCliente.setSelectedIndex(0);
        clienteActual = null;
    }

    private void seleccionarTipoEnCombo(int idTipo) {
        for (int i = 0; i < vista.cboxTipoIDCliente.getItemCount(); i++) {
            TipoIdentificacionDTO tipo = (TipoIdentificacionDTO) vista.cboxTipoIDCliente.getItemAt(i);
            if (tipo != null && tipo.getIdTipoIdentificacion() == idTipo) {
                vista.cboxTipoIDCliente.setSelectedIndex(i);
                break;
            }
        }
    }
}
