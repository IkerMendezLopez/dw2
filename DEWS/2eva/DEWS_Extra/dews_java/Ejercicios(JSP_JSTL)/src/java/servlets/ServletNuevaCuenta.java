package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletNuevaCuenta extends HttpServlet {
    
    

    @Override
    public void init() throws ServletException {
        
        ArrayList <String> lstUsersProhibidos = new ArrayList<String>();
        
        lstUsersProhibidos.add("Pepe");
        lstUsersProhibidos.add("Jokin");
        lstUsersProhibidos.add("Juan");
        lstUsersProhibidos.add("Unai");
        
        this.getServletContext().setAttribute("lstProhibidos", lstUsersProhibidos);
    }
    
    
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("submitCrearCuenta")!=null){
            ArrayList <String> lstErrores = new ArrayList<String>();
            
            String titular = request.getParameter("titular");
            ArrayList <String> lstProhibidos = (ArrayList <String>) this.getServletContext().getAttribute("lstProhibidos");
                

            if(titular.equals("")){
                lstErrores.add("*Introduzca un valor para el TITULAR!");
            }
            
            if(request.getParameter("saldo").equals("")){
                lstErrores.add("*Introduzca un valor para el SALDO!");
            }
            else{
                double saldo = Double.parseDouble(request.getParameter("saldo"));
                
                if(saldo<0){
                    lstErrores.add("*El valor del saldo no puede ser NEGATIVO!");
                }
            }
            
            if(lstProhibidos.contains(titular)){
                lstErrores.add("*Dicho titular no esta autorizado para crear una cuenta!");
            }
            
            
            request.setAttribute("errores", lstErrores);
            this.getServletContext().getRequestDispatcher("/nuevacuenta.jsp");
        }
    }

    


}
