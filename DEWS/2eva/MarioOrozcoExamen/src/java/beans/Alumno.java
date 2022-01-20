package beans;
public class Alumno {

    private String dni;
    private String apellidos;
    private String nombre;
    private String telefono;
    private String email;

    public Alumno() {
    }

    public Alumno(String dni, String apellidos, String nombre, String telefono, String email) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Alumno{" + "dni=" + dni + ", apellidos=" + apellidos + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
    
}
