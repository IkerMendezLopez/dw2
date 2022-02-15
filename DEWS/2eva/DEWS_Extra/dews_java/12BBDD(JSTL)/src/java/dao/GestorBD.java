package dao;

import beans.Autor;
import beans.Libro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestorBD {

    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost/bdlibros";
    private static final String USER="root";
    private static final String PASS="";
    
    private Connection cn;
    
    public GestorBD(){        
        
        try {
            Class.forName(DRIVER);
            cn=DriverManager.getConnection(URL, USER, PASS);
        } 
        catch (ClassNotFoundException ex) {
            System.err.println("ERROR en driver");    
        }
        catch (SQLException ex) {
            System.err.println("ERROR al conectarse a la BD");
        }        
    }
    
    
    public ArrayList <Libro> libros(){
        
        ArrayList <Libro> lstLibros = new ArrayList <Libro>();
        try{
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
        }
        catch(SQLException e){
            System.err.print("ERROR en el metodo " + e.getMessage());
        }
        
        
        return  lstLibros;
    }
    
    
    public LinkedHashMap<Integer, Autor> autores(){
     
        LinkedHashMap <Integer, Autor> mapa = new LinkedHashMap <Integer, Autor>();
        
        try{
            Statement st = cn.createStatement(); //Objeto usado para realizar acciones con la BBDD y los valores devueltos de dichas acciones
            String sql = "select idautor, nombre from autores";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Autor a = new Autor();
                a.setIdautor(rs.getInt("idautor"));
                a.setNombre(rs.getString("nombre"));
               
                mapa.put(a.getIdautor(), a);
            }   
        }
        catch(SQLException e){
            System.err.print("ERROR en el metodo " + e.getMessage());
        }
        
        
        return mapa;
    }
    
    public void insertarLibro(Libro l){
        
        try{
            Statement st = cn.createStatement(); //Objeto usado para realizar acciones con la BBDD y los valores devueltos de dichas acciones
            String sql = "insert into libros (titulo, paginas, genero, idautor) values ('" + l.getTitulo() + "'," + l.getPaginas() + ",'" + l.getGenero() + "'," + l.getIdautor() + ")";
            System.out.print(sql);
            
            st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.err.print("ERROR en el metodo " + e.getMessage());
        }
    }
}
