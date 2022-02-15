package servlets;

import beans.Errores;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletCompra extends HttpServlet {
//PRUEBA COOKIES   
    private void enviarCookie(HttpServletRequest request, HttpServletResponse response){
        
        HttpSession sesion = request.getSession();
        HashMap <String, Integer> mapaProductos = (HashMap <String, Integer>) sesion.getAttribute("carrito");
        
        String maxVendido = "";
        int maxCant = Integer.MIN_VALUE;
        for(String clave : mapaProductos.keySet()){
            if(mapaProductos.get(clave)>maxCant){
                maxCant = mapaProductos.get(clave);
                maxVendido = clave;
            }
        }
        
        Cookie c = new Cookie("maxVentas", maxVendido);
        c.setMaxAge(300);
        response.addCookie(c);
    }

    private void dibujarCompra(HttpServletRequest request, PrintWriter out){
        
        ArrayList <String> lstProductos = (ArrayList <String>) this.getServletContext().getAttribute("productos");
        
        HttpSession sesion = request.getSession();
        HashMap <String, Integer> mapaProductos = (HashMap <String, Integer>) sesion.getAttribute("carrito");
        
        
        out.print("<table>");
            out.print("<tr>");
                out.print("<th>PRODUCTO</th>");
                out.print("<th>CANTIDAD</th>");
                out.print("<th>AÑADIDOS</th>");
                out.print("<th></th>");
            out.print("</tr>");
                
            for(String prod : lstProductos){
                out.print("<tr>");
                
                    out.print("<form method='post' action='" + request.getRequestURI() + "'>"); 
                        out.print("<td>" + prod + "</td>");
                        out.print("<input type='hidden' name='nomProd' value='" + prod + "'/>");
                        out.print("<td><input type='number' name='cantidad' size='5'/></td>");
                        
                        if(mapaProductos.containsKey(prod)){
                            out.print("<td>" + mapaProductos.get(prod) + "</td>");
                        }
                        else{
                            out.print("<td>0</td>");
                        }
                        
                        out.print("<td><input type='submit' name='submitAniadir' value='AÑADIR'/></td>");
                        
                        if(request.getAttribute("error")!=null && request.getAttribute("error").equals(prod)){
                            out.print("<td>*Introduzca un valor numerico!</td>");
                        }
                        
                        /*
                        //PRUEBA PARA MOSTRAR ERRORES CON UNA CLASE
                        if(request.getAttribute("error")!=null){
                            Errores err = (Errores) request.getAttribute("error");
                            
                            if(err.getNomProd().equals(prod)){
                                out.print("<td>" + err.getError() + "</td>");
                            }
                        }*/
                        
                    out.print("</form>");
                out.print("</tr>");
            }
            
        out.print("</table>");
        
        String enlace = request.getRequestURI() + "?finaliza";
        out.print("<a href='" + enlace + "'>Finalizar Sesion</a>");
        
        
        
        /*//PRUEBA COOKIES
        Cookie [] c = request.getCookies();
        out.print("<p>" + c[0].getValue() +"</p>");*/
    }
  
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        
        
        if(request.getParameter("finaliza")!=null){
            //enviarCookie(request, response); //MAL, TAMBIEN HAY QUE GUARDAR EN UN MAPA DE APLICACION EL PRODUCTO MAS VENDIDO DE CADA SESION, DE FORMA QUE CUANDO SE MANDE UNA COOKIE EL ADMINISTRADO MUESTRE EL PRODUCTO MAS SOLICITADO
            sesion.invalidate();
            response.sendRedirect("ServletInicio");
        }
        else{
            if(sesion.getAttribute("carrito")==null){
                sesion.setAttribute("carrito", new HashMap<String, Integer>());
            }
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Compra</title>");            
                out.println("</head>");
                out.println("<body>");

                    dibujarCompra(request, out);


                out.println("</body>");
                out.println("</html>");
            }
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        HashMap <String, Integer> mapaProductos = (HashMap <String, Integer>) sesion.getAttribute("carrito");
        
        
        if(request.getParameter("submitAniadir")!=null){
            try{
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                String nomProd = request.getParameter("nomProd");
                
                if(!mapaProductos.containsKey(nomProd)){
                    mapaProductos.put(nomProd, cantidad);
                    System.err.println("holaaa");
                }
                else{
                    int cantNueva = mapaProductos.get(nomProd) + cantidad;
                    mapaProductos.put(nomProd, cantNueva);
                }
            }
            catch(NumberFormatException ex){
                request.setAttribute("error", request.getParameter("nomProd"));
                /*
                //PRUEBA PARA MOSTRAR ERRORES CON UNA CLASE
                Errores err = new Errores(request.getParameter("nomProd"), "*Introduzca un valor NUMERICO!");
                request.setAttribute("error", err);
                */
            }
        }

        doGet(request, response);
    }

    


}
