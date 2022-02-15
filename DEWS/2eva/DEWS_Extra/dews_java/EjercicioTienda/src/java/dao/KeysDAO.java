package dao;

import conex.BDConex;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class KeysDAO {
    
    public static int siguienteId(String nomtabla){
        int id=1;
        try (Connection cn=BDConex.dameConexionPool().getConnection(); Statement st=cn.createStatement();){
            String sql="SELECT max(id) id from "+nomtabla+"";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                id=rs.getInt("id");
                id++;
            }
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KeysDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
