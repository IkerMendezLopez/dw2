package servlets;

import beans.Comida;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletGrabar extends HttpServlet {
   
    private boolean crearFichComidas(String [] nombres, int [] precios){
        
        String nomFich = this.getServletContext().getInitParameter("ficherocomidas");
        String url = this.getServletContext().getRealPath(nomFich);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(url));
            
            for(int i=0; i<nombres.length; i++){
                Comida comida = new Comida(nombres[i], precios[i]);
                
                oos.writeObject(comida);
            }
            
            oos.writeObject(null);
            oos.close();
        } 
        catch (IOException e) {
            return false;
        }
        catch(Exception e2){
            return false;
        }
        
        return true;
    }
  
    private void escribir(HttpServletRequest request, HttpServletResponse response, String texto) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulario Comidas</title>");            
            out.println("</head>");
            out.println("<body>");
                out.print("<h2>" + texto + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitGrabar")!=null){
            
            String [] nombres = request.getParameterValues("nombres");
            String [] strPrecios = request.getParameterValues("precios");
            
            int [] precios = new int [strPrecios.length];
            for(int i=0; i<strPrecios.length; i++){
                try{
                    precios[i] = Integer.parseInt(strPrecios[i]);
                }
                catch(NumberFormatException e){
                    precios [i] = 0;
                }
            }
            
            if(crearFichComidas(nombres, precios)){
                escribir(request, response, "ARCHIVO CREADO!");
            }
            else{
                escribir(request, response, "ARCHIVO NO CREADO!");
            }
        }
    }

    


}
