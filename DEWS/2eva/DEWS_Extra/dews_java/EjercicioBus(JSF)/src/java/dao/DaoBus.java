package dao;

import beans.Bus;
import beans.Cliente;
import beans.Ruta;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;

public class DaoBus {

    private static final BasicDataSource ds = new BasicDataSource();
    private static PreparedStatement psReserva, psRegistroCliente;
    
    static{
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/bdbus");
        ds.setUsername("dw2");
        ds.setPassword("dw2");
        
        
        String sql = "insert into reservas (Pagado, NumAsiento, Id_DNI, Id_ruta) values(0,?,?,?)";
        String sql2 = "insert into clientes (Id_DNI, Nombre, Direccion, Email) values (?,?,?,?)";
        try{
            Connection cn = ds.getConnection();    
            psReserva = cn.prepareStatement(sql);
            psRegistroCliente = cn.prepareStatement(sql2);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        
    }
    
    
    public static ArrayList<Bus> listaBuses(){
        
        ArrayList <Bus> lstBuses = null;
        String sql = "select * from buses";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            lstBuses = new ArrayList<Bus>();
            
            while(rs.next()){
                Bus b = new Bus();
                b.setIdPlaca(rs.getString("id_placa"));
                b.setImagen(rs.getString("imagen"));
                b.setCapacidad(rs.getInt("capacidad"));
                
                
                lstBuses.add(b);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return lstBuses;
        }
    }
    
    
    public static ArrayList <Ruta> rutasBus(String id){
        
        ArrayList <Ruta> lstRutas = null; 
        String sql = "select * from rutas where Id_Placa='" + id + "'";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            lstRutas = new ArrayList<Ruta>();
            
            while(rs.next()){
                Ruta r = new Ruta();
                r.setIdRuta(rs.getInt("Id_Ruta"));
                r.setCiudadOrigen(rs.getString("CiudadOrigen"));
                r.setCiudadDestino(rs.getString("CiudadDestino"));
                Timestamp ts = new Timestamp(rs.getTimestamp("HoraSalida").getTime());
                r.setHoraSalida(ts);
                ts = new Timestamp(rs.getTimestamp("HoraLlegada").getTime());
                r.setHoraLlegada(ts);
                r.setTarifa(rs.getFloat("Tarifa"));
                r.setIdPlaca(rs.getString("Id_Placa"));
                
                lstRutas.add(r);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return lstRutas;
        }
    }

    
    public static void modificarTarifaRuta(Ruta r){
        
       // boolean estadoModificacion = false;
        String sql = "update rutas set tarifa=" + r.getTarifa() +" where Id_Ruta=" + r.getIdRuta();
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
        ){
            st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
    }
    
    
    public static ArrayList <Ruta> listaRutas(){
        
        ArrayList <Ruta> lstRutas = null; 
        String sql = "select * from rutas";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            lstRutas = new ArrayList<Ruta>();
            
            while(rs.next()){
                Ruta r = new Ruta();
                r.setIdRuta(rs.getInt("Id_Ruta"));
                r.setCiudadOrigen(rs.getString("CiudadOrigen"));
                r.setCiudadDestino(rs.getString("CiudadDestino"));
                Timestamp ts = new Timestamp(rs.getTimestamp("HoraSalida").getTime());
                r.setHoraSalida(ts);
                ts = new Timestamp(rs.getTimestamp("HoraLlegada").getTime());
                r.setHoraLlegada(ts);
                r.setTarifa(rs.getFloat("Tarifa"));
                r.setIdPlaca(rs.getString("Id_Placa"));
                
                lstRutas.add(r);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return lstRutas;
        }
    }
    
    
    public static HashMap <Integer, String> asientosReservados(int idRuta){
        
        HashMap <Integer, String> asientosReservados = null;
        String sql = "select * from reservas where exists(select * from rutas where rutas.Id_Ruta=reservas.Id_Ruta and rutas.Id_Ruta=" + idRuta + ")";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            asientosReservados = new HashMap <Integer, String>();
            
            while(rs.next()){
                
                int numAsiento = rs.getInt(3);
                String nomCli = clienteCorrespondiente(rs.getString(4));
                
                asientosReservados.put(numAsiento, nomCli);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return asientosReservados;
        }
    }
    
    
    private static String clienteCorrespondiente (String dniCli){

        String nomCli = null;
        String sql = "select * from clientes where exists(select * from reservas where reservas.Id_DNI=Clientes.Id_DNI) and Id_DNI='" + dniCli + "'";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            if(rs.first()){  
                nomCli = rs.getString(2);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return nomCli;
        }
    }
    
    
    public static int cantAsientos(int idRuta){
        
        int cantAsientos = 0;
        String sql = "select * from buses where exists(select * from rutas where rutas.Id_Placa=buses.Id_Placa and rutas.Id_Ruta=" + idRuta + ")";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            if(rs.first()){
                cantAsientos = rs.getInt(2);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return cantAsientos;
        }
    }
    
    
    public static void reservarAsientos(String idCliente, int asiento, int idRuta){

        try(
            Connection cn = ds.getConnection();
        )
        {
           psReserva.setInt(1, asiento);
           psReserva.setString(2, idCliente);
           psReserva.setInt(3, idRuta);
            
           psReserva.executeUpdate();
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
        }
    }
    
    
    public static boolean compruebaCliente (String idCliente){
        
        boolean estadoCli = false;
        String sql = "select * from clientes where Id_DNI='" + idCliente + "'";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
           if(rs.first()){
              estadoCli = true; 
           }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
        }
        finally{
            return estadoCli;
        }
    }
    
    
    public static ArrayList <Cliente> listaClientes(){
        
        ArrayList <Cliente> lstClientes = null;
        String sql = "select * from clientes";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            lstClientes = new ArrayList<Cliente>();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setDni(rs.getString(1));
                c.setNombre(rs.getString(2));
                c.setDireccion(rs.getString(3));
                c.setEmail(rs.getString(4));
                
                lstClientes.add(c);
            }
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            System.out.print("Consulta: " + sql);
        }
        finally{
            return lstClientes;
        }
    }
    
    
    public static boolean registrarCliente (Cliente c){
        
        try(
            Connection cn = ds.getConnection();
        )
        {
           psRegistroCliente.setString(1, c.getDni());
           psRegistroCliente.setString(2, c.getNombre());
           psRegistroCliente.setString(3, c.getDireccion());
           psRegistroCliente.setString(4, c.getEmail());
            
           psRegistroCliente.executeUpdate();
           return true;
        }
        catch(SQLException e){
            System.out.print("ERROR en el metodo: " + e.getMessage());
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]){
        
        ArrayList <Bus> lstBuses = listaBuses();
        for(Bus b :lstBuses){
            System.out.print(b.toString() + "\n");
        }
        
        
        System.out.println("---------------");
        
        
        ArrayList <Ruta> lstRutas = rutasBus("WMX-0002");
        for(Ruta r :lstRutas){
            System.out.print(r.toString() + "\n");
        }
        
        
        System.out.println("---------------");
        
        
        ArrayList <Ruta> lstRutas2 = listaRutas();
        for(Ruta r :lstRutas2){
            System.out.print(r.toString() + "\n");
        }
        
        
        System.out.println("---------------");
        
        
        System.out.println(clienteCorrespondiente("10845812"));
        
        
        System.out.println("---------------");
        
        
        HashMap <Integer, String> asientosReservados = asientosReservados(1);
        Iterator <Integer> it = asientosReservados.keySet().iterator();
        while(it.hasNext()){
            int asiento = it.next();
            String nombre = asientosReservados.get(asiento);
            System.out.println(asiento + " ➡ " + nombre);
        }
        
        
        System.out.println("---------------");
        
        
        System.out.println(cantAsientos(2));
        
        
        System.out.println("---------------");
        
        
        System.out.print(compruebaCliente("1357446435"));
        System.out.print(compruebaCliente("10845812"));
        
    }
}
