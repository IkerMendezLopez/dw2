package dao;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import conex.BDConex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ClaseDao {

    static PreparedStatement psInscribir;
    static Connection cn ;
    
    static{
        try{
            cn = (Connection) BDConex.ds.getConnection();
            String sqlpsInscribir = "INSERT INTO participa (actividad_id, alumno_dni, ultima_asistencia) VALUES (?, ?, NULL);";
            psInscribir = cn.prepareStatement(sqlpsInscribir);
            
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.bloqueEstático() "+e.getMessage());
        }
    }
    
    
    public static Alumno loginAlumno(String dni, String clave){
        String sql = "SELECT dni, apellidos, nombre, telefono, email FROM alumnos WHERE dni = '"+dni+"'";
        try(Connection cn = (Connection) BDConex.ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            
            if (rs.next()){
                String apellidos = rs.getString("apellidos");
                String[] ape = apellidos.split(" ");
                String aux = "";
                aux += ape[0].substring(0, 3);
                aux += ape[1].substring(0, 3);
                if(aux.toLowerCase().equals(clave.toLowerCase())){
                    Alumno a = new Alumno();
                    a.setDni(dni);
                    a.setApellidos(apellidos);
                    a.setNombre(rs.getString("nombre"));
                    a.setTelefono(rs.getString("telefono"));
                    a.setEmail(rs.getString("email"));
                    return a;
                }
            }
            
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.loginAlumno() "+e.getMessage());
        }
        
        return null;
    }
    
    public static Impartidor loginImpartidor(String usuario, String clave){
        if(!clave.equals("damocles"))return null;
        String sql = "SELECT apellido, nombre FROM impartidores WHERE id = "+usuario;
        try(Connection cn = (Connection) BDConex.ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            if (rs.next()){
                Impartidor i = new Impartidor(Integer.parseInt(usuario), rs.getString("apellido"), rs.getString("nombre"));
                return i;
            }else{
                return null;
            }
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.loginImpartidor() "+e.getMessage());
        }
        return null;
    }
    
    public static ArrayList<Actividad> actividadesImpartidor(int impartidor_id){
        ArrayList<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT id, nombre, coste_mensual, capacidad FROM actividades WHERE impartidor_id = "+impartidor_id;
        try(Connection cn = (Connection) BDConex.ds.getConnection(); 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            while(rs.next()){
                Actividad a = new Actividad();
                a.setCapacidad(rs.getInt("capacidad"));
                a.setCoste_mensual(rs.getFloat("coste_mensual"));
                a.setId(rs.getInt("id"));
                a.setImpartidor(getImpartidor(impartidor_id));
                a.setNombre(rs.getString("nombre"));
                actividades.add(a);
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.actividadesImpartidor()"+e.getMessage());
        }
        return actividades;
    }
    
    public static ArrayList<Actividad> actividadesParticipa(Alumno alum){
        ArrayList<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT id, nombre, impartidor_id, coste_mensual, capacidad FROM actividades WHERE EXISTS( SELECT * FROM participa WHERE participa.alumno_dni = '"+alum.getDni()+"'  AND participa.actividad_id = actividades.id ) ";
        try(Connection cn = (Connection) BDConex.ds.getConnection(); 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            while(rs.next()){
                Actividad a = new Actividad();
                a.setCapacidad(rs.getInt("capacidad"));
                a.setCoste_mensual(rs.getFloat("coste_mensual"));
                a.setId(rs.getInt("id"));
                a.setImpartidor(getImpartidor(rs.getInt("impartidor_id")));
                a.setNombre(rs.getString("nombre"));
                actividades.add(a);
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.actividadesParticipa()"+e.getMessage());
        }
        return actividades;
    }
    
    public static ArrayList<Actividad> actividadesNoParticipa(Alumno alum){
        ArrayList<Actividad> actividadesNO = new ArrayList<>();
        ArrayList<Actividad> actividadesSI = actividadesParticipa(alum);
        String sql = "SELECT id, nombre, impartidor_id, coste_mensual, capacidad FROM actividades WHERE capacidad > ( SELECT COUNT(*) FROM participa WHERE actividades.id = participa.actividad_id ) ";
        
        try(Connection cn = (Connection) BDConex.ds.getConnection(); 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            while(rs.next()){
                Actividad a = new Actividad();
                a.setCapacidad(rs.getInt("capacidad"));
                a.setCoste_mensual(rs.getFloat("coste_mensual"));
                a.setId(rs.getInt("id"));
                a.setImpartidor(getImpartidor(rs.getInt("impartidor_id")));
                a.setNombre(rs.getString("nombre"));
                if(!actividadesSI.contains(a)){
                    actividadesNO.add(a);
                }
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.actividadesParticipa()"+e.getMessage());
        }
        
        return actividadesNO;
    }
    
    public static Impartidor getImpartidor(int id){
        String sql = "SELECT apellido, nombre FROM impartidores WHERE id = "+id;
        try(Connection cn = (Connection) BDConex.ds.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            if (rs.next()){
                Impartidor i = new Impartidor(id, rs.getString("apellido"), rs.getString("nombre"));
                return i;
            }
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.loginImpartidor() "+e.getMessage());
        }
        return null;
    }
    
    public static void inscribir(int actividad_id, String alumno_dni){
        //"INSERT INTO participa (actividad_id, alumno_dni, ultima_asistencia) VALUES (?, ?, NULL);"
        try{
            psInscribir.setInt(1, actividad_id);
            psInscribir.setString(2, alumno_dni);
            psInscribir.executeUpdate();
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.inscribir()"+e.getMessage());
        }
    }
    
    public static HashMap<Alumno, java.util.Date> mapaAsistenciaActividad(int actividad_id){
        HashMap<Alumno, java.util.Date> mapa = new HashMap<>();
        String sql = "SELECT alumnos.dni, alumnos.apellidos, alumnos.nombre, alumnos.telefono, alumnos.email, participa.ultima_asistencia FROM alumnos, participa WHERE alumnos.dni = participa.alumno_dni AND participa.actividad_id = "+actividad_id;
        
        try(Connection cn = (Connection) BDConex.ds.getConnection(); 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            while(rs.next()){
                Alumno a = new Alumno();
                a.setApellidos(rs.getString("apellidos"));
                a.setDni(rs.getString("dni"));
                a.setEmail(rs.getString("email"));
                a.setNombre(rs.getString("nombre"));
                a.setTelefono(rs.getString("telefono"));
                java.util.Date ultima_asistencia = rs.getDate("ultima_asistencia");
                mapa.put(a, ultima_asistencia);
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("dao.ClaseDao.actividadesParticipa()"+e.getMessage());
        }
        return mapa;
    }
    
}
