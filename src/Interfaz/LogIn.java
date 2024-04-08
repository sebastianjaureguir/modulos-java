/**
 *
 * @author Ximena Giraldo A.
 */
//--------------------------------------------------------------------------------------------------------------------------------------------------//

package Interfaz;

//Codigo de la conexión a la base de datos
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; //Se usa para manejo de errores
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane; 
//--------------------------------------------------------------------------------------------------------------------------------------------------//

public class LogIn extends javax.swing.JFrame {

    //conexion a la base de datos 
    public static final String URL = "jdbc:mysql://localhost:3306/ModulosDelSoftware";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    PreparedStatement ps;
    ResultSet rs;
//--------------------------------------------------------------------------------------------------------------------------------------------------//
    
    //METODO DE LA CONEXIÓN A LA BASE DE DATOS
    public static Connection getConnection()  {
        Connection con = null;
        
        try {
            //conector que hace referencia a la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(URL,USERNAME,PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) { 
            System.out.println(e);
            
        } return con;
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//
    
    public LogIn() {
        initComponents();
        
        this.setLocationRelativeTo(null); // va a centrar la ventana del login
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    //METODO 2 
    // PARA VALIDAR USUARIO Y CONTRASEÑA
    public void Validacion(){
        int resultado = 0;
        
        Connection con = getConnection();
      
        try {
            String Usuario = TxtUsuario.getText();
            String Clave = String.valueOf(Contraseña.getPassword());
            
            //se hace una sentencia query (a la base de datos)
            String sql = "SELECT * FROM usuarios WHERE Usuario='" + Usuario + "' and Clave='" + Clave +"'";
            
            Statement st = con.createStatement();
            ResultSet Rs = st.executeQuery(sql); //para ejecutar la sentencia
            
            //se hacen las validaciones de los datos 
            if (Rs.next()) {
                resultado = 1;
                        
                if (resultado == 1) {
                    Principal inicio = new Principal();
                    inicio.setVisible(true);
                    
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "La información brindada no conincide con la que se tiene guardada."
                                                     +"\n"+ "Intente Nuevamente");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "EXISTE UN ERROR DE CONEXIÓN CON LA BASE DE DATOS." 
                                                 +"\n"+ "REVISE POR FAVOR QUE LA INTERFAZ ESTE ENCENDIDA." 
                                                 +"\n"+ "SI EL ERROR PERSISTE CONTACTE AL ENCARGADO" + e);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        TxtUsuario = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        Contraseña = new javax.swing.JPasswordField();
        BtnSalir = new javax.swing.JButton();
        BtnIngresar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(205, 205, 205));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(10, 144, 203));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(10, 144, 205));
        jTextField2.setFont(new java.awt.Font("Courier New", 1, 30)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("TOOLS INVENTORY");
        jTextField2.setToolTipText("INSTALACIONES GRÁFICAS");
        jTextField2.setBorder(null);
        jTextField2.setSelectionColor(new java.awt.Color(102, 153, 255));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 290, 40));

        jLabel4.setBackground(new java.awt.Color(10, 144, 203));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/logo_herramienta1.png"))); // NOI18N
        jLabel4.setToolTipText("INSTALACIONES GRÁFICAS");
        jLabel4.setOpaque(true);
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 70, 60));

        jLabel5.setBackground(new java.awt.Color(10, 144, 203));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/logo_herramienta1.png"))); // NOI18N
        jLabel5.setToolTipText("INSTALACIONES GRÁFICAS");
        jLabel5.setOpaque(true);
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 70, 60));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 60));

        jLabel1.setBackground(new java.awt.Color(102, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/clave.png"))); // NOI18N
        jLabel1.setToolTipText("Contraseña");
        jLabel1.setOpaque(true);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 70, 60));

        jPanel4.setBackground(new java.awt.Color(205, 205, 205));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 470, 30));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        TxtUsuario.setBackground(new java.awt.Color(204, 204, 204));
        TxtUsuario.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        TxtUsuario.setToolTipText("Usuario");
        TxtUsuario.setBorder(null);
        TxtUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TxtUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TxtUsuario.setName("txtUsuario"); // NOI18N
        TxtUsuario.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TxtUsuario.setSelectionColor(new java.awt.Color(102, 153, 255));
        TxtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtUsuarioActionPerformed(evt);
            }
        });
        TxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtUsuarioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TxtUsuario.getAccessibleContext().setAccessibleParent(this);

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 320, 60));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        Contraseña.setBackground(new java.awt.Color(204, 204, 204));
        Contraseña.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        Contraseña.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Contraseña.setToolTipText("Contraseña");
        Contraseña.setBorder(null);
        Contraseña.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Contraseña.setOpaque(false);
        Contraseña.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Contraseña.setSelectionColor(new java.awt.Color(102, 153, 255));
        Contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ContraseñaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 320, 60));

        BtnSalir.setBackground(new java.awt.Color(204, 0, 0));
        BtnSalir.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/cerrar.png"))); // NOI18N
        BtnSalir.setText("   SALIR");
        BtnSalir.setToolTipText("Salir");
        BtnSalir.setBorder(null);
        BtnSalir.setBorderPainted(false);
        BtnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSalirMouseClicked(evt);
            }
        });
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 160, 50));

        BtnIngresar.setBackground(new java.awt.Color(0, 51, 255));
        BtnIngresar.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        BtnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/ingresar.png"))); // NOI18N
        BtnIngresar.setText("   INGRESAR");
        BtnIngresar.setToolTipText("Iniciar Sesión");
        BtnIngresar.setBorder(null);
        BtnIngresar.setBorderPainted(false);
        BtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngresarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 180, 50));

        jLabel6.setBackground(new java.awt.Color(102, 153, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/user.png"))); // NOI18N
        jLabel6.setToolTipText("Usuario");
        jLabel6.setOpaque(true);
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 70, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 470, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalirActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void BtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngresarActionPerformed
        // TODO add your handling code here:
        Validacion();
    }//GEN-LAST:event_BtnIngresarActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void TxtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtUsuarioActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void BtnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSalirMouseClicked
        //Información del botón salir
        JOptionPane.showMessageDialog(null, "Vuelva Pronto");
        System.exit(0);
    }//GEN-LAST:event_BtnSalirMouseClicked
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void ContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContraseñaKeyTyped
        // ENTER PARA INGRESAR 
        char tecla = evt.getKeyChar();// se asegura que de capturar ÚNICAMENTE la tecla ENTER 
        
        //Da click al botón para ingresar cuando se presiones la tecla ENTER
        if (tecla == KeyEvent.VK_ENTER) {
            BtnIngresar.doClick();
        }
    }//GEN-LAST:event_ContraseñaKeyTyped

    private void TxtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsuarioKeyTyped
        // ENTER PARA INGRESAR 
        char tecla = evt.getKeyChar();// se asegura que de capturar ÚNICAMENTE la tecla ENTER 
        
        //Da click al botón para ingresar cuando se presiones la tecla ENTER
        if (tecla == KeyEvent.VK_ENTER) {
            BtnIngresar.doClick();
        }
    }//GEN-LAST:event_TxtUsuarioKeyTyped
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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnIngresar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
//--------------------------------------------------------------------------------------------------------------------------------------------------//
