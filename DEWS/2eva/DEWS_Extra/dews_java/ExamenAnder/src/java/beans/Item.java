package beans;

import dao.DaoExam;
import java.util.ArrayList;
import java.util.Objects;

public class Item {

    private int id;
    private String nombre;
    private double precio;
    private ArrayList <String> lstNombres;

    
    public Item() {
    }

    public Item(int id, String nombre, double precio, ArrayList <String> lstNombres) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.lstNombres = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<String> getLstNombres() {
        return lstNombres;
    }

    public void setLstNombres(ArrayList<String> lstNombres) {
        this.lstNombres = lstNombres;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", lstNombres=" + lstNombres + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.lstNombres, other.lstNombres)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
