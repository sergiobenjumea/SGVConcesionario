package Controladores;

import modelo.dao.FormaPagoDAO;
import modelo.dto.FormaPagoDTO;
import vistas.UIFormasPago;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormasPagoController implements ActionListener {

    private UIFormasPago vista;
    private FormaPagoDAO formaPagoDAO;
    private DefaultTableModel modeloTabla;
    private FormaPagoDTO formaActual;

    public FormasPagoController(UIFormasPago vista) {
        this.vista = vista;
        this.formaPagoDAO = new FormaPagoDAO();

        modeloTabla = (DefaultTableModel) vista.jTable1.getModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new String[]{"Código", "Descripción"});

        cargarCboxCodigo();

        this.vista.btnGuardarFormasPago.addActionListener(this);
        this.vista.btnConsultarFormasPago.addActionListener(this);
        this.vista.btnModificarFormasPago.addActionListener(this);
        this.vista.btnEliminarFormasPago.addActionListener(this);
        this.vista.btnConsultarTodosFormasPago.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarFormasPago)      guardarForma();
        else if (e.getSource() == vista.btnConsultarFormasPago) consultarForma();
        else if (e.getSource() == vista.btnModificarFormasPago) modificarForma();
        else if (e.getSource() == vista.btnEliminarFormasPago)  eliminarForma();
        else if (e.getSource() == vista.btnConsultarTodosFormasPago) consultarTodos();
    }

    private void cargarCboxCodigo() {
        vista.cboxCodigo.removeAllItems();
        vista.cboxCodigo.addItem(null);
        for (FormaPagoDTO f : formaPagoDAO.listarFormasPago()) {
            vista.cboxCodigo.addItem(f); // El combo muestra la descripción gracias al toString()
        }
    }

    private void guardarForma() {
        String codigo = vista.cboxCodigo.getSelectedItem() != null ?
            ((FormaPagoDTO)vista.cboxCodigo.getSelectedItem()).getCodigoForma() :
            vista.txtDescripcion.getText().trim(); // Si quieres permitir agregar desde texto
        String descripcion = vista.txtDescripcion.getText().trim();

        if (codigo.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FormaPagoDTO forma = new FormaPagoDTO();
        forma.setCodigoForma(codigo);
        forma.setDescripcion(descripcion);
        forma.setActivo(1);

        int res = formaPagoDAO.guardar(forma);
        if (res > 0) {
            JOptionPane.showMessageDialog(vista, "Forma de pago registrada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarCboxCodigo();
            consultarTodos();
        } else {
            JOptionPane.showMessageDialog(vista, "No fue posible guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarForma() {
        FormaPagoDTO forma = (FormaPagoDTO) vista.cboxCodigo.getSelectedItem();
        if (forma == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione una forma de pago.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        FormaPagoDTO f = formaPagoDAO.buscarPorCodigo(forma.getCodigoForma());
        if (f != null) {
            vista.txtDescripcion.setText(f.getDescripcion());
            formaActual = f;
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró la forma de pago.", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarForma() {
        if (formaActual == null) {
            JOptionPane.showMessageDialog(vista, "Consulte una forma de pago primero.", "Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Modificar esta forma de pago?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        formaActual.setDescripcion(vista.txtDescripcion.getText().trim());

        int res = formaPagoDAO.actualizar(formaActual);
        if (res > 0) {
            JOptionPane.showMessageDialog(vista, "Forma de pago actualizada.", "Actualización", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarCboxCodigo();
            consultarTodos();
        } else {
            JOptionPane.showMessageDialog(vista, "No fue posible actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        formaActual = null;
    }

    private void eliminarForma() {
        if (formaActual == null) {
            JOptionPane.showMessageDialog(vista, "Consulte una forma de pago primero.", "Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Eliminar esta forma de pago?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        int res = formaPagoDAO.eliminar(formaActual.getIdFormaPago());
        if (res > 0) {
            JOptionPane.showMessageDialog(vista, "Eliminado exitosamente.", "Eliminación", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarCboxCodigo();
            consultarTodos();
        } else {
            JOptionPane.showMessageDialog(vista, "No fue posible eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        formaActual = null;
    }

    private void consultarTodos() {
        List<FormaPagoDTO> lista = formaPagoDAO.listarFormasPago();
        modeloTabla.setRowCount(0);
        for (FormaPagoDTO f : lista) {
            modeloTabla.addRow(new Object[]{f.getCodigoForma(), f.getDescripcion()});
        }
    }

    private void limpiarCampos() {
        vista.cboxCodigo.setSelectedIndex(0);
        vista.txtDescripcion.setText("");
        formaActual = null;
    }
}
