package Controladores;

import modelo.dto.FormasPagoDTO;
import modelo.dao.FormasPagoDAO;
import vistas.UIFormasPago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormasPagoController implements ActionListener {

    private UIFormasPago view;
    private FormasPagoDAO dao;
    private int idSeleccionado = 0; // Para guardar el ID al hacer clic en la tabla

    public FormasPagoController(UIFormasPago view) {
        this.view = view;
        this.dao = new FormasPagoDAO();

        this.view.setLocationRelativeTo(null);
        listarFormasPago();

        // Listeners de Botones
        this.view.btnGuardarFormasPago.addActionListener(this);
        this.view.btnConsultarTodosFormasPago.addActionListener(this);
        this.view.btnConsultarFormasPago.addActionListener(this); // Botón Consultar (Listar)
        this.view.btnModificarFormasPago.addActionListener(this);
        this.view.btnEliminarFormasPago.addActionListener(this);

        // Listener de la Tabla (Para editar/eliminar)
        this.view.jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = view.jTable1.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    // Columna 0 es ID, Columna 1 es Nombre
                    idSeleccionado = (int) view.jTable1.getValueAt(fila, 0);
                    view.txtFormasPago.setText(view.jTable1.getValueAt(fila, 1).toString());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardarFormasPago) {
            guardar();
        } else if (e.getSource() == view.btnConsultarTodosFormasPago || e.getSource() == view.btnConsultarFormasPago) {
            listarFormasPago();
        } else if (e.getSource() == view.btnModificarFormasPago) {
            modificar();
        } else if (e.getSource() == view.btnEliminarFormasPago) {
            eliminar();
        }
    }

    // --- MÉTODOS CRUD ---

    private void guardar() {
        if (validar()) {
            FormasPagoDTO fp = new FormasPagoDTO();
            fp.setNombre(view.txtFormasPago.getText());
            
            if (dao.registrar(fp)) {
                JOptionPane.showMessageDialog(view, "Forma de Pago guardada");
                listarFormasPago();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(view, "Error al guardar");
            }
        }
    }

    private void listarFormasPago() {
        List<FormasPagoDTO> lista = dao.listar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Descripción");

        for (FormasPagoDTO fp : lista) {
            Object[] fila = new Object[2];
            fila[0] = fp.getId();
            fila[1] = fp.getNombre();
            model.addRow(fila);
        }
        view.jTable1.setModel(model);
    }

    private void modificar() {
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione un registro de la tabla");
            return;
        }
        if (validar()) {
            FormasPagoDTO fp = new FormasPagoDTO();
            fp.setId(idSeleccionado);
            fp.setNombre(view.txtFormasPago.getText());

            if (dao.actualizar(fp)) {
                JOptionPane.showMessageDialog(view, "Modificado correctamente");
                listarFormasPago();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(view, "Error al modificar");
            }
        }
    }

    private void eliminar() {
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione un registro de la tabla");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "¿Eliminar esta forma de pago?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(view, "Eliminado correctamente");
                listarFormasPago();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(view, "Error al eliminar");
            }
        }
    }

    private boolean validar() {
        if (view.txtFormasPago.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Ingrese el nombre de la Forma de Pago");
            return false;
        }
        return true;
    }

    private void limpiar() {
        idSeleccionado = 0;
        view.txtFormasPago.setText("");
    }
}