package Controladores;

import vistas.UIVentasporVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VentasporVendedorController implements ActionListener {
    
    private UIVentasporVendedor vista;
    
    public VentasporVendedorController(UIVentasporVendedor vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones
        this.vista.btnBuscarVentasVendedor.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnBuscarVentasVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Buscar Ventas");
        }
        else if (e.getSource() == this.vista.btnLimpiar) {
            JOptionPane.showMessageDialog(vista, "Clic en Limpiar");
        }
    }
}
