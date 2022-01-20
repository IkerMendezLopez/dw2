package beans;
public class Impartidor {

    private int id;
    private String apellido;
    private String nombre;

    public Impartidor() {
    }

    public Impartidor(int id, String apellido, String nombre) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Impartidor{" + "id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + '}';
    }
    
    
}
