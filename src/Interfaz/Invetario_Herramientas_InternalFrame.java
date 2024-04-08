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

public class Invetario_Herramientas_InternalFrame extends javax.swing.JInternalFrame {

    //CONEXION
    Conexion enlace = new Conexion();
    Connection conect = enlace.Conexion();
    Statement st;
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public Invetario_Herramientas_InternalFrame() {
        initComponents();
        mostrarDatos(0,null);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public void limpiarInfo(){
        Txt_Id_Herramienta.setText("");
        Txt_Nom_Herramienta.setText("");
        Txt_Cant_Herramienta.setText("");
        Cb_Tipo_Herramienta.setSelectedItem("Seleccionar");
        Cb_Estado_Herramienta.setSelectedItem("Seleccionar");
        Txt_Precio_Unitario.setText("");
        Txt_Buscador.setText("");
        Cb_Opcion.setSelectedItem("Mostrar Todos");
        
        Txt_Nom_Herramienta.requestFocus();
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public void mostrarDatos(int opBuscar, String valor){
        
        DefaultTableModel datosHerra = new DefaultTableModel(){
            
            //PARA QUE NO SE EDITEN LOS DATOS DE LA TABLA
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        
        datosHerra.addColumn("Id Herramienta");
        datosHerra.addColumn("Nom Herramienta");
        datosHerra.addColumn("Cantidad");
        datosHerra.addColumn("Tipo Herramienta");
        datosHerra.addColumn("Estado Herramienta");
        datosHerra.addColumn("Precio Unitario");
        
    //-------------------------------------------------------//    
        //BUSCADOR
        String codSQL;
        
        if (opBuscar == 0 && valor == null) {
            codSQL = "SELECT herramientas.Id_Herramienta, herramientas.Nombre_Herramienta, herramientas.Cantidad_Herramienta, tipo_herramientas.Descripcion AS Tipo_Herramienta, estado_herramientas.Descripcion AS Estado_Herramienta, herramientas.Precio_Compra_Herramienta AS Precio_Unitario FROM tools_inventory.herramientas INNER JOIN ModulosDelSoftware.tipo_herramientas ON herramientas.Id_Tipo_Herramienta=tipo_herramientas.Id_Tipo INNER JOIN ModulosDelSoftware.estado_herramientas ON herramientas.Id_Estado_Herramienta=estado_herramientas.Id_Estado";
        
        } else {
            
            if (opBuscar == 1 && valor != null) {
                codSQL = "SELECT herramientas.Id_Herramienta, herramientas.Nombre_Herramienta, herramientas.Cantidad_Herramienta, tipo_herramientas.Descripcion AS Tipo_Herramienta, estado_herramientas.Descripcion AS Estado_Herramienta, herramientas.Precio_Compra_Herramienta AS Precio_Unitario FROM tools_inventory.herramientas INNER JOIN ModulosDelSoftware.tipo_herramientas ON herramientas.Id_Tipo_Herramienta=tipo_herramientas.Id_Tipo INNER JOIN ModulosDelSoftware.estado_herramientas ON herramientas.Id_Estado_Herramienta=estado_herramientas.Id_Estado WHERE Id_Herramienta = '" + valor + "' ";
            
            } else {
                
                if (opBuscar == 2 && valor != null){
                    codSQL = "SELECT herramientas.Id_Herramienta, herramientas.Nombre_Herramienta, herramientas.Cantidad_Herramienta, tipo_herramientas.Descripcion AS Tipo_Herramienta, estado_herramientas.Descripcion AS Estado_Herramienta, herramientas.Precio_Compra_Herramienta AS Precio_Unitario FROM tools_inventory.herramientas INNER JOIN ModulosDelSoftware.tipo_herramientas ON herramientas.Id_Tipo_Herramienta=tipo_herramientas.Id_Tipo INNER JOIN ModulosDelSoftware.estado_herramientas ON herramientas.Id_Estado_Herramienta=estado_herramientas.Id_Estado WHERE Nombre_Herramienta = '" + valor + "' ";
                
                } else {
                    
                    if (opBuscar == 3 && valor != null){
                        codSQL = "SELECT herramientas.Id_Herramienta, herramientas.Nombre_Herramienta, herramientas.Cantidad_Herramienta, tipo_herramientas.Descripcion AS Tipo_Herramienta, estado_herramientas.Descripcion AS Estado_Herramienta, herramientas.Precio_Compra_Herramienta AS Precio_Unitario FROM ModulosDelSoftware.herramientas INNER JOIN ModulosDelSoftware.tipo_herramientas ON herramientas.Id_Tipo_Herramienta=tipo_herramientas.Id_Tipo INNER JOIN ModulosDelSoftware.estado_herramientas ON herramientas.Id_Estado_Herramienta=estado_herramientas.Id_Estado WHERE Cantidad_Herramienta = '" + valor + "' ";
                    
                    } else {
                        
                        if (opBuscar == 4 && valor != null) {
                            codSQL = "SELECT herramientas.Id_Herramienta, herramientas.Nombre_Herramienta, herramientas.Cantidad_Herramienta, tipo_herramientas.Descripcion AS Tipo_Herramienta, estado_herramientas.Descripcion AS Estado_Herramienta, herramientas.Precio_Compra_Herramienta AS Precio_Unitario FROM ModulosDelSoftware.herramientas INNER JOIN ModulosDelSoftware.tipo_herramientas ON herramientas.Id_Tipo_Herramienta=tipo_herramientas.Id_Tipo INNER JOIN ModulosDelSoftware.estado_herramientas ON herramientas.Id_Estado_Herramienta=estado_herramientas.Id_Estado WHERE Id_Estado_Herramienta = '" + valor + "' ";
                        
                        } else {
                            codSQL = "SELECT herramientas.Id_Herramienta, herramientas.Nombre_Herramienta, herramientas.Cantidad_Herramienta, tipo_herramientas.Descripcion AS Tipo_Herramienta, estado_herramientas.Descripcion AS Estado_Herramienta, herramientas.Precio_Compra_Herramienta AS Precio_Unitario FROM ModulosDelSoftware.herramientas INNER JOIN ModulosDelSoftware.tipo_herramientas ON herramientas.Id_Tipo_Herramienta=tipo_herramientas.Id_Tipo INNER JOIN ModulosDelSoftware.estado_herramientas ON herramientas.Id_Estado_Herramienta=estado_herramientas.Id_Estado";
                        }
                        
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
            
            while (resultado.next()){
                
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                datos[4] = resultado.getString(5);
                datos[5] = resultado.getString(6);
                
                datosHerra.addRow(datos);
            }
            
            Tabla_Datos_Herramienta.setModel(datosHerra);
          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener los datos de la consulta " + e);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public void actualizarDatos(){
        
        String id = Txt_Id_Herramienta.getText();
        String nomHerr = Txt_Nom_Herramienta.getText();
        String cantidad = Txt_Cant_Herramienta.getText();
        String tipoHerr = Cb_Tipo_Herramienta.getSelectedItem().toString();
        String estadoHerr = Cb_Estado_Herramienta.getSelectedItem().toString();
        String precio = Txt_Precio_Unitario.getText();

        try {
            
            String actualizar = "UPDATE ModulosDelSoftware.herramientas SET Nombre_Herramienta='" + nomHerr + "', Cantidad_Herramienta='" + cantidad +"', Id_Tipo_Herramienta='" + tipoHerr + "', Id_Estado_Herramienta='" + estadoHerr + "', Precio_Compra_Herramienta='" + precio + "' WHERE Id_Herramienta='" + id + "';";
            
            if (nomHerr.equals("") || cantidad.equals("") || tipoHerr.equals("Seleccionar") || estadoHerr.equals("Seleccionar") || precio.equals("")) {
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
            JOptionPane.showInputDialog(null, "Error al actualizar datos");
        }
        
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    public void eliminarDatos(){
        
        int fila = Tabla_Datos_Herramienta.getSelectedRow();
        String id = Tabla_Datos_Herramienta.getValueAt(fila, 0).toString(); 
        
        String eliminar = "DELETE FROM ModulosDelSoftware.herramientas WHERE Id_Herramienta='" + id + "';";
        
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
                JOptionPane.showInputDialog(null, "Error al eliminar registro" + e);
            }
        }        
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Txt_Id_Herramienta = new javax.swing.JTextField();
        Txt_Nom_Herramienta = new javax.swing.JTextField();
        Txt_Cant_Herramienta = new javax.swing.JTextField();
        Cb_Tipo_Herramienta = new javax.swing.JComboBox<>();
        Cb_Estado_Herramienta = new javax.swing.JComboBox<>();
        Txt_Precio_Unitario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        Btn_Nuevo_Herra = new javax.swing.JButton();
        Btn_Modificar_Herra = new javax.swing.JButton();
        Btn_Guardar_Herra = new javax.swing.JButton();
        Btn_Eliminar_Herra = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Btn_Limpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Txt_Buscador = new javax.swing.JTextField();
        Btn_Buscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Datos_Herramienta = new javax.swing.JTable();
        Cb_Opcion = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Inventario Herramientas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(10, 144, 205));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORMACIÓN HERRAMIENTAS");

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel3.setText("ID HERRAMIENTA:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel5.setText("Cantidad:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 92, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel6.setText("Tipo:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 131, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel7.setText("Estado:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel8.setText("Precio Unitario:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 209, -1, -1));

        Txt_Id_Herramienta.setEditable(false);
        Txt_Id_Herramienta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Txt_Id_Herramienta.setAutoscrolls(false);
        Txt_Id_Herramienta.setEnabled(false);
        jPanel4.add(Txt_Id_Herramienta, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 11, 50, -1));

        Txt_Nom_Herramienta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Nom_Herramienta, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 50, 233, -1));

        Txt_Cant_Herramienta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Cant_Herramienta, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 89, 233, -1));

        Cb_Tipo_Herramienta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Tipo_Herramienta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "2" }));
        Cb_Tipo_Herramienta.setToolTipText("Opciones: \n1-Tornilleria.  \n2- Herramienta General. ");
        jPanel4.add(Cb_Tipo_Herramienta, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 128, 233, -1));

        Cb_Estado_Herramienta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Estado_Herramienta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "2" }));
        Cb_Estado_Herramienta.setToolTipText("Opciones: \n1- Funcional. \n2- No Funcional.");
        jPanel4.add(Cb_Estado_Herramienta, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 167, 233, -1));

        Txt_Precio_Unitario.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel4.add(Txt_Precio_Unitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 206, 233, -1));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 245, 421, 10));

        Btn_Nuevo_Herra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/agregar1.png"))); // NOI18N
        Btn_Nuevo_Herra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Nuevo_HerraActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Nuevo_Herra, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 266, -1, -1));

        Btn_Modificar_Herra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/editar1.png"))); // NOI18N
        Btn_Modificar_Herra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Modificar_HerraActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Modificar_Herra, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 266, -1, -1));

        Btn_Guardar_Herra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/guardar3.png"))); // NOI18N
        Btn_Guardar_Herra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Guardar_HerraActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Guardar_Herra, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 266, -1, -1));

        Btn_Eliminar_Herra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/eliminar.png"))); // NOI18N
        Btn_Eliminar_Herra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Eliminar_HerraActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Eliminar_Herra, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, 40));

        jLabel10.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel10.setText("NUEVO");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 313, -1, -1));

        jLabel11.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel11.setText("MODIFICAR");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 313, -1, -1));

        jLabel12.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel12.setText("GUARDAR");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 313, -1, -1));

        jLabel13.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel13.setText("ELIMINAR");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 313, -1, -1));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 339, 421, 10));

        Btn_Limpiar.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/limpiar.png"))); // NOI18N
        Btn_Limpiar.setText("LIMPIAR INFORMACIÓN");
        Btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(Btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 355, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 22)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATOS HERRAMIENTAS");

        Txt_Buscador.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Txt_Buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Txt_BuscadorKeyTyped(evt);
            }
        });

        Btn_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/buscar.png"))); // NOI18N
        Btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BuscarActionPerformed(evt);
            }
        });

        Tabla_Datos_Herramienta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Tabla_Datos_Herramienta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Herramienta", "Nombre", "Cantidad", "Tipo", "Estado", "Precio Unitario"
            }
        ));
        Tabla_Datos_Herramienta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Datos_HerramientaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Datos_Herramienta);

        Cb_Opcion.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Opcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar Todos", "Id Herramienta", "Nombre Herramienta", "Cantidad", "Estado" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Cb_Opcion, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Txt_Buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Buscar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Txt_Buscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cb_Opcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Btn_Buscar))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LimpiarActionPerformed
        limpiarInfo();
    }//GEN-LAST:event_Btn_LimpiarActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Nuevo_HerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Nuevo_HerraActionPerformed
        JOptionPane.showMessageDialog(null, "Ingrese los datos correspondientes y guarde la información");
        Txt_Nom_Herramienta.requestFocus();
    }//GEN-LAST:event_Btn_Nuevo_HerraActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Tabla_Datos_HerramientaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Datos_HerramientaMouseClicked
        
        //se crea las variables de las filas
        int row = Tabla_Datos_Herramienta.getSelectedRow();//cuando se seleccione la fila se modifica
        //se declaran las variables, el get llama a la fila
        if (row ==  -1) { //si no ha ingresado nada
            JOptionPane.showInputDialog(null,"No se selccionó");

        } else {

            //que evalue las filas y columnas de la tabla con la info correspondiente y aparezca en el espacio
            String id = (String) Tabla_Datos_Herramienta.getValueAt(row, 0);
            String nombre = (String) Tabla_Datos_Herramienta.getValueAt(row, 1);
            String cantidad = (String) Tabla_Datos_Herramienta.getValueAt(row,2);
            String tipo = (String) Tabla_Datos_Herramienta.getValueAt(row, 3);
            String estado = (String) Tabla_Datos_Herramienta.getValueAt(row, 4);
            String precio = (String) Tabla_Datos_Herramienta.getValueAt(row, 5);
           
            Txt_Id_Herramienta.setText(id);
            Txt_Nom_Herramienta.setText(nombre);
            Txt_Cant_Herramienta.setText(cantidad);
            Cb_Tipo_Herramienta.setSelectedItem(tipo);
            Cb_Estado_Herramienta.setSelectedItem(estado);
            Txt_Precio_Unitario.setText(precio);
            
        }
    }//GEN-LAST:event_Tabla_Datos_HerramientaMouseClicked
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Guardar_HerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Guardar_HerraActionPerformed
        
        //variables que almacenan los datos que se ingresan
        String nomHerr = Txt_Nom_Herramienta.getText();
        String cantidad = Txt_Cant_Herramienta.getText();
        String tipoHerr = Cb_Tipo_Herramienta.getSelectedItem().toString();
        String estadoHerr = Cb_Estado_Herramienta.getSelectedItem().toString();
        String precio = Txt_Precio_Unitario.getText();
        
        try {
            
            if (nomHerr.equals("") || cantidad.equals("") || tipoHerr.equals("Seleccionar") || estadoHerr.equals("Seleccionar") || precio.equals("")) {
                JOptionPane.showMessageDialog(null,"Debes ingresar datos");
                
            } else {
                
                String guardar = ("INSERT INTO ModulosDelSoftware.herramientas (Nombre_Herramienta, Cantidad_Herramienta, Id_Tipo_Herramienta, Id_Estado_Herramienta, Precio_Compra_Herramienta) VALUES ('" + nomHerr + "', '" + cantidad + "', '" + tipoHerr + "', '" + estadoHerr + "', '" + precio + "'); ");
                
                Connection cone = enlace.Conexion();
                st = cone.createStatement();
                st.executeUpdate(guardar);
                
                JOptionPane.showMessageDialog(null, "Los datos se han guardados correctamente");
                limpiarInfo();
                mostrarDatos(0,null);
            }
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"No se logro el nuevo registro " + e);
        }
    }//GEN-LAST:event_Btn_Guardar_HerraActionPerformed
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
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Modificar_HerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Modificar_HerraActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_Btn_Modificar_HerraActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Eliminar_HerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Eliminar_HerraActionPerformed
        
        int fila = Tabla_Datos_Herramienta.getSelectedRow();
        
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un registro");
        
        } else {
            eliminarDatos();
        }
    }//GEN-LAST:event_Btn_Eliminar_HerraActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Buscar;
    private javax.swing.JButton Btn_Eliminar_Herra;
    private javax.swing.JButton Btn_Guardar_Herra;
    private javax.swing.JButton Btn_Limpiar;
    private javax.swing.JButton Btn_Modificar_Herra;
    private javax.swing.JButton Btn_Nuevo_Herra;
    private javax.swing.JComboBox<String> Cb_Estado_Herramienta;
    private javax.swing.JComboBox<String> Cb_Opcion;
    private javax.swing.JComboBox<String> Cb_Tipo_Herramienta;
    private javax.swing.JTable Tabla_Datos_Herramienta;
    private javax.swing.JTextField Txt_Buscador;
    private javax.swing.JTextField Txt_Cant_Herramienta;
    private javax.swing.JTextField Txt_Id_Herramienta;
    private javax.swing.JTextField Txt_Nom_Herramienta;
    private javax.swing.JTextField Txt_Precio_Unitario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
//--------------------------------------------------------------------------------------------------------------------------------------------------//