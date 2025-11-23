package Controladores;

import modelo.dao.AutomovilDAO;
import modelo.dto.AutomovilDTO;
import vistas.UIAutomoviles;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para UIAutomoviles
 */
public class AutomovilesController implements ActionListener {
    
    private UIAutomoviles vista;
    private AutomovilDAO modelo;
    private AutomovilDTO autoActual;
    private DefaultTableModel modeloTabla;
    
    public AutomovilesController(UIAutomoviles vista) {
        this.vista = vista;
        this.modelo = new AutomovilDAO();
        
        // Inicializar modelo de la tabla
        modeloTabla = (DefaultTableModel) vista.tblAutomovil.getModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new String[]{
            "ID", "Código", "Marca", "Modelo", "Color", "Precio Base", "Tipo Motor", "Precio Total"
        });
        
        // Agregar listeners
        this.vista.btnGuardarAutomovil.addActionListener(this);
        this.vista.btnConsultarAutomovil.addActionListener(this);
        this.vista.btnModificarAutomovil.addActionListener(this);
        this.vista.btnEliminarAutomovil.addActionListener(this);
        this.vista.btnConsultarTodosAutomovil.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarAutomovil) {
            registrarAutomovil();
        } else if (e.getSource() == vista.btnConsultarAutomovil) {
            consultarAutomovil();
        } else if (e.getSource() == vista.btnModificarAutomovil) {
            modificarAutomovil();
        } else if (e.getSource() == vista.btnEliminarAutomovil) {
            eliminarAutomovil();
        } else if (e.getSource() == vista.btnConsultarTodosAutomovil) {
            consultarTodos();
        }
    }
    
    // ========== REGISTRAR AUTOMÓVIL ==========
    private void registrarAutomovil() {
        try {
            AutomovilDTO auto = new AutomovilDTO();
            
            auto.setCodigo(vista.txtCodigo.getText().trim());
            
            String marca = (String) vista.cboxMarca.getSelectedItem();
            if (marca == null || marca.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Seleccione una marca", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            auto.setMarca(marca);
            
            String modeloAuto = (String) vista.cboxModelo.getSelectedItem();
            if (modeloAuto == null || modeloAuto.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Seleccione un modelo (año)", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            auto.setModelo(modeloAuto);
            
            auto.setColor(vista.txtColor.getText().trim());
            
            String precioText = vista.txtPrecioBase.getText().trim();
            if (precioText.isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Ingrese el precio base", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            auto.setPrecioBase(Double.parseDouble(precioText));
            
            // Mapear tipo de motor a ID
            String tipoMotor = (String) vista.cboxTipoMotor.getSelectedItem();
            int idTipoMotor = 0;
            if ("Gasolina".equals(tipoMotor)) {
                idTipoMotor = 1;
            } else if ("Eléctrico".equals(tipoMotor)) {
                idTipoMotor = 2;
            } else if ("Híbrido".equals(tipoMotor)) {
                idTipoMotor = 3;
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Seleccione un tipo de motor", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            auto.setIdTipoMotor(idTipoMotor);
            
            // Validar campos obligatorios
            if (auto.getCodigo().isEmpty() || auto.getColor().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Complete todos los campos obligatorios", 
                    "Datos incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int resultado = modelo.crear(auto);
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, 
                    "Automóvil registrado exitosamente\n" +
                    "El impuesto, IVA y precio total se calcularon automáticamente", 
                    "Registro exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Error al registrar automóvil", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, 
                "El precio debe ser un número válido", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== CONSULTAR AUTOMÓVIL ==========
    private void consultarAutomovil() {
        String codigo = vista.txtCodigo.getText().trim();
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, 
                "Ingrese el código del automóvil a consultar", 
                "Campo vacío", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        AutomovilDTO auto = modelo.leerPorCodigo(codigo);
        
        if (auto != null) {
            autoActual = auto;
            
            vista.cboxMarca.setSelectedItem(auto.getMarca());
            vista.cboxModelo.setSelectedItem(auto.getModelo());
            vista.txtColor.setText(auto.getColor());
            vista.txtPrecioBase.setText(String.valueOf(auto.getPrecioBase()));
            vista.cboxTipoMotor.setSelectedItem(auto.getNombreTipoMotor());
            
            JOptionPane.showMessageDialog(vista, 
                "Automóvil encontrado\n" +
                "Precio Total: $" + String.format("%,.2f", auto.getPrecioTotal()), 
                "Consulta exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, 
                "Automóvil no encontrado con código: " + codigo, 
                "No encontrado", 
                JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
        }
    }
    
    // ========== MODIFICAR AUTOMÓVIL ==========
    private void modificarAutomovil() {
        if (autoActual == null) {
            JOptionPane.showMessageDialog(vista, 
                "Primero consulte un automóvil para modificarlo", 
                "Consulta requerida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro de modificar este automóvil?", 
            "Confirmar modificación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                autoActual.setCodigo(vista.txtCodigo.getText().trim());
                autoActual.setMarca((String) vista.cboxMarca.getSelectedItem());
                autoActual.setModelo((String) vista.cboxModelo.getSelectedItem());
                autoActual.setColor(vista.txtColor.getText().trim());
                autoActual.setPrecioBase(Double.parseDouble(vista.txtPrecioBase.getText().trim()));
                
                String tipoMotor = (String) vista.cboxTipoMotor.getSelectedItem();
                int idTipoMotor = 0;
                if ("Gasolina".equals(tipoMotor)) idTipoMotor = 1;
                else if ("Eléctrico".equals(tipoMotor)) idTipoMotor = 2;
                else if ("Híbrido".equals(tipoMotor)) idTipoMotor = 3;
                autoActual.setIdTipoMotor(idTipoMotor);
                
                int resultado = modelo.actualizar(autoActual);
                
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(vista, 
                        "Automóvil modificado exitosamente", 
                        "Modificación exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    autoActual = null;
                } else {
                    JOptionPane.showMessageDialog(vista, 
                        "Error al modificar automóvil", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, 
                    "Error: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== ELIMINAR AUTOMÓVIL ==========
    private void eliminarAutomovil() {
        if (autoActual == null) {
            JOptionPane.showMessageDialog(vista, 
                "Primero consulte un automóvil para eliminarlo", 
                "Consulta requerida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro de eliminar este automóvil?\n" +
            "Código: " + autoActual.getCodigo() + "\n" +
            "Marca: " + autoActual.getMarca(), 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            int resultado = modelo.eliminar(autoActual.getIdAuto());
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, 
                    "Automóvil eliminado exitosamente", 
                    "Eliminación exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                autoActual = null;
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Error: No se puede eliminar (puede estar vendido)", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== CONSULTAR TODOS ==========
    private void consultarTodos() {
        List<AutomovilDTO> listaAutos = modelo.leerTodos();
        
        if (listaAutos.isEmpty()) {
            JOptionPane.showMessageDialog(vista, 
                "No hay automóviles registrados", 
                "Sin registros", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        modeloTabla.setRowCount(0);
        
        for (AutomovilDTO auto : listaAutos) {
            Object[] fila = {
                auto.getIdAuto(),
                auto.getCodigo(),
                auto.getMarca(),
                auto.getModelo(),
                auto.getColor(),
                String.format("$%,.2f", auto.getPrecioBase()),
                auto.getNombreTipoMotor(),
                String.format("$%,.2f", auto.getPrecioTotal())
            };
            modeloTabla.addRow(fila);
        }
        
        JOptionPane.showMessageDialog(vista, 
            "Se encontraron " + listaAutos.size() + " automóviles", 
            "Consulta exitosa", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limpiarCampos() {
        vista.txtCodigo.setText("");
        vista.cboxMarca.setSelectedIndex(0);
        vista.cboxModelo.setSelectedIndex(0);
        vista.txtColor.setText("");
        vista.txtPrecioBase.setText("");
        vista.cboxTipoMotor.setSelectedIndex(0);
        autoActual = null;
    }
}
