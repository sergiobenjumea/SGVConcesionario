
package vistas;

public class UIFormasPago extends javax.swing.JFrame {
    
    public UIFormasPago() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFormasdePago = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblCodigo = new javax.swing.JLabel();
        btnGuardarFormasPago = new javax.swing.JButton();
        btnConsultarFormasPago = new javax.swing.JButton();
        btnModificarFormasPago = new javax.swing.JButton();
        btnEliminarFormasPago = new javax.swing.JButton();
        btnConsultarTodosFormasPago = new javax.swing.JButton();
        spFormasPago = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtFormasPago = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formas de Pago");

        lblFormasdePago.setText("Formas de Pago");

        lblCodigo.setText("Forma de Pago");

        btnGuardarFormasPago.setText("Guardar");

        btnConsultarFormasPago.setText("Consultar");

        btnModificarFormasPago.setText("Modificar");
        btnModificarFormasPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarFormasPagoActionPerformed(evt);
            }
        });

        btnEliminarFormasPago.setText("Eliminar");

        btnConsultarTodosFormasPago.setText("Consultar Todos");
        btnConsultarTodosFormasPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTodosFormasPagoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Descripción"
            }
        ));
        spFormasPago.setViewportView(jTable1);

        txtFormasPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormasPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(spFormasPago)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(lblFormasdePago))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(lblCodigo)
                        .addGap(27, 27, 27)
                        .addComponent(txtFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(233, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(btnGuardarFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnConsultarFormasPago)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnModificarFormasPago)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnEliminarFormasPago)
                    .addGap(54, 54, 54)
                    .addComponent(btnConsultarTodosFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(80, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblFormasdePago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(spFormasPago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(229, 229, 229)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnConsultarTodosFormasPago)
                        .addComponent(btnGuardarFormasPago)
                        .addComponent(btnConsultarFormasPago)
                        .addComponent(btnModificarFormasPago)
                        .addComponent(btnEliminarFormasPago))
                    .addContainerGap(229, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarFormasPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarFormasPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarFormasPagoActionPerformed

    private void btnConsultarTodosFormasPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarTodosFormasPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarTodosFormasPagoActionPerformed

    private void txtFormasPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormasPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormasPagoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultarFormasPago;
    public javax.swing.JButton btnConsultarTodosFormasPago;
    public javax.swing.JButton btnEliminarFormasPago;
    public javax.swing.JButton btnGuardarFormasPago;
    public javax.swing.JButton btnModificarFormasPago;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel lblCodigo;
    public javax.swing.JLabel lblFormasdePago;
    public javax.swing.JScrollPane spFormasPago;
    public javax.swing.JTextField txtFormasPago;
    // End of variables declaration//GEN-END:variables
}
