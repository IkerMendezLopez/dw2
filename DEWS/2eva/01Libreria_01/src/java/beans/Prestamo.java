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
public class Prestamo {
    private int id;
    private Date fecha;
    private int idLibro;
    private int dias;

    public Prestamo(int id, Date fecha, int idLibro) {
        this.id = id;
        this.fecha = fecha;
        this.idLibro = idLibro;
        this.dias = diasPrestado();
    }

    public Prestamo(int id) {
        this.id = id;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    
    public int diasPrestado(){
        Date hoy = new Date();
        long diferencia = hoy.getTime()- fecha.getTime();
        return(int)diferencia/(24*60*60*1000);
    }
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
