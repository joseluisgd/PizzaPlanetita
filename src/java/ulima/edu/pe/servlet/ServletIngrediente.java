package ulima.edu.pe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.dao.IngredienteDAO;
import ulima.edu.pe.dao.ProductoDAO;

public class ServletIngrediente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession(true);

        IngredienteDAO daoIngrediente = new IngredienteDAO();
        ses.setAttribute("ingredientes", daoIngrediente.getIngredientes());
        
        ProductoDAO daoProducto = new ProductoDAO();
        ses.setAttribute("productos", daoProducto.getProductos());

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
