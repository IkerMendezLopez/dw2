package validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorEmail implements Validator{

    private static final ArrayList <String> PROHIBIDOS = new ArrayList <>(Arrays.asList("com", "es", "net"));
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        boolean error = true;
        
        String emailInsertado = (String) value;
        String expresion = "(.+)@(.+)";
        if(Pattern.matches(expresion, emailInsertado)){
            String dominio = emailInsertado.substring(emailInsertado.lastIndexOf('.')+1);
            if(!PROHIBIDOS.contains(dominio)){
                error = false;
            }
        }
        
        
        if(error){
            context.addMessage(component.getClientId(context), new FacesMessage("*ERROR (Formato incorrecto; Dominio Prohibido)"));
            throw new ValidatorException(new FacesMessage("*Email Erroneo!"));
        }
    }
}
