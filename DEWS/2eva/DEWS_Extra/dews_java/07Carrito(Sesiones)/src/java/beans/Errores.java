package beans;


public class Errores {
    
    private String nomProd;
    private String error;

    public Errores() {
        
    }

    public Errores(String nomProd, String error) {
        this.nomProd = nomProd;
        this.error = error;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}

