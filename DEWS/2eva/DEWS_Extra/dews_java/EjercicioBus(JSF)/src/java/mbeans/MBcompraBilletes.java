package mbeans;

import beans.Bus;
import beans.Ruta;
import dao.DaoBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MBcompraBilletes {

    private ArrayList <Ruta> rutas;
    private Ruta rutaSeleccionada;
    private HashMap <Integer, String> asientosReservados;
    private ArrayList <Integer> asientosDisponibles;
    private int [] asientosSeleccionados;
    private String dniCliente;
    private String errorReserva;
   
    
    public MBcompraBilletes() {

        rutas = DaoBus.listaRutas();
    }

    public MBcompraBilletes(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }

    public Ruta getRutaSeleccionada() {
        return rutaSeleccionada;
    }

    public void setRutaSeleccionada(Ruta rutaSeleccionada) {
        this.rutaSeleccionada = rutaSeleccionada;
    }

    public HashMap<Integer, String> getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(HashMap<Integer, String> asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public ArrayList<Integer> getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(ArrayList<Integer> asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public int[] getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(int[] asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getErrorReserva() {
        return errorReserva;
    }

    public void setErrorReserva(String errorReserva) {
        this.errorReserva = errorReserva;
    }
    
    
    
    

  
    
   
    
//METODOS    
   /* public String cargarRutasBus(){
        
        rutas = DaoBus.rutasBus(busActual);
        
        return null;
    }
    
    
    public void cambiarTarifa(Ruta r){
        
        DaoBus.modificarTarifaRuta(r);
        r.setEstadoEdit(false);
    }*/
    
    public String comprar(Ruta r){
        
        rutaSeleccionada = r;
        
        return "reserva";
    }
    
    
    public ArrayList<Map.Entry> asientosAsArray(int idRuta){
        
        asientosReservados = DaoBus.asientosReservados(idRuta);
        
        return  new ArrayList(asientosReservados.entrySet());
    }
    
    
    public ArrayList <Integer> asientosDisponibles (int idRuta){
        
        asientosDisponibles = new ArrayList <Integer>();
        int cantAsientos = DaoBus.cantAsientos(idRuta);
        asientosReservados = DaoBus.asientosReservados(idRuta);
        
        for(int i=1; i<=cantAsientos; i++){
            if(!asientosReservados.containsKey(i)){
                asientosDisponibles.add(i);
            }
        }
        
        return asientosDisponibles;
    }
    
    
    public void reservar(){
        
        if(asientosSeleccionados.length>0){
            if(!DaoBus.compruebaCliente(dniCliente)){
                errorReserva = "*Cliente inexistente!";
            }
            else{
                for(int i : asientosSeleccionados){
                    DaoBus.reservarAsientos(dniCliente, i, rutaSeleccionada.getIdRuta());
                }
            }

            dniCliente = "";
        }
        else{
            errorReserva = "*Seleccione un asiento porfavor!";
        }
    }
    
    
    
    
    public static void main (String args[]){
        
        MBcompraBilletes mb = new MBcompraBilletes();
        ArrayList <Integer> lstDisponibles = mb.asientosDisponibles(1);
        System.out.print(lstDisponibles);
    }

}
