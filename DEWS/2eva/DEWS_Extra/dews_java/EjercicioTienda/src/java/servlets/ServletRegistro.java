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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitRegistro")==null){
            response.sendRedirect("login.jsp");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitRegistro")!=null){
            if(request.getParameter("nombre").equals("") || request.getParameter("password").equals("") || request.getParameter("domicilio").equals("")
                    || request.getParameter("codigopostal").equals("") || request.getParameter("telefono").equals("") || request.getParameter("email").equals("")){
                response.sendRedirect("registro.jsp?err");
            }
            else{
                String nombre=request.getParameter("nombre");
                if(ClienteDAO.buscaCliente(nombre) || ClienteDAO.cuentaClis()>=Integer.parseInt(getServletContext().getInitParameter("maxCli"))){
                    response.sendRedirect("registro.jsp?err");
                }
                else{
                    int id=KeysDAO.siguienteId("clientes");
                    Cliente c=new Cliente(id,request.getParameter("nombre"),request.getParameter("password"),
                            request.getParameter("domicilio"),request.getParameter("codigopostal"),request.getParameter("telefono"), request.getParameter("email"));
                    if(ClienteDAO.guardaCliente(c)){
                        response.sendRedirect("login.jsp?registrado");
                    }
                }
            }
        }
    }

}
