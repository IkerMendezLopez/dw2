package beans;

import java.util.ArrayList;


public class AlmacenMensajes {
    
    private static ArrayList <Mensaje> mensajes;
    
    static{
        mensajes = new ArrayList<Mensaje>();
    }
    
    
    public static void aniadirMensaje(Mensaje mensaje){
        
        mensajes.add(mensaje);
    }

    public static ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }
   
}
