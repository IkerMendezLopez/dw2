package validators;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorFecha implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        boolean fechaOk = false;
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        
        LocalDate fechaIntroducida = (LocalDate) value;
        Date fechaActual = new Date();
        
        String fActual = formateador.format(fechaActual);
        String fIntroducida = ""+fechaIntroducida;

        System.out.print("Introducida--> " + fechaIntroducida);
        System.out.print("Fecha Actual--> " + fActual);
        
        if(fActual.equals(fechaIntroducida)){
            System.out.print("Son iguales");
        }
        System.out.print("Weeeyy salio mal pero etamoh bien, roooodamooh");
        
        
        if(fechaOk){
            context.addMessage(component.getClientId(context), new FacesMessage("*La fecha debe ser mayor a la actual!"));
            throw new ValidatorException(new FacesMessage("*Fecha Erronea!"));
        }
    }

    
    
}
