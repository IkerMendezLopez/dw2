package servlets;

import beans.AlmacenMensajes;
import beans.Mensaje;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletVerMensajes extends HttpServlet {


    private void verMensajes(PrintWriter out){
        
        ArrayList <Mensaje> lstMensajes = AlmacenMensajes.getMensajes();
        
        out.print("<table>");
            out.print("<tr>");
                out.print("<th>Emisor</th>");
                out.print("<th>Mensaje</th>");
                out.print("<th>A Favor</th>");
                out.print("<th>En Contra</th>");
            out.print("</tr>");
        
            int i=0;
            Iterator <Mensaje> it = lstMensajes.iterator();
            while(it.hasNext()){
                
                Mensaje m = it.next();
                out.print("<tr>");
                    out.print("<td>" + m.getEmisor() + "</td>");
                    out.print("<td>" + m.getTexto()+ "</td>");
                    out.print("<td>" + m.getaFavor()+ "</td>");
                    out.print("<td>" + m.getEnContra()+ "</td>");
                    
                    String enlace = "ServletVerMensajes?posicion="+i+"&afavor"; //i es la posicion del arraylist en la que estamos
                    out.print("<td><a href='" + enlace + "'>Votar a Favor</a></td>");
                    
                    String enlace2 = "ServletVerMensajes?posicion="+i+"&encontra";
                    out.print("<td><a href='" + enlace2 + "'>Votar en Contra</a></td>");
                out.print("</tr>");
                
                i++;
            }
        out.print("</table>");
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("posicion")!=null){
                
            int i = Integer.parseInt(request.getParameter("posicion"));
            ArrayList <Mensaje> lstMensajes = AlmacenMensajes.getMensajes();
            
            if(request.getParameter("afavor")!=null){
                lstMensajes.get(i).unoMasAFavor();
            }
            
            if(request.getParameter("encontra")!=null){
                lstMensajes.get(i).unoMasEnContra();
            }
        }
         
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletVerMensajes</title>");            
            out.println("</head>");
            out.println("<body>");
    
            verMensajes(out);
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
    }
}
