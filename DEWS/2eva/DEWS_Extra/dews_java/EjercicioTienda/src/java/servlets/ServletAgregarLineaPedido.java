package servlets;

import beans.CarroCompra;
import beans.Item;
import beans.LineaPedido;
import dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletAgregarLineaPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAgregarLineaPedido</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletAgregarLineaPedido at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitAniadir")==null){
            response.sendRedirect("login.jsp");
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s=request.getSession();
        
        if(s.getAttribute("carroCompra")==null){
            s.setAttribute("carroCompra", new CarroCompra());
        }
        CarroCompra c=(CarroCompra)s.getAttribute("carroCompra");
        if(request.getParameter("submitAniadir")!=null && !request.getParameter("cantidad").equals("0")){
            int iditem=Integer.parseInt(request.getParameter("iditem"));
            Item i=PedidoDAO.buscarItem(iditem);
            int cant=Integer.parseInt(request.getParameter("cantidad"));
            LineaPedido l=new LineaPedido();
            l.setItempedido(i);
            l.setCantidad(cant);
            c.aniadeLinea(l);
            s.setAttribute("carroCompra",c);
            //No es necesario hacer un set porque al hacer un get siempre arriba cogemos la referencia y se actualiza en la sesión automáticamente
        }
        response.sendRedirect("tienda.jsp");
    }
}