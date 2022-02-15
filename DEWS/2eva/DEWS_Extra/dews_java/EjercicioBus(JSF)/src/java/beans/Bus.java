package beans;

public class Bus {

    private String idPlaca, imagen;
    private int capacidad;

    
    public Bus() {
    }

    public Bus(String idPlaca, String imagen, int capacidad) {
        this.idPlaca = idPlaca;
        this.imagen = imagen;
        this.capacidad = capacidad;
    }

    public String getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(String idPlaca) {
        this.idPlaca = idPlaca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public String toString(){
        
        return "Bus{" + idPlaca + ", " + imagen + ", " + capacidad + "}";
    }
    
    
    
}
