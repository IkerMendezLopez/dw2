/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Akaitz
 */
public class Trabajador extends Persona {
    private double dinero;
    private TelefonoMovil movil;

    public Trabajador(TelefonoMovil movil, String nombre, String dni) {
        super(nombre, dni);
        this.dinero = 0;
        this.movil = movil;
    }

    public Trabajador() {
    }

    public double getDinero() {
        return dinero;
    }

    public TelefonoMovil getMovil() {
        return movil;
    }
    
    public void ganarDinero(double cant){
        this.dinero += cant;
    }
    
    public void gastarDinero(double cant){
        if(cant >= this.dinero){
            this.dinero -= cant;
        }
    }
    
    public void trabajar(){
        this.movil.usar();
        this.dinero += 10;
    }
}
