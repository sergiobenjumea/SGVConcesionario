
package vistas;

import Controladores.PrincipalController;


public class UIPrincipal extends javax.swing.JFrame {
    
    public UIPrincipal() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mbPrincipal = new javax.swing.JMenuBar();
        menuDatos = new javax.swing.JMenu();
        menuiClientes = new javax.swing.JMenuItem();
        menuiAutomoviles = new javax.swing.JMenuItem();
        menuiVendedores = new javax.swing.JMenuItem();
        menuiFormasPago = new javax.swing.JMenuItem();
        menuVentas = new javax.swing.JMenu();
        menuiRegistrarVenta = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        menuiVentasporVendedor = new javax.swing.JMenuItem();
        menuiVendedorMayorVenta = new javax.swing.JMenuItem();
        menuiBusquedaporVendedor = new javax.swing.JMenuItem();
        menuiVentasCreditoConcesionario = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenu();
        menuiSalirdelSistema = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Gestión de Ventas Concesionario");

        menuDatos.setText("Datos");

        menuiClientes.setText("Clientes");
        menuiClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuiClientesActionPerformed(evt);
            }
        });
        menuDatos.add(menuiClientes);

        menuiAutomoviles.setText("Automóviles");
        menuiAutomoviles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuiAutomovilesActionPerformed(evt);
            }
        });
        menuDatos.add(menuiAutomoviles);

        menuiVendedores.setText("Vendedores");
        menuDatos.add(menuiVendedores);

        menuiFormasPago.setText("Formas de Pago");
        menuDatos.add(menuiFormasPago);

        mbPrincipal.add(menuDatos);

        menuVentas.setText("Ventas");

        menuiRegistrarVenta.setText("Registrar Venta");
        menuiRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuiRegistrarVentaActionPerformed(evt);
            }
        });
        menuVentas.add(menuiRegistrarVenta);

        mbPrincipal.add(menuVentas);

        menuConsultas.setText("Consultas");

        menuiVentasporVendedor.setText("Ventas por Vendedor");
        menuConsultas.add(menuiVentasporVendedor);

        menuiVendedorMayorVenta.setText("Vendedor con Mayor Venta");
        menuConsultas.add(menuiVendedorMayorVenta);

        menuiBusquedaporVendedor.setText("Búsqueda por Vendedor");
        menuConsultas.add(menuiBusquedaporVendedor);

        menuiVentasCreditoConcesionario.setText("Ventas con Crédito Concesionario");
        menuiVentasCreditoConcesionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuiVentasCreditoConcesionarioActionPerformed(evt);
            }
        });
        menuConsultas.add(menuiVentasCreditoConcesionario);

        mbPrincipal.add(menuConsultas);

        menuSalir.setText("Salir");

        menuiSalirdelSistema.setText("Salir del Sistema");
        menuSalir.add(menuiSalirdelSistema);

        mbPrincipal.add(menuSalir);

        setJMenuBar(mbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuiAutomovilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuiAutomovilesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuiAutomovilesActionPerformed

    private void menuiClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuiClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuiClientesActionPerformed

    private void menuiRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuiRegistrarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuiRegistrarVentaActionPerformed

    private void menuiVentasCreditoConcesionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuiVentasCreditoConcesionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuiVentasCreditoConcesionarioActionPerformed

   
public static void main(String args[]) {
    // Look and Feel (Opcional)
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception ex) {}

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Iniciar la aplicación con el controlador
            new PrincipalController(new UIPrincipal());
        }
    });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuBar mbPrincipal;
    public javax.swing.JMenu menuConsultas;
    public javax.swing.JMenu menuDatos;
    public javax.swing.JMenu menuSalir;
    public javax.swing.JMenu menuVentas;
    public javax.swing.JMenuItem menuiAutomoviles;
    public javax.swing.JMenuItem menuiBusquedaporVendedor;
    public javax.swing.JMenuItem menuiClientes;
    public javax.swing.JMenuItem menuiFormasPago;
    public javax.swing.JMenuItem menuiRegistrarVenta;
    public javax.swing.JMenuItem menuiSalirdelSistema;
    public javax.swing.JMenuItem menuiVendedorMayorVenta;
    public javax.swing.JMenuItem menuiVendedores;
    public javax.swing.JMenuItem menuiVentasCreditoConcesionario;
    public javax.swing.JMenuItem menuiVentasporVendedor;
    // End of variables declaration//GEN-END:variables
}
