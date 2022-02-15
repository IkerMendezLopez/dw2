package dao;

import beans.Prestamo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.apache.commons.dbcp2.BasicDataSource;

public class DaoBiblio {

    //Realizamos la conexion a la base de datos, solo que en vez de realizar la conexion con glassFish la creamos con las librerias importadas, y declaramos la pool de conexiones en un bloque static para que este accesible desde cualquier metodo
    private static final BasicDataSource ds = new BasicDataSource();
    static{
        //Este ds va con un pool de conexiones
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/bdlibros");
        ds.setUsername("dw2");
        ds.setPassword("dw2");
    }
    
    
    public static boolean insertarPrestamo(Prestamo prestamo){
        
        int idlibro = prestamo.getIdlibro();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
        String strFecha = formateador.format(prestamo.getFecha());
        String strFechaDev = formateador.format(prestamo.getFechaDev());
        
                
        String sql="insert into prestamos (fecha, fechadev, idlibro) values ('" + strFecha + "', '" + strFechaDev + "'," + idlibro + ")";
        try(
            //Los elementos que esten entre los parentesis del try se cerraran automaticante
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();    
        )
        {
            st.executeUpdate(sql);
            
            return true;
        }
        catch(SQLException e){
            System.err.println("Error en el metodo: " + e.getMessage());
            System.err.println("Sintaxis de la consulta: " + sql);
            return false;
        }
    }
    
}
