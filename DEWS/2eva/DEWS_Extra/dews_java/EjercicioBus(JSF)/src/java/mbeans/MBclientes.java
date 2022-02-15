package mbeans;

import beans.Cliente;
import dao.DaoBus;
import java.util.ArrayList;


public class MBclientes {

    private Cliente cliente;
    private ArrayList <Cliente> lstClientes;
    private String estadoInserccion;
    
    
    public MBclientes() {
        cliente = new Cliente();
        lstClientes = DaoBus.listaClientes();
    }
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Cliente> getLstClientes() {
        return lstClientes;
    }

    public void setLstClientes(ArrayList<Cliente> lstClientes) {
        this.lstClientes = lstClientes;
    }

    public String getEstadoInserccion() {
        return estadoInserccion;
    }

    public void setEstadoInserccion(String estadoInserccion) {
        this.estadoInserccion = estadoInserccion;
    }
    
    
    
   
    
//METODOS
    public void registrar(){
        
        if(DaoBus.registrarCliente(cliente)){
            estadoInserccion = "Cliente insertado!";
            lstClientes = DaoBus.listaClientes(); //Estaria bien que registrarCliente devuelva un cliente y añadir ese cliente a la lista para no tener que realizar otra vez la consulta en la bdd
        }
        else{
            estadoInserccion = "*No se ha podido insertar!";
        }
        
        return;
    }
    
    
    

}
