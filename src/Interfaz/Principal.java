/**
 *
 * @author Ximena
 */
//--------------------------------------------------------------------------------------------------------------------------------------------------//

package Interfaz;

import java.sql.Connection;
import Conexion.Conexion;
import java.awt.Desktop;
import javax.swing.JOptionPane;

//CÓDIGO DE LA IMAGEN DE FONDO 
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
//--------------------------------------------------------------------------------------------------------------------------------------------------//

public class Principal extends javax.swing.JFrame {

    //CONEXION
    Conexion enlace = new Conexion();
    Connection conect = enlace.Conexion();
    
    //CÓDIGO DE LA IMAGEN DE FONDO
    Fondo fondo = new Fondo();
    
    //PARA QUE SE MUESTRE LA INFORMACIÓN DEL MENÚ (INTERNAL FRAME)
    
    Invetario_Herramientas_InternalFrame inventario_herramientas_InternalFrame;
    Empleados_InternalFrame empleados_InternalFrame;
    Usuarios_InternalFrame usuarios_InternalFrame;
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public Principal(){
       //--------------------------------------------------------------------------------------------------------------------------------------------------//
 
        this.setContentPane(fondo); //PARA QUE SE MUESTRE LA IMAGEN
       //--------------------------------------------------------------------------------------------------------------------------------------------------//
 
        //PARA QUE SE MUESTRE LA INFORMACIÓN DEL MENÚ (INTERNAL FRAME)
       
        inventario_herramientas_InternalFrame = new Invetario_Herramientas_InternalFrame();
        empleados_InternalFrame = new Empleados_InternalFrame();
        usuarios_InternalFrame = new Usuarios_InternalFrame();

        add(inventario_herramientas_InternalFrame);
        add(empleados_InternalFrame);
        add(usuarios_InternalFrame);
        
        //POR DEFECTO
        initComponents();
        
        probarConexion();//metodo
       
        setExtendedState (MAXIMIZED_BOTH); //PANTALLA COMPLETA
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public void probarConexion(){
        if (conect == null) {
            JOptionPane.showMessageDialog(null, "No se logró la conexión"); // en caso de que quede sin nada
        } else {
            //Si se logra
            JOptionPane.showMessageDialog(null, "La conexión se establecio correctamente!");
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    //Para abrir el manual del usuario 
    public void abrirAtchivo(String archivo){

        try {

            File objetoFile = new File(archivo);
            Desktop.getDesktop().open(objetoFile);

        } catch (IOException ex) {// problemas para encontrar el recurso especifico
            System.out.println(ex);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        Archivos = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        JMenu_Herramientas = new javax.swing.JMenu();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        JItem_Herramientas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        Empleados = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        Usuarios = new javax.swing.JMenuItem();
        Sistema = new javax.swing.JMenu();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        Salir = new javax.swing.JMenuItem();
        Ayuda = new javax.swing.JMenu();
        Conexion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Archivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/folder.png"))); // NOI18N
        Archivos.setText("Archivos");
        Archivos.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        Archivos.add(jSeparator1);

        JMenu_Herramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/herramientas.png"))); // NOI18N
        JMenu_Herramientas.setText("Herramientas");
        JMenu_Herramientas.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        JMenu_Herramientas.add(jSeparator6);

        JItem_Herramientas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        JItem_Herramientas.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        JItem_Herramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/editarH.png"))); // NOI18N
        JItem_Herramientas.setText("Registro Herramientas");
        JItem_Herramientas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JItem_HerramientasActionPerformed(evt);
            }
        });
        JMenu_Herramientas.add(JItem_Herramientas);

        Archivos.add(JMenu_Herramientas);
        Archivos.add(jSeparator2);
        Archivos.add(jSeparator3);

        Empleados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        Empleados.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/empleados.png"))); // NOI18N
        Empleados.setText("Empleados");
        Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpleadosActionPerformed(evt);
            }
        });
        Archivos.add(Empleados);
        Archivos.add(jSeparator4);

        Usuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        Usuarios.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        Usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/usuarios.png"))); // NOI18N
        Usuarios.setText("Usuarios");
        Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosActionPerformed(evt);
            }
        });
        Archivos.add(Usuarios);

        jMenuBar1.add(Archivos);

        Sistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/sistema2.png"))); // NOI18N
        Sistema.setText("Opciones del Sistema");
        Sistema.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        Sistema.add(jSeparator5);

        Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        Salir.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/cerrar1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        Sistema.add(Salir);

        jMenuBar1.add(Sistema);

        Ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/question1.png"))); // NOI18N
        Ayuda.setText("Ayuda");
        Ayuda.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N

        Conexion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_NUMPAD6, java.awt.event.InputEvent.CTRL_MASK));
        Conexion.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        Conexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/logo_herramienta.png"))); // NOI18N
        Conexion.setText("Comprobar Conexión");
        Conexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConexionActionPerformed(evt);
            }
        });
        Ayuda.add(Conexion);

        jMenuBar1.add(Ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
                //Información del botón salir
        JOptionPane.showMessageDialog(null, "Gracias por su visita. "+"\n"+" Vuelva Pronto");
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosActionPerformed
        // PARA QUE SE MUESTRE EL FORMULARIO CUANDO SE DE CLICK
        usuarios_InternalFrame.setVisible(true);
    }//GEN-LAST:event_UsuariosActionPerformed

    private void EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpleadosActionPerformed
        // PARA QUE SE MUESTRE EL FORMULARIO CUANDO SE DE CLICK
        empleados_InternalFrame.show();
    }//GEN-LAST:event_EmpleadosActionPerformed

    private void JItem_HerramientasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JItem_HerramientasActionPerformed
        // PARA QUE SE MUESTRE EL FORMULARIO CUANDO SE DE CLICK
        inventario_herramientas_InternalFrame.show();
    }//GEN-LAST:event_JItem_HerramientasActionPerformed

    private void ConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConexionActionPerformed

        probarConexion();
    }//GEN-LAST:event_ConexionActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Archivos;
    private javax.swing.JMenu Ayuda;
    private javax.swing.JMenuItem Conexion;
    private javax.swing.JMenuItem Empleados;
    private javax.swing.JMenuItem JItem_Herramientas;
    private javax.swing.JMenu JMenu_Herramientas;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenu Sistema;
    private javax.swing.JMenuItem Usuarios;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    // End of variables declaration//GEN-END:variables
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    class Fondo extends JPanel{
        
        private Image imagen;
        
        @Override
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/Imagenes_Iconos/Logo-fondo.jpeg")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            
            setOpaque(false);
            
            super.paint(g);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//
}
//--------------------------------------------------------------------------------------------------------------------------------------------------//