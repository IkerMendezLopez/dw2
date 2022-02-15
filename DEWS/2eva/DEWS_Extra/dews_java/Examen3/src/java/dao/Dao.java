package dao;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.dbcp2.BasicDataSource;

public class Dao {
    private static BasicDataSource dataSource = new BasicDataSource();
    
    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/bdactividades");
        dataSource.setUsername("aritz");
        dataSource.setPassword("123456");
    }
	
    public static Connection dameConexion() throws SQLException{
        return dataSource.getConnection();
    }
    
    public static Alumno loginAlumno(String dni, String pass){
        Alumno alumno = null;
        String sql = "select dni, nombre, apellidos, telefono, email "
                + "from alumnos where dni = ?";
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            pst.setString(1, dni);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    alumno = new Alumno(rs.getString("dni"), rs.getString("nombre"), 
                        rs.getString("apellidos"), rs.getString("telefono"),
                        rs.getString("email"));
                
                    if(validarAlumno(alumno, pass)){
                        return alumno;
                    }
                }
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
 
        return null;
    }
    
    private static boolean validarAlumno(Alumno alumno, String pass){
        
        String[] apellidos = alumno.getApellidos().split(" ");
        String passOK;
        
        if(apellidos.length==2 && apellidos[0].length()>=3 && apellidos[1].length()>=3){
            passOK = apellidos[0].substring(0, 3).toLowerCase()
                    +apellidos[1].substring(0, 3).toLowerCase();
            if(pass.equals(passOK)){
                return true;
            }
        }
        
        return false;
    }
    
    public static Impartidor loginImpartidor(int id, String pass){
        Impartidor impartidor = null;
        String sql = "select id, nombre, apellido "
                + "from impartidores where id=?";
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            pst.setInt(1, id);
            
            try (ResultSet rs = pst.executeQuery()){
                if(rs.next() && pass.equals("damocles")){
                    impartidor = new Impartidor(rs.getInt("id"), rs.getString("nombre"), 
                            rs.getString("apellido"));
                }
            }

        } catch(SQLException e){
            System.err.println("Error en metodo loginImpartidor " + e.getMessage());
        }
        
        
        return impartidor;
    }
    
    public static ArrayList<Actividad> actividadesImpartidor(Impartidor impartidor){
        ArrayList<Actividad> actividades = new ArrayList<>();
        String sql = "select id, impartidor_id, nombre, coste_mensual, capacidad "
                + "from actividades where impartidor_id = ?";
        
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setInt(1, impartidor.getId());
            
            try (ResultSet rs = pst.executeQuery()){
                
                while(rs.next()){
                    actividades.add(new Actividad(rs.getInt("id"), obtenerImpartidor(rs.getInt("impartidor_id")), 
                        rs.getString("nombre"), rs.getFloat("coste_mensual"), rs.getInt("capacidad")));
                }
            }
            
        } catch(SQLException e){
            System.err.println("Error en metodo actividadesImpartidor " + e.getMessage());
        }
        
        
        return actividades;
    }
    
    private static Impartidor obtenerImpartidor(int id){
        Impartidor impartidor = null;
        String sql = "select nombre, apellido "
                + "from impartidores where id=?";
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            pst.setInt(1, id);
            
            try (ResultSet rs = pst.executeQuery()){

                if(rs.next()){
                    impartidor = new Impartidor(id, rs.getString("nombre"), rs.getString("apellido"));
                }
            }
            
        } catch(SQLException e){
            System.err.println("Error en metodo obtenerImpartidor " + e.getMessage());
        }

        return impartidor;
    }
    
    public static ArrayList<Actividad> actividadesParticipa(Alumno alumno){
        ArrayList<Actividad> actividades = new ArrayList<>();
        String sql = "select id, impartidor_id, nombre, coste_mensual, capacidad "
                + "from actividades where exists (select * from participa "
                + "where actividad_id=id and alumno_dni=?)";

        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            pst.setString(1, alumno.getDni());
            
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    System.out.println("aaa");
                    actividades.add(new Actividad(rs.getInt("id"), obtenerImpartidor(rs.getInt("impartidor_id")), 
                            rs.getString("nombre"), rs.getFloat("coste_mensual"), rs.getInt("capacidad")));
                }
            }

        } catch(SQLException e){
            System.err.println("Error en metodo actividadesParticipa " + e.getMessage());
        }
        
        
        return actividades;
    }
    
    public static ArrayList<Actividad> actividadesLibresNoParticipa(Alumno alumno){
        ArrayList<Actividad> actividades = new ArrayList<>();
        String sql = "select id, impartidor_id, nombre, coste_mensual, capacidad "
                + "from actividades where capacidad>(select count(*) "
                + "from participa where actividad_id=id) and "
                + "actividades.nombre not in (select nombre "
                + "from actividades, participa where id=actividad_id "
                + "and alumno_dni=?)";
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            pst.setString(1, alumno.getDni());
            
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    actividades.add(new Actividad(rs.getInt("id"), obtenerImpartidor(rs.getInt("impartidor_id")), 
                            rs.getString("nombre"), rs.getFloat("coste_mensual"), rs.getInt("capacidad")));
                    System.out.println(rs.getInt("id"));
                }
            }
            
            
            
        } catch(SQLException e){
            System.err.println("Error en metodo actividadesLibresNoParticipa " + e.getMessage());
        }
        
        
        return actividades;
    }
    
    public static boolean inscribir(Alumno alumno, ArrayList<Integer> actividades){
        
        String sql = "insert into participa (actividad_id, alumno_dni) "
                + "values (?, ?)";
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            for(int actId: actividades){
                pst.setInt(1, actId);
                pst.setString(2, alumno.getDni());
                pst.executeUpdate();
            }
            
            return true;
            
        } catch(SQLException e){
            System.err.println("Error en metodo inscribir " + e.getMessage());
            return false;
        }
    }
    
    public static HashMap<Alumno, Date> mapaAsistenciaActividad (int idActividad){
        HashMap<Alumno, Date> mapa = new HashMap<>();
        
        String sql = "select dni, nombre, apellidos, telefono, email, ultima_asistencia "
                + "from alumnos, participa "
                + "where dni=alumno_dni and "
                + "actividad_id=?";
        
        try(
            Connection cn = dameConexion();
            PreparedStatement pst = cn.prepareStatement(sql)){
            
            pst.setInt(1, idActividad);
            
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    Alumno alumno = new Alumno();
                    alumno.setDni(rs.getString("dni"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellidos(rs.getString("apellidos"));
                    alumno.setTelefono(rs.getString("telefono"));
                    alumno.setEmail(rs.getString("email"));

                    if(rs.getTimestamp("ultima_asistencia")==null)
                        mapa.put(alumno, null);
                    else
                        mapa.put(alumno, new Date(rs.getTimestamp("ultima_asistencia").getTime()));
                }
            }
            
        } catch(SQLException e){
            System.err.println("Error en metodo mapaAsistenciaActividad " + e.getMessage());
        }
        
        return mapa;
    }

}
