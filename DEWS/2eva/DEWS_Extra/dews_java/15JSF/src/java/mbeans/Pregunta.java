package mbeans;


public class Pregunta {
//ATRIBUTOS
    private final static int TOPE_INTENTOS = 3;
    
    private String enunciado;
    private int rptaOk, rptaUser;
    
    private String feedBack;
    
    private int intentos;
    
    
//CONSTRUCTOR    
    public Pregunta() {
        this.enunciado = "Raiz de 25";
        this.rptaOk = (int) Math.sqrt(25);
        this.feedBack = "";
        this.intentos = 0;
    }

    public Pregunta(String enunciado, int rptaOk, int rptaUser) {
        this.enunciado = enunciado;
        this.rptaOk = rptaOk;
        this.rptaUser = rptaUser;
    }
    
    
//METODOS    
    public String comprobar(){
        
        //Al llegar aqui ya ha hecho un set de rptaUser
        /*
        if(rptaUser==rptaOk)
            return "congratulations"; //tambien se puede poner congratulations.xhtml
        else
            return null; //Si devuelve null permanece en la vista de la pregunta
        */
        
        if(rptaUser==rptaOk){
            feedBack = "Eres un genio!";
            return "resultado";
        }
        else{
            intentos++;
            if(intentos==TOPE_INTENTOS){
                feedBack = "Has agotado el Nº de intentos!";
                return "resultado";
            }
        }
        
        return null;
    }


//GETTERS & SETTERS    
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getRptaOk() {
        return rptaOk;
    }

    public void setRptaOk(int rptaOk) {
        this.rptaOk = rptaOk;
    }

    public int getRptaUser() {
        return rptaUser;
    }

    public void setRptaUser(int rptaUser) {
        this.rptaUser = rptaUser;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    
    
    
    
    
}
