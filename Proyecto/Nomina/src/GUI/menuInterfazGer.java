/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Usuario;

/**
 *
 * @author Adan
 */
public class menuInterfazGer extends javax.swing.JFrame {
    
    private Usuario usuario;
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    /**
     * Creates new form menuInterfazGer
     */
    public menuInterfazGer(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        
        this.setLocationRelativeTo(null);
        nombreUsuario();
    }
    
    //Vizualiza en nombre de usuario en el frame
    private void nombreUsuario (){
        String nombreUsuario = getUsuario().getApellidoP() + " " + getUsuario().getApellidoM() + " " + getUsuario().getNombre();
        lblNombreUsuario.setText(nombreUsuario);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cuotaImage = new javax.swing.JLabel();
        usuarioImage = new javax.swing.JLabel();
        nominaImage = new javax.swing.JLabel();
        empleadoImage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        cerrarSesion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cuotaImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/patron.png"))); // NOI18N
        cuotaImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(cuotaImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        usuarioImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuarioNew.png"))); // NOI18N
        usuarioImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioImageMouseClicked(evt);
            }
        });
        jPanel1.add(usuarioImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        nominaImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nomina.png"))); // NOI18N
        nominaImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nominaImageMouseClicked(evt);
            }
        });
        jPanel1.add(nominaImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        empleadoImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Empleado.png"))); // NOI18N
        empleadoImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empleadoImageMouseClicked(evt);
            }
        });
        jPanel1.add(empleadoImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Seleccione el área");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jLabel3.setText("Nómina");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        jLabel4.setText("Empleado");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel2.setText("Cuota");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        jLabel5.setText("Usuarios");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));
        jPanel1.add(lblNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 90, 10));

        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarSesionMouseClicked(evt);
            }
        });
        jMenuBar1.add(cerrarSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nominaImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nominaImageMouseClicked
        nominaInterfaz nomInter = new nominaInterfaz(getUsuario());
        nomInter.setVisible(true);
        dispose();
    }//GEN-LAST:event_nominaImageMouseClicked

    private void empleadoImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empleadoImageMouseClicked
        empleadoInterfaz empInter = new empleadoInterfaz(getUsuario());
        empInter.setVisible(true);
        dispose();
    }//GEN-LAST:event_empleadoImageMouseClicked

    private void usuarioImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioImageMouseClicked
        usuarioInterfaz usuarioInter = new usuarioInterfaz(getUsuario());
        usuarioInter.setVisible(true);
        dispose();
    }//GEN-LAST:event_usuarioImageMouseClicked

    private void cerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMouseClicked
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_cerrarSesionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu cerrarSesion;
    private javax.swing.JLabel cuotaImage;
    private javax.swing.JLabel empleadoImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel nominaImage;
    private javax.swing.JLabel usuarioImage;
    // End of variables declaration//GEN-END:variables
}
