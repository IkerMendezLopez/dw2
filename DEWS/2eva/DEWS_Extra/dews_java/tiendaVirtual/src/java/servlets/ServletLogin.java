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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitLogin")!=null || request.getParameter("submitReset")!=null){
            HttpSession session = request.getSession();
            
            if(request.getParameter("submitLogin")!=null){
                String username = request.getParameter("usuario");
                String password = request.getParameter("password");
            
                if(!username.equals("") && !password.equals("")){
                    ClienteDAO cliDAO = new ClienteDAO();
                    
                    if(cliDAO.buscaCliente(username)){
                        Cliente c = cliDAO.buscaCliente(username, password);
                        
                        if(c!=null){
                            PedidoDAO pedDAO = new PedidoDAO();
                            HashMap <Integer, Item> mapaItems = pedDAO.todosItems();
                            
                            session.setAttribute("cliente", c);
                            session.setAttribute("itemsEnVenta", mapaItems);
                            request.getRequestDispatcher("tienda.jsp").forward(request, response);
                        }
                        else{
                            request.setAttribute("userLogin", username);
                            request.setAttribute("errorLogin", "*Su contraseña es incorrecta, pruebe otra vez!");
                        }
                    }
                    else{
                        request.setAttribute("errorLogin", "*Cliente inexistente registrese primero!");
                    }
                }
                else{
                    request.setAttribute("errorLogin", "*Introduzca valores para Usuario/Contraseña!"); 
                }
            }
        }
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
