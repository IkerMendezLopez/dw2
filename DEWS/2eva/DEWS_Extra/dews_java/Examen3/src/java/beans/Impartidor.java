package beans;

import java.io.Serializable;

public class Impartidor implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre, apellido;

    public Impartidor() {
    }

    public Impartidor(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return nombre+" "+apellido;
    }

}
