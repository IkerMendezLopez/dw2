package dao;

import beans.Cliente;
import conex.BDConex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    
   private static PreparedStatement pstGuardaCliente;
    private static PreparedStatement pstActualizaCliente;
    
    static{
        String GuardaCliente = "INSERT INTO clientes VALUES(?, ?, ?, ?, ?, ?, ?)";
        String ActualizaCliente = "UPDATE clientes "
                    + "SET nombre = ?, password = ?, domicilio = ?, codigopostal = ?, telefono = ?, email = ? "
                    + "WHERE id=?";

        try {
             Connection cn=BDConex.dameConexionPool().getConnection(); 
             pstGuardaCliente = cn.prepareStatement(GuardaCliente);
             pstActualizaCliente = cn.prepareStatement(ActualizaCliente);
        }
        catch (SQLException ex) {
            System.out.println("FALLO sql al guardar o actualizar cliente: "+ex);
        }
    }
    
    public static Cliente buscaCliente(String nom, String pass){
        Cliente c=null;
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="Select id, nombre, password, domicilio, codigopostal, telefono, email from clientes where nombre='"+nom+"' and password='"+pass+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                c=new Cliente();
                
                int id=rs.getInt("id");
                String nombre=rs.getString("nombre");
                String password=rs.getString("password");
                String domicilio=rs.getString("domicilio");
                String codigopostal=rs.getString("codigopostal");
                String telefono=rs.getString("telefono");
                String email=rs.getString("email");
                
                c.setId(id);
                c.setNombre(nombre);
                c.setPassword(password);
                c.setDomicilio(domicilio);
                c.setCodigopostal(codigopostal);
                c.setTelefono(telefono);
                c.setEmail(email);
            }
        } catch (SQLException ex) {
           System.out.println("Error en buscaCliente(nom,pass): "+ex);
        }  
        return c;
    }
    
    public static boolean buscaCliente(String nom){
        boolean encontrado=false;
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="Select nombre from clientes where nombre='"+nom+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                encontrado=true;
            }
        } catch (SQLException ex) {
            System.out.println("Error en buscaCliente(nom): "+ ex);
        }
        return encontrado;
    }
    
    public static boolean guardaCliente(Cliente c){
        boolean guardado=false;
        try{
            pstGuardaCliente.setInt(1, c.getId());
            pstGuardaCliente.setString(2, c.getNombre());
            pstGuardaCliente.setString(3, c.getPassword());
            pstGuardaCliente.setString(4, c.getDomicilio());
            pstGuardaCliente.setString(5, c.getCodigopostal());
            pstGuardaCliente.setString(6, c.getTelefono());
            pstGuardaCliente.setString(7, c.getEmail());
            pstGuardaCliente.executeUpdate();
            guardado=true;
        } catch (SQLException ex) {
            System.out.println("Error en guardaCliente: "+ ex);
        }
        return guardado;
    }
    
    public static boolean actualizaCliente(Cliente c){
        boolean actualizado=false;

        try{
            pstActualizaCliente.setString(1, c.getNombre());
            pstActualizaCliente.setString(2, c.getPassword());
            pstActualizaCliente.setString(3, c.getDomicilio());
            pstActualizaCliente.setString(4, c.getCodigopostal());
            pstActualizaCliente.setString(5, c.getTelefono());
            pstActualizaCliente.setString(6, c.getEmail());
            pstActualizaCliente.setInt(7, c.getId());
            pstActualizaCliente.executeUpdate();
            actualizado=true;
        } catch (SQLException ex) {
            System.out.println("Error en guardaCliente: "+ ex);
        }
        return actualizado;
    }
    
    public static int cuentaClis(){
        int clientes=0;
        try(Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="select count(*) cuantos from clientes";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                clientes=rs.getInt("cuantos");
            }
        } catch (SQLException ex) {
            System.out.println("Error en cuentaClis: "+ ex);
        }
        
        return clientes;
    }
}
