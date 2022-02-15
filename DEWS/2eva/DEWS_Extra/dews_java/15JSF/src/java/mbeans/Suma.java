package mbeans;

import java.util.ArrayList;


public class Suma {

    private int num1,num2;
    private int rptaUser;
    private ArrayList <String> lstRespuestas;
    
    
    public Suma() {
        
        generaNums();
        lstRespuestas = new ArrayList<String>();
    }
    
    
    public void generaNums(){
        
        this.num1 = (int) Math.floor(Math.random()*5);
        this.num2 = (int) Math.floor(Math.random()*5);
    }
    
    public String comprobar(){
        
        String estadoSuma = " MAL" + (num1+num2);
        if(rptaUser==(num1+num2)){
            estadoSuma = " BIEN";
        }
        
        lstRespuestas.add(num1 + " + " + num2 + " = " + rptaUser + estadoSuma);

        generaNums();
        
        return null;
    }
    
    
    public String toString(){
        
        String str = "";
        for(String rpta : lstRespuestas){
            str += rpta + "\n";
        }
        
        return str;
    }
            
    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getRptaUser() {
        return rptaUser;
    }

    public void setRptaUser(int rptaUser) {
        this.rptaUser = rptaUser;
    }

    public ArrayList<String> getLstRespuestas() {
        return lstRespuestas;
    }

    public void setLstRespuestas(ArrayList<String> lstRespuestas) {
        this.lstRespuestas = lstRespuestas;
    }
    
    
    
    
    
    
    
}
