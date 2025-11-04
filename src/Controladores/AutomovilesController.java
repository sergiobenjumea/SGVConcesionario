package Controladores;

import vistas.UIAutomoviles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AutomovilesController implements ActionListener {
    
    private UIAutomoviles vista;
    
    public AutomovilesController(UIAutomoviles vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones
        this.vista.btnGuardarAutomovil.addActionListener(this);
        this.vista.btnConsultarAutomovil.addActionListener(this);
        this.vista.btnModificarAutomovil.addActionListener(this);
        this.vista.btnEliminarAutomovil.addActionListener(this);
        this.vista.btnConsultarTodosAutomovil.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnGuardarAutomovil) {
            JOptionPane.showMessageDialog(vista, "Clic en Guardar");
        }
        else if (e.getSource() == this.vista.btnConsultarAutomovil) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar");
        }
        else if (e.getSource() == this.vista.btnModificarAutomovil) {
            JOptionPane.showMessageDialog(vista, "Clic en Modificar");
        }
        else if (e.getSource() == this.vista.btnEliminarAutomovil) {
            JOptionPane.showMessageDialog(vista, "Clic en Eliminar");
        }
        else if (e.getSource() == this.vista.btnConsultarTodosAutomovil) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Todos");
        }
    }
}
