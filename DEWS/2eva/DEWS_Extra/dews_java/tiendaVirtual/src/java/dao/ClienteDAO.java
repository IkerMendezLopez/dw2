package dao;

import beans.Cliente;
import conex.BDConex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;


public class ClienteDAO {
    
    private static BDConex ds;
    static PreparedStatement ps;
    static PreparedStatement ps2;
    
    static{
        try{
            Connection cn = ds.damePoolConexiones().getConnection(); //org.apache.commons.dbcp2.DelegatingPreparedStatement with address: "NULL" is closed. --> No cerrar la conexion hasta despues de realizar la accion
            
            String sql = "insert into clientes (id, nombre, password, domicilio, codigopostal, telefono, email) values(?,?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            
            String sql2 = "update clientes set nombre=?, password=?, domicilio=?, codigopostal=?, telefono=?, email=? where id=?";
            ps2 = cn.prepareStatement(sql2);
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
    }
    
    

    public Cliente buscaCliente(String nombre, String password){
        
        Cliente c = null;
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            Statement st = cn.createStatement();
            String sql = "select * from clientes where nombre='" + nombre + "' and password='" + password + "'";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                String pass = rs.getString(3);
                String domicilio = rs.getString(4);
                String codigoPostal = rs.getString(5);
                String telefono = rs.getString(6);
                String email = rs.getString(7);
                
                c = new Cliente(id, nom, pass, domicilio, codigoPostal, telefono, email);
            }
        }
        catch(SQLException e){
            System.err.println("ERROR el metodo: " + e.getMessage());
        }
        finally{
            return c; 
        }
    }
    
    
    public boolean buscaCliente(String nombre){
        
        boolean encontrado = false;
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();  */  
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            Statement st = cn.createStatement();
            String sql = "select * from clientes where nombre='" + nombre + "'";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                encontrado = true;
            }
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return encontrado;
        }
    }
    
    
    public boolean guardaCliente(Cliente c){
        
        boolean insertado = false;
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        ){
            ps.setInt(1, KeysDAO.siguienteId("clientes"));
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getDomicilio());
            ps.setString(5, c.getCodigopostal());
            ps.setString(6, c.getTelefono());
            ps.setString(7, c.getEmail());
            
            if(ps.executeUpdate()!=0){
                insertado = true;
            }
        }
        catch(SQLException e){
            System.err.println("ERROR el metodo: " + e.getMessage());
        }
        finally{
            return insertado;
        }
    }
    
    
    public boolean actualizaCliente(Cliente c){
        
        boolean actualizado = false;
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            ps2.setString(2, c.getNombre());
            ps2.setString(3, c.getPassword());
            ps2.setString(4, c.getDomicilio());
            ps2.setString(5, c.getCodigopostal());
            ps2.setString(6, c.getTelefono());
            ps2.setString(7, c.getEmail());
            
            if(ps2.executeUpdate()!=0){
                actualizado = true;
            }
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return actualizado;
        }
    }
    
    
}
