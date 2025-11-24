package Controladores;

import modelo.dao.AutomovilDAO;
import modelo.dao.LineaVehiculoDAO;
import modelo.dao.MarcaDAO;
import modelo.dao.TipoMotorDAO;
import modelo.dto.AutomovilDTO;
import modelo.dto.LineaVehiculoDTO;
import modelo.dto.MarcaDTO;
import modelo.dto.TipoMotorDTO;
import vistas.UIAutomoviles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.List;

public class AutomovilesController implements ActionListener {

    private UIAutomoviles vista;
    private AutomovilDAO autoDAO;
    private MarcaDAO marcaDAO;
    private LineaVehiculoDAO lineaDAO;
    private TipoMotorDAO tipoMotorDAO;
    private DecimalFormat formatoMoneda = new DecimalFormat("$#,##0.00");

    public AutomovilesController(UIAutomoviles vista) {
        this.vista = vista;
        this.autoDAO = new AutomovilDAO();
        this.marcaDAO = new MarcaDAO();
        this.lineaDAO = new LineaVehiculoDAO();
        this.tipoMotorDAO = new TipoMotorDAO();

        inicializarCombos();
        this.vista.cboxMarca.addActionListener(this::cargarLineasPorMarca);

        this.vista.btnGuardarAutomovil.addActionListener(this);
        this.vista.btnConsultarAutomovil.addActionListener(this);
        this.vista.btnModificarAutomovil.addActionListener(this);
        this.vista.btnEliminarAutomovil.addActionListener(this);
        this.vista.btnConsultarTodosAutomovil.addActionListener(this);

        consultarTodos();
    }

    private void inicializarCombos() {
        // Combo Marca (objetos)
        DefaultComboBoxModel<MarcaDTO> modeloMarca = new DefaultComboBoxModel<>();
        modeloMarca.addElement(null);
        for (MarcaDTO m : marcaDAO.listarMarcas()) {
            modeloMarca.addElement(m);
        }
        vista.cboxMarca.setModel(modeloMarca);

        // Combo Línea (objetos)
        vista.cboxLinea.setModel(new DefaultComboBoxModel<>());

        // Combo Año (Strings)
        vista.cboxAnio.removeAllItems();
        vista.cboxAnio.addItem("");
        for (int y = 1990; y <= 2026; y++) {
            vista.cboxAnio.addItem(String.valueOf(y));
        }

        // Combo Tipo Motor (objetos)
        DefaultComboBoxModel<TipoMotorDTO> modeloMotor = new DefaultComboBoxModel<>();
        modeloMotor.addElement(null);
        for (TipoMotorDTO t : tipoMotorDAO.listarTiposMotor()) {
            modeloMotor.addElement(t);
        }
        vista.cboxTipoMotor.setModel(modeloMotor);
    }

    private void cargarLineasPorMarca(ActionEvent evt) {
        MarcaDTO marca = (MarcaDTO) vista.cboxMarca.getSelectedItem();
        DefaultComboBoxModel<LineaVehiculoDTO> modeloLinea = new DefaultComboBoxModel<>();
        modeloLinea.addElement(null);
        if (marca != null) {
            List<LineaVehiculoDTO> lineas = lineaDAO.listarLineasPorMarca(marca.getIdMarca());
            for (LineaVehiculoDTO l : lineas) {
                modeloLinea.addElement(l);
            }
        }
        vista.cboxLinea.setModel(modeloLinea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == vista.btnGuardarAutomovil) guardarAutomovil();
        else if (s == vista.btnConsultarAutomovil) consultarAutomovil();
        else if (s == vista.btnModificarAutomovil) modificarAutomovil();
        else if (s == vista.btnEliminarAutomovil) eliminarAutomovil();
        else if (s == vista.btnConsultarTodosAutomovil) consultarTodos();
    }

    private void guardarAutomovil() {
        try {
            if (vista.txtCodigo.getText().trim().isEmpty() ||
                vista.cboxMarca.getSelectedIndex() <= 0 ||
                vista.cboxLinea.getSelectedIndex() <= 0 ||
                vista.cboxAnio.getSelectedIndex() <= 0 ||
                vista.txtColor.getText().trim().isEmpty() ||
                vista.txtPrecioBase.getText().trim().isEmpty() ||
                vista.cboxTipoMotor.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(vista, "Faltan campos obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AutomovilDTO auto = new AutomovilDTO();
            auto.setCodigo(vista.txtCodigo.getText().trim());
            MarcaDTO marca = (MarcaDTO) vista.cboxMarca.getSelectedItem();
            LineaVehiculoDTO linea = (LineaVehiculoDTO) vista.cboxLinea.getSelectedItem();
            auto.setIdMarca(marca.getIdMarca());
            auto.setNombreMarca(marca.getNombreMarca());
            auto.setIdLinea(linea.getIdLinea());
            auto.setNombreLinea(linea.getNombreLinea());
            auto.setAnio(Integer.parseInt(vista.cboxAnio.getSelectedItem().toString()));
            auto.setColor(vista.txtColor.getText().trim());
            auto.setPrecioBase(Double.parseDouble(vista.txtPrecioBase.getText().trim()));
            TipoMotorDTO motor = (TipoMotorDTO) vista.cboxTipoMotor.getSelectedItem();
            auto.setIdTipoMotor(motor.getIdTipoMotor());
            auto.setNombreTipoMotor(motor.getNombreTipo());
            auto.setEstado("Disponible");

            int res = autoDAO.crear(auto);

            if (res > 0) {
                JOptionPane.showMessageDialog(vista, "Automóvil guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                consultarTodos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar el automóvil", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Verifica los tipos de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarAutomovil() {
        String codigo = vista.txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el código a consultar", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AutomovilDTO auto = autoDAO.buscarPorCodigo(codigo);
        if (auto != null) {
            seleccionaComboPorId(vista.cboxMarca, auto.getIdMarca());
            seleccionaComboPorId(vista.cboxLinea, auto.getIdLinea());
            vista.cboxAnio.setSelectedItem(String.valueOf(auto.getAnio()));
            vista.txtColor.setText(auto.getColor());
            vista.txtPrecioBase.setText(String.valueOf(auto.getPrecioBase()));
            seleccionaComboPorId(vista.cboxTipoMotor, auto.getIdTipoMotor());
            JOptionPane.showMessageDialog(vista, "Automóvil encontrado", "Consulta exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró el automóvil", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarAutomovil() {
        String codigo = vista.txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Código requerido", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AutomovilDTO auto = autoDAO.buscarPorCodigo(codigo);
        if (auto == null) {
            JOptionPane.showMessageDialog(vista, "No existe el automóvil", "No encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            MarcaDTO marca = (MarcaDTO) vista.cboxMarca.getSelectedItem();
            LineaVehiculoDTO linea = (LineaVehiculoDTO) vista.cboxLinea.getSelectedItem();
            auto.setIdMarca(marca.getIdMarca());
            auto.setIdLinea(linea.getIdLinea());
            auto.setAnio(Integer.parseInt(vista.cboxAnio.getSelectedItem().toString()));
            auto.setColor(vista.txtColor.getText().trim());
            auto.setPrecioBase(Double.parseDouble(vista.txtPrecioBase.getText().trim()));
            TipoMotorDTO motor = (TipoMotorDTO) vista.cboxTipoMotor.getSelectedItem();
            auto.setIdTipoMotor(motor.getIdTipoMotor());
            int res = autoDAO.actualizar(auto);
            if (res > 0) {
                JOptionPane.showMessageDialog(vista, "Automóvil actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                consultarTodos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error actualizando el automóvil", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Verifica los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarAutomovil() {
        String codigo = vista.txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Código requerido", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AutomovilDTO auto = autoDAO.buscarPorCodigo(codigo);
        if (auto == null) {
            JOptionPane.showMessageDialog(vista, "No existe el automóvil", "No encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirma = JOptionPane.showConfirmDialog(vista,
            "¿Está seguro de eliminar el automóvil " + auto.getNombreMarca() + " " + auto.getNombreLinea() + "?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            int res = autoDAO.eliminar(auto.getIdAuto());
            if (res > 0) {
                JOptionPane.showMessageDialog(vista, "Automóvil eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                consultarTodos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar el automóvil", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void consultarTodos() {
        List<AutomovilDTO> lista = autoDAO.leerTodos();
        DefaultTableModel modelo = (DefaultTableModel) vista.tblAutomovil.getModel();
        modelo.setRowCount(0);
        for (AutomovilDTO auto : lista) {
            Object[] fila = {
                auto.getCodigo(),
                auto.getNombreMarca(),
                auto.getNombreLinea(),
                auto.getAnio(),
                auto.getColor(),
                formatoMoneda.format(auto.getPrecioBase()),
                auto.getNombreTipoMotor()
            };
            modelo.addRow(fila);
        }
    }

    private void limpiarCampos() {
        vista.txtCodigo.setText("");
        vista.cboxMarca.setSelectedIndex(0);
        vista.cboxLinea.setModel(new DefaultComboBoxModel<>());
        vista.cboxAnio.setSelectedIndex(0);
        vista.txtColor.setText("");
        vista.txtPrecioBase.setText("");
        vista.cboxTipoMotor.setSelectedIndex(0);
    }

    private void seleccionaComboPorId(JComboBox combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            if (item != null) {
                if (item instanceof MarcaDTO && ((MarcaDTO) item).getIdMarca() == id) {
                    combo.setSelectedIndex(i);
                    break;
                } else if (item instanceof LineaVehiculoDTO && ((LineaVehiculoDTO) item).getIdLinea() == id) {
                    combo.setSelectedIndex(i);
                    break;
                } else if (item instanceof TipoMotorDTO && ((TipoMotorDTO) item).getIdTipoMotor() == id) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
}
