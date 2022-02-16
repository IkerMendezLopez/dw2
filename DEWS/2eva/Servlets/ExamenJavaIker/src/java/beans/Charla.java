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
public class Charla {
    private int id_charla;
    private HoraCharla hora_comienzo;
    private String tema;
    private String id_sala;
    private int tarifa;

    public Charla() {
    }

    public Charla(int id_charla, HoraCharla horaComienzo, String tema, String id_sala, int tarifa) {
        this.id_charla = id_charla;
        this.hora_comienzo = horaComienzo;
        this.tema = tema;
        this.id_sala = id_sala;
        this.tarifa = tarifa;
    }

    public Charla(HoraCharla hora_comienzo, String tema, String id_sala, int tarifa) {
        this.hora_comienzo = hora_comienzo;
        this.tema = tema;
        this.id_sala = id_sala;
        this.tarifa = tarifa;
    }
    

    public int getId_charla() {
        return id_charla;
    }

    public HoraCharla getHoraComienzo() {
        return hora_comienzo;
    }

    public String getTema() {
        return tema;
    }

    public String getId_sala() {
        return id_sala;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setId_charla(int id_charla) {
        this.id_charla = id_charla;
    }

    public void setHoraComienzo(HoraCharla horaComienzo) {
        this.hora_comienzo = horaComienzo;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setId_sala(String id_sala) {
        this.id_sala = id_sala;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }
    
    
}
