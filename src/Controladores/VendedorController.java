package Controladores;

import vistas.UIVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VendedorController implements ActionListener {
    
    private UIVendedor vista;
    
    public VendedorController(UIVendedor vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones CRUD
        this.vista.btnGuardarVendedor.addActionListener(this);
        this.vista.btnConsultarVendedor.addActionListener(this);
        this.vista.btnModificarVendedor.addActionListener(this);
        this.vista.btnEliminarVendedor.addActionListener(this);
        this.vista.btnConsultarTodosVendedor.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnGuardarVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Guardar");
        }
        else if (e.getSource() == this.vista.btnConsultarVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar");
        }
        else if (e.getSource() == this.vista.btnModificarVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Modificar");
        }
        else if (e.getSource() == this.vista.btnEliminarVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Eliminar");
        }
        else if (e.getSource() == this.vista.btnConsultarTodosVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Todos");
        }
    }
}
