package servlets;

import beans.Cliente;
import dao.ClienteDAO;
import dao.KeysDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletRegistro extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitRegistrarse")!=null || request.getParameter("submitReset")!=null){
            
            if(request.getParameter("submitRegistrarse")!=null){
                int maxClientesParametro = Integer.parseInt(this.getInitParameter("maxClientes")); //FALTA DEFINIR EL MAXIMO DE CLIENTES
                
                if(KeysDAO.cantClientes()<maxClientesParametro){ 
                   
                    String username = request.getParameter("usuario");
                    String password = request.getParameter("password");
                    String domicilio = request.getParameter("domicilio");
                    String codPostal = request.getParameter("codigoPostal");
                    String telefono = request.getParameter("telefono");
                    String email = request.getParameter("email");
                
                    if(!username.equals("") && !password.equals("") && !domicilio.equals("") && !codPostal.equals("") && !telefono.equals("") && !email.equals("")){ 
                        ClienteDAO cliDAO = new ClienteDAO();
                        
                        if(cliDAO.buscaCliente(username)){
                            request.setAttribute("errorRegistro", "*El nombre de usuario ya esta en uso!"); 
                        }
                        else{
                            Cliente c = new Cliente();
                            
                            c.setNombre(username);
                            c.setPassword(password);
                            c.setDomicilio(domicilio);
                            c.setCodigopostal(codPostal);
                            c.setTelefono(telefono);
                            c.setEmail(email);
                            
                            if(cliDAO.guardaCliente(c)){
                                request.setAttribute("registrado", "Enhorabuena, cliente registrado!");
                                request.getRequestDispatcher("login.jsp").forward(request, response); 
                            }
                            else{
                                request.setAttribute("errorRegistro", "*Ha ocurrido un error inesperado!");
                            }
                        }
                    }
                    else{
                        request.setAttribute("errorRegistro", "*Introduzca valores para todos los campos!"); 
                    }
                }
                else{
                    request.setAttribute("errorRegistro", "*Limite de clientes superado en la BBDD!"); 
                }
            }
        }
        
        request.getRequestDispatcher("registro.jsp").forward(request, response);
    }

}
