package beans;

public class Libro {

    private int idlibro, paginas, idautor;
    private String titulo, genero;

    
    public Libro() {
        
    }

    public Libro(int idlibro, int paginas, int idautor, String titulo, String genero) {
        this.idlibro = idlibro;
        this.paginas = paginas;
        this.idautor = idautor;
        this.titulo = titulo;
        this.genero = genero;
    }

    public Libro( String titulo, String genero, int paginas, int idautor) {
        this.paginas = paginas;
        this.idautor = idautor;
        this.titulo = titulo;
        this.genero = genero;
    }
    
    

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    
    
    
}
