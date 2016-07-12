package ulima.edu.pe.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPizzaPersonalizada extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ChF: Comentado porque jode:
/*
        HttpSession ses = request.getSession(true);

        IngredienteDAO daoIngrediente = new IngredienteDAO();
        ses.setAttribute("ingredientes", daoIngrediente.obtenerIngredientes());
        
        AdicionalDAO daoProducto = new AdicionalDAO();
        ses.setAttribute("productos", daoProducto.getProductos());
//        ses.setAttribute("productos", daoProducto.obtenerAdicionales());
        //Agregue esta parte -----
        TamanoDAO daoTamano= new TamanoDAO();
        //ChF: Los nombres de tamaños y demás ahora se obtienen mediante la clase Tamano
        ses.setAttribute("tamanos", daoTamano.getTamanos());
        //-----
        RequestDispatcher rd = request.getRequestDispatcher("crearPedidoPersonalizado.jsp");
        rd.forward(request, response);*/
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
