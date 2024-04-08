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

public class Empleados_InternalFrame extends javax.swing.JInternalFrame {

    //CONEXION
    Conexion enlace = new Conexion();
    Connection conect = enlace.Conexion();
    Statement st;
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public Empleados_InternalFrame() {
        initComponents();
        mostrarDatos(0,null);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public void limpiarInfo(){
        Txt_Id_Empleado.setText("");
        Txt_Nom_Empleado.setText("");
        Txt_Ape_Empleado.setText("");
        Txt_Correo_Empleado.setText("");
        Txt_Cel_Empleado.setText("");
        Txt_NumDoc_Empleado.setText("");
        Cb_Cargo_Empleado.setSelectedItem("Seleccionar");
        Cb_Estado_Empleado.setSelectedItem("Seleccionar");
        Txt_Buscador.setText("");
        Cb_Opcion.setSelectedItem("Mostrar Todos");

        Txt_Nom_Empleado.requestFocus();
   }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    public void mostrarDatos(int opBuscar, String valor){
        
        DefaultTableModel datosEmplea = new DefaultTableModel(){
            
            //PARA QUE NO SE EDITEN LOS DATOS DE LA TABLA
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };
        
        datosEmplea.addColumn("Id Empleado");
        datosEmplea.addColumn("Nombres");
        datosEmplea.addColumn("Apellidos");
        datosEmplea.addColumn("Correo");
        datosEmplea.addColumn("Núm Contacto");
        datosEmplea.addColumn("Núm Documento");
        datosEmplea.addColumn("Cargo");
        datosEmplea.addColumn("Estado");
        
    //-------------------------------------------------------//    
        //BUSCADOR
        String codSQL;
        
        if (opBuscar == 0 && valor == null) {
            codSQL = "SELECT empleados.Id_Empleado AS Id_Empleado, empleados.Nombre_Empleado AS Nombres, empleados.Apellido_Empleado AS Apellidos, empleados.Correo_Empleado AS Correo, empleados.Numero_Contacto_Empleado AS Num_Contancto, empleados.Numero_Documento_Empleado AS Num_Documento, cargos_empleados.Descripcion_Cargo AS Cargo, empleados.Estado_Empleado AS Estado FROM ModulosDelSoftware.empleados INNER JOIN ModulosDelSoftware.cargos_empleados ON empleados.Id_Cargo_Empleado=cargos_empleados.Id_Cargo";
        
        } else {
            
            if (opBuscar == 1 && valor != null){
                codSQL = "SELECT empleados.Id_Empleado AS Id_Empleado, empleados.Nombre_Empleado AS Nombres, empleados.Apellido_Empleado AS Apellidos, empleados.Correo_Empleado AS Correo, empleados.Numero_Contacto_Empleado AS Num_Contancto, empleados.Numero_Documento_Empleado AS Num_Documento, cargos_empleados.Descripcion_Cargo AS Cargo, empleados.Estado_Empleado AS Estado FROM ModulosDelSoftware.empleados INNER JOIN ModulosDelSoftware.cargos_empleados ON empleados.Id_Cargo_Empleado=cargos_empleados.Id_Cargo WHERE Id_Empleado = '" + valor + "' ";
            
            } else {
                
                if (opBuscar == 2 && valor != null) {
                    codSQL = "SELECT empleados.Id_Empleado AS Id_Empleado, empleados.Nombre_Empleado AS Nombres, empleados.Apellido_Empleado AS Apellidos, empleados.Correo_Empleado AS Correo, empleados.Numero_Contacto_Empleado AS Num_Contancto, empleados.Numero_Documento_Empleado AS Num_Documento, cargos_empleados.Descripcion_Cargo AS Cargo, empleados.Estado_Empleado AS Estado FROM ModulosDelSoftware.empleados INNER JOIN ModulosDelSoftware.cargos_empleados ON empleados.Id_Cargo_Empleado=cargos_empleados.Id_Cargo WHERE Numero_Documento_Empleado = '" + valor + "' ";
                
                } else {
                    
                    if (opBuscar == 3 && valor != null){
                        codSQL = "SELECT empleados.Id_Empleado AS Id_Empleado, empleados.Nombre_Empleado AS Nombres, empleados.Apellido_Empleado AS Apellidos, empleados.Correo_Empleado AS Correo, empleados.Numero_Contacto_Empleado AS Num_Contancto, empleados.Numero_Documento_Empleado AS Num_Documento, cargos_empleados.Descripcion_Cargo AS Cargo, empleados.Estado_Empleado AS Estado FROM ModulosDelSoftware.empleados INNER JOIN ModulosDelSoftware.cargos_empleados ON empleados.Id_Cargo_Empleado=cargos_empleados.Id_Cargo WHERE Id_Cargo_Empleado = '" + valor + "' ";
                    
                    } else {
                        
                        if (opBuscar == 4 && valor != null){
                            codSQL = "SELECT empleados.Id_Empleado AS Id_Empleado, empleados.Nombre_Empleado AS Nombres, empleados.Apellido_Empleado AS Apellidos, empleados.Correo_Empleado AS Correo, empleados.Numero_Contacto_Empleado AS Num_Contancto, empleados.Numero_Documento_Empleado AS Num_Documento, cargos_empleados.Descripcion_Cargo AS Cargo, empleados.Estado_Empleado AS Estado FROM ModulosDelSoftware.empleados INNER JOIN ModulosDelSoftware.cargos_empleados ON empleados.Id_Cargo_Empleado=cargos_empleados.Id_Cargo WHERE Estado_Empleado = '" + valor + "' ";
                        
                        } else {
                            codSQL = "SELECT empleados.Id_Empleado AS Id_Empleado, empleados.Nombre_Empleado AS Nombres, empleados.Apellido_Empleado AS Apellidos, empleados.Correo_Empleado AS Correo, empleados.Numero_Contacto_Empleado AS Num_Contancto, empleados.Numero_Documento_Empleado AS Num_Documento, cargos_empleados.Descripcion_Cargo AS Cargo, empleados.Estado_Empleado AS Estado FROM ModulosDelSoftware.empleados INNER JOIN ModulosDelSoftware.cargos_empleados ON empleados.Id_Cargo_Empleado=cargos_empleados.Id_Cargo";
                        }
                    }
                }
            }
        }
        
    //-------------------------------------------------------//    
        //DATOS TABLA
        String [] datos = new String [8];
        
        try {
            Statement leer = conect.createStatement();
            ResultSet resultado = leer.executeQuery(codSQL); //CONSULTA A LA BASE DE DATOS
            
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                datos[4] = resultado.getString(5);
                datos[5] = resultado.getString(6);
                datos[6] = resultado.getString(7);
                datos[7] = resultado.getString(8);
                
                datosEmplea.addRow(datos);
            }
            
            Tabla_Datos_Empleados.setModel(datosEmplea);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener los datos de la consulta " + e);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

        public void actualizarDatos(){
        
            String id = Txt_Id_Empleado.getText();
            String nombre = Txt_Nom_Empleado.getText();
            String apellido = Txt_Ape_Empleado.getText();
            String correo = Txt_Correo_Empleado.getText();
            String cel = Txt_Cel_Empleado.getText();
            String documento = Txt_NumDoc_Empleado.getText();
            String cargo = Cb_Cargo_Empleado.getSelectedItem().toString();
            String estado = Cb_Estado_Empleado.getSelectedItem().toString();

            try {

                String actualizar = "UPDATE ModulosDelSoftware.empleados SET Nombre_Empleado='" + nombre + "', Apellido_Empleado='" + apellido +"', Correo_Empleado='" + correo + "', Numero_Contacto_Empleado='" + cel + "', Numero_Documento_Empleado='" + documento + "', Id_Cargo_Empleado='" + cargo + "', Estado_Empleado='" + estado + "' WHERE Id_Empleado='" + id + "';";

                if (nombre.equals("") || apellido.equals("") || correo.equals("") || cel.equals("") || documento.equals("") || cargo.equals("Seleccionar") || estado.equals("Seleccionar")){
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
                JOptionPane.showMessageDialog(null, "Error al actualizar datos" + e);
            }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

        public void eliminarDatos(){
        
        int fila = Tabla_Datos_Empleados.getSelectedRow();
        String id = Tabla_Datos_Empleados.getValueAt(fila, 0).toString(); 
        
        String eliminar = "DELETE FROM ModulosDelSoftware.empleados WHERE Id_Empleado='" + id + "';";
        
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

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Btn_Nuevo_Empleado = new javax.swing.JButton();
        Btn_Modificar_Empleado = new javax.swing.JButton();
        Btn_Guardar_Empleado = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Btn_Limpiar = new javax.swing.JButton();
        Txt_Id_Empleado = new javax.swing.JTextField();
        Txt_Nom_Empleado = new javax.swing.JTextField();
        Txt_Ape_Empleado = new javax.swing.JTextField();
        Txt_Correo_Empleado = new javax.swing.JTextField();
        Txt_Cel_Empleado = new javax.swing.JTextField();
        Txt_NumDoc_Empleado = new javax.swing.JTextField();
        Btn_Eliminar_Empleado = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Cb_Cargo_Empleado = new javax.swing.JComboBox<>();
        Cb_Estado_Empleado = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Txt_Buscador = new javax.swing.JTextField();
        Btn_Buscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Datos_Empleados = new javax.swing.JTable();
        Cb_Opcion = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Empleados");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(10, 144, 205));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(10, 144, 205));
        jLabel1.setFont(new java.awt.Font("Courier New", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORMACIÓN EMPLEADOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 391, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century", 1, 12)); // NOI18N
        jLabel2.setText("ID EMPLEADO:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel4.setText("Apellido:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 92, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel5.setText("Correo:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 131, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel6.setText("Núm Celular:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel7.setText("Núm Documento:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 209, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel8.setText("Cargo:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 248, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel9.setText("Estado:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 287, -1, -1));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 323, 391, 10));

        Btn_Nuevo_Empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/agregar1.png"))); // NOI18N
        Btn_Nuevo_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Nuevo_EmpleadoActionPerformed(evt);
            }
        });
        jPanel3.add(Btn_Nuevo_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        Btn_Modificar_Empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/editar_U_E.png"))); // NOI18N
        Btn_Modificar_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Modificar_EmpleadoActionPerformed(evt);
            }
        });
        jPanel3.add(Btn_Modificar_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        Btn_Guardar_Empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/guardar3.png"))); // NOI18N
        Btn_Guardar_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Guardar_EmpleadoActionPerformed(evt);
            }
        });
        jPanel3.add(Btn_Guardar_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, -1, -1));

        jLabel10.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel10.setText("NUEVO");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        jLabel11.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel11.setText("MODIFICAR");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, -1, -1));

        jLabel13.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel13.setText("GUARDAR");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 417, 391, 10));

        Btn_Limpiar.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/limpiar.png"))); // NOI18N
        Btn_Limpiar.setText("LIMPIAR INFORMACIÓN");
        Btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(Btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 433, -1, -1));

        Txt_Id_Empleado.setEditable(false);
        Txt_Id_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Txt_Id_Empleado.setEnabled(false);
        jPanel3.add(Txt_Id_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 11, 50, -1));

        Txt_Nom_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel3.add(Txt_Nom_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 50, 257, -1));

        Txt_Ape_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel3.add(Txt_Ape_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 89, 257, -1));

        Txt_Correo_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel3.add(Txt_Correo_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 128, 257, -1));

        Txt_Cel_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel3.add(Txt_Cel_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 167, 257, -1));

        Txt_NumDoc_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jPanel3.add(Txt_NumDoc_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 206, 257, -1));

        Btn_Eliminar_Empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Iconos/eliminar.png"))); // NOI18N
        Btn_Eliminar_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Eliminar_EmpleadoActionPerformed(evt);
            }
        });
        jPanel3.add(Btn_Eliminar_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jLabel12.setFont(new java.awt.Font("Century", 0, 10)); // NOI18N
        jLabel12.setText("ELIMINAR");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        Cb_Cargo_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Cargo_Empleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "2", "3" }));
        Cb_Cargo_Empleado.setToolTipText("Opciones: \n1- Jefe de Instalaciones. \n2-  Instalador. \n3-  Secretario/a Administrativo/a.");
        jPanel3.add(Cb_Cargo_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 246, 247, -1));
        Cb_Cargo_Empleado.getAccessibleContext().setAccessibleName("");
        Cb_Cargo_Empleado.getAccessibleContext().setAccessibleDescription("1- Jefe de Instalaciones, 2- Instalador, 3- Secretario/a Administrativo/a.");

        Cb_Estado_Empleado.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Cb_Estado_Empleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Desactivado" }));
        Cb_Estado_Empleado.setToolTipText("Opciones: \n1- Activo. \n2- Desactivado.");
        jPanel3.add(Cb_Estado_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 286, 247, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 490));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 560));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Courier New", 1, 22)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DATOS EMPLEADOS");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 750, -1));

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

        Tabla_Datos_Empleados.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        Tabla_Datos_Empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Empleado", "Nombre", "Apellido", "Correo", "Núm Celuar", "Núm Documento", "Cargo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Datos_Empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Datos_EmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Datos_Empleados);

        Cb_Opcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar Todos", "Id Empleado", "Número Documento", "Cargo", "Estado" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cb_Opcion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Txt_Buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_Buscar)
                .addGap(50, 50, 50))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Txt_Buscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cb_Opcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Btn_Buscar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 760, 490));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 0, 780, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_Guardar_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Guardar_EmpleadoActionPerformed
        
        //variables que almacenan los datos que se ingresan
        String nombre = Txt_Nom_Empleado.getText();
        String apellido = Txt_Ape_Empleado.getText();
        String correo = Txt_Correo_Empleado.getText();
        String cel = Txt_Cel_Empleado.getText();
        String documento = Txt_NumDoc_Empleado.getText();
        String cargo = Cb_Cargo_Empleado.getSelectedItem().toString();
        String estado = Cb_Estado_Empleado.getSelectedItem().toString();
       
        try {
            if (nombre.equals("") || apellido.equals("") || correo.equals("") || cel.equals("") || documento.equals("") || cargo.equals("Seleccionar") || estado.equals("Seleccionar")){
                JOptionPane.showMessageDialog(null,"Debes ingresar datos");
                
            } else {
                String guardar = ("INSERT INTO ModulosDelSoftware.empleados (Nombre_Empleado, Apellido_Empleado, Correo_Empleado, Numero_Contacto_Empleado, Numero_Documento_Empleado, Id_Cargo_Empleado, Estado_Empleado) VALUES ('" + nombre + "', '" + apellido + "', '" + correo + "', '" + cel + "', '" + documento + "', '" + cargo + "', '" +  estado + "');"); //CONSULTA SQL
                
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
 
    }//GEN-LAST:event_Btn_Guardar_EmpleadoActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LimpiarActionPerformed
        limpiarInfo();
    }//GEN-LAST:event_Btn_LimpiarActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Nuevo_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Nuevo_EmpleadoActionPerformed
        JOptionPane.showMessageDialog(null, "Ingrese los datos correspondientes y guarde la información");
        Txt_Nom_Empleado.requestFocus();
    }//GEN-LAST:event_Btn_Nuevo_EmpleadoActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Tabla_Datos_EmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Datos_EmpleadosMouseClicked
        
        //se crea las variables de las filas
        int row = Tabla_Datos_Empleados.getSelectedRow();//cuando se seleccione la fila se modifica
        //se declaran las variables, el get llama a la fila
        if (row ==  -1) { //si no ha ingresado nada
            JOptionPane.showInputDialog(null,"No se selccionó");

        } else {

            //que evalue las filas y columnas de la tabla con la info correspondiente y aparezca en el espacio
            String id = (String) Tabla_Datos_Empleados.getValueAt(row, 0);
            String nombre = (String) Tabla_Datos_Empleados.getValueAt(row, 1);
            String apellido = (String) Tabla_Datos_Empleados.getValueAt(row,2);
            String correo = (String) Tabla_Datos_Empleados.getValueAt(row, 3);
            String numero = (String) Tabla_Datos_Empleados.getValueAt(row, 4);
            String documento = (String) Tabla_Datos_Empleados.getValueAt(row, 5);
            String cargo = (String) Tabla_Datos_Empleados.getValueAt(row, 6);
            String estado = (String) Tabla_Datos_Empleados.getValueAt(row, 7);

            Txt_Id_Empleado.setText(id);
            Txt_Nom_Empleado.setText(nombre);
            Txt_Ape_Empleado.setText(apellido);
            Txt_Correo_Empleado.setText(correo);
            Txt_Cel_Empleado.setText(numero);
            Txt_NumDoc_Empleado.setText(documento);
            Cb_Cargo_Empleado.setSelectedItem(cargo);
            Cb_Estado_Empleado.setSelectedItem(estado);

        }
        
    }//GEN-LAST:event_Tabla_Datos_EmpleadosMouseClicked
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

    private void Btn_Modificar_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Modificar_EmpleadoActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_Btn_Modificar_EmpleadoActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//    

    private void Btn_Eliminar_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Eliminar_EmpleadoActionPerformed
        int fila = Tabla_Datos_Empleados.getSelectedRow();
        
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un registro");
        
        } else {
            eliminarDatos();
        }
    }//GEN-LAST:event_Btn_Eliminar_EmpleadoActionPerformed
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Buscar;
    private javax.swing.JButton Btn_Eliminar_Empleado;
    private javax.swing.JButton Btn_Guardar_Empleado;
    private javax.swing.JButton Btn_Limpiar;
    private javax.swing.JButton Btn_Modificar_Empleado;
    private javax.swing.JButton Btn_Nuevo_Empleado;
    private javax.swing.JComboBox<String> Cb_Cargo_Empleado;
    private javax.swing.JComboBox<String> Cb_Estado_Empleado;
    private javax.swing.JComboBox<String> Cb_Opcion;
    private javax.swing.JTable Tabla_Datos_Empleados;
    private javax.swing.JTextField Txt_Ape_Empleado;
    private javax.swing.JTextField Txt_Buscador;
    private javax.swing.JTextField Txt_Cel_Empleado;
    private javax.swing.JTextField Txt_Correo_Empleado;
    private javax.swing.JTextField Txt_Id_Empleado;
    private javax.swing.JTextField Txt_Nom_Empleado;
    private javax.swing.JTextField Txt_NumDoc_Empleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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