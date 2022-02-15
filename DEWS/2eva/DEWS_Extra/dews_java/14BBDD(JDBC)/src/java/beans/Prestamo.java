package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prestamo {

    private int idprestamo, idlibro;
    private Date fecha, fechadev;

    
    public Prestamo() {
        
    }

    public Prestamo(int idprestamo, int idlibro, Date fecha, Date fechadev) {
        this.idprestamo = idprestamo;
        this.idlibro = idlibro;
        this.fecha = fecha;
        this.fechadev = fechadev;
    }

    public int getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(int idprestamo) {
        this.idprestamo = idprestamo;
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechadev() {
        return fechadev;
    }

    public void setFechadev(Date fechadev) {
        this.fechadev = fechadev;
    }

    @Override
    public String toString() {
        return "Prestamos{" + "idprestamo=" + idprestamo + ", idlibro=" + idlibro + ", fecha=" + fecha + ", fechadev=" + fechadev + '}';
    }
    
    public String strFecha(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
        return formateador.format(fecha);
    }
    
    public String strFechaDev(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
        return formateador.format(fechadev);
    }
    
    
    
    
    
}
