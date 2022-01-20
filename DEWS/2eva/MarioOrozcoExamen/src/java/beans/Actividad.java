package beans;

import java.util.Objects;

public class Actividad {

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

    @Override
    public String toString() {
        return "Actividad{" + "id=" + id + ", impartidor=" + impartidor + ", nombre=" + nombre + ", coste_mensual=" + coste_mensual + ", capacidad=" + capacidad + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actividad other = (Actividad) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    

    
    
}
