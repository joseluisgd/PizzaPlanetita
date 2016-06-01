package ulima.edu.pe.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.dao.IngredienteDAO;
import ulima.edu.pe.dao.ProductoDAO;
import ulima.edu.pe.dao.TamanoDAO;

public class ServletIngrediente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession(true);

        IngredienteDAO daoIngrediente = new IngredienteDAO();
        ses.setAttribute("ingredientes", daoIngrediente.getIngredientes());
        
        ProductoDAO daoProducto = new ProductoDAO();
        ses.setAttribute("productos", daoProducto.getProductos());
        //Agregue esta parte -----
        TamanoDAO daoTamano= new TamanoDAO();
        ses.setAttribute("tamanos", daoTamano.getTamanos());
        //-----
        RequestDispatcher rd = request.getRequestDispatcher("crearPedido.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
