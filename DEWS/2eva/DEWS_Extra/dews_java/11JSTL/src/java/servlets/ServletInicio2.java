package servlets;

import beans.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletInicio2 extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HashMap <Persona, List <Persona>> mapaAmigos = new HashMap<Persona, List <Persona>>();
        
        ArrayList <Persona> lst1 = new ArrayList <Persona>();
            lst1.add(new Persona("Juan", 5, null));
            lst1.add(new Persona("Ana", 50, null));
            lst1.add(new Persona("Pepe", 51, null));
        ArrayList <Persona> lst2 = new ArrayList <Persona>();
            lst2.add(new Persona("Ibon", 25, null));
            lst2.add(new Persona("Hector", 20, null));
        
        
        mapaAmigos.put(new Persona("Nerea", 25, null), lst1);
        mapaAmigos.put(new Persona("Javier", 27, null), lst2);
        
        
        request.setAttribute("mapaAmigos", mapaAmigos);
        request.getRequestDispatcher("tablaAmigos.jsp").forward(request, response); //Si redireccionamos con sendRedirect no tenemos acceso al HashMap ya que es atributo del servlet
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    


}
