package mbeans;

import beans.Ciclo;
import beans.Persona;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class MBregistro {

    private Persona persona;
    private String pass;
    private Ciclo ciclo;
    
   
    public MBregistro() {
        persona = new Persona();
    }

    public Persona getPersona() {
        return persona;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
    
    
    
    

//METODOS
    public void validaNombre(FacesContext context, UIComponent inputNombre, Object objNombre) throws ValidatorException {
        
        //Que el nombre tenga "nombre + apellido" y que no contenga caracteres de dollar($)
        String txtNombre = (String) objNombre;
        String [] partes = txtNombre.split(" ");
        
        if(partes.length<2 || (txtNombre.contains("$"))){
            context.addMessage(inputNombre.getClientId(context), new FacesMessage("Nombre debe tener 2 palabras (Nombre+Apellido) y no contener $s"));
            
            throw new ValidatorException(new FacesMessage("*Nombre Erroneo!"));
        }
    }
    
    public Ciclo [] todosCiclos(){
        
        return new Ciclo []{new Ciclo(10, "Adm Web", 10000), new Ciclo(20, "Marketing", 20000), new Ciclo(30, "Soldadura", 30000)};
    }
    
}
