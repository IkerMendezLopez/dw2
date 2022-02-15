package beans;

import java.util.Objects;

public class Empresa {

    private String nombre;
    private float beneficio;
    private Trabajador [] trabajadores;
    private int trabActuales;

    
    public Empresa(String nombre, float beneficio, int maxTrabajadores) {
        
        this.nombre = nombre;
        this.beneficio = beneficio;
        trabajadores = new Trabajador [maxTrabajadores];
        trabActuales = 0;
    }
    
    
    public void trabajar(){
        //Trabajan todos los trabajadores
        for(Trabajador t : trabajadores){
            t.trabajar();
        }
        
        beneficio += 100;
    }
    
    public boolean contratar(Trabajador t){
        
        if(trabActuales<trabajadores.length){
            trabajadores [trabActuales] = t;
            trabActuales++;
            return true;
        }
        
        return false;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(float beneficio) {
        this.beneficio = beneficio;
    }

    public Trabajador[] getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(Trabajador[] trabajadores) {
        this.trabajadores = trabajadores;
    }

    public int getTrabActuales() {
        return trabActuales;
    }

    @Override
    public String toString() {
        return "Empresa{" +  nombre + ", " + beneficio + "€, " + trabActuales + " trabajadores actualmente}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Float.floatToIntBits(this.beneficio);
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
        final Empresa other = (Empresa) obj;
        if (Float.floatToIntBits(this.beneficio) != Float.floatToIntBits(other.beneficio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
    
    

   
    
    
    
    
}
