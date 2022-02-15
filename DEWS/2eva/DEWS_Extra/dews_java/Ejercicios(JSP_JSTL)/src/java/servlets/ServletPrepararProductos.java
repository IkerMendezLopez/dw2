package servlets;

import java.io.BufferedReader;
import java.io.File;
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
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;


public class ServletPrepararProductos extends HttpServlet {

    private ArrayList <String> leeFich(String categoria){
        
        boolean estadoCategoria = false;
        if(!categoria.equals("")){
            estadoCategoria = true;
        }
        
        
        String nomFich = this.getInitParameter("fichProductos");
        String urlFich = this.getServletContext().getRealPath(nomFich);
        ArrayList <String> lstProductos = new ArrayList<String>();
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(new File(urlFich)));
            
            String linea = br.readLine();
            while(linea!=null){
                
                String [] arrProdActual = linea.split(";");
                String cat = arrProdActual[0];
                String nombre = arrProdActual[1];
                if(estadoCategoria){
                    if(cat.equals(categoria)){
                        lstProductos.add(nombre);
                    }
                }
                else{
                    lstProductos.add(nombre);
                }

                
                linea = br.readLine();
            }
            
            br.close();
        } 
        catch (FileNotFoundException ex) { System.err.print(urlFich);} 
        catch (IOException ex) {}
        

        return lstProductos;
    }
   
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String categoria = "";
        if(request.getParameter("categ")!=null){
            categoria = request.getParameter("categ");
        }
        
        if(session.getAttribute("lstSession")==null){
            ArrayList <String> lstProductos = leeFich(categoria);
            session.setAttribute("lstSession", lstProductos);
        }
        
        
        response.sendRedirect("compra.jsp");
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    


}
