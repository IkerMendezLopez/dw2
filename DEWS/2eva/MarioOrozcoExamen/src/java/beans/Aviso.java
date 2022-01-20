package beans;

import java.io.Serializable;

public class Aviso implements Serializable{

    private String dni;
    private String destino;

    public Aviso() {
    }

    public Aviso(String dni, String destino) {
        this.dni = dni;
        this.destino = destino;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return dni + "\t" + destino;
    }
    
}
