package beans;


public class Correccion {
    
    private boolean correcta;
    private int num_correcto;

    
    public Correccion() {
        
    }
    
    public Correccion(boolean correcta, int num_correcto) {
        this.correcta = correcta;
        this.num_correcto = num_correcto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public int getNum_correcto() {
        return num_correcto;
    }

    public void setNum_correcto(int num_correcto) {
        this.num_correcto = num_correcto;
    }
    
    

    
    
    
    
    
    
}
