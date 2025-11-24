package Controladores;

import vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class PrincipalController implements ActionListener {

    private UIPrincipal vistaPrincipal;

    public PrincipalController(UIPrincipal vista) {
        this.vistaPrincipal = vista;
        this.vistaPrincipal.setVisible(true); // Mostrar ventana principal
        this.vistaPrincipal.setLocationRelativeTo(null);
        
        // Asignar listeners a los menús
        // Asegúrate que los JMenuItem sean public en UIPrincipal
        try {
            this.vistaPrincipal.menuiClientes.addActionListener(this);
            this.vistaPrincipal.menuiAutomoviles.addActionListener(this);
            this.vistaPrincipal.menuiVendedores.addActionListener(this);
            this.vistaPrincipal.menuiFormasPago.addActionListener(this);
            this.vistaPrincipal.menuiRegistrarVenta.addActionListener(this);
            this.vistaPrincipal.menuiVentasporVendedor.addActionListener(this);
            this.vistaPrincipal.menuiVendedorMayorVenta.addActionListener(this);
            this.vistaPrincipal.menuiVentasCreditoConcesionario.addActionListener(this);
            this.vistaPrincipal.menuiBusquedaporVendedor.addActionListener(this);
            // ... otros menús
        } catch (NullPointerException e) {
            System.err.println("Error: Algunos componentes del menú son nulos o no son públicos en la vista.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vistaPrincipal.menuiClientes) {
                UICliente vistaC = new UICliente();
                // Inyección: Pasamos la vista al controlador
                new ClienteController(vistaC); 
                vistaC.setVisible(true);
                
            } else if (e.getSource() == vistaPrincipal.menuiAutomoviles) {
                UIAutomoviles vistaAuto = new UIAutomoviles();
                AutomovilController ctrlAuto = new AutomovilController(vistaAuto);
                vistaAuto.setVisible(true);
                
            } else if (e.getSource() == vistaPrincipal.menuiVendedores) {
                UIVendedor vistaV = new UIVendedor();
                new VendedorController(vistaV);
                vistaV.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiClientes) {
                UICliente vistaCliente = new UICliente();
                ClienteController ctrlCliente = new ClienteController(vistaCliente);
                vistaCliente.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiVendedores) {
                UIVendedor vistaVend = new UIVendedor();
                VendedorController ctrlVend = new VendedorController(vistaVend);
                vistaVend.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiFormasPago) {
                UIFormasPago vistaFP = new UIFormasPago();
                FormasPagoController ctrlFP = new FormasPagoController(vistaFP);
                vistaFP.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiRegistrarVenta) {
                UIRegistrarventa vistaVenta = new UIRegistrarventa();
                RegistrarVentaController ctrlVenta = new RegistrarVentaController(vistaVenta);
                vistaVenta.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiVentasporVendedor) {
                UIVentasporVendedor vistaReporte = new UIVentasporVendedor();
                VentasPorVendedorController ctrlReporte = new VentasPorVendedorController(vistaReporte);
                vistaReporte.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiVendedorMayorVenta) {
                UIVendedorconMayorVenta vistaMayor = new UIVendedorconMayorVenta();
                VendedorMayorVentaController ctrlMayor = new VendedorMayorVentaController(vistaMayor);
                vistaMayor.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiVentasCreditoConcesionario) {
                UIVentasCreditoConcesionario vistaCredito = new UIVentasCreditoConcesionario();
                VentasCreditoController ctrlCredito = new VentasCreditoController(vistaCredito);
                vistaCredito.setVisible(true);
            }
            else if (e.getSource() == vistaPrincipal.menuiBusquedaporVendedor) {
                UIBuscarporVendedor vistaBuscar = new UIBuscarporVendedor();
                BuscarVendedorController ctrlBuscar = new BuscarVendedorController(vistaBuscar);
                vistaBuscar.setVisible(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Error al abrir ventana: " + ex.getMessage());
            ex.printStackTrace();
        }
        
    }
}