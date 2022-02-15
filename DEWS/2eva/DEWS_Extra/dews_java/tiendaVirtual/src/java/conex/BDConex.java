package conex;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class BDConex {
    
    
    public static BasicDataSource damePoolConexiones(){
        
        BasicDataSource ds = new BasicDataSource();
        
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/bdvirtual");
        ds.setUsername("dw2");
        ds.setPassword("dw2");
        
        return ds;
    }
    
}
