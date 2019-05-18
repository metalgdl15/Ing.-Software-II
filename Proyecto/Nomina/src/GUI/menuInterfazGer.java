/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Adan
 */
public class menuInterfazGer extends javax.swing.JFrame {

    /**
     * Creates new form menuInterfazGer
     */
    public menuInterfazGer() {
        initComponents();
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
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
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
        nominaInterfaz nomInter = new nominaInterfaz();
        nomInter.setVisible(true);
        dispose();
    }//GEN-LAST:event_nominaImageMouseClicked

    private void empleadoImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empleadoImageMouseClicked
        empleadoInterfaz empInter = new empleadoInterfaz();
        empInter.setVisible(true);
        dispose();
    }//GEN-LAST:event_empleadoImageMouseClicked

    private void usuarioImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioImageMouseClicked
        usuarioInterfaz usuarioInter = new usuarioInterfaz();
        usuarioInter.setVisible(true);
        dispose();
    }//GEN-LAST:event_usuarioImageMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuInterfazGer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuInterfazGer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuInterfazGer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuInterfazGer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuInterfazGer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cuotaImage;
    private javax.swing.JLabel empleadoImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nominaImage;
    private javax.swing.JLabel usuarioImage;
    // End of variables declaration//GEN-END:variables
}
