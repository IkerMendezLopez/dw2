package mbeans;

import beans.Articulo;
import java.util.ArrayList;

public class GestorArticulos {

    private ArrayList <Articulo> lstArticulos; //Articulos disponibles
    private ArrayList <String> lstPedidos;
    
    
    public GestorArticulos() {
        
        lstArticulos = new ArrayList <Articulo>();
        lstArticulos.add(new Articulo("Lavadora", 505));
        lstArticulos.add(new Articulo("Ambientador", 36.5f));
        lstArticulos.add(new Articulo("Desinfectante", 28.3f));
        
        lstPedidos = new ArrayList <String>();
    }
    
    public String aniadir(String nombreArticulo){
        
        if(!lstPedidos.contains(nombreArticulo)){
            lstPedidos.add(nombreArticulo);
        }
        
        return null;
    }
    
    public String quitar(String nombreArticulo){
        
        lstPedidos.remove(nombreArticulo);
        
        return null;
    }

    
    public ArrayList<Articulo> getLstArticulos() {
        return lstArticulos;
    }

    public void setLstArticulos(ArrayList<Articulo> lstArticulos) {
        this.lstArticulos = lstArticulos;
    }

    public ArrayList<String> getLstPedidos() {
        return lstPedidos;
    }

    public void setLstPedidos(ArrayList<String> lstPedidos) {
        this.lstPedidos = lstPedidos;
    }
    
    
    
    
    
    
    
}
