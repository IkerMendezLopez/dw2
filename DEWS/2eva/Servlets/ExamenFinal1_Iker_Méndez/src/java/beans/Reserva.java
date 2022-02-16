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

    public int getId_reserva() {
        return id_reserva;
    }

    public int isPagado() {
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
