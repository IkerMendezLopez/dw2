package beans;

public class Cuenta {

    private String titular;
    private double saldo;

    
    public Cuenta() {
        
    }

    public Cuenta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }
    
    
    public void ingresar(double cant){
        
        saldo+=cant;
    }
    
    public boolean gastar(double cant){
        
        if(cant<=saldo){
            saldo-=cant;
            return true;
        }
        
        return false;
    }
    
    

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    
    
    
}
