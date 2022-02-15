/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 *
 * @author dw2
 */
public class Dao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bdactividad?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root";
    private static final String PASS = "";
    private static BasicDataSource dataSource;

   

    public Dao() {
        //Creamos el pool de conexiones
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        //Indicamos el tamaño del pool de conexiones
        dataSource.setInitialSize(50);
    }
    
    public Alumno loginAlumno(String dni, String clave){
        String sql = "SELECT * FROM alumno WHERE dni = '"+dni+"'";
        Alumno al= new Alumno();
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
                 String ape = rs.getString("apellidos");
                 String contra = ape.substring(0,3).toLowerCase()+ape.substring(ape.indexOf(" ")+1,ape.indexOf(" ")+4).toLowerCase();
                 if(clave.equals(contra)){
                    al = new Alumno(rs.getString("dni"),ape,rs.getString("nombre"),rs.getString("telefono"),rs.getString("email"));
                 }
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo loginAlumno: " + ex);
        }
        return al;
    }
    
    public Alumno getAlumno(String dni){
        String sql = "SELECT * FROM alumno WHERE dni="+dni;
        Alumno al= new Alumno();
        try{
        Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("dni")!=null){
                    al = new Alumno(rs.getString("dni"),rs.getString("apellidos"),rs.getString("nombre"),rs.getString("telefono"),rs.getString("email"));
                }
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo getAlumno: " + ex);
        }
        return al;
    }
    public Impartidor loginImpartidor(int id_imp, String clave){
        String sql = "SELECT * FROM impartidor WHERE id="+id_imp;
        Impartidor im = new Impartidor();
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(clave.equals("damocles")){
                    im = new Impartidor(rs.getInt("id"),rs.getString("apellido"),rs.getString("nombre"));
                }
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo loginImpartidor: " + ex);
        }
        return im;
    }
    public Impartidor getImpartidor(int id){
        String sql = "SELECT * FROM impartidor WHERE id="+id;
        Impartidor im= new Impartidor();
        try{
        Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("id")!=null){
                    im = new Impartidor(rs.getInt("id"),rs.getString("apellido"),rs.getString("nombre"));
                }
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo getImpartidor: " + ex);
        }
        return im;
    }
    
    public ArrayList<String> actividadesImpartidor(Impartidor im){
        String sql = "SELECT * FROM actividad WHERE impartidor_id="+im.getId();
        ArrayList<String> ActividadesImpartidor = new ArrayList<String>();
        try {
            Connection con = dataSource.getConnection();
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ActividadesImpartidor.add(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo loginImpartidor: " + ex);
        }
        return ActividadesImpartidor;
    }
    
    public HashMap<Actividad, Impartidor> actividadesParticipa(Alumno al){
        String sql = "SELECT * FROM actividad WHERE EXISTS(SELECT * FROM participa WHERE participa.alumno_dni = '"+al.getDni()+"'  AND participa.actividad_id = actividad.id ) ";
        HashMap<Actividad, Impartidor> ActividadesParticipa = new HashMap<Actividad, Impartidor>();
        try {
            Connection con = dataSource.getConnection();
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ActividadesParticipa.put(new Actividad(rs.getInt("id"),rs.getInt("impartidor_id"),rs.getString("nombre"), rs.getInt("coste_mensual"),rs.getInt("capacidad")), getImpartidor(rs.getInt("impartidor_id")));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo loginImpartidor: " + ex);
        }
        return ActividadesParticipa;
    }
    
    public HashMap<Actividad, Impartidor> actividadesNoParticipa(Alumno al){
        String sql = "SELECT * FROM actividad WHERE NOT EXISTS(SELECT * FROM participa WHERE participa.alumno_dni = '"+al.getDni()+"'  AND participa.actividad_id = actividad.id AND capacidad > ( SELECT COUNT(*) FROM participa WHERE actividad.id = participa.actividad_id )) ";
        HashMap<Actividad, Impartidor> ActividadesNoParticipa = new HashMap<Actividad, Impartidor>();
        try {
            Connection con = dataSource.getConnection();
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt("impartidor_id"));
                ActividadesNoParticipa.put(new Actividad(rs.getInt("id"),rs.getInt("impartidor_id"),rs.getString("nombre"), rs.getInt("coste_mensual"),rs.getInt("capacidad")), getImpartidor(rs.getInt("impartidor_id")));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo loginImpartidor: " + ex);
        }
        return ActividadesNoParticipa;
    }
    
     public LinkedHashMap<Alumno, Date> mapaAsistenciaActividad(int actividad_id){
         String sql = "SELECT * FROM participa where actividad_id="+actividad_id;
         LinkedHashMap<Alumno, Date> mapa = new LinkedHashMap<Alumno, Date>();
        try {
            Connection con = dataSource.getConnection();
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                mapa.put(getAlumno(rs.getString("alumno_dni")), rs.getDate("ultima_asistencia"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo loginImpartidor: " + ex);
        }
        return mapa;
     }
      public void inscribir(Integer act, String dni) {
        Alumno al = getAlumno(dni);
        String sql = "INSERT INTO participa VALUES "+act +", "+ dni + ", null"  ;
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(sql); 
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo Inscribir: " + ex);
        }
    }
}
