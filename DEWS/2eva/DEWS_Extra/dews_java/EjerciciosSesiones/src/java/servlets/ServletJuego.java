package servlets;

import beans.AlmacenPalabras;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletJuego extends HttpServlet {
   
    private boolean acertada = true;
    
    
    private void dibujarForm(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException{
        
        HttpSession s = request.getSession();
        
        String palSecreta = s.getAttribute("strSession").toString();
        int cantVidas = Integer.parseInt(s.getAttribute("cantVidas").toString());
        String [] posiciones =  (String[]) s.getAttribute("posiciones");
        
        
        if(cantVidas==0){
            
            acertada = true;
            s.invalidate();
            response.sendRedirect("ServletJuego?palAnterior=" + palSecreta);
            
            /*
            //OPCION 2 FINAL : Para iniciar un nuevo juego mediante un enlace 
            out.print("<h3>Has perdido! La palabra era: " + palSecreta + "</h3>");
            String enlace = request.getRequestURI() + "?newJuego";
            out.print("<h3><a href='" + enlace + "'>Nuevo Juego<a/></h3>");
            */
        }
        else{
            out.print("<form method='post' action='ServletComprobar'>");
                
            
                for(int i=0; i<palSecreta.length(); i++){
                    
                    if(posiciones[i]!=null){
                        out.print("<span href='#'><strong>" + posiciones[i] + "</strong><span/>");
                    }
                    else{
                        String enlace = "ServletComprobar?indice=" + i;
                        out.print("<a href='" + enlace + "'> Ver </a>");
                    }
                }

                out.print("<ul>");
                    if(cantVidas==1){
                        out.print("<li style='color: red'><strong>Solo te queda 1 vida!</strong></li>");
                    }
                    else{
                        out.print("<li>" + cantVidas + " vidas restante</li>");
                    }
                    out.print("<li>Tu respuesta <input type='text' name='strRespuesta'/> <input type='submit' name='submitComprobar' size='1' value='Comprobar'/></li>");
                out.print("</ul>");
            out.print("</form>");
        }
        
        
        
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession();  
        
        /*
        //OPCION 2 FINAL : Para iniciar un nuevo juego mediante un enlace 
        if(request.getParameter("newJuego")!=null){
            acertada = true;
            s.invalidate();
            s = request.getSession();  
        }
        */
        
        if(acertada){
            String [] strOculta = AlmacenPalabras.getSTROCULTA();
            
            String palSecreta = strOculta[(int) Math.floor(Math.random()*strOculta.length)];
            int cantVidas = (int) Math.floor(palSecreta.length()/2);
            
            if(s.getAttribute("strSession")==null){
                s.setAttribute("strSession", palSecreta);
                s.setAttribute("cantVidas", cantVidas);
                s.setAttribute("posiciones", new String [palSecreta.length()]);
            }
            
            acertada = false;
        }
        

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletJuego</title>");            
            out.println("</head>");
            out.println("<body>");
            
            //OPCION 1 FINAL :  Cuando te quedas sin vidas directamente inicia un nuevo juego mostrando la palabra anterior
            if(request.getParameter("palAnterior")!=null){
                out.print("<h3>Has perdido! La palabra era: " + request.getParameter("palAnterior") + "</h3>");
            }
            
            if(Boolean.parseBoolean(request.getParameter("error"))){
                out.print("<p style='color: red'><strong>*Introduzca un valor!</strong></p>");
            }

            
            dibujarForm(request, response, out);
            
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response);
    }

    
    

}
