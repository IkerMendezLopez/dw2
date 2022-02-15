package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DaoViajes {

    private static final BasicDataSource ds = new BasicDataSource();
    
    static{
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/bdbus");
        ds.setUsername("dw2");
        ds.setPassword("dw2");
    }
    
}
