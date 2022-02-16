/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author dw2
 */
public class Sala {
    private String id_sala;
    private int capacidad;

    public Sala(String id_sala, int capacidad) {
        this.id_sala = id_sala;
        this.capacidad = capacidad;
    }

    public Sala() {
    }

    public String getId_sala() {
        return id_sala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setId_sala(String id_sala) {
        this.id_sala = id_sala;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
}
