package beans;

public class Autor {

    private int idautor;
    private String nombre;

    
    public Autor() {
        
    }

    public Autor(int idautor, String nombre) {
        this.idautor = idautor;
        this.nombre = nombre;
    }

    public int getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
