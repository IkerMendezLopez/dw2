package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorCodigo implements Validator {

    private final static String [] CODS = {"123", "234", "345", "456", "567", "678"};
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        boolean codigoOk = false;
        String strValue = (String) value;
        
        for(String cod : CODS){
            if(strValue.equals(cod)){
                codigoOk = true;
                break;
            }
        }
        
        
        if(!codigoOk){
            context.addMessage(component.getClientId(context), new FacesMessage("*Debes disponer de un codigo secreto para registrarte!"));
            throw new ValidatorException(new FacesMessage("*Codigo Erroneo!"));
        }
    }
}
