/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Objects;

/**
 *
 * @author dw2
 */
public class Reserva {
    private int id_reserva;
    private int pagado;
    private String nom_cli;

    public Reserva() {
    }

    public Reserva(int id_reserva, int pagado, String nom_cli) {
        this.id_reserva = id_reserva;
        this.pagado = pagado;
        this.nom_cli = nom_cli;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Reserva other = (Reserva) obj;
        if (this.id_reserva != other.id_reserva) {
            return false;
        }
        if (this.pagado != other.pagado) {
            return false;
        }
        if (!Objects.equals(this.nom_cli, other.nom_cli)) {
            return false;
        }
        return true;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public int getPagado() {
        return pagado;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }
    
    
}
