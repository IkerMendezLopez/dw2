package dao;

import beans.Cliente;
import beans.Item;
import beans.LineaPedido;
import beans.Pedido;
import conex.BDConex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO {
    private static PreparedStatement pstguardarPedido;
    private static PreparedStatement pstguardarLineaPedido;
    
    static{
        String guardarPedido="Insert into pedidos(id,total,fecha,idcliente) values(?,?,?,?)";
        String guardarLineaPedido="Insert into lineaspedido(id,cantidad,idpedido,iditem) values(?,?,?,?)";
        
        try {
             Connection cn=BDConex.dameConexionPool().getConnection(); 
             pstguardarPedido = cn.prepareStatement(guardarPedido);
             pstguardarLineaPedido= cn.prepareStatement(guardarLineaPedido);
        }
        catch (SQLException ex) {
            System.out.println("FALLO sql al guardar o actualizar cliente: "+ex);
        }
    }
    
    
    public static Map todosItems(){
        HashMap<Integer, Item> hm=new HashMap<Integer,Item>();
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="Select id,nombre,precio from items";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt("id");
                String nombre=rs.getString("nombre");
                double precio=rs.getDouble("precio");
                Item i=new Item(id,nombre,precio);
                hm.put(id, i);
            }
        } catch (SQLException ex) {
            System.out.println("Error en todosItems: "+ex);
        }
        return hm;
    }
    
    public static Item buscarItem(int iditem){
        Item i=null;
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="Select id,nombre,precio from items where id="+iditem;
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
               int id=rs.getInt("id");
               String nombre=rs.getString("nombre");
               double precio=rs.getDouble("precio");
               
               i=new Item(id,nombre,precio);
            }
        } catch (SQLException ex) {
            System.out.println("Error en tbuscarItem: "+ex);
        }
        
        return i;
    }
    
    public static boolean guardarPedido(Pedido p){
        boolean guardado=false;
        try{
            int id=p.getId();
            double total=p.getTotal();
            Date fecha=p.getFecha();
            int idcliente=p.getCliente().getId();
            
            pstguardarPedido.setInt(1, id);
            pstguardarPedido.setDouble(2, total);
            pstguardarPedido.setTimestamp(3, new Timestamp(fecha.getTime()));
            pstguardarPedido.setInt(4,idcliente);
            pstguardarPedido.executeUpdate();
            guardado=true;
        } catch (SQLException ex) {
            System.out.println("Error en guardaPedido: "+ex);
        }

        return guardado;
    }
    
    public static boolean guardarLineaPedido(LineaPedido l){
        boolean guardado=false;

        try{
            int id=l.getId();
            double cantidad=l.getCantidad();
            int idpedido=l.getPedido().getId();
            int iditem=l.getItempedido().getId();
            
            pstguardarLineaPedido.setInt(1, id);
            pstguardarLineaPedido.setDouble(2, cantidad);
            pstguardarLineaPedido.setInt(3, idpedido);
            pstguardarLineaPedido.setInt(4, iditem);
            pstguardarLineaPedido.executeUpdate();
            guardado=true;
        } catch (SQLException ex) {
            System.out.println("Error en guardarLinePedido: "+ex);
        }
         
        return guardado;
    }
    
    public static Map<Integer, Pedido> todosPedidos(Cliente c){
        Map<Integer, Pedido> mpedidos=new HashMap<Integer, Pedido>();
        int idcliente=c.getId();
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="Select id, total, fecha, idcliente from pedidos where idcliente="+idcliente;
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                
                Pedido p=new Pedido();
                p.setId(rs.getInt("id"));
                p.setTotal(rs.getDouble("total"));
                p.setFecha(rs.getDate("fecha"));
                p.setCliente(c);

                Statement st2=cn.createStatement();
                String sql2="Select id,cantidad,idpedido,iditem from lineaspedido where idpedido="+p.getId();
                ResultSet rs2=st2.executeQuery(sql2);
                HashSet<LineaPedido> lineas=new HashSet<LineaPedido>();
                while(rs2.next()){
                    LineaPedido lp=new LineaPedido();
                    lp.setId(rs2.getInt("id"));
                    lp.setCantidad(rs2.getInt("cantidad"));
                    int iditem=rs2.getInt("iditem");
                    Item i=buscarItem(iditem);
                    lp.setItempedido(i);
                    lp.setPedido(p);
                    lineas.add(lp);
                }
                st2.close();
                p.setLineas(lineas);
                
                mpedidos.put(rs.getInt("id"), p);
            }
        } catch (SQLException ex) {
            System.out.println("Error en todosPedidos: "+ex);
        }

        return mpedidos;
    }
    
    public static void borrarPedido(int idpedido){
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="Delete from lineaspedido where idpedido="+idpedido;
            st.executeUpdate(sql);
            
            Statement st2=cn.createStatement();
            String sql2="Delete from pedidos where id="+idpedido;
            st2.executeUpdate(sql2);
            
            st2.close();
        } catch (SQLException ex) {
            System.out.println("Error en borrarPedido: "+ex);
        }
        
    }
}
