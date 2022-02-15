
package dao;

import beans.Libro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestorBD {

    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost/bdbiblio";
    private static final String USER="root";
    private static final String PASS="";
    
    private Connection cn;
    
    public GestorBD(){        
        
        try {
            Class.forName(DRIVER);
            cn=DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            
        } catch (SQLException ex) {
            
        }        
    }
    
    
}
