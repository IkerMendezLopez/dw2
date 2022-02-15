package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prestamo {

    private int idprestamo, idlibro;
    private Date fecha, fechaDev;

    
    public Prestamo() {
        
    }

    public Prestamo(int idprestamo, int idlibro, Date fecha, Date fechadev) {
        this.idprestamo = idprestamo;
        this.idlibro = idlibro;
        this.fecha = fecha;
        this.fechaDev = fechadev;
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

    public Date getFechaDev() {
        return fechaDev;
    }

    public void setFechaDev(Date fechaDev) {
        this.fechaDev = fechaDev;
    }

    @Override
    public String toString() {
        return "Prestamos{" + "idprestamo=" + idprestamo + ", idlibro=" + idlibro + ", fecha=" + fecha + ", fechadev=" + fechaDev + '}';
    }
    
    public String strFecha(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
        return formateador.format(fecha);
    }
    
    public String strFechaDev(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
        return formateador.format(fechaDev);
    }
    
    
    
    
    
}
