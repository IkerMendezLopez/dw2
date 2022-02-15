package conex;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BDConex {
    
    public static DataSource dameConexionPool(){
        DataSource ds=null;
        
        try {
            InitialContext ctx=new InitialContext();
            ds= (DataSource) ctx.lookup("jdbc/tienda");
        }
        catch (NamingException ex) {
            System.out.println("Error en la conexión: "+ ex);
        }
        finally{
            return ds;
        }   
    }
}
