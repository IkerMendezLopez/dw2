package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CarroCompra {

    private HashMap <Integer, LineaPedido> carro; //Identificativo el item del pedido, valor un objeto de LineaPedido; Representacion del estado de la compra, va variando segun vayamos añadiendo/quitando/modificando elementos del carro

    
    public CarroCompra(){
        
        carro = new HashMap <Integer, LineaPedido>();
    }
    
    
    public void aniadeLinea(LineaPedido linea){
        
        int idItem = linea.getItem().getId();
        
        if(!carro.containsKey(idItem)){
            carro.put(idItem, linea);
        }
        else{
            int cantidad = linea.getCantidad();
            int cantidadActual = carro.get(linea.getItem().getId()).getCantidad();
            
            LineaPedido l = new LineaPedido();
            l.setId(linea.getId());
            l.setCantidad(cantidadActual+cantidad);
            l.setItem(linea.getItem());
            l.setPedido(linea.getPedido());
            
            carro.put(idItem, l);
        }
    }
    
    
    public void borraLinea(int idItem){
        
        if(carro.containsKey(idItem)){
            carro.remove(idItem);
        }
    }
    
    
    public LineaPedido getLineaPedido(int idItem){
        
        LineaPedido linea = new LineaPedido();
        
        if(carro.containsKey(idItem)){
            LineaPedido lineaCarro = carro.get(idItem);
        
            linea.setId(lineaCarro.getId());
            linea.setCantidad(lineaCarro.getCantidad());
            linea.setItem(lineaCarro.getItem());
            linea.setPedido(lineaCarro.getPedido());
        }

        return linea;
    }
    
    
    public ArrayList <LineaPedido> getLineasPedido(){
        
        ArrayList <LineaPedido> lstLineaPedidos = new ArrayList <LineaPedido>();
        
        if(vacio()){
            Iterator <Integer> it = carro.keySet().iterator();
            while(it.hasNext()){

                Integer idItem = it.next();

                LineaPedido linea = new LineaPedido();
                linea.setId(carro.get(idItem).getId());
                linea.setCantidad(carro.get(idItem).getCantidad());
                linea.setItem(carro.get(idItem).getItem());
                linea.setPedido(carro.get(idItem).getPedido());

                lstLineaPedidos.add(linea);
            }
        }

        return lstLineaPedidos;
    }
    
    
    public double total(){
        
        double precioTotal = 0;
        
        if(vacio()){
            Iterator <Integer> it = carro.keySet().iterator();
            while(it.hasNext()){

                Integer idItem = it.next();

                precioTotal += carro.get(idItem).getCantidad();
            }
        }

        return precioTotal;
    }
    
    
    public void removeAll(){
        
        carro.clear();
    }
    
    
    public boolean vacio(){
        
        if(!carro.isEmpty()){
            return true;
        }
        
        return false;
    }

    
}
