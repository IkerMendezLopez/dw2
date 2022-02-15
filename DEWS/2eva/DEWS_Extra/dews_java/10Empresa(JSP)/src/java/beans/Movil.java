package beans;

public class Movil {
    
    private String numero;
    private int bateria;

    
    public Movil() {
        
    }

    public Movil(String numero, int bateria) {
        this.numero = numero;
        this.bateria = bateria;
    }
    
    protected void usarMovil(){
        
        if(bateria>0){
            bateria--;
        }
    }

    
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
    
    
    
    
    
    
    
}
