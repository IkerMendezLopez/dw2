package converters;

import beans.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConversorNombre implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        String nomFinal = "";
        String nombreInsertado = value.toLowerCase();
        if(!nombreInsertado.equals("")){
            String [] arrNombre = nombreInsertado.split(" ");
            for(String pal : arrNombre){
                char [] letra = pal.toCharArray();
                char primeraLetra = Character.toUpperCase(letra[0]);
                nomFinal += primeraLetra +  pal.substring(1) + " ";
            }
        }
        
        return nomFinal;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String nomFinal = "";
        String nombreInsertado =  (String) value;
        String [] arrNombre = nombreInsertado.split(" ");
        for(String pal : arrNombre){
            char [] letra = pal.toCharArray();
            char primeraLetra = Character.toUpperCase(letra[0]);
            nomFinal += primeraLetra +  pal.substring(1) + " ";
        }
        
        return nomFinal;
    }

    
    
}
