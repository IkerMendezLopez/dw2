package mbeans;

import beans.Pregunta;
import java.util.ArrayList;

public class MBTest {

    private Pregunta [] arrPreguntas;
    private Pregunta pregunta;
    private int pregunta_actual, num_aciertos;
    private boolean ultima_pregunta;
    private int cant_preguntas;
    private int [] arrCantPreguntas;
    private ArrayList <String> lstPreguntasMostradas;
    
   
    public MBTest() {
        
        pregunta = new Pregunta();
        arrPreguntas = new Pregunta[]{new Pregunta("¿En que año se descubrio America?", "1492", null), new Pregunta("¿Donde se forman los globulos rojos?", "En la medula osea", null), new Pregunta("¿En cuál de los siguientes paises NO hay ningún desierto?", "Alemania", null), new Pregunta("¿Quien introdujo la lira en la lirica española?", "Garcilaso de la Vega", null)};
        pregunta_actual = 0;
        num_aciertos = 0;
        ultima_pregunta = false;
        cant_preguntas = 0;
        cargarArrayCantidad();
        lstPreguntasMostradas = new ArrayList<String>();
    }

    public Pregunta[] getArrPreguntas() {
        return arrPreguntas;
    }

    public void setArrPreguntas(Pregunta[] arrPreguntas) {
        this.arrPreguntas = arrPreguntas;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getPregunta_actual() {
        return pregunta_actual;
    }

    public void setPregunta_actual(int pregunta_actual) {
        this.pregunta_actual = pregunta_actual;
    }

    public int getNum_aciertos() {
        return num_aciertos;
    }

    public void setNum_aciertos(int num_aciertos) {
        this.num_aciertos = num_aciertos;
    }

    public boolean isUltima_pregunta() {
        return ultima_pregunta;
    }

    public void setUltima_pregunta(boolean ultima_pregunta) {
        this.ultima_pregunta = ultima_pregunta;
    }

    public int[] getArrCantPreguntas() {
        return arrCantPreguntas;
    }

    public void setArrCantPreguntas(int[] arrCantPreguntas) {
        this.arrCantPreguntas = arrCantPreguntas;
    }

    public int getCant_preguntas() {
        return cant_preguntas;
    }

    public void setCant_preguntas(int cant_preguntas) {
        this.cant_preguntas = cant_preguntas;
    }
    
    
    
    
    
    
    
    
//METODOS
    public String comprobar(){

        if(pregunta_actual+1==arrPreguntas.length-1){
            ultima_pregunta = true;
        }
        
        if(arrPreguntas[pregunta_actual].getRptaUser().equals(arrPreguntas[pregunta_actual].getRptaOk())){   
            num_aciertos++;
            arrPreguntas[pregunta_actual].setRptaOk("OK");
        }     
        pregunta_actual++;
        
        
        if(pregunta_actual==arrPreguntas.length){
            return "rtdoTest";
        }
        
        return null;
    }
    
    
    public int cantPreguntas(){
        
        return arrPreguntas.length;
    }
    
    
    private void cargarArrayCantidad (){
        
        arrCantPreguntas = new int [cantPreguntas()];
        for(int i=0 ;i<arrCantPreguntas.length; i++){
            arrCantPreguntas [i] = i+1;
        }
    }
    
    
    public String comprobarMejora(){
        
        int indicePreguntaAleatoria = (int) Math.floor(Math.random()*cant_preguntas);
        
        if(pregunta_actual+1==cant_preguntas-1){
            ultima_pregunta = true;
        }
        
        if(!lstPreguntasMostradas.contains(arrPreguntas[pregunta_actual].getPregunta())){
            if(arrPreguntas[pregunta_actual].getRptaUser().equals(arrPreguntas[pregunta_actual].getRptaOk())){   
                num_aciertos++;
                arrPreguntas[pregunta_actual].setRptaOk("OK");
                lstPreguntasMostradas.add(arrPreguntas[pregunta_actual].getPregunta());
            }     
            pregunta_actual++;
        }

        
        if(pregunta_actual==cant_preguntas){
            return "rtdoTest";
        }
        
        return null;
    }
    
    

    
    
}
