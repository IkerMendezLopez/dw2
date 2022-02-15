package validators;

import dao.DaoBus;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import mbeans.MBclientes;

public class ValidadorDNI implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        boolean error = true;
        
        String dniInsertado = (String) value;
        String expresion = "[0-9]{8}[A-Z]";
        if(Pattern.matches(expresion, dniInsertado)){
            if(compruebaLetra(dniInsertado)){
                if(!DaoBus.compruebaCliente(dniInsertado.substring(0, dniInsertado.length()-1))){
                    error = false;
                }
            }
        }
        
        
        if(error){
            context.addMessage(component.getClientId(context), new FacesMessage("*ERROR (Formato: 8nums + 1letra; Letra InCorrecta; Cliente Existente)!"));
             throw new ValidatorException(new FacesMessage("*DNI Erroneo!"));
        }
    }
    
    private boolean compruebaLetra(String dni){
        
        int numsDNI = Integer.parseInt(dni.substring(0, dni.length()-1));
        char letraDNI = dni.charAt(dni.length()-1);
        
        char [] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'};
        int resto = numsDNI%23;
        if(letras[resto] == letraDNI){
            return true;
        }
        else{
            return false;
        }
    }
}
