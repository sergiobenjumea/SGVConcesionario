package Controladores;

import modelo.dto.AutomovilDTO;
import modelo.dao.AutomovilDAO;
import modelo.util.ItemCombo;
import vistas.UIAutomoviles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.util.List;
import modelo.util.Formato;
import javax.swing.table.DefaultTableModel;

public class AutomovilController implements ActionListener, ItemListener {

    private UIAutomoviles view;
    private AutomovilDAO dao;

    public AutomovilController(UIAutomoviles view) {
        this.view = view;
        this.dao = new AutomovilDAO();

        // Configuración visual
        this.view.txtColor.setEditable(true);
        this.view.setLocationRelativeTo(null);
        
        // Cargar Combos Iniciales
        cargarMarcas();
        cargarMotores();
        cargarAnios();

        // Listeners
        this.view.btnGuardarAutomovil.addActionListener(this);
        this.view.cboxMarca.addItemListener(this);
        this.view.btnConsultarTodosAutomovil.addActionListener(this); // Escuchar el botón
        
        // Cargar la tabla automáticamente al abrir la ventana
        listarAutomoviles(); 
    }

    private void cargarMarcas() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(dao.obtenerMarcas());
        view.cboxMarca.setModel(model);
    }
    
    private void cargarMotores() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(dao.obtenerTiposMotor());
        view.cboxTipoMotor.setModel(model);
    }

    private void cargarAnios() {
        view.cboxAnio.removeAllItems();
        for (int i = 2026; i >= 2000; i--) {
            view.cboxAnio.addItem(String.valueOf(i));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.cboxMarca && e.getStateChange() == ItemEvent.SELECTED) {
            ItemCombo marcaSeleccionada = (ItemCombo) view.cboxMarca.getSelectedItem();
            
            if (marcaSeleccionada.getId() == 0) {
                view.cboxLinea.removeAllItems();
                return;
            }
            DefaultComboBoxModel modelLineas = new DefaultComboBoxModel(
                dao.obtenerLineasPorMarca(marcaSeleccionada.getId())
            );
            view.cboxLinea.setModel(modelLineas);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardarAutomovil) {
            guardarAutomovil();
        }
        else if (e.getSource() == view.btnConsultarTodosAutomovil) {
            listarAutomoviles();
        }
    } // <--- ¡AQUÍ FALTABA ESTA LLAVE DE CIERRE!

    private void guardarAutomovil() {
        try {
            ItemCombo marcaSel = (ItemCombo) view.cboxMarca.getSelectedItem();
            ItemCombo lineaSel = (ItemCombo) view.cboxLinea.getSelectedItem();
            ItemCombo motorSel = (ItemCombo) view.cboxTipoMotor.getSelectedItem();
            
            // Validaciones básicas
            if (marcaSel.getId() == 0) {
                JOptionPane.showMessageDialog(view, "Seleccione una Marca.");
                return;
            }
            if (lineaSel == null || lineaSel.getId() == 0) {
                JOptionPane.showMessageDialog(view, "Seleccione una Línea.");
                return;
            }
            if (motorSel.getId() == 0) {
                JOptionPane.showMessageDialog(view, "Seleccione un Motor.");
                return;
            }

            // Crear objeto y enviar
            AutomovilDTO auto = new AutomovilDTO();
            auto.setIdLinea(lineaSel.getId());
            auto.setIdTipoMotor(motorSel.getId());
            auto.setAnio(Integer.parseInt(view.cboxAnio.getSelectedItem().toString()));
            auto.setColor(view.txtColor.getText());
            // Validamos que el precio no esté vacío
            if(view.txtPrecioBase.getText().isEmpty()){
                 JOptionPane.showMessageDialog(view, "Ingrese el Precio Base.");
                 return;
            }
            auto.setPrecioBase(Double.parseDouble(view.txtPrecioBase.getText()));
            
            if(dao.registrar(auto)) {
                JOptionPane.showMessageDialog(view, "¡Guardado exitosamente!");
                limpiarFormulario();
                listarAutomoviles(); // Refrescar la tabla
            } else {
                JOptionPane.showMessageDialog(view, "Error al guardar.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El precio debe ser un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }
    
    private void limpiarFormulario() {
        view.cboxMarca.setSelectedIndex(0);
        view.cboxTipoMotor.setSelectedIndex(0);
        view.txtColor.setText("");
        view.txtPrecioBase.setText("");
    }

    private void listarAutomoviles() {
        List<AutomovilDTO> lista = dao.listar();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Línea");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Color");
        modeloTabla.addColumn("Precio Base");
        modeloTabla.addColumn("Motor");
        
        for (AutomovilDTO auto : lista) {
            Object[] fila = new Object[7];
            fila[0] = auto.getId();
            fila[1] = auto.getNombreMarca();
            fila[2] = auto.getNombreLinea();
            fila[3] = auto.getAnio();
            fila[4] = auto.getColor();
            
            // --- CAMBIO: Usamos Formato.moneda() para visualizar ---
            fila[5] = Formato.moneda(auto.getPrecioBase()); 
            // -------------------------------------------------------
            
            fila[6] = auto.getNombreMotor();
            modeloTabla.addRow(fila);
        }
        view.tblAutomovil.setModel(modeloTabla);
    }    
    
}