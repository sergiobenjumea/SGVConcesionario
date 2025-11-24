package vistas;



public class UIAutomoviles extends javax.swing.JFrame {
    // Declaraciones variables sin genéricos para compatibilidad con diseñador NetBeans
    public UIAutomoviles() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lblCodigoAuto = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblPrecioBase = new javax.swing.JLabel();
        lblTipoMotor = new javax.swing.JLabel();
        lblDatosdelAutomovil = new javax.swing.JLabel();
        btnGuardarAutomovil = new javax.swing.JButton();
        btnConsultarAutomovil = new javax.swing.JButton();
        btnModificarAutomovil = new javax.swing.JButton();
        btnEliminarAutomovil = new javax.swing.JButton();
        btnConsultarTodosAutomovil = new javax.swing.JButton();
        sptblAutomovil = new javax.swing.JScrollPane();
        tblAutomovil = new javax.swing.JTable();
        txtCodigo = new javax.swing.JTextField();
        txtPrecioBase = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        cboxTipoMotor = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        cboxAnio = new javax.swing.JComboBox<>();
        lblLinea = new javax.swing.JLabel();
        cboxLinea = new javax.swing.JComboBox();
        cboxMarca = new javax.swing.JComboBox();

        jLabel7.setText("Tipo Motor");

        jButton3.setText("Consultar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Automóviles");

        lblCodigoAuto.setText("Código");

        lblMarca.setText("Marca");

        lblModelo.setText("Año");

        lblColor.setText("Color");

        lblPrecioBase.setText("Precio Base");

        lblTipoMotor.setText("Tipo de Motor");

        lblDatosdelAutomovil.setText("Datos del Automóvil");

        btnGuardarAutomovil.setText("Guardar");

        btnConsultarAutomovil.setText("Consultar");

        btnModificarAutomovil.setText("Modificar");

        btnEliminarAutomovil.setText("Eliminar");

        btnConsultarTodosAutomovil.setText("Consultar Todos");

        tblAutomovil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Marca", "Línea", "Año", "Color", "Precio Base", "Tipo de Motor"
            }
        ));
        sptblAutomovil.setViewportView(tblAutomovil);

        lblLinea.setText("Línea");

        cboxLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLineaActionPerformed(evt);
            }
        });

        cboxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrecioBase)
                            .addComponent(lblModelo))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioBase, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColor)
                            .addComponent(lblTipoMotor))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboxTipoMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE))
                            .addComponent(txtColor)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodigoAuto)
                        .addGap(18, 18, 18)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lblMarca)
                        .addGap(18, 18, 18)
                        .addComponent(cboxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblLinea)
                        .addGap(18, 18, 18)
                        .addComponent(cboxLinea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(sptblAutomovil)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(lblDatosdelAutomovil)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnGuardarAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarAutomovil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificarAutomovil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarAutomovil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnConsultarTodosAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblDatosdelAutomovil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigoAuto)
                        .addComponent(lblMarca)
                        .addComponent(lblLinea)
                        .addComponent(cboxLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioBase)
                    .addComponent(lblTipoMotor)
                    .addComponent(cboxTipoMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultarTodosAutomovil)
                    .addComponent(btnGuardarAutomovil)
                    .addComponent(btnConsultarAutomovil)
                    .addComponent(btnModificarAutomovil)
                    .addComponent(btnEliminarAutomovil))
                .addGap(29, 29, 29)
                .addComponent(sptblAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboxLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLineaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxLineaActionPerformed

    private void cboxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxMarcaActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultarAutomovil;
    public javax.swing.JButton btnConsultarTodosAutomovil;
    public javax.swing.JButton btnEliminarAutomovil;
    public javax.swing.JButton btnGuardarAutomovil;
    public javax.swing.JButton btnModificarAutomovil;
    public javax.swing.JComboBox<String> cboxAnio;
    public javax.swing.JComboBox cboxLinea;
    public javax.swing.JComboBox cboxMarca;
    public javax.swing.JComboBox cboxTipoMotor;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lblCodigoAuto;
    public javax.swing.JLabel lblColor;
    public javax.swing.JLabel lblDatosdelAutomovil;
    public javax.swing.JLabel lblLinea;
    public javax.swing.JLabel lblMarca;
    public javax.swing.JLabel lblModelo;
    public javax.swing.JLabel lblPrecioBase;
    public javax.swing.JLabel lblTipoMotor;
    public javax.swing.JScrollPane sptblAutomovil;
    public javax.swing.JTable tblAutomovil;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtColor;
    public javax.swing.JTextField txtPrecioBase;
    // End of variables declaration//GEN-END:variables
}
