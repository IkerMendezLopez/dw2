package servlets;

import beans.Cliente;
import beans.Item;
import dao.ClienteDAO;
import dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitLogin")!=null){
            HttpSession s=request.getSession(true);
            s.invalidate();
            s=request.getSession();
            if(request.getParameter("nomcli").equals("")||request.getParameter("pass").equals("")){
                s.setAttribute("err", "Error, ningún campo puede estar vacío");
                response.sendRedirect("login.jsp");    
            }
            else{
                String nomcli=(String)request.getParameter("nomcli");
                String pass=(String)request.getParameter("pass");
                Cliente c=ClienteDAO.buscaCliente(nomcli, pass);
                if(c!=null){
                    s.setAttribute("usuario", c);
                    HashMap<Integer, Item> itemsD=(HashMap<Integer,Item>)PedidoDAO.todosItems();
                    s.setAttribute("itemsDisponibles", itemsD);
                    response.sendRedirect("tienda.jsp");
                }
                else{
                    s.setAttribute("err", "Error, el usuario no existe. Comprueba que lo has escrito correctamente");
                    response.sendRedirect("login.jsp");
                } 
            }
        }
        else{
            response.sendRedirect("login.jsp");
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
