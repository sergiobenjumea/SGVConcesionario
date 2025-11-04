package vistas;

public class UIAutomoviles extends javax.swing.JFrame {
    
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
        cboxMarca = new javax.swing.JComboBox<>();
        txtColor = new javax.swing.JTextField();
        cboxTipoMotor = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        cboxModelo = new javax.swing.JComboBox<>();

        jLabel7.setText("Tipo Motor");

        jButton3.setText("Consultar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Automóviles");

        lblCodigoAuto.setText("Código");

        lblMarca.setText("Marca");

        lblModelo.setText("Modelo");

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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Marca", "Modelo", "Color", "Precio Base", "Tipo de Motor"
            }
        ));
        sptblAutomovil.setViewportView(tblAutomovil);

        cboxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Acura", "Alfa Romeo", "Alpine", "Aston Martin", "Audi", "Bentley", "BMW", "Bugatti", "Buick", "BYD", "Cadillac", "Changan", "Chery", "Chevrolet", "Chrysler", "Citroën", "Cupra", "Dacia", "Dodge", "DS Automobiles", "Ferrari", "Fiat", "Ford", "Geely", "Genesis", "GMC", "Great Wall Motors (GWM)", "Haval", "Honda", "Hyundai", "Infiniti", "Isuzu", "Iveco", "Jaguar", "Jeep", "Kia", "Koenigsegg", "Lamborghini", "Lancia", "Land Rover", "Lexus", "Lincoln", "Lotus", "Maserati", "Mazda", "McLaren", "Mercedes-Benz", "MG", "Mini", "Mitsubishi", "NIO", "Nissan", "Opel", "Pagani", "Peugeot", "Polestar", "Porsche", "Ram", "Renault", "Rivian", "Rolls-Royce", "Saab", "SEAT", "Škoda", "Smart", "SsangYong", "Subaru", "Suzuki", "Tata", "Tesla", "Toyota", "Vauxhall", "Volkswagen", "Volvo", "XPeng" }));

        cboxTipoMotor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Gasolina", "Eléctrico", "Híbrido" }));

        cboxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026" }));

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
                            .addComponent(lblCodigoAuto)
                            .addComponent(lblModelo))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioBase, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarca)
                            .addComponent(lblColor)
                            .addComponent(lblTipoMotor))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboxTipoMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sptblAutomovil)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2))
                        .addGap(57, 57, 57))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigoAuto)
                    .addComponent(lblMarca)
                    .addComponent(cboxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioBase)
                    .addComponent(lblTipoMotor)
                    .addComponent(cboxTipoMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
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

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultarAutomovil;
    public javax.swing.JButton btnConsultarTodosAutomovil;
    public javax.swing.JButton btnEliminarAutomovil;
    public javax.swing.JButton btnGuardarAutomovil;
    public javax.swing.JButton btnModificarAutomovil;
    public javax.swing.JComboBox<String> cboxMarca;
    public javax.swing.JComboBox<String> cboxModelo;
    public javax.swing.JComboBox<String> cboxTipoMotor;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lblCodigoAuto;
    public javax.swing.JLabel lblColor;
    public javax.swing.JLabel lblDatosdelAutomovil;
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
