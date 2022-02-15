package beans;

public class Pregunta {

    private String pregunta, rptaOk, rptaUser;

    public Pregunta() {
        
    }

    public Pregunta(String pregunta, String rptaOk, String rptaUser) {
        this.pregunta = pregunta;
        this.rptaOk = rptaOk;
        this.rptaUser = rptaUser;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRptaOk() {
        return rptaOk;
    }

    public void setRptaOk(String rptaOk) {
        this.rptaOk = rptaOk;
    }

    public String getRptaUser() {
        return rptaUser;
    }

    public void setRptaUser(String rptaUser) {
        this.rptaUser = rptaUser;
    }
        
    
    
    
    
}
