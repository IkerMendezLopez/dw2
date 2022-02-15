/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.CarroCompra;
import beans.Cliente;
import beans.LineaPedido;
import beans.Pedido;
import dao.ClienteDAO;
import dao.KeysDAO;
import dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletGrabarCompra extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletGrabarCompra</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletGrabarCompra at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitCompra")==null){
            response.sendRedirect("login.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s=request.getSession();
        CarroCompra ccompra=(CarroCompra)s.getAttribute("carroCompra");
        if(request.getParameter("submitCompra")!=null){
            if(ccompra.vacio()){
                response.sendRedirect("tienda.jsp?carrovacio");
            }
            else{
                //Actualiza el cliente
                Cliente cli=(Cliente)s.getAttribute("usuario");

                cli.setCodigopostal(request.getParameter("codigopostal"));
                cli.setDomicilio(request.getParameter("domicilio"));
                cli.setTelefono(request.getParameter("telefono"));
                cli.setEmail(request.getParameter("email"));
                
                ClienteDAO.actualizaCliente(cli);
                
                s.setAttribute("usuario", cli);
                
                //Crea un pedido y lo guarda
                Pedido p=new Pedido();
                int idpedido=KeysDAO.siguienteId("pedidos");
                double total=Double.parseDouble(request.getParameter("totalcompra"));
                Date fechahoy=new Date();
                p.setId(idpedido);
                p.setTotal(total);
                p.setFecha(fechahoy);
                p.setCliente(cli);
                
                PedidoDAO.guardarPedido(p);
                
                //Crea las LineaPedido necesarias y las guarda
                Collection <LineaPedido> lp=(Collection<LineaPedido>)ccompra.getLineasPedido();
                for(LineaPedido linea : lp){
                    linea.setId(KeysDAO.siguienteId("lineaspedido"));
                    linea.setPedido(p);
                    PedidoDAO.guardarLineaPedido(linea);
                }
                
                response.sendRedirect("gracias.jsp");
            }
        }
    }

}
