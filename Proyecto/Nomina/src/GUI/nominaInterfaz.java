/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Empleado;
import Entity.nominaEmpleado;
import Entity.Nomina;
import Entity.Usuario;
import dao.empleadoDao;
import dao.nominaDao;
import dao.nominaEmpleadoDao;
import daoMysql.empleadoDaoMysql;
import daoMysql.nominaDaoMysql;
import daoMysql.nominaEmpleadoDaoMysql;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adan
 */
public class nominaInterfaz extends javax.swing.JFrame {

    private Usuario usuario;
    
    public Usuario getUsuario(){
        return usuario;
    }
    /**
     * Creates new form nominaInterfaz
     */
    
    private nominaEmpleadoDao daoNE = new nominaEmpleadoDaoMysql();
    private nominaDao daoN= new nominaDaoMysql();
    
    public nominaInterfaz(Usuario usuario) {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        this.usuario = usuario;
        daoNE.setUsuario(getUsuario());
        daoN.setUsuario(getUsuario());
        
        cargarComboBoxEmpleado();
        cargarComboBoxNomina(1);
        SpinnerModelA(1);
        SpinnerModelB(1);
    }
    
    //Inicializa y carga los box de empleados desde la DB
    private void cargarComboBoxEmpleado(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        empleadoDao dao = new empleadoDaoMysql();
        dao.setUsuario(getUsuario());
        
        List <Empleado> listaEmpleado = dao.obtenTodos();
        for(int j=0; j<listaEmpleado.size(); j++){
            model.addElement(listaEmpleado.get(j));
        }
        
        empleadoCombo.setModel(model);
        empleadoBoxCon.setModel(model);
    }
    
    //Inicializa y carga los box de nomina desde la DB
    private void cargarComboBoxNomina(int tipo){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        nominaDao dao = new nominaDaoMysql();
        
        List <Nomina> listaEmpleado = dao.obtenTipo(tipo);
        for(int j=0; j<listaEmpleado.size(); j++){
            model.addElement(listaEmpleado.get(j));
        }
        
        nominaCombo.setModel(model);
        nominaComboConsulta.setModel(model);
        //empleadoBoxCon.setModel(model);
    }
    
    
    private void agregaEnTabla(List <nominaEmpleado> listaNomina){
        double total = 0;
        DefaultTableModel modelo = (DefaultTableModel) tableNomina.getModel();
        modelo.setRowCount(0);
        Object []nom=new Object[9];
        for (int i=0; i<listaNomina.size(); i++){
            nominaEmpleado nomina = listaNomina.get(i);
            
            nom[0] = nomina.getId();
            nom[1] = nomina.getEmpleado().getApellidoP()+ " " +nomina.getEmpleado().getApellidoM()+ " " + nomina.getEmpleado().getNombre();
            nom[2] = nomina.getEmpleado().getSueldo();
            nom[3] = nomina.getEmpleado().getSdi();
            nom[4] = nomina.getDiasTrabajados();
            nom[5] = nomina.getInfonavit();
            nom[6] = nomina.getCuotaImss();
            nom[7] = nomina.getCensantiaVejez();
            nom[8] = nomina.getSueldoNeto();
            
            modelo.addRow(nom);
            total += nomina.getSueldoNeto();
        }
        
        lblTotal.setText(Double.toString(total));
        alinear();
    }
    
    // PARA ALINEAR LOS DATOS DE LA TABLA
    private void alinear(){
       DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
       tcr.setHorizontalAlignment(SwingConstants.LEFT);
               
       tableNomina.getColumnModel().getColumn(0).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(1).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(2).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(3).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(4).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(5).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(6).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(7).setCellRenderer(tcr);
       tableNomina.getColumnModel().getColumn(8).setCellRenderer(tcr);
    }
    
    //Actualizar el primer spinner
    private void SpinnerModelA(int tipo){
        if (tipo == 1){
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,7,1);
            diasTrabajoSpinner.setModel(model);
        }else if (tipo == 2){
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,31,1);
            diasTrabajoSpinner.setModel(model);
        }else if (tipo == 3){
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,365,1);
            diasTrabajoSpinner.setModel(model);
        }
    }
    
    //Actualizar el segundo spinner
    private void SpinnerModelB(int tipo){
        if (tipo == 1){
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,7,1);
            diasTrabajoSpinnerCambio.setModel(model);
        }else if (tipo == 2){
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,31,1);
            diasTrabajoSpinnerCambio.setModel(model);
        }else if (tipo == 3){
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,365,1);
            diasTrabajoSpinnerCambio.setModel(model);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        inicioDate = new com.toedter.calendar.JDateChooser();
        finDate = new com.toedter.calendar.JDateChooser();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        tipoCombo = new javax.swing.JComboBox<>();
        lblRegistro = new javax.swing.JLabel();
        btnAgregarN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        empleadoCombo = new javax.swing.JComboBox<>();
        nominaCombo = new javax.swing.JComboBox<>();
        txtInfonavit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        diasTrabajoSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        btnAgregarNE = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        registroComboCon = new javax.swing.JComboBox<>();
        empleadoBoxCon = new javax.swing.JComboBox<>();
        btnTodoCon = new javax.swing.JButton();
        btnEmpleadoCon = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNomina = new javax.swing.JTable();
        btnEliminarRegistro = new javax.swing.JButton();
        lblDiasTrabajados = new javax.swing.JLabel();
        lblInfonavitCambio = new javax.swing.JLabel();
        diasTrabajoSpinnerCambio = new javax.swing.JSpinner();
        txtInfonavitCambio = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        nominaComboConsulta = new javax.swing.JComboBox<>();
        btnFechaCon = new javax.swing.JToggleButton();
        jLabel8 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        cerrarSesion = new javax.swing.JMenu();
        regresar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(inicioDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 130, 154, -1));
        jPanel1.add(finDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 202, 154, -1));

        lblFechaInicio.setText("Fecha Inicio");
        jPanel1.add(lblFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 108, -1, -1));

        lblFechaFin.setText("Fecha fin");
        jPanel1.add(lblFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, -1));

        tipoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semanal", "Mensual", "Anual" }));
        tipoCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoComboItemStateChanged(evt);
            }
        });
        jPanel1.add(tipoCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 154, -1));

        lblRegistro.setText("Tipo de registo");
        jPanel1.add(lblRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        btnAgregarN.setText("Agregar Nómina");
        btnAgregarN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarN, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 278, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Nueva Nomina");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 15, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Agregar Empleado");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setMaximumSize(new java.awt.Dimension(248, 24));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 260, 30));

        jPanel1.add(empleadoCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 230, -1));

        jPanel1.add(nominaCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 193, 230, -1));
        jPanel1.add(txtInfonavit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 172, -1));

        jLabel3.setText("Empleado");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        jLabel4.setText("Nómina");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, -1, -1));

        jLabel5.setText("Días trabajados");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, -1, -1));
        jPanel1.add(diasTrabajoSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 75, -1));

        jLabel6.setText("Crédito Infonavit");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, -1));

        btnAgregarNE.setText("Insertar");
        btnAgregarNE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNEActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarNE, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, -1, -1));

        jTabbedPane1.addTab("Agregar", jPanel1);

        registroComboCon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semanal", "Mensual", "Anual" }));
        registroComboCon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                registroComboConItemStateChanged(evt);
            }
        });

        btnTodoCon.setText("Todo");
        btnTodoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodoConActionPerformed(evt);
            }
        });

        btnEmpleadoCon.setText("Empleado");
        btnEmpleadoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadoConActionPerformed(evt);
            }
        });

        tableNomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Empleado", "Sueldo Diario", "SDI", "Días Trabajados", "Infonavit", "Cuota IMSS", "Cesantía y Vejez", "Sueldo Neto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Short.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableNomina);
        if (tableNomina.getColumnModel().getColumnCount() > 0) {
            tableNomina.getColumnModel().getColumn(0).setResizable(false);
            tableNomina.getColumnModel().getColumn(1).setResizable(false);
            tableNomina.getColumnModel().getColumn(2).setResizable(false);
            tableNomina.getColumnModel().getColumn(3).setResizable(false);
            tableNomina.getColumnModel().getColumn(4).setResizable(false);
            tableNomina.getColumnModel().getColumn(5).setResizable(false);
            tableNomina.getColumnModel().getColumn(6).setResizable(false);
            tableNomina.getColumnModel().getColumn(7).setResizable(false);
            tableNomina.getColumnModel().getColumn(8).setResizable(false);
        }

        btnEliminarRegistro.setText("Eliminar Registro");
        btnEliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRegistroActionPerformed(evt);
            }
        });

        lblDiasTrabajados.setText("Días Trabajados:");

        lblInfonavitCambio.setText("Infonavit:");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel7.setText("Para realizar una acción seleccione la fila de la tabla");

        btnFechaCon.setText("Fecha");
        btnFechaCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaConActionPerformed(evt);
            }
        });

        jLabel8.setText("TOTAL:");

        lblTotal.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTodoCon, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnFechaCon, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnEmpleadoCon)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominaComboConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registroComboCon, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empleadoBoxCon, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblInfonavitCambio)
                                    .addComponent(lblDiasTrabajados))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(diasTrabajoSpinnerCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInfonavitCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminarRegistro)
                                    .addComponent(btnModificar))))
                        .addGap(0, 147, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(empleadoBoxCon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registroComboCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nominaComboConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechaCon)
                    .addComponent(btnTodoCon)
                    .addComponent(btnEmpleadoCon))
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiasTrabajados)
                    .addComponent(diasTrabajoSpinnerCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfonavitCambio)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtInfonavitCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar)))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Consultar", jPanel3);

        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarSesionMouseClicked(evt);
            }
        });
        jMenuBar1.add(cerrarSesion);

        regresar.setText("Regresar");
        regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regresarMouseClicked(evt);
            }
        });
        jMenuBar1.add(regresar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTodoConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodoConActionPerformed
        int tipo = 0;
        switch (registroComboCon.getSelectedItem().toString()){
            case "Semanal": tipo = 1; break;
            case "Mensual": tipo = 2; break;
            case "Anual": tipo = 3; break;
        }

        List <nominaEmpleado> listaNomina = daoNE.obtenTodos(tipo);
        agregaEnTabla(listaNomina);
    }//GEN-LAST:event_btnTodoConActionPerformed

    //Carga los elementos de agregar segun su tipo
    private void tipoComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoComboItemStateChanged
        int tipo = 0;

        switch (tipoCombo.getSelectedItem().toString()){
            case "Semanal": tipo = 1; break;
            case "Mensual": tipo = 2; break;
            case "Anual": tipo = 3; break;
        }
        cargarComboBoxNomina(tipo);
        SpinnerModelA(tipo);
    }//GEN-LAST:event_tipoComboItemStateChanged

    private void btnAgregarNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNActionPerformed
        Nomina nomina = new Nomina ();
        nomina.setFechaInicio(inicioDate.getDate());
        nomina.setFechaFin(finDate.getDate());
        
        int tipo = 0;
        switch (tipoCombo.getSelectedItem().toString()){
            case "Semanal": tipo = 1; break;
            case "Mensual": tipo = 2; break;
            case "Anual": tipo = 3; break;
        }
        nomina.setTipo(tipo);
        daoN.Agrega(nomina);
        cargarComboBoxNomina(tipo);
    }//GEN-LAST:event_btnAgregarNActionPerformed

    private void btnAgregarNEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNEActionPerformed
        Empleado empleado = (Empleado) empleadoCombo.getSelectedItem();
        Nomina nomina = (Nomina) nominaCombo.getSelectedItem();
        
        nominaEmpleado nominaEmp = new nominaEmpleado();
        nominaEmp.setEmpleado(empleado);
        nominaEmp.setNomina(nomina);
        nominaEmp.setInfonavit(Double.parseDouble(txtInfonavit.getText()));
        nominaEmp.setDiasTrabajados(Integer.parseInt(diasTrabajoSpinner.getValue().toString()));
        
        daoNE.Agrega(nominaEmp);
    }//GEN-LAST:event_btnAgregarNEActionPerformed

    //Consulta por empleado
    private void btnEmpleadoConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoConActionPerformed
        Empleado emp = (Empleado) empleadoBoxCon.getSelectedItem();
        List <nominaEmpleado> listaNomina = daoNE.obtenNominaEmpleado(emp.getCodigo());
        
        agregaEnTabla(listaNomina);
    }//GEN-LAST:event_btnEmpleadoConActionPerformed
    
    //ELIMINAR NOMINA
    private void btnEliminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRegistroActionPerformed
        int row = tableNomina.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tableNomina.getModel();
        
        int id = Integer.parseInt(tableNomina.getValueAt(row, 0).toString());
        
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quiere eliminar?","¡ADVERTENCIA!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            daoNE.Elimina(id);
            modelo.removeRow(row);
        }
        
        
    }//GEN-LAST:event_btnEliminarRegistroActionPerformed

    private void cerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMouseClicked
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_cerrarSesionMouseClicked

    private void regresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regresarMouseClicked
        if(getUsuario().getRole().equals("CONTADOR")){
            menuInterfazCon conInter = new menuInterfazCon(getUsuario());
            conInter.setVisible(true);
            dispose();
        }else{
            menuInterfazGer gerInter = new menuInterfazGer(getUsuario());
            gerInter.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_regresarMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int row = tableNomina.getSelectedRow();
        double infonavit;

        if (row >-1){
            
            
            nominaEmpleado nominaE = new nominaEmpleado();
            Nomina nomina = (Nomina) nominaComboConsulta.getSelectedItem();
            Empleado empleado= new Empleado();
            
            DefaultTableModel modelo = (DefaultTableModel) tableNomina.getModel();
            
            //Obtenemos el id de la nomina
            nominaE.setId(Integer.parseInt(tableNomina.getValueAt(row, 0).toString()));
             
            //Capturamos el resultado de infonavit
            if(txtInfonavitCambio.getText().equals("")){
                infonavit = Double.parseDouble(tableNomina.getValueAt(row, 5).toString());
            }else{
                infonavit = Double.parseDouble(txtInfonavitCambio.getText());
            }
            
            //Campturamos el resultado de dias trabajados
            int diasTrabajados = Integer.parseInt(diasTrabajoSpinnerCambio.getValue().toString());
            
            if( diasTrabajados <= 0.0){
                diasTrabajados = Integer.parseInt(tableNomina.getValueAt(row, 4).toString());
            }
            
            //Obtenemos el sdi y sueldo del empleado para los calculos
            double sdi = Double.parseDouble(tableNomina.getValueAt(row, 3).toString());
            double sueldo = Double.parseDouble(tableNomina.getValueAt(row, 2).toString());
            
            empleado.setSdi(sdi);
            empleado.setSueldo(sueldo);
            
            nominaE.setEmpleado(empleado);
            
            nominaE.setInfonavit(infonavit);
            nominaE.setDiasTrabajados(diasTrabajados);
            
            //Obnetenos la nomina
            nominaE.setNomina(nomina);
                        
            daoNE.Actualiza(nominaE);
                        
            //Limpiamos los campos de modificacion
            diasTrabajoSpinnerCambio.setValue(0);
            txtInfonavitCambio.setText("");
            
        }else{
            System.out.println("Seleccione una nomina");
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void registroComboConItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_registroComboConItemStateChanged
        int tipo = 0;

        switch (registroComboCon.getSelectedItem().toString()){
            case "Semanal": tipo = 1; break;
            case "Mensual": tipo = 2; break;
            case "Anual": tipo = 3; break;
        }
        cargarComboBoxNomina(tipo);
        SpinnerModelB(tipo);
    }//GEN-LAST:event_registroComboConItemStateChanged

    private void btnFechaConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaConActionPerformed
        Nomina nomina = (Nomina) nominaComboConsulta.getSelectedItem();
        List <nominaEmpleado> listaNomina = daoNE.obtenNominaFecha(nomina.getId());
        
        agregaEnTabla(listaNomina);
    }//GEN-LAST:event_btnFechaConActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarN;
    private javax.swing.JButton btnAgregarNE;
    private javax.swing.JButton btnEliminarRegistro;
    private javax.swing.JButton btnEmpleadoCon;
    private javax.swing.JToggleButton btnFechaCon;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnTodoCon;
    private javax.swing.JMenu cerrarSesion;
    private javax.swing.JSpinner diasTrabajoSpinner;
    private javax.swing.JSpinner diasTrabajoSpinnerCambio;
    private javax.swing.JComboBox<String> empleadoBoxCon;
    private javax.swing.JComboBox<String> empleadoCombo;
    private com.toedter.calendar.JDateChooser finDate;
    private com.toedter.calendar.JDateChooser inicioDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDiasTrabajados;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblInfonavitCambio;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JComboBox<String> nominaCombo;
    private javax.swing.JComboBox<String> nominaComboConsulta;
    private javax.swing.JComboBox<String> registroComboCon;
    private javax.swing.JMenu regresar;
    private javax.swing.JTable tableNomina;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JTextField txtInfonavit;
    private javax.swing.JTextField txtInfonavitCambio;
    // End of variables declaration//GEN-END:variables
}
