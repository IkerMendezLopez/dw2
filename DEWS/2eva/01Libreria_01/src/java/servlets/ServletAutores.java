/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.Autor;
import dao.GestorBD;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Méndez
 */
@WebServlet(name = "ServletAutores", urlPatterns = {"/autores"})
public class ServletAutores extends HttpServlet {
private GestorBD bd;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        if(request.getParameter("aniadir")!=null){
            ses.setAttribute("aniadido", bd.hacerPrestamo(Integer.parseInt(request.getParameter("aniadir"))));
            String nombre = request.getParameter("nombreAutor");
            ses.setAttribute("autor", nombre);   
            request.getRequestDispatcher("autor.jsp").forward(request, response);
        }else{
            ses.setAttribute("aniadido", null);
            if(request.getParameter("idAutor")!=null){
                String nombre = request.getParameter("nombreAutor");
                ses.setAttribute("autor", nombre);
                ses.setAttribute("librosAutor", bd.librosDeAutor(Integer.parseInt(request.getParameter("idAutor"))));
                request.getRequestDispatcher("autor.jsp").forward(request, response);
            }else{
//                request.setAttribute("errorAniadir", null);
                ses.setAttribute("autores", bd.getAutores());
                request.getRequestDispatcher("autores.jsp").forward(request, response);
            }   
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         if(request.getParameter("nuevoAutor")==null){
             System.out.println("#----------->Hola he entrado");
             doGet(request,response);
         }else{
             if(request.getParameter("nombre").equals("") ||
                request.getParameter("fecha").equals("")||
                request.getParameter("nacionalidad").equals("")){
                request.setAttribute("errorAniadir", "Hay campos sin rellenar");
             }else{
                if(bd.existeAutor(request.getParameter("nombre"))){
                    request.setAttribute("errorAniadir", "El autor "+ request.getParameter("nombre") + " ya existe");
                }else{
                   Autor autorNuevo = new Autor();
                   autorNuevo.setNombre(request.getParameter("nombre"));
                   autorNuevo.setNacionalidad(request.getParameter("nacionalidad"));
                   Date fecha = formatoFecha(request.getParameter("fecha"));
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                   System.out.println(sdf.format(fecha));
                   if(fecha==null){
                       request.setAttribute("errorAniadir", "Formato de fecha erroneo");
                   }else{
                        Date d =fecha;
                        autorNuevo.setFechanac(d);
                        if(!bd.aniadirAutor(autorNuevo)){
                            request.setAttribute("errorAniadir", "Error en los datos");
                        }else{
                            request.setAttribute("errorAniadir", "Se a añadido el autor " + autorNuevo.getNombre());
                        }
                   }
                }
             }
         }
         request.setAttribute("nuevoAutor", null);
         request.getRequestDispatcher("autores.jsp").forward(request, response);
    }
    
    private Date formatoFecha(String fecha){
        String[] fechaPartes;
        if(fecha.contains("/")){
            fechaPartes = fecha.split("/");
        }else if(fecha.contains("-")){
            fechaPartes = fecha.split("-");
        }else if(fecha.contains(".")){
            fechaPartes = fecha.split(".");
        }else{
            return null;
        }
        int anio = Integer.parseInt(fechaPartes[0]);
        int mes = Integer.parseInt(fechaPartes[1]);
        int dia = Integer.parseInt(fechaPartes[2]);
        if(anio>2022 || mes<1 || mes>12 ||dia<1 ||dia>31){
            return null;
        }else{
            return new Date((anio-1900), (mes-1), dia);
        }
    }

//FECHA
//Input type date
    
//    public boolean isValidDate(String dateStr) {
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.setLenient(false);
//        try {
//            sdf.parse(dateStr);
//            return true;
//        } catch (ParseException e) {
//            System.out.println("invalid date");
//        }
//        return false;
//
//    }
    
//    author.setFechanac(Date.valueOf(fechaNac));
}
