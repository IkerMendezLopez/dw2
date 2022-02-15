package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorDireccion implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String direccion = (String) value;
        
        if(direccion.equals("")){
            context.addMessage(component.getClientId(context), new FacesMessage("*Introduzca un valor!"));
             throw new ValidatorException(new FacesMessage("*Direccion Erronea!"));
        }
    }
}
