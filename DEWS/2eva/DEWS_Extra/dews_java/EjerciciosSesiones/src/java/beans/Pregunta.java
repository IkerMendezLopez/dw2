package beans;

public class Pregunta {
    
    private String enunciado;
    private String pista;
    private String [] respuestas;
    private int pos;

    
    public Pregunta(String enunciado, String pista, String[] respuestas, int pos) {
        this.enunciado = enunciado;
        this.pista = pista;
        this.respuestas = respuestas;
        this.pos = pos;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "enunciado=" + enunciado + ", pista=" + pista + ", respuestas=" + respuestas + ", pos=" + pos + '}';
    }
    
    
    
    
    
}
