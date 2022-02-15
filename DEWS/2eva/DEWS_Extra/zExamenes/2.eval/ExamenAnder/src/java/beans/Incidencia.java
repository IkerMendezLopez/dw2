package beans;

import java.util.Date;

public class Incidencia {

    private int id, idpedido;
    private String descripcion;
    private Date fecha;

    
    public Incidencia() {
    }

    public Incidencia(int id, int idpedido, String descripcion, Date fecha) {
        this.id = id;
        this.idpedido = idpedido;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "id=" + id + ", idpedido=" + idpedido + ", descripcion=" + descripcion + ", fecha=" + fecha + '}';
    }
    
    
    
}
