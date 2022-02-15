package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletComprobar extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        String palOculta = s.getAttribute("strSession").toString();
        int cantVidas = (int) s.getAttribute("cantVidas");
        String [] posiciones = (String[]) s.getAttribute("posiciones");
        

        if(request.getParameter("indice")!=null){
            
            int indice = Integer.parseInt(request.getParameter("indice"));    
            char letra = palOculta.charAt(indice);
            
            posiciones[indice] = "" + letra;
            
            cantVidas--;
            s.setAttribute("cantVidas", cantVidas);
        }
        
       response.sendRedirect("ServletJuego");
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        String palOculta = s.getAttribute("strSession").toString();
        int cantVidas = (int) s.getAttribute("cantVidas");
        String [] posiciones =  (String[]) s.getAttribute("posiciones");
        
        
        boolean error = false;
        if(request.getParameter("submitComprobar")!=null){
            
            String strRespuesta = request.getParameter("strRespuesta");
            
            if(!strRespuesta.equals("")){
                
                int existencia = palOculta.indexOf(strRespuesta);
                if(existencia!=-1){
                    while(existencia!=-1){

                        posiciones [existencia] = ""+ palOculta.charAt(existencia);

                        existencia = palOculta.indexOf(strRespuesta, existencia+1);
                    }
                }
                else{
                    cantVidas--;
                    s.setAttribute("cantVidas", cantVidas);
                }
            }
            else{
                error = true;
            }
        }

        response.sendRedirect("ServletJuego?error=" + error);
    }

    


}
