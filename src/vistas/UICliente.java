
package vistas;


public class UICliente extends javax.swing.JFrame {
    

    public UICliente() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDatosdelCliente = new javax.swing.JLabel();
        lblIdentificacionCliente = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtIdentificacionCliente = new javax.swing.JTextField();
        lblnombreCliente = new javax.swing.JLabel();
        txtnombreCliente = new javax.swing.JTextField();
        lblfechadeNacimientoCliente = new javax.swing.JLabel();
        txtfechadeNacimientoCliente = new javax.swing.JTextField();
        lblformatofechaNacCliente = new javax.swing.JTextField();
        lblEdadCliente = new javax.swing.JLabel();
        txtedadCliente = new javax.swing.JTextField();
        lblemailCliente = new javax.swing.JLabel();
        lblTipoIDCliente = new javax.swing.JLabel();
        cboxTipoIDCliente = new javax.swing.JComboBox();
        txtemailCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnConsultarCliente = new javax.swing.JButton();
        btnModificarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnConsultarTodosCliente = new javax.swing.JButton();
        jSeparator = new javax.swing.JSeparator();
        sptblClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Clientes");

        lblDatosdelCliente.setText("Datos del cliente");

        lblIdentificacionCliente.setText("Identificación:");

        lblnombreCliente.setText("Nombre:");

        txtnombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreClienteActionPerformed(evt);
            }
        });

        lblfechadeNacimientoCliente.setText("Fecha de Nacimiento:");

        lblformatofechaNacCliente.setEditable(false);
        lblformatofechaNacCliente.setFont(new java.awt.Font("sansserif", 2, 10)); // NOI18N
        lblformatofechaNacCliente.setText("dd/mm/aaaa");

        lblEdadCliente.setText("Edad:");

        txtedadCliente.setEditable(false);

        lblemailCliente.setText("E-mail:");

        lblTipoIDCliente.setText("Tipo ID:");

        btnGuardarCliente.setText("Guardar");

        btnConsultarCliente.setText("Consultar");

        btnModificarCliente.setText("Modificar");
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("Eliminar");

        btnConsultarTodosCliente.setText("Consultar Todos");
        btnConsultarTodosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTodosClienteActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Identificación", "Nombre", "Edad", "Correo"
            }
        ));
        sptblClientes.setViewportView(tblClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sptblClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(lblDatosdelCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblfechadeNacimientoCliente)
                                .addGap(18, 18, 18)
                                .addComponent(txtfechadeNacimientoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblformatofechaNacCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(lblEdadCliente)
                                .addGap(18, 18, 18)
                                .addComponent(txtedadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipoIDCliente)
                                .addGap(33, 33, 33)
                                .addComponent(cboxTipoIDCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdentificacionCliente)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdentificacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblemailCliente)
                                .addGap(18, 18, 18)
                                .addComponent(txtemailCliente)
                                .addGap(123, 123, 123))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblnombreCliente)
                                .addGap(28, 28, 28)
                                .addComponent(txtnombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(204, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConsultarTodosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblDatosdelCliente)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxTipoIDCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdentificacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTipoIDCliente)
                        .addComponent(lblIdentificacionCliente)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblnombreCliente)
                    .addComponent(txtnombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblfechadeNacimientoCliente)
                    .addComponent(txtfechadeNacimientoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdadCliente)
                    .addComponent(txtedadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblformatofechaNacCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblemailCliente)
                    .addComponent(txtemailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultarTodosCliente)
                    .addComponent(btnGuardarCliente)
                    .addComponent(btnConsultarCliente)
                    .addComponent(btnModificarCliente)
                    .addComponent(btnEliminarCliente))
                .addGap(26, 26, 26)
                .addComponent(sptblClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void btnConsultarTodosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarTodosClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarTodosClienteActionPerformed

    private void txtnombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreClienteActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultarCliente;
    public javax.swing.JButton btnConsultarTodosCliente;
    public javax.swing.JButton btnEliminarCliente;
    public javax.swing.JButton btnGuardarCliente;
    public javax.swing.JButton btnModificarCliente;
    public javax.swing.JComboBox cboxTipoIDCliente;
    public javax.swing.JSeparator jSeparator;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lblDatosdelCliente;
    public javax.swing.JLabel lblEdadCliente;
    public javax.swing.JLabel lblIdentificacionCliente;
    public javax.swing.JLabel lblTipoIDCliente;
    public javax.swing.JLabel lblemailCliente;
    public javax.swing.JLabel lblfechadeNacimientoCliente;
    public javax.swing.JTextField lblformatofechaNacCliente;
    public javax.swing.JLabel lblnombreCliente;
    public javax.swing.JScrollPane sptblClientes;
    public javax.swing.JTable tblClientes;
    public javax.swing.JTextField txtIdentificacionCliente;
    public javax.swing.JTextField txtedadCliente;
    public javax.swing.JTextField txtemailCliente;
    public javax.swing.JTextField txtfechadeNacimientoCliente;
    public javax.swing.JTextField txtnombreCliente;
    // End of variables declaration//GEN-END:variables
}
