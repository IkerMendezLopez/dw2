<%-- 
    Document   : contratar
    Created on : 07-ene-2022, 7:00:41
    Author     : Akaitz
--%>

<%@page import="beans.Trabajador"%>
<%@page import="beans.TelefonoMovil"%>
<%@page import="beans.Empresa"%>
<%
    Empresa e = (Empresa)session.getAttribute("empresa");
    if(request.getParameter("nuevotrab") != null){
        TelefonoMovil movil = new TelefonoMovil(request.getParameter("numero"),
                Integer.parseInt(request.getParameter("bateria")));
        Trabajador trab = new Trabajador(movil, request.getParameter("nombre"), 
                request.getParameter("dni"));
        request.setAttribute("ultimo_trabajador", trab);
        if(!e.contratar(trab)){
%>
            <jsp:forward page="trabajadores.jsp">
                <jsp:param name="error" value="Empresa no admite más trabajadores."/>
            </jsp:forward>
<%
        }else{
%>
            <jsp:forward page="trabajadores.jsp"/>
<%
        }
    }else{
%>
        <jsp:forward page="trabajadores.jsp"/>
<%
    }
%>