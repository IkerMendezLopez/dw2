package dao;

import conex.BDConex;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;


public class KeysDAO {
    
    private static BDConex ds;
    
    public static int siguienteId(String nomTabla){
        
        int siguienteID = 1;
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection(); */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            Statement st = cn.createStatement();
            String sql = "select max(id) as maxID from " + nomTabla;
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                siguienteID = rs.getInt("maxID") + 1;
            }
            
            
            //PROBAR A VER SI DE ESTA FORMA FUNCIONARIA
            /*
            if(!rs.wasNull()){
                siguienteID = rs.getInt("maxID") + 1;
            }*/
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return siguienteID;
        }
    }
    
    
    public static int cantClientes(){
        
        int cantClientes = 0;
        
        try(
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            Statement st = cn.createStatement();
            String sql = "select count(*) as cantClientes from clientes";
            ResultSet rs = st.executeQuery(sql);
                    
            if(rs.next()){
                cantClientes = rs.getInt("cantClientes");
            }
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return cantClientes;
        }
    }

}
