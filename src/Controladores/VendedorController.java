package Controladores;

import modelo.dto.VendedorDTO;
import modelo.dao.VendedorDAO;
import vistas.UIVendedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VendedorController implements ActionListener {

    private UIVendedor view;
    private VendedorDAO dao;
    private int idVendedorSeleccionado = 0; // Para saber a cuál editar/eliminar

    public VendedorController(UIVendedor view) {
        this.view = view;
        this.dao = new VendedorDAO();

        // Configuración inicial
        this.view.setLocationRelativeTo(null);
        listarVendedores();

        // Listeners Botones
        this.view.btnGuardarVendedor.addActionListener(this);
        this.view.btnConsultarTodosVendedor.addActionListener(this);
        this.view.btnConsultarVendedor.addActionListener(this); // Botón Consultar Específico
        this.view.btnModificarVendedor.addActionListener(this);
        this.view.btnEliminarVendedor.addActionListener(this);

        // Listener Tabla (Clic para editar)
        this.view.tblVendedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = view.tblVendedores.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    // Recuperamos ID (Columna 0 oculta o visible)
                    idVendedorSeleccionado = (int) view.tblVendedores.getValueAt(fila, 0);
                    // Llenamos campos
                    view.txtidVendedor.setText(view.tblVendedores.getValueAt(fila, 1).toString()); // Identificación
                    view.txtnombreVendedor.setText(view.tblVendedores.getValueAt(fila, 2).toString()); // Nombre
                    view.txtprofesionVendedor.setText(view.tblVendedores.getValueAt(fila, 3).toString()); // Profesión
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardarVendedor) {
            guardarVendedor();
        } else if (e.getSource() == view.btnConsultarTodosVendedor) {
            listarVendedores();
        } else if (e.getSource() == view.btnConsultarVendedor) {
            buscarVendedor();
        } else if (e.getSource() == view.btnModificarVendedor) {
            modificarVendedor();
        } else if (e.getSource() == view.btnEliminarVendedor) {
            eliminarVendedor();
        }
    }

    // --- GUARDAR ---
    private void guardarVendedor() {
        if (validarCampos()) {
            VendedorDTO v = new VendedorDTO();
            v.setIdentificacion(view.txtidVendedor.getText());
            v.setNombre(view.txtnombreVendedor.getText());
            v.setProfesion(view.txtprofesionVendedor.getText());
            // Asignamos FECHA ACTUAL automáticamente porque la vista no tiene campo fecha
            v.setFechaContratacion(new java.sql.Date(System.currentTimeMillis()));

            if (dao.registrar(v)) {
                JOptionPane.showMessageDialog(view, "Vendedor registrado exitosamente");
                listarVendedores();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(view, "Error al guardar (revise si el ID ya existe)");
            }
        }
    }

    // --- LISTAR ---
    private void listarVendedores() {
        List<VendedorDTO> lista = dao.listar();
        DefaultTableModel model = new DefaultTableModel();
        
        // Estructura tabla
        model.addColumn("ID BD"); // Columna 0 (PK interna)
        model.addColumn("Identificación"); // Columna 1
        model.addColumn("Nombre");
        model.addColumn("Profesión");
        model.addColumn("Fecha Contratación");

        for (VendedorDTO v : lista) {
            Object[] fila = new Object[5];
            fila[0] = v.getId();
            fila[1] = v.getIdentificacion();
            fila[2] = v.getNombre();
            fila[3] = v.getProfesion();
            fila[4] = v.getFechaContratacion();
            model.addRow(fila);
        }
        view.tblVendedores.setModel(model);
    }

    // --- MODIFICAR ---
    private void modificarVendedor() {
        if (idVendedorSeleccionado == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione un vendedor de la tabla");
            return;
        }
        if (validarCampos()) {
            VendedorDTO v = new VendedorDTO();
            v.setId(idVendedorSeleccionado);
            v.setIdentificacion(view.txtidVendedor.getText());
            v.setNombre(view.txtnombreVendedor.getText());
            v.setProfesion(view.txtprofesionVendedor.getText());
            
            if (dao.actualizar(v)) {
                JOptionPane.showMessageDialog(view, "Vendedor modificado exitosamente");
                listarVendedores();
                limpiar();
            }
        }
    }

    // --- ELIMINAR ---
    private void eliminarVendedor() {
        if (idVendedorSeleccionado == 0) {
            JOptionPane.showMessageDialog(view, "Seleccione un vendedor de la tabla");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "¿Eliminar este vendedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminar(idVendedorSeleccionado)) {
                JOptionPane.showMessageDialog(view, "Eliminado exitosamente");
                listarVendedores();
                limpiar();
            }
        }
    }
    
    // --- BUSCAR ESPECÍFICO ---
    private void buscarVendedor() {
        String idBusqueda = view.txtidVendedor.getText();
        if(idBusqueda.isEmpty()){
             JOptionPane.showMessageDialog(view, "Ingrese una Identificación para buscar");
             return;
        }
        VendedorDTO v = dao.buscarPorIdentificacion(idBusqueda);
        if(v != null){
             view.txtnombreVendedor.setText(v.getNombre());
             view.txtprofesionVendedor.setText(v.getProfesion());
             idVendedorSeleccionado = v.getId(); // Importante para poder modificarlo luego
             JOptionPane.showMessageDialog(view, "Vendedor Encontrado");
        } else {
             JOptionPane.showMessageDialog(view, "Vendedor no encontrado");
        }
    }

    private boolean validarCampos() {
        if (view.txtidVendedor.getText().isEmpty() || view.txtnombreVendedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID y Nombre son obligatorios");
            return false;
        }
        return true;
    }

    private void limpiar() {
        idVendedorSeleccionado = 0;
        view.txtidVendedor.setText("");
        view.txtnombreVendedor.setText("");
        view.txtprofesionVendedor.setText("");
    }
}