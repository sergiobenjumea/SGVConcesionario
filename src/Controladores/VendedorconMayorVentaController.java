package Controladores;

import vistas.UIVendedorconMayorVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VendedorconMayorVentaController implements ActionListener {
    
    private UIVendedorconMayorVenta vista;
    
    public VendedorconMayorVentaController(UIVendedorconMayorVenta vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones
        this.vista.btnBuscarMayorVenta.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnBuscarMayorVenta) {
            JOptionPane.showMessageDialog(vista, "Clic en Buscar Mayor Venta");
        }
        else if (e.getSource() == this.vista.btnLimpiar) {
            JOptionPane.showMessageDialog(vista, "Clic en Limpiar");
        }
    }
}