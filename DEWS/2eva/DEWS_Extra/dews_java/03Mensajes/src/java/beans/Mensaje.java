package beans;

import java.util.Date;


public class Mensaje {
    
    private String emisor;
    private String texto;
    private Date fecha;
    private int aFavor;
    private int enContra;

    
    public Mensaje() {
        
    }

    public Mensaje(String emisor, String texto, Date fecha, int aFavor) {
        this.emisor = emisor;
        this.texto = texto;
        this.fecha = fecha;
        this.aFavor = aFavor;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getaFavor() {
        return aFavor;
    }

    public void setaFavor(int aFavor) {
        this.aFavor = aFavor;
    }

    public int getEnContra() {
        return enContra;
    }

    public void setEnContra(int enContra) {
        this.enContra = enContra;
    }
    
    public void unoMasAFavor(){
        aFavor++;
    }
    
    public void unoMasEnContra(){
        enContra++;
    }
    
    
}
