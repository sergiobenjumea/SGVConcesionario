package Controladores;

import modelo.dao.VendedorDAO;
import modelo.dto.VendedorDTO;
import vistas.UIVendedor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class VendedorController implements ActionListener {

    private UIVendedor vista;
    private VendedorDAO vendedorDAO;
    private VendedorDTO vendedorActual;
    private DefaultTableModel modeloTabla;

    public VendedorController(UIVendedor vista) {
        this.vista = vista;
        this.vendedorDAO = new VendedorDAO();

        modeloTabla = (DefaultTableModel) vista.tblVendedores.getModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new String[]{
            "ID", "Identificación", "Nombre", "Profesión", "Estado"
        });

        this.vista.btnGuardarVendedor.addActionListener(this);
        this.vista.btnConsultarVendedor.addActionListener(this);
        this.vista.btnModificarVendedor.addActionListener(this);
        this.vista.btnEliminarVendedor.addActionListener(this);
        this.vista.btnConsultarTodosVendedor.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarVendedor) {
            registrarVendedor();
        } else if (e.getSource() == vista.btnConsultarVendedor) {
            consultarVendedor();
        } else if (e.getSource() == vista.btnModificarVendedor) {
            modificarVendedor();
        } else if (e.getSource() == vista.btnEliminarVendedor) {
            eliminarVendedor();
        } else if (e.getSource() == vista.btnConsultarTodosVendedor) {
            consultarTodos();
        }
    }

    private void registrarVendedor() {
        try {
            VendedorDTO vendedor = new VendedorDTO();
            vendedor.setIdentificacion(vista.txtidVendedor.getText().trim());
            vendedor.setNombre(vista.txtnombreVendedor.getText().trim());
            vendedor.setProfesion(vista.txtprofesionVendedor.getText().trim());
            vendedor.setFechaContratacion(new Date());
            vendedor.setEstado("Activo");

            if (vendedor.getIdentificacion().isEmpty() || vendedor.getNombre().isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Complete los campos obligatorios (ID y Nombre)",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int resultado = vendedorDAO.crear(vendedor);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista,
                        "Vendedor registrado exitosamente",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al registrar vendedor",
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

    private void consultarVendedor() {
        String identificacion = vista.txtidVendedor.getText().trim();
        if (identificacion.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Ingrese el ID del vendedor a consultar",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        vendedorActual = vendedorDAO.leerPorIdentificacion(identificacion);

        if (vendedorActual != null) {
            vista.txtnombreVendedor.setText(vendedorActual.getNombre());
            vista.txtprofesionVendedor.setText(vendedorActual.getProfesion());
            JOptionPane.showMessageDialog(vista,
                    "Vendedor encontrado",
                    "Consulta exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Vendedor no encontrado con ID: " + identificacion,
                    "No encontrado",
                    JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
        }
    }

    private void modificarVendedor() {
        if (vendedorActual == null) {
            JOptionPane.showMessageDialog(vista,
                    "Primero consulte un vendedor para modificarlo",
                    "Consulta requerida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Está seguro de modificar este vendedor?",
                "Confirmar modificación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                vendedorActual.setIdentificacion(vista.txtidVendedor.getText().trim());
                vendedorActual.setNombre(vista.txtnombreVendedor.getText().trim());
                vendedorActual.setProfesion(vista.txtprofesionVendedor.getText().trim());

                int resultado = vendedorDAO.actualizar(vendedorActual);

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(vista,
                            "Vendedor modificado exitosamente",
                            "Modificación exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    vendedorActual = null;
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Error al modificar vendedor",
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

    private void eliminarVendedor() {
        if (vendedorActual == null) {
            JOptionPane.showMessageDialog(vista,
                    "Primero consulte un vendedor para eliminarlo",
                    "Consulta requerida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Está seguro de eliminar este vendedor?\nNombre: " + vendedorActual.getNombre(),
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            int resultado = vendedorDAO.eliminar(vendedorActual.getIdVendedor());
            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista,
                        "Vendedor eliminado exitosamente",
                        "Eliminación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                vendedorActual = null;
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al eliminar vendedor",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void consultarTodos() {
        List<VendedorDTO> listaVendedores = vendedorDAO.leerTodos();

        if (listaVendedores.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "No hay vendedores registrados",
                    "Sin registros",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        modeloTabla.setRowCount(0);

        for (VendedorDTO vendedor : listaVendedores) {
            Object[] fila = {
                    vendedor.getIdVendedor(),
                    vendedor.getIdentificacion(),
                    vendedor.getNombre(),
                    vendedor.getProfesion(),
                    vendedor.getEstado()
            };
            modeloTabla.addRow(fila);
        }

        JOptionPane.showMessageDialog(vista,
                "Se encontraron " + listaVendedores.size() + " vendedores",
                "Consulta exitosa",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        vista.txtidVendedor.setText("");
        vista.txtnombreVendedor.setText("");
        vista.txtprofesionVendedor.setText("");
        vendedorActual = null;
    }
}
