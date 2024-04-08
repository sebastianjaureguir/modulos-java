/**
 *
 * @author Ximena
 */
//--------------------------------------------------------------------------------------------------------------------------------------------------//

package Conexion;

//CÓDIGO NECESARIO PARA HACER LA CONEXIÓN 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
//CÓDIGO PARA VERIFICAR SI LA CONEXIÓN ES CORRECTA O NO
import java.sql.Statement;
import java.sql.ResultSet;
*/

//--------------------------------------------------------------------------------------------------------------------------------------------------//

public class Conexion {

    //variables de conexion a la base de datos 
    public static final String URL = "jdbc:mysql://localhost:3306/ModulosDelSoftware";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    Connection con;
    
//--------------------------------------------------------------------------------------------------------------------------------------------------//

    //METODO PARA LA CONEXIÓN 
    public Connection Conexion(){
        
        try {
            //conector que hace referencia a la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) { 
            System.out.println("Error:" + e);
            
        } 
        return con;
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------//

/*    
    public static void main(String[] args) {
        Conexion cn = new Conexion();
        
        Statement st;
        ResultSet rs;
        
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("SELECT * FROM usuarios");
            
            while (rs.next()) {
                System.out.println(rs.getInt("Id_Usuario") + " " 
                                    + rs.getString("Nombre_Usuario") + " " 
                                    + rs.getString("Apellido_Usuario") + " " 
                                    + rs.getString("Usuario"));
                
            }
    
            cn.con.close();
            
        } catch (Exception e) {
            System.err.println(e);
    
        }
    }
*/
//--------------------------------------------------------------------------------------------------------------------------------------------------//

} //FIN CLASE CONEXION
