package beans;

import java.util.Date;



public class Ruta {

    private int idRuta;
    private String ciudadOrigen, ciudadDestino, idPlaca;
    private Date horaSalida, horaLlegada;
    private float tarifa;
    private boolean estadoEdit;
    

    public Ruta() {
    }

    public Ruta(int idRuta, String idPlaca, String ciudadOrigen, String ciudadDestino, Date horaSalida, Date horaLlegada, float tarifa) {
        this.idRuta = idRuta;
        this.idPlaca = idPlaca;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.tarifa = tarifa;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(String idPlaca) {
        this.idPlaca = idPlaca;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public boolean isEstadoEdit() {
        return estadoEdit;
    }

    public void setEstadoEdit(boolean estadoEdit) {
        this.estadoEdit = estadoEdit;
    }
    
    

    @Override
    public String toString() {
        return ciudadOrigen + " ➡ " + ciudadDestino + ",  " + horaSalida + " ➡ " + horaLlegada + ",  " + tarifa + "€";
    }
    
    
    
    
    
    
}
