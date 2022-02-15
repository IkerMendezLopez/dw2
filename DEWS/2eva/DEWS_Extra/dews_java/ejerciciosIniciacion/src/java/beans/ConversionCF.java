package beans;

public class ConversionCF {
    
    private double celsius, fahrenheit;

    
    public ConversionCF() {
        
    }

    public ConversionCF(double temperatura, char tipo) {
        
        if(tipo=='c'){
            this.celsius = temperatura * 9/5 + 32;
        }
        
        if(tipo=='f'){
            this.fahrenheit = (temperatura-32) * 5/9;
        }     
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }
    
    
    
    
    
    
    
}
