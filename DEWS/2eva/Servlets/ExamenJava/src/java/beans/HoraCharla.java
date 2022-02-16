/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author dw2
 */
public class HoraCharla {
    private Date fecha;
    private int hora;
    private int minutos;

    public HoraCharla() {
    }

    public HoraCharla(Date fecha, int hora, int minutos) {
        this.fecha = fecha;
        this.hora = hora;
        this.minutos = minutos;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    
    
}
