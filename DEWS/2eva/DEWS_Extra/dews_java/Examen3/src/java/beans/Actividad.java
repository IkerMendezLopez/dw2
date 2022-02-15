package beans;

import java.io.Serializable;

public class Actividad implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private Impartidor impartidor;
    private String nombre;
    private float coste_mensual;
    private int capacidad;

    public Actividad() {
    }

    public Actividad(int id, Impartidor impartidor, String nombre, float coste_mensual, int capacidad) {
        this.id = id;
        this.impartidor = impartidor;
        this.nombre = nombre;
        this.coste_mensual = coste_mensual;
        this.capacidad = capacidad;
    }
    
    public Actividad(int id, String nombre, float coste, Impartidor impartidor) {
        this.id = id;
        this.nombre = nombre;
        this.coste_mensual = coste;
        this.impartidor = impartidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Impartidor getImpartidor() {
        return impartidor;
    }

    public void setImpartidor(Impartidor impartidor) {
        this.impartidor = impartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCoste_mensual() {
        return coste_mensual;
    }

    public void setCoste_mensual(float coste_mensual) {
        this.coste_mensual = coste_mensual;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    

}
