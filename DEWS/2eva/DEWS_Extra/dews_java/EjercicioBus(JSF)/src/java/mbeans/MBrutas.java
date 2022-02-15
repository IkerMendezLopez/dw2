package mbeans;

import beans.Bus;
import beans.Ruta;
import dao.DaoBus;
import java.util.ArrayList;


public class MBrutas {

    private ArrayList <Bus> buses;
    private String busActual;
    private ArrayList <Ruta> rutas;
   
    public MBrutas() {

        buses = DaoBus.listaBuses();
    }

    public MBrutas(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public String getBusActual() {
        return busActual;
    }

    public void setBusActual(String busActual) {
        this.busActual = busActual;
    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }
    
    
    
    
    
    
//METODOS    
    public String cargarRutasBus(){
        
        rutas = DaoBus.rutasBus(busActual);
        
        return null;
    }
    
    
    public void cambiarTarifa(Ruta r){
        
        DaoBus.modificarTarifaRuta(r);
        r.setEstadoEdit(false);
    }
    

}
