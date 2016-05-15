package ulima.edu.pe.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.beans.Estado;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.Pizza;
import ulima.edu.pe.beans.Producto;
import ulima.edu.pe.dao.PedidoDAO;

public class ServletPedidoIngresado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession(true);
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        Date date = new Date();
        //estado
        Estado estado = new Estado();
        estado.setHora((String)dateFormat.format(date));
        estado.setEstado("En cola");
        
        //usuario
        String usuario = (String) ses.getAttribute("username");
        String direccion = request.getParameter("direccion");
        
        
        //ingredientes
        List<Ingrediente> ingr = (List<Ingrediente>) ses.getAttribute("ingredientesIngresados");
        //precio
        float precio=0.0f;
        for (Ingrediente ingrediente : ingr) {
            precio*=precio;
        }
        //pizza
        List<Pizza> pizza = new ArrayList<>();
        pizza.add(new Pizza(precio, ingr));
        
        //productos
        List<Producto> prod= (List<Producto>)ses.getAttribute("productosIngresados");
        
        
        PedidoDAO dao10= new PedidoDAO();
        dao10.agregar(estado, usuario, direccion, pizza, prod);
        
        
        
        //rd
        RequestDispatcher rd = request.getRequestDispatcher("EXITOS.html");

        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
