package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

public class Pedido implements Serializable{
    private int id;
    private double total;
    private Date fecha;
    private Cliente cliente;
    private HashSet<LineaPedido> lineas;
    
    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public HashSet<LineaPedido> getLineas() {
        return lineas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente c) {
        this.cliente = c;
    }

    public void setLineas(HashSet<LineaPedido> lineas) {
        this.lineas = lineas;
    }
    
    
}
