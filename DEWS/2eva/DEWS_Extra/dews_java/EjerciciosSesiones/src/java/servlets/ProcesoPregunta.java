package servlets;

import beans.Pregunta;
import beans.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ProcesoPregunta extends HttpServlet {

    
    private void dibujarForm(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        HttpSession s = request.getSession();
        ArrayList <Pregunta> lstPreguntas = (ArrayList <Pregunta>) s.getAttribute("lstPreguntas");
        boolean mostrarPistas = Boolean.parseBoolean(s.getAttribute("chkPistas").toString());
        int indicePregunta = (int) s.getAttribute("indicePregunta");
        

        String enlace = "";
        boolean ultima = false;
        if(indicePregunta==lstPreguntas.size()-1){
            enlace = "Resultado";
            ultima = true;
        }
        else{
            enlace = request.getRequestURI();
        }
        
        out.print("<form method='post' action='" + enlace + "'>");
            out.print("<h3>" + lstPreguntas.get(indicePregunta).getEnunciado() + "</h3>");    
            
            String [] opciones = lstPreguntas.get(indicePregunta).getRespuestas();
            for(int i=0; i<opciones.length; i++){
                out.print("<p><input type='radio' name='opcion' value='" + i + "' id='op" + i + "'/> <label for='op" + i +"'>" + opciones[i] + "</label></p>");
            }
            
            if(ultima){
                out.print("<input type='submit' name='submitFin' value='FIN'/>");
            }
            else{
                out.print("<input type='submit' name='submitSiguiente' value='SIGUIENTE'/>");
            }
            
            if(mostrarPistas){
                out.print("<p><em>*PISTA: " + lstPreguntas.get(indicePregunta).getPista() + "</em></p>");
            }
        out.print("</form>");
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        
        if(request.getParameter("errorRadios")!=null || request.getParameter("siguientePregunta")!=null){
                
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Preguntas</title>");            
                out.println("</head>");
                out.println("<body>");
            
                if(request.getParameter("errorRadios")!=null){
                    out.print("<p style='color: red'><strong>*Debes seleccionar una opcion!</strong></p>");
                }
                
                dibujarForm(request, response, out);

                out.println("</body>");
                out.println("</html>");
            }
        }
        else{
            doPost(request, response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean errorFormPrincipal = false;
        if(request.getParameter("submitComenzar")!=null){
            
            try{
                String nombre = request.getParameter("nombre");
                int cantPreguntas = Integer.parseInt(request.getParameter("cantPreguntas"));
                
                if(nombre.equals("")){
                    errorFormPrincipal = true;
                }
            }
            catch(NumberFormatException e){
                errorFormPrincipal = true;
            }
        }
        else{
            if(request.getParameter("submitSiguiente")!=null){
                
                if(request.getParameter("opcion")!=null){
                    
                    HttpSession s = request.getSession();
                    int opcionSeleccionada = Integer.parseInt(request.getParameter("opcion"));
                    int indicePregunta = (int) s.getAttribute("indicePregunta");
                    ArrayList <Integer> lstRespuestas = (ArrayList <Integer>) s.getAttribute("lstRespuestas");
                    
                    lstRespuestas.add(opcionSeleccionada);
                    indicePregunta++;
                    s.setAttribute("indicePregunta", indicePregunta);
                    
                    response.sendRedirect("ProcesoPregunta?siguientePregunta");
                }
                else{
                    response.sendRedirect("ProcesoPregunta?errorRadios");
                }
            }
            else{
                errorFormPrincipal = true;
            }
        }
        
        
        
        if(errorFormPrincipal){
           response.sendRedirect("../Ejercicio2.html");
        }
        else{
            HttpSession s = request.getSession();
            
            if(s.getAttribute("nombre")==null){
                
                String nombre = request.getParameter("nombre");
                int cantPreguntas = Integer.parseInt(request.getParameter("cantPreguntas"));
                boolean mostrarPistas = false;
                if(request.getParameter("chkPistas")!=null){
                    mostrarPistas = true;
                }
                
                Test t = new Test(cantPreguntas);
                
                s.setAttribute("nombre", nombre);
                s.setAttribute("lstPreguntas", t.getLstPreguntas());
                s.setAttribute("chkPistas", mostrarPistas);
                s.setAttribute("indicePregunta", 0);
                s.setAttribute("lstRespuestas", new ArrayList<Integer>());
                s.setAttribute("tiempoInicio", new Date());
            }
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Preguntas</title>");            
                out.println("</head>");
                out.println("<body>");
                
                dibujarForm(request, response, out);

                out.println("</body>");
                out.println("</html>");
            }
        } 
    }

}
