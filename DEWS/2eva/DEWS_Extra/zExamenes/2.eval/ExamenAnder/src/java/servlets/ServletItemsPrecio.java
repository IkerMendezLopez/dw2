package servlets;

import beans.Item;
import dao.DaoExam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletItemsPrecio extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getAttribute("minPrecio")==null){
            double [] arrPrecios = DaoExam.preciosMinMax();
            request.setAttribute("minPrecio", arrPrecios[0]);
            request.setAttribute("maxPrecio", arrPrecios[1]);
        }
        
        
        if(request.getParameter("submitVerItems")!=null){
            try{
                double minPrecio = Double.parseDouble(request.getParameter("minPrecio"));
                double maxPrecio = Double.parseDouble(request.getParameter("maxPrecio"));
                
                HashSet <Item> lstItems = DaoExam.itemsPorPrecio(minPrecio, maxPrecio);
                request.getSession().setAttribute("lstItems", lstItems);
                
                request.setAttribute("minPrecio", minPrecio);
                request.setAttribute("maxPrecio", maxPrecio);
            }
            catch(NumberFormatException e){
                request.setAttribute("errorPrecios", "¡¡¡Debes rellenar minimo y maximo con valores numericos!!!");
            }
            catch(NullPointerException e){
                request.setAttribute("errorPrecios", "¡¡¡Debes rellenar minimo y maximo con valores numericos!!!");
            }
        }
        
        
        request.getRequestDispatcher("selectItems.jsp").forward(request, response);
    }

    


}
