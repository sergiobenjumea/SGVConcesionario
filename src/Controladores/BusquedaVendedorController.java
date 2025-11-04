package Controladores;

import vistas.UIBuscarporVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class BusquedaVendedorController implements ActionListener {
    
    private UIBuscarporVendedor vista;
    
    public BusquedaVendedorController(UIBuscarporVendedor vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnbuscar) {
            JOptionPane.showMessageDialog(vista, "Clic en Buscar");
        }
        else if (e.getSource() == this.vista.btnLimpiar) {
            JOptionPane.showMessageDialog(vista, "Clic en Limpiar");
        }
    }
}
