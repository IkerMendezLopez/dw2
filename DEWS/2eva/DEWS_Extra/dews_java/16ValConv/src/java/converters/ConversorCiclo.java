package converters;

import beans.Ciclo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConversorCiclo implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        //Cuando coja de la lista un Ciclo el devolvera el Ciclo elegido como Ciclo en vez de como String
        String [] partes = value.split(":");
        
        int id = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        int duracion = Integer.parseInt(partes[2]);
        
        return new Ciclo(id, nombre, duracion);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     
        //Pasar objeto Ciclo a String
        Ciclo c = (Ciclo) value;
        
        return c.getId() + ":" + c.getNombre() + ":" + c.getDuracion();
    }

}
