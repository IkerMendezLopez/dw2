/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Libro;
import dao.GestorBD;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akaitz
 */
@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {
    private GestorBD bd;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("libros", bd.libros());
        request.getSession().setAttribute("autores", bd.autores());
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        //Para evitar problemas con caracteres especiales
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("insertar") == null){
            doGet(request, response);
        }else{
            if(request.getParameter("titulo").equals("") || 
                    request.getParameter("paginas").equals("") || 
                    request.getParameter("genero").equals("")){
                request.setAttribute("errorinsercion", "Hay que rellenar todos los datos");
            }else{
                try{
                    //Recuperamos los datos del formulario y creamos un objeto de tipo libro.
                    //Este objeto no tendrá ID hasta que se inserte en la base de datos
                    String titulo = request.getParameter("titulo");
                    int paginas = Integer.parseInt(request.getParameter("paginas"));
                    String genero = request.getParameter("genero");
                    int autor = Integer.parseInt(request.getParameter("idautor"));
                    
                    Libro libro = new Libro(titulo, paginas, genero, autor);
                    
                    //Si el libro ya existe no se inserta en la base de datos
                    boolean existe = bd.existeLibro(libro);
                    if(existe){
                        request.setAttribute("errorinsercion", "El libro " 
                                + libro.getTitulo() + " ya existe");
                    }else{
                        int id = bd.insertarLibro(libro);
                        
                        if(id == -1){
                            request.setAttribute("errorinsercion", "No se ha podido insertar el libro");
                            request.setAttribute("libroerroneo", libro);
                        }else{
                            //Si no ha habido ningún problema se añade el ID al 
                            //al objeto libro y se añadae el nuevo libro a la 
                            //seión
                            libro.setIdLibro(id);
                            ((ArrayList<Libro>)request.getSession().getAttribute("libros")).add(libro);
                        }
                    }
                }catch(NumberFormatException e){
                    request.setAttribute("errorinsercion", "Numero de páginas erroneo");
                }
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
