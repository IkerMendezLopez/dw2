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
public class Empresa {
    private String nombre;
    private double beneficio;
    private Trabajador[] trabajadores;
    private int cantTrab;

    public Empresa(String nombre, double beneficio, int maxTrab) {
        this.nombre = nombre;
        this.beneficio = beneficio;
        this.trabajadores = new Trabajador[maxTrab];
        this.cantTrab = 0;
    }

    public Empresa() {
    }

    public String getNombre() {
        return nombre;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public Trabajador[] getTrabajadores() {
        return trabajadores;
    }

    public int getCantTrab() {
        return cantTrab;
    }
    
    public boolean contratar(Trabajador t){
        if(this.cantTrab == trabajadores.length){
            return false;
        }else{
            this.trabajadores[this.cantTrab] = t;
            this.cantTrab++;
            return true;
        }
    }
    
    public void trabajar(){
        for(int i = 0;i < this.cantTrab;i++){
            this.trabajadores[i].trabajar();
            this.beneficio += 100;
        }
    }
}
