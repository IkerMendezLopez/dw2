package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    
    private int cantPreguntas;
    private static ArrayList <Pregunta> lstPreguntas;
    private static Pregunta [] arrPreguntas = {new Pregunta("¿En que año se descubrio America?", "Ese mismo año expulsaron a los Judios de la Peninsula", new String [] {"1501", "1494", "1492"}, 2), new Pregunta("¿Donde se forman los globulos rojos?", "Las celulas madre de dicho sitio tambien conocidas como 'hemoblascitos' dan lurgan a la formacion de estos", new String [] {"En los pulmones", "En el musculo", "En la medula osea", "En el higado"}, 2), new Pregunta("¿En cuál de los siguientes paises NO hay ningún desierto?", "En dicho pais se jugo la Copa Mundial del 2006", new String [] {"España", "Alemania", "Mongolia"}, 1), new Pregunta("¿Quien introdujo la lira en la lirica española?", "Hizo parte de varias expediciones militares, una de ellas junto al otro famoso poeta Juan Boscan", new String [] {"Garcilaso de la Vega","Luis de Gongora","Lope de Vega"} , 0), new Pregunta("¿Quién interpreta la cancion 'Baby One More Time'?", "Sustenta un total de 481 premios ganados", new String [] {"Maddona", "Monstserrat Caballe", "Britney Spears", "Lady Gaga"}, 2)};
    

    
    public Test(int cantPreguntas) {
        this.cantPreguntas = cantPreguntas;
        this.lstPreguntas = new ArrayList<Pregunta>();
        
        
        int limite = 0;
        if(cantPreguntas<=arrPreguntas.length){
            limite = cantPreguntas;
        }
        else{
            limite = arrPreguntas.length;                    
        }
        
        
        for(int i=0; i<limite;){
            int random = (int) Math.floor(Math.random()*arrPreguntas.length);
            if(!lstPreguntas.contains(arrPreguntas[random])){
                lstPreguntas.add(arrPreguntas[random]);
                i++;
            }
        }
    }
    
    
    public static int comprobar (ArrayList <Integer> lstRespuestas){
        
        int aciertos = 0;
        
        for(int i=0; i<lstRespuestas.size(); i++){
            
            if(lstPreguntas.get(i).getPos()==lstRespuestas.get(i)){
                aciertos++;
            }
        }
        
        return aciertos;
    }


    public ArrayList<Pregunta> getLstPreguntas() {
        return lstPreguntas;
    }

    
    
    
    
}
