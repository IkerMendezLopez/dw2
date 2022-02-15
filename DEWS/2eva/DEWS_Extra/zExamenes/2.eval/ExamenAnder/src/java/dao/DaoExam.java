package dao;

import beans.Incidencia;
import beans.Item;
import beans.Pedido;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.packageMappingType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import sun.text.resources.FormatData;

public class DaoExam {
    
    private static PreparedStatement psPreciosMinMax;
    private static PreparedStatement psGrabaIncidencia;
    static{
        try{
            Connection cn = dameConexionPool();
            
            String sql = "select * from items where precio between ? and ? and exists (select * from lineaspedido where items.id=lineaspedido.iditem)";
            psPreciosMinMax = cn.prepareStatement(sql);
            
            String sql2 = "insert into incidencias (descripcion, idpedido, fecha) values (?,?,?)" ;
            psGrabaIncidencia = cn.prepareStatement(sql2);
        }
        catch(SQLException e){
            System.err.println("ERROR en el metodo: " + e.getMessage());
        }
    }
    

    
    private static Connection dameConexionPool(){
        
        Connection cn = null;
        try {
            BasicDataSource ds = new BasicDataSource();
        
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost/bdtienda");
            ds.setUsername("dw2");
            ds.setPassword("dw2");
            cn =  ds.getConnection();
        } 
        catch (SQLException e) {
             System.out.print("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return cn;
        }
    }
    
    
    private static void cierraConexion (java.sql.Connection cn){
        
        try {
            cn.close();
        } 
        catch (SQLException ex) {
           System.out.print("ERROR en el metodo: " + ex.getMessage());
        }
    }
    
    
    public static double [] preciosMinMax(){
        
        double [] arrPrecios = new double[2];
        Connection cn = (Connection) dameConexionPool();
        String sql = "select min(precio), max(precio) from items";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.first()){
                arrPrecios [0] = rs.getDouble(1);
                arrPrecios [1] = rs.getDouble(2);
            }
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Sintaxis de la consulta: " + sql);
        }
        finally{
            return arrPrecios;
        }
    }
    
    
    public static HashSet itemsPorPrecio (double minPrecio, double maxPrecio){
        
        HashSet <Item> lstItems = new HashSet <Item>();
        Connection cn = (Connection) dameConexionPool();
        try{
            psPreciosMinMax.setDouble(1, minPrecio);
            psPreciosMinMax.setDouble(2, maxPrecio);
            psPreciosMinMax.executeQuery();
            
            ResultSet rs = psPreciosMinMax.getResultSet();
            
            while(rs.next()){
                Item i = new Item();
                i.setId(rs.getInt(1));
                i.setNombre(rs.getString(2));
                i.setPrecio(rs.getDouble(3));
                i.setLstNombres(DaoExam.lstNombres(rs.getInt(1)));
                
                lstItems.add(i);
            }
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return lstItems;
        }
    }
    
    
    public static ArrayList <String> lstNombres (int idItem){
        
        ArrayList <String> lstNombres = new ArrayList<String>();
        Connection cn = (Connection) dameConexionPool();
        String sql = "select * from clientes where exists(select * from pedidos where idcliente=clientes.id and exists(select * from lineaspedido where pedidos.id=lineaspedido.idpedido and exists(select * from items where lineaspedido.iditem=items.id and items.id=" + idItem + ")))";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String nombre = rs.getString(2);
              System.out.print(nombre);
                lstNombres.add(nombre);
            }
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Sintaxis de la consulta: " + sql);
        }
        finally{
            return lstNombres;
        }
    }
    
    
    public static LinkedHashMap mapaPedidos(){
        
        LinkedHashMap <Pedido, ArrayList <Incidencia>> mapaPedidos = new LinkedHashMap <Pedido, ArrayList <Incidencia>>();    
        
        Connection cn = (Connection) dameConexionPool();
        String sql = "select * from pedidos";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Pedido p = new Pedido();
                p.setId(rs.getInt(1));
                p.setTotal(rs.getDouble(2));
                p.setFecha(rs.getDate(3));
                
                ArrayList <Incidencia> lstIncidencias = lstIncidencias(rs.getInt(1));
                
                mapaPedidos.put(p, lstIncidencias);
            }
           
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Sintaxis de la consulta: " + sql);
        }
        finally{
            return mapaPedidos;
        }
    }
    
    
    public static ArrayList <Incidencia> lstIncidencias (int idPedido){
        
        ArrayList <Incidencia> lstIncidencias = new ArrayList<Incidencia>();
        Connection cn = (Connection) dameConexionPool();
        String sql = "select * from incidencias where exists(select * from pedidos where pedidos.id=incidencias.idpedido)";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Incidencia i = new Incidencia();
                i.setId(rs.getInt(1));
                i.setDescripcion(rs.getString(2));
                i.setIdpedido(rs.getInt(3));
                i.setFecha(rs.getDate(4));
                
                lstIncidencias.add(i);
            }
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Sintaxis de la consulta: " + sql);
        }
        finally{
            return lstIncidencias;
        }
    }
    
    
    public static boolean grabarIncidencia(Incidencia i){
        
        boolean exitoso = false;
        Connection cn = (Connection) dameConexionPool();
        try{
            if(!compruebaDescripcion(i.getDescripcion()) && !compruebaFechaIncidencia(i.getFecha())){
                psGrabaIncidencia.executeUpdate();
                exitoso = true;
            }

            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return exitoso;
        }
    }
    
    
    private static boolean compruebaDescripcion(String descripcion){
        
        boolean iguales = false;
        Connection cn = (Connection) dameConexionPool();
        String sql = "select * from incidencias where descripcion='" + descripcion + "'";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.first()){
                iguales = true;
            }
        
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Sintaxis de la consulta: " + sql);
        }
        finally{
            return iguales;
        }
    }
    
    
    private static boolean compruebaFechaIncidencia(Date fecha){
        
        boolean iguales = false;
        Connection cn = (Connection) dameConexionPool();

        String sql = "select count(fecha) from incidencias where fecha='" + fecha.getTime() +"' group by fecha";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
           
            if(rs.getInt(1)>=2){
                iguales = true;
            }
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());    
            System.out.print("Sintaxis de la consulta: " + sql);
        }
        catch(IllegalArgumentException e){
            
        }
        
        finally{
            return iguales;
        }
    }
    
    
    
    
    public static void main (String args[]){
        
        DaoExam e = new DaoExam();
        double [] precios = preciosMinMax();
        System.out.println(precios[0] + precios[1]);
        
        
        System.out.println("------------------------");
        
        HashSet <Item> lstItems = itemsPorPrecio(10, 40);
        for(Item i : lstItems){
            System.out.print(i.toString()); 
        }
        
        System.out.println("------------------------");
        
        HashMap <Pedido, ArrayList <Incidencia>> mapaPrueba = mapaPedidos();
        Iterator <Pedido> it = mapaPrueba.keySet().iterator();
        while(it.hasNext()){
            Pedido p = it.next();
            System.out.print(p.toString() + "----------" + mapaPrueba.get(p));
        }
        
        
        System.out.println(compruebaDescripcion("fasdfas"));
        System.out.println(compruebaDescripcion("pepe"));
        
        
        System.out.println("------------------------");
          
        System.out.print(compruebaFechaIncidencia(new Date()));
          
        
    }
    
}
