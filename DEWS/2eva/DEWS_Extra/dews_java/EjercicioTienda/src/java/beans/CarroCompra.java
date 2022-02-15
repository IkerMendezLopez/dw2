package beans;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class CarroCompra {
    HashMap<Integer, LineaPedido> carro;

    public CarroCompra() {
        this.carro =new HashMap<Integer, LineaPedido>();
    }
    
    
    public void aniadeLinea(LineaPedido l){
        Item i=l.getItempedido();
        int iditem=i.getId();
        
        if(carro.containsKey(iditem)){
            LineaPedido existente=carro.get(iditem);
            existente.setCantidad(existente.getCantidad()+l.getCantidad());
        }
        else{
            carro.put(iditem,l);      
        }
        
    }
    
    public void borrarlinea(int iditem){
        if(carro.containsKey(iditem)){
            carro.remove(iditem);
        }
    }
    
    public LineaPedido getLineaPedido(int iditem){
        LineaPedido l=carro.get(iditem);
        
        return l;
    }
    
    public Collection<LineaPedido> getLineasPedido(){
       return carro.values();
    }
    
    public double total(){
        double total=0;
        for(LineaPedido linea : carro.values()){
            total+=(linea.getItempedido().getPrecio())*(linea.getCantidad());
        }
        
        return total;
    }
    
    public void vaciarCarro(){
        carro.clear();
    }
    
    public boolean vacio(){
        if(carro.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
