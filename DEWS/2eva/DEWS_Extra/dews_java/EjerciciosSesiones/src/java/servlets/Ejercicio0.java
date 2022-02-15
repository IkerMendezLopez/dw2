package servlets;

import beans.Catalogo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Ejercicio0 extends HttpServlet {

    private void dibujarForm(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        Catalogo c = new Catalogo();
        String [] arrLibros = c.getLibros();
       
        
        if(request.getParameter("error")!=null){
           out.print("<p style='color: red'><strong>*Ya has seleccionado este libro anteriormente!</strong></p>");
        }
        
        if(request.getParameter("finaliza")!=null){
            HttpSession s = request.getSession();
            s.invalidate();
            s = request.getSession();
        }
        
        boolean ultimo = false;
        out.print("<form method='post' action='" + request.getRequestURI() + "'>");
            out.print("<select name='comboLibros'>");
            
                for(String libro : arrLibros){
                    if(request.getParameter("comboLibros")!=null){
                        if(request.getParameter("comboLibros").equals(libro)){
                            out.print("<option value='" + libro + "' selected>" + libro + "</option>");
                        }
                        else{
                            out.print("<option value='" + libro + "'>" + libro + "</option>");
                        }
                    }
                    else{
                        out.print("<option value='" + libro + "'>" + libro + "</option>");
                    }
                    
                }
            
            out.print("</select>");
            out.print(" <input type='submit' name='submitAgregar' value='AGREGAR'/>");
        out.print("</form>");
    }
    
    
    private void dibujarLista(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        HttpSession s = request.getSession();
        if(s.getAttribute("lstLibros")==null){
            s.setAttribute("lstLibros", new ArrayList<String>());
        }
        
        
        ArrayList <String> libros = (ArrayList <String>) s.getAttribute("lstLibros");
        if(libros.isEmpty()){
            out.print("<h2>No se han elegido libros</h2>");
        }
        else{
            out.print("<h2>TU ELECCION: </h2>");
            
            out.print("<ul>");
                for(String libro : libros){
                    out.print("<li>" + libro + "</li>");
                }
            out.print("</ul>");
            
            String enlace = request.getRequestURI() + "?finaliza";
            out.print("<p><a href='" + enlace + "'>Reiniciar</a></p>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ejercicio1</title>");            
            out.println("</head>");
            out.println("<body>");
                
                dibujarForm(request, response, out);
                dibujarLista(request, response, out);
            
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        if(s.getAttribute("lstLibros")==null){
            s.setAttribute("lstLibros", new ArrayList<String>());
        }
        
        
        ArrayList <String> libros = (ArrayList <String>) s.getAttribute("lstLibros");
        if(request.getParameter("submitAgregar")!=null){
            String libro = request.getParameter("comboLibros");
            
            if(!libros.contains(libro)){
                libros.add(request.getParameter("comboLibros"));
            }
            else{
                response.sendRedirect(request.getRequestURI()+"?error");
            }
        }
        
        doGet(request, response);
    }

    

}
