package Controladores;
import vistas.UIPrincipal;
import vistas.UICliente;
import vistas.UIAutomoviles;
import vistas.UIBuscarporVendedor;
import vistas.UIFormasPago;
import vistas.UIRegistrarventa;
import vistas.UIVendedor;
import vistas.UIVendedorconMayorVenta;
import vistas.UIVentasCreditoConcesionario;
import vistas.UIVentasporVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PrincipalController implements ActionListener {
    
    private UIPrincipal vista;
    
    public PrincipalController(UIPrincipal vista) {
        this.vista = vista;
        
        // Habilitar listener para el menú Automóviles
        this.vista.menuiAutomoviles.addActionListener(this);
        this.vista.menuiBusquedaporVendedor.addActionListener(this);
        this.vista.menuiClientes.addActionListener(this);
        this.vista.menuiFormasPago.addActionListener(this);
        this.vista.menuiRegistrarVenta.addActionListener(this);
        this.vista.menuiVendedores.addActionListener(this);
        this.vista.menuiVendedorMayorVenta.addActionListener(this);
        this.vista.menuiVentasCreditoConcesionario.addActionListener(this);
        this.vista.menuiVentasporVendedor.addActionListener(this);
        this.vista.menuiSalirdelSistema.addActionListener(this);
        // Mostrar la vista principal
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.menuiAutomoviles) {
            new AutomovilesController(new UIAutomoviles());
        }
        else if (e.getSource() == this.vista.menuiBusquedaporVendedor) {
            new BusquedaVendedorController(new UIBuscarporVendedor());
        }
        else if (e.getSource() == this.vista.menuiClientes) {
            new ClienteController(new UICliente());
        }
        else if (e.getSource() == this.vista.menuiFormasPago) {
            new FormasPagoController(new UIFormasPago());
        }
        else if (e.getSource() == this.vista.menuiRegistrarVenta) {
            new RegistrarVentaController(new UIRegistrarventa());
        }
        else if (e.getSource() == this.vista.menuiVendedores) {
        new VendedorController(new UIVendedor());
        }
        else if (e.getSource() == this.vista.menuiVendedorMayorVenta) {
        new VendedorconMayorVentaController(new UIVendedorconMayorVenta());
        }
        else if (e.getSource() == this.vista.menuiVentasCreditoConcesionario) {  
        new VentasCreditoConcesionarioController(new UIVentasCreditoConcesionario());
        }
        else if (e.getSource() == this.vista.menuiVentasporVendedor) {  
        new VentasporVendedorController(new UIVentasporVendedor());
    }
        else if (e.getSource() == this.vista.menuiSalirdelSistema) {
            vista.dispose();
            System.exit(0);
        }

    }
   
}
