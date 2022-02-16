/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Charla;
import beans.HoraCharla;
import beans.Reserva;
import beans.Sala;
import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author dw2
 */
public class GestorBD {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bdcharlas?zeroDateTimeBehavior=CONVERT_TO_NULL";
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
    
    public ArrayList<Sala> salasPreparadas(){
        ArrayList<Sala> lstSala= new ArrayList<>();
        String sql = "SELECT * FROM Sala where preparada=1";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Sala s = new Sala(rs.getString("id"), rs.getInt("capacidad"));
                lstSala.add(s);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return lstSala;
    }
    
    public String grabarCharla(Charla c){
        String msg = "CHARLA GRABADA";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int minutos = c.getHoraComienzo().getHora()*60+c.getHoraComienzo().getMinutos();
        String sql = "SELECT * FROM `charla` WHERE 3>(SELECT count(*) from charla where sala='"+c.getId_sala()+"' AND dia='"+sdf.format(c.getHoraComienzo().getFecha())+"') AND sala='"+c.getId_sala()+"'";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(!rs.next()){
                msg="La sala ya tiene 3 charlas ese dia";
            }else{
                while(rs.next()){
                    Charla cAux = new Charla(rs.getInt("id"), 
                            new HoraCharla(rs.getDate("dia"), rs.getInt("hora"), rs.getInt("minutos")), 
                            rs.getString("tema"),rs.getString("sala"), 
                            rs.getInt("tarifa"));
                    int todoMins = cAux.getHoraComienzo().getHora()*60+cAux.getHoraComienzo().getMinutos();
                    if((todoMins>minutos && todoMins-minutos<120) || minutos-todoMins<120){
                        msg= "Coincide con otra charla";
                    }else{
                        if(insertarCharla(c)){
                            msg= "Error en los parametros";
                        };
                    }
                }
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo grabarCharla: " + ex);
        }
        return msg;
    }
 
    private Boolean insertarCharla(Charla c) {
        int id = -1;
        String sql = "INSERT INTO charla(tema, dia, hora, minutos, tarifa, sala) "
                + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            st.setString(1, c.getTema());
            st.setString(1, sdf.format(c.getHoraComienzo().getFecha()));
            st.setInt(2, c.getHoraComienzo().getHora());
            st.setInt(2, c.getHoraComienzo().getMinutos());
            st.setInt(4, c.getTarifa());
            st.setString(4, c.getId_sala());
            
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
        
        return id>-1;
    }
    
    public LinkedHashMap<Charla, HashSet<Reserva>> mapaReservas(){
        LinkedHashMap<Charla, HashSet<Reserva>> mapaReservas = new LinkedHashMap<>();
        String sql = "SELECT * FROM charla ORDER BY id";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Charla c = new Charla(rs.getInt("id"), new HoraCharla(rs.getDate("dia"), rs.getInt("hora"), rs.getInt("minutos")), rs.getString("tema"), rs.getString("sala"), rs.getInt("tarifa"));
                HashSet<Reserva> setReserva = reservasDeCharla(c);
                mapaReservas.put(c, setReserva);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return mapaReservas;
    }
    
    public HashSet<Reserva> reservasDeCharla(Charla c){
        HashSet<Reserva> setReserva = new HashSet<>();
         String sql = "SELECT id, pagado, nombre FROM reserva, cliente WHERE reserva.dni=cliente.dni AND charla="+ c.getId_charla();
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Reserva r = new Reserva(rs.getInt("id"), rs.getInt("pagado"), rs.getString("nombre"));
                setReserva.add(r);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return setReserva;
    }
}
