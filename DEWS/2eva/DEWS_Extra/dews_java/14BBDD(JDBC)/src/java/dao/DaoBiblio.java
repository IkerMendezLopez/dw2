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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoBiblio {
    
    //Dejar la conexion, y el PrepareStatement directamente creados de forma estatica para acceder a ellos en caso de necesitar mas de una vez realizar la misma consulta sobre la bbdd
    static PreparedStatement ps;
    static{
        try{
            Connection cn = dameConexionPool();
            
            String sql = "select idprestamo, fecha, fechadev from prestamos where idlibro=?";
            ps = cn.prepareStatement(sql);
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.err.print("ERROR en el metodo " + e.getMessage());
        }
    }

    private static Connection dameConexionPool(){
        
        Connection cn = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/biblio");
            cn = ds.getConnection();
        } 
        catch (NamingException ex) {
            Logger.getLogger(DaoBiblio.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return cn;
        }
    }
    
    
    private static void cierraConexion (Connection cn){
        
        try {
            cn.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(DaoBiblio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static ArrayList <Libro> libros(){

        ArrayList <Libro> lstLibros = new ArrayList <Libro>();
        try{
            Connection cn = dameConexionPool();
            
            Statement st = cn.createStatement(); //Objeto usado para realizar acciones con la BBDD y los valores devueltos de dichas acciones
            String sql = "select idlibro, titulo, paginas, genero, idautor from libros";
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                Libro l = new Libro();
                l.setIdlibro(rs.getInt("idlibro")); //tambien podemos poner rs.getInt(indice) y hariamos refencia a la columna del indice indicado
                l.setTitulo(rs.getString("titulo"));
                l.setPaginas(rs.getInt("paginas"));
                l.setGenero(rs.getString("genero"));
                l.setIdautor(rs.getInt("idautor"));

                lstLibros.add(l);
            } 
            
            cierraConexion(cn);
        }
        catch(SQLException e){
            System.err.print("ERROR en el metodo " + e.getMessage());
        }
        finally{
            return  lstLibros;
        }
    }
    
    
    public static void prestarLibros(int [] ids){
        
        try{
            Connection cn = dameConexionPool();
            
            String sql = "insert into prestamos (fecha, idlibro, fechadev) values (?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            
            for(int i=0; i<ids.length; i++){
                
                ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
                ps.setInt(2, ids[i]);
                ps.setDate(3, null);
                ps.executeUpdate();
            }

            ps.close();
            cierraConexion(cn);
        }
        catch(SQLException e){
             System.err.print("ERROR en el metodo " + e.getMessage());
        }
    }
    
    
    public static HashMap <Integer,Integer> mapCantidadPrestamos(){
        
        HashMap <Integer,Integer> mapa = new HashMap <Integer,Integer>();
        ArrayList <Libro> libros = libros();
        
        try{
            Connection cn = dameConexionPool();
            
            String sql = "select count(*) as cantidad from prestamos where idlibro=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            
            for(Libro libro : libros){
                ps.setInt(1, libro.getIdlibro());
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    mapa.put(libro.getIdlibro(), rs.getInt("cantidad"));
                }
                
                rs.close();
            }

            ps.close();
            cierraConexion(cn);
        }
        catch(SQLException e){
             System.err.print("ERROR en el metodo " + e.getMessage());
        }

        return mapa;
    }
    
    
    public static HashMap <Integer, ArrayList <Prestamo>> mapaPrestamos(){
        
        HashMap <Integer, ArrayList <Prestamo>> mapaPrestamos = new HashMap<Integer, ArrayList<Prestamo>>();
        ArrayList <Libro> lstLibros = libros();

        try{
            /*Connection cn = dameConexionPool();
            
            String sql = "select idprestamo, fecha, fechadev from prestamos where idlibro=?";
            PreparedStatement ps = cn.prepareStatement(sql);*/
            
            for(Libro libro : lstLibros){
                ArrayList <Prestamo> lstPrestamos = new ArrayList<Prestamo>();
                ps.setInt(1, libro.getIdlibro());
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int idPrestamo = rs.getInt("idprestamo");
                    
                    java.util.Date fechaDev = null;
                    if(rs.getDate("fechadev")!=null){
                        fechaDev = new java.util.Date(rs.getDate("fechadev").getTime());
                    }
                    java.util.Date fecha = new java.util.Date(rs.getDate("fecha").getTime()); //Crear fecha java.util apartir de fecha java.sql
                    
                    Prestamo p = new Prestamo();
                    p.setIdprestamo(idPrestamo);
                    p.setFecha(fecha);
                    p.setIdlibro(libro.getIdlibro());
                    p.setFechadev(fechaDev);
                    
                   lstPrestamos.add(p);
                }
                
                mapaPrestamos.put(libro.getIdlibro(), lstPrestamos);
                rs.close();
            }
            ps.close();

            //cierraConexion(cn);
        }
        catch(SQLException e){
            System.err.print("ERROR en el metodo " + e.getMessage());
        }
        finally{
            return  mapaPrestamos;
        }
        
    }
    
    
}
