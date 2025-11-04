package Controladores;

import vistas.UICliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ClienteController implements ActionListener {
    
    private UICliente vista;
    
    public ClienteController(UICliente vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones CRUD
        this.vista.btnGuardarCliente.addActionListener(this);
        this.vista.btnConsultarCliente.addActionListener(this);
        this.vista.btnModificarCliente.addActionListener(this);
        this.vista.btnEliminarCliente.addActionListener(this);
        this.vista.btnConsultarTodosCliente.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnGuardarCliente) {
            JOptionPane.showMessageDialog(vista, "Clic en Guardar");
        }
        else if (e.getSource() == this.vista.btnConsultarCliente) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar");
        }
        else if (e.getSource() == this.vista.btnModificarCliente) {
            JOptionPane.showMessageDialog(vista, "Clic en Modificar");
        }
        else if (e.getSource() == this.vista.btnEliminarCliente) {
            JOptionPane.showMessageDialog(vista, "Clic en Eliminar");
        }
        else if (e.getSource() == this.vista.btnConsultarTodosCliente) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Todos");
        }
    }
}
