package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ProcesaApuesta extends HttpServlet {
   
    
    private boolean contieneBlancos(String [] apuestas){
        
        for(String apuesta : apuestas){
            if(apuesta.equals(" ")){
                return true;
            }
        }
        
        return false;
    }
    
    
    public void muestraApuesta(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        HttpSession s = request.getSession();
        String [] apuestas = (String[]) s.getAttribute("apuestasRealizadas");
        
        out.print("<h2>Apuesta guardada</h2>");
        
        ArrayList <String> equipos = (ArrayList <String>) this.getServletContext().getAttribute("equipos");
        for(int i=0; i<equipos.size(); i++){
            String [] parEquipos = equipos.get(i).split("/");
            String equipo1 = parEquipos[0];
            String equipo2 = parEquipos[1];
            
            out.print("<p><strong>" + equipo1 + " : " + equipo2 + " " + apuestas[i] + "</strong></p>");
        }
        
        
        String enlace = "EscribeApuesta?revisar";
        out.print("<p><a href='" + enlace + "'>REVISAR LA APUESTA</a></p>");
    }
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        if(request.getParameter("submitGuardar")!=null){
           
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Preguntas</title>");            
                out.println("</head>");
                out.println("<body>");
                           
                String [] apuestas = request.getParameterValues("comboOp");
                HttpSession s = request.getSession();
                if(s.getAttribute("arrApuestas")==null){                    
                    s.setAttribute("apuestasRealizadas", new String [apuestas.length]);
                    
                    String [] apuestasRealizadas = (String[]) s.getAttribute("apuestasRealizadas");
                    for(int i=0; i<apuestas.length; i++){
                        apuestasRealizadas [i] = apuestas[i];
                    }
                }     
                
                
                if(contieneBlancos(apuestas)){
                    RequestDispatcher rd = request.getRequestDispatcher("EscribeApuesta?error= "); //Si mandamos un parametro con el forward tenemos que asignarle un valor
                    rd.forward(request, response);
                }
                else{
                    muestraApuesta(request, response, out);
                }

                out.println("</body>");
                out.println("</html>");
            }
            
            
            
        }
        else{
            response.sendRedirect("Ejercicio3.html");
        }
        
    }

    


}
