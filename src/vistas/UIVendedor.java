
package vistas;

public class UIVendedor extends javax.swing.JFrame {
    
    public UIVendedor() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblDatosdelVendedor = new javax.swing.JLabel();
        lblidVendedor = new javax.swing.JLabel();
        lblnombreVendedor = new javax.swing.JLabel();
        lblProfesionVendedor = new javax.swing.JLabel();
        txtidVendedor = new javax.swing.JTextField();
        txtnombreVendedor = new javax.swing.JTextField();
        txtprofesionVendedor = new javax.swing.JTextField();
        btnGuardarVendedor = new javax.swing.JButton();
        btnConsultarVendedor = new javax.swing.JButton();
        btnModificarVendedor = new javax.swing.JButton();
        btnEliminarVendedor = new javax.swing.JButton();
        btnConsultarTodosVendedor = new javax.swing.JButton();
        spVendedor = new javax.swing.JScrollPane();
        tblVendedores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Vendedores");

        lblDatosdelVendedor.setText("Datos del Vendedor");

        lblidVendedor.setText("ID Vendedor");

        lblnombreVendedor.setText("Nombre:");

        lblProfesionVendedor.setText("Profesión:");

        btnGuardarVendedor.setText("Guardar");

        btnConsultarVendedor.setText("Consultar");

        btnModificarVendedor.setText("Modificar");
        btnModificarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarVendedorActionPerformed(evt);
            }
        });

        btnEliminarVendedor.setText("Eliminar");

        btnConsultarTodosVendedor.setText("Consultar Todos");
        btnConsultarTodosVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTodosVendedorActionPerformed(evt);
            }
        });

        tblVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Vendedor", "Nombre", "Profesión"
            }
        ));
        spVendedor.setViewportView(tblVendedores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(lblDatosdelVendedor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblidVendedor)
                            .addComponent(lblnombreVendedor)
                            .addComponent(lblProfesionVendedor))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprofesionVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                            .addComponent(spVendedor))))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(btnGuardarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificarVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarVendedor)
                .addGap(54, 54, 54)
                .addComponent(btnConsultarTodosVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblDatosdelVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblidVendedor)
                    .addComponent(txtidVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblnombreVendedor)
                    .addComponent(txtnombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProfesionVendedor)
                    .addComponent(txtprofesionVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultarTodosVendedor)
                    .addComponent(btnGuardarVendedor)
                    .addComponent(btnConsultarVendedor)
                    .addComponent(btnModificarVendedor)
                    .addComponent(btnEliminarVendedor))
                .addGap(28, 28, 28)
                .addComponent(spVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarVendedorActionPerformed

    private void btnConsultarTodosVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarTodosVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarTodosVendedorActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultarTodosVendedor;
    public javax.swing.JButton btnConsultarVendedor;
    public javax.swing.JButton btnEliminarVendedor;
    public javax.swing.JButton btnGuardarVendedor;
    public javax.swing.JButton btnModificarVendedor;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lblDatosdelVendedor;
    public javax.swing.JLabel lblProfesionVendedor;
    public javax.swing.JLabel lblidVendedor;
    public javax.swing.JLabel lblnombreVendedor;
    public javax.swing.JScrollPane spVendedor;
    public javax.swing.JTable tblVendedores;
    public javax.swing.JTextField txtidVendedor;
    public javax.swing.JTextField txtnombreVendedor;
    public javax.swing.JTextField txtprofesionVendedor;
    // End of variables declaration//GEN-END:variables
}
