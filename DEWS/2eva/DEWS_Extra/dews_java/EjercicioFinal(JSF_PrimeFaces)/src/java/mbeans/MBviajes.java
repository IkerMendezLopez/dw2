package mbeans;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


public class MBviajes {

    private Date fechaActual;
    
    public MBviajes() {
         fechaActual = new Date();
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    
  
    
    
//METODOS
    public void verRutas(){
        
        
    }
    
    
    
}
