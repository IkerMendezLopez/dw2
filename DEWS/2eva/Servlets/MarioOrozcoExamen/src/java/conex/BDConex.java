package conex;

import org.apache.commons.dbcp2.BasicDataSource;

public class BDConex {

    public static final BasicDataSource ds = new BasicDataSource();
    
    static{
        //Este ds va con un pool de conexiones
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/bdactividades");
        ds.setUsername("mario");
        ds.setPassword("123");
    }
}

