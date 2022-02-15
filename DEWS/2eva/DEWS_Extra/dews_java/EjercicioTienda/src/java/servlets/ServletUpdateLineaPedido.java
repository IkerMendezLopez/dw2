/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.CarroCompra;
import beans.LineaPedido;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUpdateLineaPedido extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("submitBorrar")==null && request.getParameter("submitCambiar")==null){
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion="";
        int iditem=0;
        HttpSession s=request.getSession();
        CarroCompra c=(CarroCompra)s.getAttribute("carroCompra");
        if(request.getParameter("submitBorrar")!=null){
            iditem=Integer.parseInt(request.getParameter("iditem"));
            c.borrarlinea(iditem);
            accion="borrar";
        }
        else{
            iditem=Integer.parseInt(request.getParameter("iditem"));
            System.out.print(iditem);
            LineaPedido lp=c.getLineaPedido(iditem);
            int cantidad=Integer.parseInt(request.getParameter("cantidad"));
            lp.setCantidad(cantidad);
            accion="cambiar";
        }
        response.sendRedirect("listar_cesta.jsp?accion="+accion+"&iditem="+iditem);
    }
}
