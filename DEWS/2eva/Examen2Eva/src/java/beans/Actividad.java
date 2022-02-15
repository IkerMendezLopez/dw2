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
public class Actividad {
    private int id;
    private int impartidor_id;
    private String nombre;
    private int coste_mensual;
    private int capacidad;

    public void setId(int id) {
        this.id = id;
    }

    public void setImpartidor_id(int impartidor_id) {
        this.impartidor_id = impartidor_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCoste_mensual(int coste_mensual) {
        this.coste_mensual = coste_mensual;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public int getImpartidor_id() {
        return impartidor_id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCoste_mensual() {
        return coste_mensual;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Actividad(int id, int impartidor_id, String nombre, int coste_mensual, int capacidad) {
        this.id = id;
        this.impartidor_id = impartidor_id;
        this.nombre = nombre;
        this.coste_mensual = coste_mensual;
        this.capacidad = capacidad;
    }

    public Actividad() {
    }
}
