package dao;

import beans.Item;
import beans.LineaPedido;
import beans.Pedido;
import conex.BDConex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.apache.commons.dbcp2.BasicDataSource;


public class PedidoDAO {
    
    private static BDConex ds;
    static PreparedStatement ps;
    static PreparedStatement ps2;
    
    static{
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            String sql = "insert into pedidos (id, total, fecha, idcliente) values (?,?,?,?)";
            ps = cn.prepareStatement(sql);
            
            String sql2 = "insert into lineaspedidos (id, cantidad, idpedido, iditem) values (?,?,?,?)";
            ps2 = cn.prepareStatement(sql2);
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
    }
    
    
    
    public HashMap <Integer, Item> todosItems(){
        
        HashMap <Integer, Item> mapaItems = new HashMap <Integer, Item>();
        
        try{
            Connection cn = ds.damePoolConexiones().getConnection();
            
            Statement st = cn.createStatement();
            String sql = "select * from items";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                Double precio = rs.getDouble(3);
                
                Item i = new Item(id, nombre, precio);
                mapaItems.put(rs.getInt(1), i);
            }
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return mapaItems;
        }
    }
    
    
    public Item buscaItemPorId(int iditem){
        
        Item i = null;
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            Statement st = cn.createStatement();
            String sql = "select * from item where id=" + iditem;
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                Double precio = rs.getDouble(3);
                
                i = new Item(id, nombre, precio);
            }
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return i; 
        }
    }
    
    
    public void guardaPedido(Pedido p){
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            ps.setInt(1, p.getId());
            ps.setDouble(2, p.getTotal());
            ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            ps.setInt(4, p.getCliente().getId());
            
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
    }
    
    
    public void guardarLineaPedido(LineaPedido l){
        
        try(
            /*BasicDataSource ds = BDConex.damePoolConexiones();
            Connection cn = ds.getConnection();    */
            Connection cn = ds.damePoolConexiones().getConnection();
        )
        {
            ps.setInt(1, l.getId());
            ps.setInt(2, l.getCantidad());
            ps.setInt(3, l.getPedido().getId());
            ps.setInt(4, l.getItem().getId());
                    
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
    }
    
    
    
}
