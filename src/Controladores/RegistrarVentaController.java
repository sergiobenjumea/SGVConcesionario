package Controladores;

import vistas.UIRegistrarventa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RegistrarVentaController implements ActionListener {
    
    private UIRegistrarventa vista;
    
    public RegistrarVentaController(UIRegistrarventa vista) {
        this.vista = vista;
        
        // Habilitar listeners para botones de consulta
        this.vista.btnConsultarVendedor.addActionListener(this);
        this.vista.btnConsultarAuto.addActionListener(this);
        this.vista.btnConsultarCliente.addActionListener(this);
        
        // Habilitar listeners para botones de transacción
        this.vista.btnCalcularTotal.addActionListener(this);
        this.vista.btnRegistrarVenta.addActionListener(this);
        
        // Mostrar la vista
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnConsultarVendedor) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Vendedor");
        }
        else if (e.getSource() == this.vista.btnConsultarAuto) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Automóvil");
        }
        else if (e.getSource() == this.vista.btnConsultarCliente) {
            JOptionPane.showMessageDialog(vista, "Clic en Consultar Cliente");
        }
        else if (e.getSource() == this.vista.btnCalcularTotal) {
            JOptionPane.showMessageDialog(vista, "Clic en Calcular Total");
        }
        else if (e.getSource() == this.vista.btnRegistrarVenta) {
            JOptionPane.showMessageDialog(vista, "Clic en Registrar Venta");
        }
    }
}
