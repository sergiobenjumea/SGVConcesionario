package Controladores;

import vistas.UIVentasCreditoConcesionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VentasCreditoConcesionarioController implements ActionListener {
    
    private UIVentasCreditoConcesionario vista;
    
    public VentasCreditoConcesionarioController(UIVentasCreditoConcesionario vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones
        this.vista.btnCargarVentas.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnCargarVentas) {
            JOptionPane.showMessageDialog(vista, "Clic en Cargar Ventas");
        }
        else if (e.getSource() == this.vista.btnLimpiar) {
            JOptionPane.showMessageDialog(vista, "Clic en Limpiar");
        }
    }
}
