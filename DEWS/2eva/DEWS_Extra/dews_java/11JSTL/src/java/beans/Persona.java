package beans;

public class Persona {

    private String nombre;
    private int edad;
    private String [] aficiones;

    
    public Persona() {
        
    }

    public Persona(String nombre, int edad, String[] aficciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.aficiones = aficciones;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String[] getAficiones() {
        return aficiones;
    }

    public void setAficiones(String[] aficiones) {
        this.aficiones = aficiones;
    }
    
    public String caracteristicas(){
        
        String caract = (edad<10) ? "MENOR de edad" : "MAYOR de edad";
        
        if(aficiones==null){
            caract+= " sin aficiones";
        }
        else{
            caract += aficiones.toString() + " aficiones";
        }
        
        return caract;
    }
    
    
    
}
