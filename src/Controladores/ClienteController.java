package Controladores;

import modelo.dao.ClienteDAO;
import modelo.dao.TipoIdentificacionDAO;
import modelo.dto.ClienteDTO;
import modelo.dto.TipoIdentificacionDTO;
import vistas.UICliente;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Controlador para UICliente
 * Con tipos de identificación desde base de datos
 */
public class ClienteController implements ActionListener {
    
    private UICliente vista;
    private ClienteDAO modelo;
    private TipoIdentificacionDAO tipoIdDAO;
    private ClienteDTO clienteActual;
    private DefaultTableModel modeloTabla;
    
    public ClienteController(UICliente vista) {
        this.vista = vista;
        this.modelo = new ClienteDAO();
        this.tipoIdDAO = new TipoIdentificacionDAO();
        
        // Cargar tipos de identificación desde la BD
        cargarTiposIdentificacion();
        
        // Inicializar modelo de la tabla
        modeloTabla = (DefaultTableModel) vista.tblClientes.getModel();
        
        // Limpiar la tabla y configurar columnas
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new String[]{
            "ID", "Tipo ID", "Identificación", "Nombre", "Edad", "Email"
        });
        
        // Agregar listeners a los botones
        this.vista.btnGuardarCliente.addActionListener(this);
        this.vista.btnConsultarCliente.addActionListener(this);
        this.vista.btnModificarCliente.addActionListener(this);
        this.vista.btnEliminarCliente.addActionListener(this);
        this.vista.btnConsultarTodosCliente.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarCliente) {
            registrarCliente();
        } else if (e.getSource() == vista.btnConsultarCliente) {
            consultarCliente();
        } else if (e.getSource() == vista.btnModificarCliente) {
            modificarCliente();
        } else if (e.getSource() == vista.btnEliminarCliente) {
            eliminarCliente();
        } else if (e.getSource() == vista.btnConsultarTodosCliente) {
            consultarTodos();
        }
    }
    
    // ========== CARGAR TIPOS DE IDENTIFICACIÓN ==========
    private void cargarTiposIdentificacion() {
        List<TipoIdentificacionDTO> tipos = tipoIdDAO.leerActivos();
        
        vista.cboxTipoIDCliente.removeAllItems();
        vista.cboxTipoIDCliente.addItem(""); // Opción vacía
        
        for (TipoIdentificacionDTO tipo : tipos) {
            vista.cboxTipoIDCliente.addItem(tipo.getCodigo());
        }
        
        System.out.println("✅ Cargados " + tipos.size() + " tipos de identificación");
    }
    
    // ========== REGISTRAR CLIENTE ==========
    private void registrarCliente() {
        try {
            ClienteDTO cliente = new ClienteDTO();
            
            // Extraer datos de la vista
            String tipoID = (String) vista.cboxTipoIDCliente.getSelectedItem();
            if (tipoID == null || tipoID.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Seleccione un tipo de identificación", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            cliente.setTipoIdentificacion(tipoID);
            cliente.setIdentificacion(vista.txtIdentificacionCliente.getText().trim());
            cliente.setNombre(vista.txtnombreCliente.getText().trim());
            cliente.setEmail(vista.txtemailCliente.getText().trim());
            
            // Convertir fecha (formato: dd/mm/aaaa)
            String fechaText = vista.txtfechadeNacimientoCliente.getText().trim();
            if (fechaText.isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Ingrese la fecha de nacimiento", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Validación estricta
            Date fechaNac = sdf.parse(fechaText);
            cliente.setFechaNacimiento(fechaNac);
            
            // *** CALCULAR EDAD AUTOMÁTICAMENTE ***
            int edad = calcularEdad(fechaNac);
            cliente.setEdad(edad);
            
            // Mostrar la edad calculada en el campo
            vista.txtedadCliente.setText(String.valueOf(edad));
            
            // Validar campos obligatorios
            if (cliente.getIdentificacion().isEmpty() || cliente.getNombre().isEmpty() || 
                cliente.getEmail().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Complete todos los campos obligatorios", 
                    "Datos incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Llamar al DAO para insertar
            int resultado = modelo.crear(cliente);
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, 
                    "Cliente registrado exitosamente\n" +
                    "Edad calculada: " + edad + " años", 
                    "Registro exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Error al registrar cliente", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(vista, 
                "La fecha debe estar en formato: dd/mm/aaaa (ej: 15/05/1990)\n" +
                "Verifique que la fecha sea válida", 
                "Error de fecha", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== CONSULTAR CLIENTE ==========
    private void consultarCliente() {
        String identificacion = vista.txtIdentificacionCliente.getText().trim();
        
        if (identificacion.isEmpty()) {
            JOptionPane.showMessageDialog(vista, 
                "Ingrese la identificación a consultar", 
                "Campo vacío", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ClienteDTO cliente = modelo.leerPorIdentificacion(identificacion);
        
        if (cliente != null) {
            clienteActual = cliente;
            
            vista.cboxTipoIDCliente.setSelectedItem(cliente.getTipoIdentificacion());
            vista.txtnombreCliente.setText(cliente.getNombre());
            vista.txtedadCliente.setText(String.valueOf(cliente.getEdad()));
            vista.txtemailCliente.setText(cliente.getEmail());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            vista.txtfechadeNacimientoCliente.setText(sdf.format(cliente.getFechaNacimiento()));
            
            JOptionPane.showMessageDialog(vista, 
                "Cliente encontrado", 
                "Consulta exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, 
                "Cliente no encontrado con identificación: " + identificacion, 
                "No encontrado", 
                JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
        }
    }
    
    // ========== MODIFICAR CLIENTE ==========
    private void modificarCliente() {
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(vista, 
                "Primero consulte un cliente para modificarlo", 
                "Consulta requerida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro de modificar este cliente?", 
            "Confirmar modificación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                clienteActual.setTipoIdentificacion((String) vista.cboxTipoIDCliente.getSelectedItem());
                clienteActual.setIdentificacion(vista.txtIdentificacionCliente.getText().trim());
                clienteActual.setNombre(vista.txtnombreCliente.getText().trim());
                clienteActual.setEmail(vista.txtemailCliente.getText().trim());
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaNac = sdf.parse(vista.txtfechadeNacimientoCliente.getText().trim());
                clienteActual.setFechaNacimiento(fechaNac);
                
                // Recalcular edad
                int edad = calcularEdad(fechaNac);
                clienteActual.setEdad(edad);
                vista.txtedadCliente.setText(String.valueOf(edad));
                
                int resultado = modelo.actualizar(clienteActual);
                
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(vista, 
                        "Cliente modificado exitosamente", 
                        "Modificación exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    clienteActual = null;
                } else {
                    JOptionPane.showMessageDialog(vista, 
                        "Error al modificar cliente", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, 
                    "Error en los datos ingresados: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== ELIMINAR CLIENTE ==========
    private void eliminarCliente() {
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(vista, 
                "Primero consulte un cliente para eliminarlo", 
                "Consulta requerida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro de eliminar este cliente?\n" +
            "Nombre: " + clienteActual.getNombre() + "\n" +
            "Identificación: " + clienteActual.getIdentificacion(), 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            int resultado = modelo.eliminar(clienteActual.getIdCliente());
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, 
                    "Cliente eliminado exitosamente", 
                    "Eliminación exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                clienteActual = null;
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Error al eliminar cliente", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== CONSULTAR TODOS ==========
    private void consultarTodos() {
        List<ClienteDTO> listaClientes = modelo.leerTodos();
        
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(vista, 
                "No hay clientes registrados", 
                "Sin registros", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        // Llenar tabla
        for (ClienteDTO cliente : listaClientes) {
            Object[] fila = {
                cliente.getIdCliente(),
                cliente.getTipoIdentificacion(),
                cliente.getIdentificacion(),
                cliente.getNombre(),
                cliente.getEdad(),
                cliente.getEmail()
            };
            modeloTabla.addRow(fila);
        }
        
        JOptionPane.showMessageDialog(vista, 
            "Se encontraron " + listaClientes.size() + " clientes", 
            "Consulta exitosa", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ========== MÉTODO AUXILIAR: Calcular edad ==========
    private int calcularEdad(Date fechaNacimiento) {
        java.util.Calendar fechaNac = java.util.Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);
        
        java.util.Calendar hoy = java.util.Calendar.getInstance();
        
        int edad = hoy.get(java.util.Calendar.YEAR) - fechaNac.get(java.util.Calendar.YEAR);
        
        // Ajustar si aún no ha cumplido años este año
        int mesActual = hoy.get(java.util.Calendar.MONTH);
        int mesNacimiento = fechaNac.get(java.util.Calendar.MONTH);
        
        if (mesNacimiento > mesActual) {
            edad--;
        } else if (mesNacimiento == mesActual) {
            int diaActual = hoy.get(java.util.Calendar.DAY_OF_MONTH);
            int diaNacimiento = fechaNac.get(java.util.Calendar.DAY_OF_MONTH);
            if (diaNacimiento > diaActual) {
                edad--;
            }
        }
        
        return edad;
    }
    
    // ========== MÉTODO AUXILIAR: Limpiar campos ==========
    private void limpiarCampos() {
        vista.txtIdentificacionCliente.setText("");
        vista.txtnombreCliente.setText("");
        vista.txtedadCliente.setText("");
        vista.txtemailCliente.setText("");
        vista.txtfechadeNacimientoCliente.setText("");
        vista.cboxTipoIDCliente.setSelectedIndex(0);
        clienteActual = null;
    }
}
