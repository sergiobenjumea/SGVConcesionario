package Controladores;

import vistas.UIPrincipal;
import vistas.UICliente;
import vistas.UIAutomoviles;
import vistas.UIVendedor;
import vistas.UIRegistrarventa;
import vistas.UIFormasPago;
import vistas.UIVentasporVendedor;
import vistas.UIVentasCreditoConcesionario;
import vistas.UIVendedorconMayorVenta;
import vistas.UIBuscarporVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController implements ActionListener {
    
    private UIPrincipal vista;
    
    public PrincipalController(UIPrincipal vista) {
        this.vista = vista;
        
        this.vista.menuiClientes.addActionListener(this);
        this.vista.menuiVendedores.addActionListener(this);
        this.vista.menuiAutomoviles.addActionListener(this);
        this.vista.menuiSalirdelSistema.addActionListener(this);
        this.vista.menuiRegistrarVenta.addActionListener(this);
        this.vista.menuiVendedorMayorVenta.addActionListener(this);
        this.vista.menuiVentasporVendedor.addActionListener(this);
        this.vista.menuiVentasCreditoConcesionario.addActionListener(this);
        this.vista.menuiBusquedaporVendedor.addActionListener(this);
        if(this.vista.menuiFormasPago != null){
            this.vista.menuiFormasPago.addActionListener(this); // Incluye Formas de Pago si tienes menú
        }
        this.vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.menuiClientes) {
            UICliente vistaCliente = new UICliente();
            new ClienteController(vistaCliente);
            vistaCliente.setVisible(true);
        } 
        else if (src == vista.menuiVendedores) {
            UIVendedor vistaVendedor = new UIVendedor();
            new VendedorController(vistaVendedor);
            vistaVendedor.setVisible(true);
        } 
        else if (src == vista.menuiAutomoviles) {
            UIAutomoviles vistaAutos = new UIAutomoviles();
            new AutomovilesController(vistaAutos);
            vistaAutos.setVisible(true);
        }
        else if (src == vista.menuiRegistrarVenta) {
            UIRegistrarventa vistaVenta = new UIRegistrarventa();
            new RegistrarVentaController(vistaVenta);
            vistaVenta.setVisible(true);
        }
        else if (src == vista.menuiFormasPago) {
            UIFormasPago vistaFormasPago = new UIFormasPago();
            new FormasPagoController(vistaFormasPago);
            vistaFormasPago.setVisible(true);
        }
        else if (src == vista.menuiVentasporVendedor) {
            UIVentasporVendedor vistaVentasVendedor = new UIVentasporVendedor();
            new VentasporVendedorController(vistaVentasVendedor);
            vistaVentasVendedor.setVisible(true);
        }
        else if (src == vista.menuiVentasCreditoConcesionario) {
            UIVentasCreditoConcesionario vistaCreditos = new UIVentasCreditoConcesionario();
            new VentasCreditoConcesionarioController(vistaCreditos);
            vistaCreditos.setVisible(true);
        }
        else if (src == vista.menuiVendedorMayorVenta) {
            UIVendedorconMayorVenta vistaVendedorMayor = new UIVendedorconMayorVenta();
            new VendedorconMayorVentaController(vistaVendedorMayor);
            vistaVendedorMayor.setVisible(true);
        }
        else if (src == vista.menuiBusquedaporVendedor) {
            UIBuscarporVendedor vistaBuscarVendedor = new UIBuscarporVendedor();
            new BusquedaVendedorController(vistaBuscarVendedor);
            vistaBuscarVendedor.setVisible(true);
        }
        else if (src == vista.menuiSalirdelSistema) {
            vista.dispose();
            System.exit(0);
        }
    }
}
