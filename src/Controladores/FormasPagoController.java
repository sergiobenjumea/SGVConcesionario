package Controladores;

import vistas.UIFormasPago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class FormasPagoController implements ActionListener {
    
    private UIFormasPago vista;
    
    public FormasPagoController(UIFormasPago vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones CRUD
        this.vista.btnGuardarFormasPago.addActionListener(this);
        this.vista.btnConsultarFormasPago.addActionListener(this);
        this.vista.btnModificarFormasPago.addActionListener(this);
        this.vista.btnEliminarFormasPago.addActionListener(this);
        this.vista.btnConsultarTodosFormasPago.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnGuardarFormasPago) {
            JOptionPane.showMessageDialog(vista, "Clic en Guardar");
        }
        else if (e.getSource() == this.vista.btnConsultarFormasPago) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar");
        }
        else if (e.getSource() == this.vista.btnModificarFormasPago) {
            JOptionPane.showMessageDialog(vista, "Clic en Modificar");
        }
        else if (e.getSource() == this.vista.btnEliminarFormasPago) {
            JOptionPane.showMessageDialog(vista, "Clic en Eliminar");
        }
        else if (e.getSource() == this.vista.btnConsultarTodosFormasPago) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Todos");
        }
    }
}
