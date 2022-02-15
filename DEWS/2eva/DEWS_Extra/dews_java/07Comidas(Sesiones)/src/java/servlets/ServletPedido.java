package servlets;

import beans.Comida;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletPedido extends HttpServlet {

    //De esta forma siempre que se lanza nuestro servlet el mapa se quedara instanciado y con los valores que haya en el fichero
    @Override
    public void init() throws ServletException {
        
        HashMap <String, Comida> mapComidas = new HashMap <String, Comida>();
        
        try {
            String nomFich = this.getServletContext().getInitParameter("ficherocomidas");
            String url = this.getServletContext().getRealPath(nomFich);
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(url));
            Comida c = (Comida) ois.readObject();
            while(c!=null){
                System.err.println("Leido ");
                mapComidas.put(c.getNombre(), c);
                c = (Comida) ois.readObject();
            }
            
            ois.close();
        } 
        catch (IOException ex) {
        }
        catch(ClassNotFoundException ex2){ 
        }
        
        //Establecer el mapa como atributo de la aplicacion
        this.getServletContext().setAttribute("mapa", mapComidas);
    }
    
    
    private void verTablaPedido(PrintWriter out, HttpServletRequest request){
        
        HashMap <String, Comida> mapComidas = (HashMap <String, Comida>) this.getServletContext().getAttribute("mapa");
        HttpSession s = request.getSession();
        ArrayList <Comida> lstComidaSesion = (ArrayList <Comida>) s.getAttribute("pedido");
        
        
        out.print("<table>");
        
        Iterator <String> it = mapComidas.keySet().iterator();
        while(it.hasNext()){
            
            String nombre = it.next();
            Comida comida = mapComidas.get(nombre);
            
            out.print("<tr>");
                out.print("<td>" + nombre + "</td>");
                out.print("<td>" + comida.getPrecio() + "</td>");
               
                if(!lstComidaSesion.contains(comida)){
                    String enlace = request.getRequestURI() + "?nombre=" + nombre;
                    out.print("<td><a href='" + enlace + "'>Enlace</a></td>");
                }
                else{
                    out.print("<td>PEDIDO</td>");
                }

                       
            out.print("</tr>");
        }
                        
        /*if(lstComidaSesion.size()>0){
            for(Comida c : lstComidaSesion){
                out.print("<p>" + c.toString() + "</p>");
            }
        }*/
        out.print("</table>");
        
    }
  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HashMap <String, Comida> mapComidas = (HashMap <String, Comida>) this.getServletContext().getAttribute("mapa");
        
        HttpSession s = request.getSession();
        if(s.getAttribute("pedido")==null){
            s.setAttribute("pedido", new ArrayList<Comida>());
        }
        
        ArrayList <Comida> lstComidaSesion = (ArrayList <Comida>) s.getAttribute("pedido");
        if(request.getParameter("nombre")!=null){
            lstComidaSesion.add(mapComidas.get(request.getParameter("nombre")));
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                out.println("<title>Pedido</title>");            
            out.println("</head>");
            out.println("<body>");
                //out.print("<p>cucu</p>");
                verTablaPedido(out, request);
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    


}
