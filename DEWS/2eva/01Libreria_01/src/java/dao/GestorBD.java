/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Autor;
import beans.Libro;
import beans.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Akaitz
 */
public class GestorBD {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root";
    private static final String PASS = "";
    private static BasicDataSource dataSource;

    public GestorBD() {
        //Creamos el pool de conexiones
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        //Indicamos el tamaño del pool de conexiones
        dataSource.setInitialSize(50);
    }
    
    public ArrayList<Libro> libros(){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        String sql = "SELECT * FROM libro";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Libro libro = new Libro(rs.getInt("id"), rs.getString("titulo"),
                                        rs.getInt("paginas"), rs.getString("genero"), 
                                        rs.getInt("idAutor"));
                libros.add(libro);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return libros;
    }
    public ArrayList<Prestamo> prestamos(){
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        String sql = "SELECT * FROM prestamo";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Prestamo prestamo = new Prestamo(rs.getInt("id"), rs.getDate("fecha"),rs.getInt("idlibro"));
                prestamos.add(prestamo);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return prestamos;
    }
    
    public LinkedHashMap<Integer, String> autores(){
        LinkedHashMap<Integer, String> autores = new LinkedHashMap<Integer, String>();
        String sql = "SELECT id, nombre FROM autor";
        Connection con;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                autores.put(rs.getInt("id"), rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    }
    
    public ArrayList<Autor> getAutores(){
        ArrayList<Autor> autores = new ArrayList<Autor>();
        String sql = "SELECT * FROM autor";
        Connection con;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                autores.add(new Autor(rs.getInt("id"), rs.getString("nombre"), rs.getDate("fechanac"), rs.getString("nacionalidad")));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    }
    
    public boolean existeLibro(Libro libro){
        boolean existe = false;
        String sql = "SELECT id FROM libro WHERE titulo = '" + libro.getTitulo() +
                "' AND idautor = " + libro.getIdAutor();
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                existe = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public int insertarLibro(Libro libro){
        int id = -1;
        String sql = "INSERT INTO libro(titulo, paginas, genero, idautor) "
                + " VALUES(?, ?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, libro.getTitulo());
            st.setInt(2, libro.getPaginas());
            st.setString(3, libro.getGenero());
            st.setInt(4, libro.getIdAutor());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarLibro: " + ex);
        }
        
        return id;
    }
    
    public Libro dameLibro(int id){
        ArrayList<Libro> libros = libros();
        for(Libro l:libros){
            if(l.equals(new Libro(id))){
                return l;
            }
        }
        return null;
    }
    public Prestamo damePrestamo(int id){
        ArrayList<Prestamo> prestamos = prestamos();
        for(Prestamo p: prestamos){
            if(p.equals(new Prestamo(id))){
                return p;
            }
        }
        return null;
    }
    public LinkedHashMap<Prestamo, Libro> librosPrestados(){
        LinkedHashMap<Prestamo, Libro> libros = new LinkedHashMap<Prestamo, Libro>();
        String sql = "SELECT * FROM prestamo order by fecha";
         try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                libros.put(damePrestamo(rs.getInt("id")),dameLibro(rs.getInt("idlibro")));
                
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return libros;
    }
    
    public boolean devolverLibro(int id){
        String sql = "delete from prestamo where id=" +id;
        int n=0;
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            n = st.executeUpdate(sql); 
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return n>0;
    }
    
    public ArrayList<Libro> librosDeAutor(int idAutor){
        ArrayList<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libro where idAutor="+ idAutor;
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
//            while(rs.next()){
            while(rs.next()){
                Libro libro = new Libro(rs.getInt("id"), rs.getString("titulo"),
                                        rs.getInt("paginas"), rs.getString("genero"), 
                                        rs.getInt("idAutor"));
                libros.add(libro);
            }
                
//            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo librosDeAutor: " + ex);
        }
        return libros;
    }
    
    public boolean hacerPrestamo(int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String sql = "INSERT INTO prestamo(fecha, idlibro) "
                + " VALUES('" +  sdf.format(new Date()) + "'," + id + ")";
//        String sql = "insert into prestamo values ('"+ sdf.format(new Date())+"', "+ id +")";
        int n =-1;
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            n = st.executeUpdate(); 
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
//        System.out.println(n);
        System.out.println(id);
        return n>0;
    }
    
    public boolean existeAutor(String nombre){
         String sql = "SELECT * FROM autor where nombre='"+nombre+"'";
         System.out.println(sql);
          try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
               return true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo existeAutor: " + ex);
        }
        return false;
    }
    
    public boolean aniadirAutor(Autor a){
        int n = -1;
        String sql = "INSERT INTO autor(nombre, fechanac, nacionalidad) "
                + " VALUES(?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getNombre());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            st.setString(2, sdf.format(a.getFechanac()));
            st.setString(3, a.getNacionalidad());
            n = st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarLibro: " + ex);
        }
        
        return n>0;
    }
}
