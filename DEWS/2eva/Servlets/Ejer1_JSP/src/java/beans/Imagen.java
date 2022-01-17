/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;

/**
 *
 * @author dw2
 */
public class Imagen {
    private String ruta;
    private String nombre;
    private long tamanio;
    
    public Imagen(){
        
    }

    public Imagen(String ruta) {
        this.ruta = ruta;
        File f =new File(ruta);
        this.nombre = f.getName().substring(0, f.getName().lastIndexOf('.'));
        this.tamanio = f.getTotalSpace();
    }

    public String getRuta() {
        return ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTamanio() {
        return tamanio;
    }
    
    public String tamanioDesglosado(){
        return (tamanio/1024)/1024 + " Mb " + (tamanio/1024)%1024 + " Kb " + tamanio%1024 + " bytes";
    }
    
    public static void main(String[] args) {
        Imagen img= new Imagen("C:\\Users\\dw2\\Desktop\\git\\dw2\\DEWS\\2eva\\Servlets\\Ejer1_JSP\\web\\images\\cupHeadFondo.jpg");
        System.out.println(img.getNombre()+ " " + img.getTamanio());
        System.out.println(img.tamanioDesglosado()); 
   }
}
