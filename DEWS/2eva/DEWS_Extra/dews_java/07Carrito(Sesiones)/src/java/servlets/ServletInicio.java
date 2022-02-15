package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletInicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ArrayList <String> lstProductos = new ArrayList<String>();
        boolean creado = true;
        
        String nomFich = this.getServletContext().getInitParameter("fichproductos");
        String ruta = this.getServletContext().getRealPath(nomFich);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            
            String linea = br.readLine();
            while(linea!=null){
                
                lstProductos.add(linea);
                linea = br.readLine();
            }
            br.close();
            
            if(lstProductos.isEmpty()){
                creado = false;
            }
        } 
        catch (FileNotFoundException ex){
            creado = false;
        }
        catch(NullPointerException ex){
            creado = false;
        }
        catch(IOException ex) {
            creado = false;
        }
        
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Inicio</title>");            
            out.println("</head>");
            out.println("<body>");
            
                if(creado){
                    this.getServletContext().setAttribute("productos", lstProductos);
                    
                    response.sendRedirect("ServletCompra");
                }
                else{
                    out.print("<h3 style='color: red'>*ERROR NO SE HA CREADO LA LISTA!</h3>");
                }
                
            out.println("</body>");
            out.println("</html>");
        }
    } 
    
    
   
}
