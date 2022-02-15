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
public class TelefonoMovil {
    private String numero;
    private int bateria;

    public TelefonoMovil(String numero, int bateria) {
        this.numero = numero;
        this.bateria = bateria;
    }

    public TelefonoMovil() {
    }

    public String getNumero() {
        return numero;
    }

    public int getBateria() {
        return bateria;
    }
    
    public void usar(){
        this.bateria--;
    }
}
