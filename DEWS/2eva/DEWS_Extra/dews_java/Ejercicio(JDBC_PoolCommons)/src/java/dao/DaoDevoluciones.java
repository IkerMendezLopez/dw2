package dao;

import beans.Libro;
import beans.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.dbcp2.BasicDataSource;

public class DaoDevoluciones {

    private static final BasicDataSource ds = new BasicDataSource();
    static{
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/bdlibros");
        ds.setUsername("dw2");
        ds.setPassword("dw2");
    }
    
    
    public static HashMap <Integer, Libro> libros(){
        
        HashMap <Integer, Libro> mapaLibros = new HashMap<Integer, Libro>();
        String sql = "select idlibro, titulo, paginas, genero, idautor from libros";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        )
        {
            while(rs.next()){
                Libro l = new Libro();
                
                l.setIdlibro(rs.getInt("idlibro"));
                l.setTitulo(rs.getString("titulo"));
                l.setPaginas(rs.getInt("paginas"));
                l.setGenero(rs.getString("genero"));
                l.setIdautor(rs.getInt("idautor"));
                
                mapaLibros.put(rs.getInt("idlibro"), l);
            }
            
        }
        catch(SQLException e){
            System.err.println("Error en el metodo: " + e.getMessage());
            System.err.println("Sintaxis de la consulta: " + sql);
        }
        finally{
            return mapaLibros;
        }
    }
    
    
    public static ArrayList <Prestamo> prestamos(){
        
        ArrayList <Prestamo> lstPrestamos = new ArrayList<Prestamo>();
        String sql = "select idprestamo, fecha, idlibro from prestamos where fechadev is null order by fecha";
        try(
            Connection cn = ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);)
        {             
            while(rs.next()){
                Prestamo p = new Prestamo();
                        
                p.setIdprestamo(rs.getInt("idprestamo"));
                p.setFecha(rs.getDate("fecha"));
                p.setIdlibro(rs.getInt("idlibro"));
                        
                lstPrestamos.add(p);
            }      
        }
        catch(SQLException e){
            System.err.println("Error en el metodo: " + e.getMessage());
            System.err.println("Sintaxis de la consulta: " + sql);
        }
        finally{
            return lstPrestamos; 
        }
    }
    
    
    public static void grabarDevoluciones(ArrayList <Integer> lstDevoluciones){
        
        String sql = "update prestamos set fechadev=now() where idprestamo=?";
        try(
            Connection cn = ds.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);)
        {
            for(Integer idPrestamo : lstDevoluciones){
                ps.setInt(1, idPrestamo);
                ps.executeUpdate();
            }
        }
        catch(SQLException e){
            System.err.println("Error en el metodo: " + e.getMessage());
            System.err.println("Sintaxis de la consulta: " + sql);
        }
    }
    
    
}
