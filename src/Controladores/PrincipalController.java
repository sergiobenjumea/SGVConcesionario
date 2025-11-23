package Controladores;

import vistas.UIPrincipal;
import vistas.UICliente;
import vistas.UIAutomoviles;
import vistas.UIVendedor;
import vistas.UIRegistrarventa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController implements ActionListener {
    
    private UIPrincipal vista;
    
    public PrincipalController(UIPrincipal vista) {
        this.vista = vista;
        
        // Agregar listeners solo para los módulos que ya tienen controlador
        this.vista.menuiClientes.addActionListener(this);
        this.vista.menuiVendedores.addActionListener(this);
        this.vista.menuiAutomoviles.addActionListener(this);
        this.vista.menuiSalirdelSistema.addActionListener(this);
        this.vista.menuiRegistrarVenta.addActionListener(this);

        // Mostrar la vista principal
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.menuiClientes) {
            UICliente vistaCliente = new UICliente();
            new ClienteController(vistaCliente);
            vistaCliente.setVisible(true);
        }
        else if (e.getSource() == vista.menuiVendedores) {
            UIVendedor vistaVendedor = new UIVendedor();
            new VendedorController(vistaVendedor);
            vistaVendedor.setVisible(true);
        }
        else if (e.getSource() == vista.menuiAutomoviles) {
            UIAutomoviles vistaAutos = new UIAutomoviles();
            new AutomovilesController(vistaAutos);
            vistaAutos.setVisible(true);
        }
        else if (e.getSource() == vista.menuiSalirdelSistema) {
            vista.dispose();
            System.exit(0);
        }
        else if (e.getSource() == vista.menuiRegistrarVenta) {
            UIRegistrarventa vistaVenta = new UIRegistrarventa();
            new RegistrarVentaController(vistaVenta);
            vistaVenta.setVisible(true);
        }

    }
}
