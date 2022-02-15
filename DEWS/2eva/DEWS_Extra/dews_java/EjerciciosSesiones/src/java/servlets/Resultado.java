
package servlets;

import beans.Pregunta;
import beans.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Resultado extends HttpServlet {

    private void mostrarResultado(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        HttpSession s = request.getSession();
        if(s.getAttribute("nombre")!=null){
            String nombre = s.getAttribute("nombre").toString();
            ArrayList <Integer> lstRespuestas = (ArrayList <Integer>) s.getAttribute("lstRespuestas");
            int cantAciertos = Test.comprobar(lstRespuestas);
            Date tiempoInicio = (Date) s.getAttribute("tiempoInicio");
            Date tiempoFin = new Date();
            long tiempoTotal = tiempoFin.getTime()-tiempoInicio.getTime();
            
            long seg = 1000;
            long min = seg*60;
            long minTranscurridos = tiempoTotal/min;
            tiempoTotal = tiempoTotal%min;
            long segTranscurridos = tiempoTotal/seg;

            out.print("<p><strong>" + nombre + "</strong>, has acertado " + cantAciertos + " preguntas de un total de " + lstRespuestas.size() + "</p>");
            out.print("<p>Tienes muy buenos conocimientos, enhorabuena!</p>");
            out.print("<p>Tiempo de respuesta: " + minTranscurridos + " minutos y " + segTranscurridos + " segundos</p>");

            String enlace = request.getRequestURI() + "?newIntento";
            out.print("<p><a href='" + enlace + "'>NUEVO INTENTO</a></p>");
        }
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        if(request.getParameter("newIntento")!=null){

            s.invalidate();
            response.sendRedirect("../Ejercicio2.html");
        }
        else{
            doPost(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitFin")!=null){
            
            if(request.getParameter("opcion")!=null){
                
                HttpSession s = request.getSession();
                int opcionSeleccionada = Integer.parseInt(request.getParameter("opcion"));
                ArrayList <Integer> lstRespuestas = (ArrayList <Integer>) s.getAttribute("lstRespuestas");    
                
                lstRespuestas.add(opcionSeleccionada);
            }
            else{
                response.sendRedirect("ProcesoPregunta?errorRadios");
            }
        }
        else{
            response.sendRedirect("../Ejercicio2.html");
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado</title>");            
            out.println("</head>");
            out.println("<body>");
            
            mostrarResultado(request, response, out);

            out.println("</body>");
            out.println("</html>");
        }
    }

}
