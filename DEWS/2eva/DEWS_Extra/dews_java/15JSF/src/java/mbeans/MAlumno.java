package mbeans;

import beans.Alumno;
import beans.Ciclo;
import java.util.ArrayList;


public class MAlumno {

    private Alumno alumno;
    private String pass, sexo, ciclo;
    private ArrayList <Ciclo> lstCiclos;
    
    
    public MAlumno() {
        
        this.alumno = new Alumno();
        this.sexo = "M";
        
        cargarCiclos();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public ArrayList<Ciclo> getLstCiclos() {
        return lstCiclos;
    }

    public void setLstCiclos(ArrayList<Ciclo> lstCiclos) {
        this.lstCiclos = lstCiclos;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    
    
    
//METODOS
    private boolean passOk(){
        
        String str="";
        
        for(int i=alumno.getNombre().length()-1; i>=0; i--){
            str += alumno.getNombre().charAt(i);
        }
        
        return str.equals(pass);
    }
    
    
    public String login(){
        
        if(passOk()){
            return "materias";
        }
        
        return null;
    }
    
    
    private void cargarCiclos(){
        
        lstCiclos = new ArrayList <Ciclo>();
        lstCiclos.add(new Ciclo("Desarollo Web", "DW", 5500));
        lstCiclos.add(new Ciclo("Peluqueria", "PL", 4500));
        lstCiclos.add(new Ciclo("Marketing", "MK", 7000));
    }
    
    
    public String strSexo(){
        
        if(sexo.equals("M")){
            return "👩 Doña";
        }
        else{
            if(sexo.equals("H")){
                return "👨 Don";
            }
            else{
                return "❓Desconocido";
            }
        }
    }
    
    
    
}
