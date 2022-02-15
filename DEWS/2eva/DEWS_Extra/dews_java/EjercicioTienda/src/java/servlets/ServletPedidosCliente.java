package servlets;

import beans.Cliente;
import beans.Pedido;
import dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletPedidosCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s=request.getSession();
   
        Cliente c=(Cliente)s.getAttribute("usuario");

        Map<Integer, Pedido> mapaPedidos=PedidoDAO.todosPedidos(c);
        
        c.setMapaPedidos((HashMap<Integer, Pedido>) mapaPedidos);
        
        response.sendRedirect("listarPedidosCliente.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitAnular")!=null){
            String[] pedidosAnular=request.getParameterValues("anular");
            for(String str : pedidosAnular){
                PedidoDAO.borrarPedido(Integer.parseInt(str));
            }
        }
        response.sendRedirect("ServletPedidosCliente");
    }
}
