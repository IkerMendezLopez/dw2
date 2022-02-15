package beans;

import java.util.Date;

public class Persona {

    private String nombre;
    private Date fechaNac;
    private int edad;

    
    public Persona() {
    }

    public Persona(String nombre, Date fechaNac, int edad) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
    
}
