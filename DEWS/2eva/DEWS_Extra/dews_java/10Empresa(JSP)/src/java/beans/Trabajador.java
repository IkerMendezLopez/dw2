package beans;

public class Trabajador {
    
    private String nombre, dni;
    private float dinero;
    private Movil movil;

    public Trabajador() {
        
    }

    public Trabajador(String nombre, String dni, float dinero, Movil movil) {
        this.nombre = nombre;
        this.dni = dni;
        this.dinero = dinero;
        this.movil = movil;
    }
    
    
    public void trabajar(){
        
        dinero+=10;
        movil.usarMovil();
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public Movil getMovil() {
        return movil;
    }

    public void setMovil(Movil movil) {
        this.movil = movil;
    }
    
    
    
    
    
}
