/**
 *
 * @author Ximena
 */
//--------------------------------------------------------------------------------------------------------------------------------------------------//

package Interfaz;

import Conexion.Conexion;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//--------------------------------------------------------------------------------------------------------------------------------------------------//

public class Usuarios_InternalFrame extends javax.swing.JInternalFrame {
    
    //CONEXION
    Conexion enlace = new Conexion();
    Connection conect = enlace.Conexion();
    Statement st;
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public Usuarios_InternalFrame() {
        initComponents();
        mostrarDatos(0,null);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//
  
    public void limpiarInfo(){
        Txt_Id_Usu.setText("");
        Txt_Nombre_Usu.setText("");
        Txt_Apellido_Usu.setText("");
        Txt_Email_Usu.setText("");
        Txt_Usuario.setText("");
        Txt_Clave.setText("");
        Cb_Estado_Usu.setSelectedItem("Seleccionar");
        
        Txt_Buscador.setText("");
        Cb_Opcion.setSelectedItem("Mostrar Todos");
        
        Txt_Nombre_Usu.requestFocus();
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    public void mostrarDatos(int opBuscar, String valor){
        
        DefaultTableModel datosUsu = new DefaultTableModel(){
            
            //PARA QUE NO SE EDITEN LOS DATOS DE LA TABLA
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        
        datosUsu.addColumn("Id Usuario");
        datosUsu.addColumn("Estado");
        datosUsu.addColumn("Nombres");
        datosUsu.addColumn("Apellidos");
        datosUsu.addColumn("Correo");
        datosUsu.addColumn("Usuario");
        
    //-------------------------------------------------------//    
        //BUSCADOR
        String codSQL;
        
        if (opBuscar == 0 && valor == null){
            codSQL = "SELECT usuarios.Id_Usuario, perfiles.perfil AS Estado, usuarios.Nombre_Usuario AS Nombres, usuarios.Apellido_Usuario AS Apellidos, usuarios.Correo_Usuario AS Correo, usuarios.Usuario FROM ModulosDelSoftware.usuarios INNER JOIN ModulosDelSoftware.perfiles ON usuarios.Id_Perfil=perfiles.Id_Perfil";
        
        } else {
            
            if (opBuscar == 1 && valor != null){
                codSQL = "SELECT usuarios.Id_Usuario, perfiles.perfil AS Estado, usuarios.Nombre_Usuario AS Nombres, usuarios.Apellido_Usuario AS Apellidos, usuarios.Correo_Usuario AS Correo, usuarios.Usuario FROM ModulosDelSoftware.usuarios INNER JOIN ModulosDelSoftware.perfiles ON usuarios.Id_Perfil=perfiles.Id_Perfil WHERE Id_Usuario = '" + valor + "' ";
            
            } else {
                
                if (opBuscar == 2 && valor != null){
                    codSQL = "SELECT usuarios.Id_Usuario, perfiles.perfil AS Estado, usuarios.Nombre_Usuario AS Nombres, usuarios.Apellido_Usuario AS Apellidos, usuarios.Correo_Usuario AS Correo, usuarios.Usuario FROM ModulosDelSoftware.usuarios INNER JOIN ModulosDelSoftware.perfiles ON usuarios.Id_Perfil=perfiles.Id_Perfil WHERE Nombre_Usuario = '" + valor + "' ";
                
                } else {
                    
                    if (opBuscar == 3 && valor != null){
                        codSQL = "SELECT usuarios.Id_Usuario, perfiles.perfil AS Estado, usuarios.Nombre_Usuario AS Nombres, usuarios.Apellido_Usuario AS Apellidos, usuarios.Correo_Usuario AS Correo, usuarios.Usuario FROM ModulosDelSoftware.usuarios INNER JOIN ModulosDelSoftware.perfiles ON usuarios.Id_Perfil=perfiles.Id_Perfil WHERE usuarios.Id_Perfil = '" + valor + "' ";
                    
                    } else {
                        codSQL = "SELECT usuarios.Id_Usuario, perfiles.perfil AS Estado, usuarios.Nombre_Usuario AS Nombres, usuarios.Apellido_Usuario AS Apellidos, usuarios.Correo_Usuario AS Correo, usuarios.Usuario FROM ModulosDelSoftware.usuarios INNER JOIN ModulosDelSoftware.perfiles ON usuarios.Id_Perfil=perfiles.Id_Perfil";
                    }
                }
            } 
        }
    
    //-------------------------------------------------------//
        //DATOS TABLA
        String [] datos = new String [6];
        
        try {
            Statement leer = conect.createStatement();
            ResultSet resultado = leer.executeQuery(codSQL);
            
            while(resultado.next()){
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                datos[4] = resultado.getString(5);
                datos[5] = resultado.getString(6);
                
                datosUsu.addRow(datos);
            }
            
            Tabla_Datos_Usu.setModel(datosUsu);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener los datos de la consulta " + e);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    public void actualizarDatos(){
        
        String id = Txt_Id_Usu.getText();
        String nom = Txt_Nombre_Usu.getText();
        String ape = Txt_Apellido_Usu.getText();
        String email = Txt_Email_Usu.getText();
        String usuario = Txt_Usuario.getText();
        String clave = Txt_Clave.getText();
        String estado = Cb_Estado_Usu.getSelectedItem().toString();
        
        try {
            
            String actualizar = "UPDATE ModulosDelSoftware.usuarios SET Nombre_Usuario='" + nom + "', Apellido_Usuario='" + ape + "', Correo_Usuario='" + email + "', Usuario='" + usuario + "', Clave='" + clave + "' WHERE Id_Usuario='" + id + "';";
            
            if (nom.equals("") || ape.equals("") || email.equals("") || usuario.equals("") || estado.equals("Seleccionar")) {
                JOptionPane.showMessageDialog(null, "No hay datos para actualizar, debes ingresar los datos correspondientes");
            
            } else {
                Connection cone = enlace.Conexion();
                st = cone.createStatement();
                st.executeUpdate(actualizar);
                
                JOptionPane.showMessageDialog(null, "Los datos se han actualizado correctamente");
                limpiarInfo();
                mostrarDatos(0,null);
            } 
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showInputDialog(null, "Error al actualizar datos" + e);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    public void eliminarDatos(){
        
        int fila = Tabla_Datos_Usu.getSelectedRow();
        String id = Tabla_Datos_Usu.getValueAt(fila, 0).toString();
        
        String eliminar = "DELETE FROM ModulosDelSoftware.usuarios WHERE Id_Usuario='" + id + "';";
        
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un registro");
        
        } else {
            
            try {
                
                Connection cone = enlace.Conexion();
                st = cone.createStatement();
                st.executeUpdate(eliminar);
                
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                limpiarInfo();
                mostrarDatos(0,null);
                
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "No es Posible eliminar al administrador");
            }
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Txt_Id_Usu = new javax.swing.JTextField();
        Txt_Email_Usu = new javax.swing.JTextField();
        Txt_Apellido_Usu = new javax.swing.JTextField();
        Txt_Nombre_Usu = new javax.swing.JTextField();
        Txt_Usuario = new javax.swing.JTextField();
        Txt_Clave = new javax.swing.JTextField();
        Btn_Nuevo_Usu = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Btn_Modificar_Usu = new javax.swing.JButton();
        Btn_Eliminar_Usu = new javax.swing.JButton();
        Btn_Guardar_Usu = new javax.swing.JButton();
        Btn_Limpiar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Cb_Estado_Usu = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        Txt_Buscador = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla_Datos_Usu = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        Btn_Buscar = new javax.swing.JButton();
        Cb_Opcion = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuarios");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(10, 144, 205));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel1.setText("ID USUARIO:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel2.setText("Nombres:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel3.setText("Apellidos:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel4.setText("Email:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 124, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel5.setText("Usuario:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 163, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel6.setText("Contraseña:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 202, -1, -1));

        Txt_Id_Usu.setEditable(false);
        Txt_Id_Usu.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Txt_Id_Usu.setAutoscrolls(false);
        Txt_Id_Usu.setEnabled(false);
        jPanel4.add(Txt_Id_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 11, 52, -1));

        Txt_Email_Usu.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Email_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 121, 255, -1));

        Txt_Apellido_Usu.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Apellido_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 82, 255, -1));

        Txt_Nombre_Usu.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Nombre_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 43, 255, -1));

        Txt_Usuario.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 160, 255, -1));

        Txt_Clave.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 199, 255, -1));

        Btn_Nuevo_Usu.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Nuevo_Usu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/agregar1.png"))); // NOI18N
        Btn_Nuevo_Usu.setToolTipText("");
        Btn_Nuevo_Usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Nuevo_UsuActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Nuevo_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel7.setText("NUEVO");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel8.setText("MODIFICAR");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        Btn_Modificar_Usu.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Modificar_Usu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/editar_U_E.png"))); // NOI18N
        Btn_Modificar_Usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Modificar_UsuActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Modificar_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));

        Btn_Eliminar_Usu.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Eliminar_Usu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/eliminar.png"))); // NOI18N
        Btn_Eliminar_Usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Eliminar_UsuActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Eliminar_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        Btn_Guardar_Usu.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Guardar_Usu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/guardar1.png"))); // NOI18N
        Btn_Guardar_Usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Guardar_UsuActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Guardar_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, -1, 40));

        Btn_Limpiar.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/limpiar.png"))); // NOI18N
        Btn_Limpiar.setText("LIMPIAR INFORMACIÓN");
        Btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 267, 390, -1));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 390, -1));

        jLabel11.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel11.setText("Estado:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 241, -1, -1));

        jLabel12.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel12.setText("GUARDAR");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, -1, -1));

        jLabel13.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel13.setText("ELIMINAR");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        Cb_Estado_Usu.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Estado_Usu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "2" }));
        Cb_Estado_Usu.setToolTipText("Opciones: \n1- Activo. \n2- Desactivado. ");
        jPanel4.add(Cb_Estado_Usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 239, 255, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 400, 420));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(10, 144, 205));
        jTextField3.setFont(new java.awt.Font("Courier New", 1, 22)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("INFORMACIÓN USUARIOS");
        jTextField3.setToolTipText("INFORMACIÓN USUARIOS");
        jTextField3.setBorder(null);
        jTextField3.setSelectionColor(new java.awt.Color(102, 153, 255));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 490));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(new java.awt.Font("Courier New", 1, 22)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("DATOS USUARIOS");
        jTextField2.setToolTipText("DATOS USUARIOS");
        jTextField2.setBorder(null);
        jTextField2.setSelectionColor(new java.awt.Color(102, 153, 255));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 30));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Txt_Buscador.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Txt_Buscador.setToolTipText("Buscar Información");
        Txt_Buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Txt_BuscadorKeyTyped(evt);
            }
        });
        jPanel5.add(Txt_Buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 360, -1));

        Tabla_Datos_Usu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tabla_Datos_Usu.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Tabla_Datos_Usu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Usuario", "Estado", "Nombres", "Apellidos", "Correo", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Datos_Usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Datos_UsuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tabla_Datos_Usu);
        Tabla_Datos_Usu.getAccessibleContext().setAccessibleDescription("");

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 111, 680, 280));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 89, 700, 21));

        Btn_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/buscar.png"))); // NOI18N
        Btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BuscarActionPerformed(evt);
            }
        });
        jPanel5.add(Btn_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));

        Cb_Opcion.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Opcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar Todos", "Id Usuario", "Nombre", "Estado" }));
        jPanel5.add(Cb_Opcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 700, 420));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 0, 720, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void Tabla_Datos_UsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Datos_UsuMouseClicked

        //se crea las variables de las filas
        int row = Tabla_Datos_Usu.getSelectedRow();//cuando se seleccione la fila se modifica
        
        if (row ==  -1) { //si no ha ingresado nada
            JOptionPane.showInputDialog(null,"No se selccionó");

        } else {

            //que evalue las filas y columnas de la tabla con la info correspondiente y aparezca en el espacio
            String id = (String) Tabla_Datos_Usu.getValueAt(row, 0);
            String estado = (String) Tabla_Datos_Usu.getValueAt(row, 1);
            String nombre = (String) Tabla_Datos_Usu.getValueAt(row, 2);
            String apellido = (String) Tabla_Datos_Usu.getValueAt(row, 3);
            String correo = (String) Tabla_Datos_Usu.getValueAt(row, 4);
            String usuario = (String) Tabla_Datos_Usu.getValueAt(row, 5);

            Txt_Id_Usu.setText(id);
            Cb_Estado_Usu.setSelectedItem(estado);
            Txt_Nombre_Usu.setText(nombre);
            Txt_Apellido_Usu.setText(apellido);
            Txt_Email_Usu.setText(correo);
            Txt_Usuario.setText(usuario);
        }
    }//GEN-LAST:event_Tabla_Datos_UsuMouseClicked
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void Btn_Nuevo_UsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Nuevo_UsuActionPerformed
        JOptionPane.showMessageDialog(null, "TENER PRESENTE:\n" 
        +"Si desea agregar un nuevo usuario, debe hacerlo directamente desde la BASE DE DATOS.\n"
        + "GRACIAS POR SU COMPRENSIÓN.");
        Txt_Nombre_Usu.requestFocus();
    }//GEN-LAST:event_Btn_Nuevo_UsuActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    private void Btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LimpiarActionPerformed
        limpiarInfo();
    }//GEN-LAST:event_Btn_LimpiarActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Guardar_UsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Guardar_UsuActionPerformed
        JOptionPane.showMessageDialog(null, "ATENCIÓN:\n" 
        +"Lo sentimos,no fue posible guardar los datos ingresados. Debe tener presente las instruscciones brindadas. \n"
        + "GRACIAS POR SU COMPRENSIÓN.");
        Txt_Nombre_Usu.requestFocus();
    }//GEN-LAST:event_Btn_Guardar_UsuActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_BuscarActionPerformed
        int opcion = Cb_Opcion.getSelectedIndex();
        String buscar = Txt_Buscador.getText();
        mostrarDatos(opcion, buscar);
    }//GEN-LAST:event_Btn_BuscarActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Txt_BuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Txt_BuscadorKeyTyped
        // ENTER PARA BUSCAR 
        char tecla = evt.getKeyChar();// se asegura que de capturar ÚNICAMENTE la tecla ENTER 
        
        //Da click al botón para BUSCAR cuando se presiones la tecla ENTER
        if (tecla == KeyEvent.VK_ENTER) {
            Btn_Buscar.doClick();
        }
    }//GEN-LAST:event_Txt_BuscadorKeyTyped

    private void Btn_Modificar_UsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Modificar_UsuActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_Btn_Modificar_UsuActionPerformed

    private void Btn_Eliminar_UsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Eliminar_UsuActionPerformed
        int fila = Tabla_Datos_Usu.getSelectedRow();
        
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un registro");
        
        } else {
            eliminarDatos();
        }
    }//GEN-LAST:event_Btn_Eliminar_UsuActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Buscar;
    private javax.swing.JButton Btn_Eliminar_Usu;
    private javax.swing.JButton Btn_Guardar_Usu;
    private javax.swing.JButton Btn_Limpiar;
    private javax.swing.JButton Btn_Modificar_Usu;
    private javax.swing.JButton Btn_Nuevo_Usu;
    private javax.swing.JComboBox<String> Cb_Estado_Usu;
    private javax.swing.JComboBox<String> Cb_Opcion;
    private javax.swing.JTable Tabla_Datos_Usu;
    private javax.swing.JTextField Txt_Apellido_Usu;
    private javax.swing.JTextField Txt_Buscador;
    private javax.swing.JTextField Txt_Clave;
    private javax.swing.JTextField Txt_Email_Usu;
    private javax.swing.JTextField Txt_Id_Usu;
    private javax.swing.JTextField Txt_Nombre_Usu;
    private javax.swing.JTextField Txt_Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
//--------------------------------------------------------------------------------------------------------------------------------------------------//