package ulima.edu.pe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.Producto;
import ulima.edu.pe.dao.IngredienteDAO;
import ulima.edu.pe.dao.PedidoPersonalizadoDAO;
import ulima.edu.pe.dao.ProductoDAO;

public class ServletPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);

        String ingredientes[] = request.getParameterValues("ingredientes");
        String productos[] = request.getParameterValues("productos");
        
        
        //Productos
        ProductoDAO dao1= new ProductoDAO();
        List<Producto> listaProd = new ArrayList<Producto>();
        for (int i = 0; i < productos.length; i++) {
            listaProd.add(dao1.buscarProducto(Integer.parseInt(productos[i])));
        }
        ses.setAttribute("productosIngresados", listaProd);
        
        //Ingredientes
        IngredienteDAO dao2 = new IngredienteDAO();
        List<Ingrediente> listaIngre = new ArrayList<>();
        for (int i = 0; i < ingredientes.length; i++) {
            listaIngre.add(dao2.BuscarIngrediente((Integer.parseInt(ingredientes[i]))));
        }
        ses.setAttribute("ingredientesIngresados", listaIngre);
        //usuario ???

        PedidoPersonalizadoDAO dao = new PedidoPersonalizadoDAO();

        dao.ingresarPedidoxUsuario(listaIngre, (String) ses.getAttribute("username"));

        RequestDispatcher rd = request.getRequestDispatcher("pedidoingresado.jsp");

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
