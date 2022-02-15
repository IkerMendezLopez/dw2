package beans;

public class Ciclo {

    private String nombre, nivel;
    private int horas;

    
    public Ciclo() {
    }

    public Ciclo(String nombre, String nivel, int horas) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    
    
}
