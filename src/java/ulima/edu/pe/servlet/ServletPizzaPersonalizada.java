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
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.dao.IngredienteDAO;
import ulima.edu.pe.dao.AdicionalDAO;

public class ServletPizzaPersonalizada extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession(true);

        IngredienteDAO daoIngrediente = new IngredienteDAO();
        ses.setAttribute("ingredientes", daoIngrediente.obtenerIngredientes());
        
        AdicionalDAO daoProducto = new AdicionalDAO();
        ses.setAttribute("productos", daoProducto.obtenerAdicionales());
//        ses.setAttribute("productos", daoProducto.obtenerAdicionales());
        //Agregue esta parte -----
        List<Tamano> tamanos = new ArrayList<>();
        Tamano t = new  Tamano();
        tamanos.add(new Tamano(1, t.NOMBRE_ID1));
        tamanos.add(new Tamano(2, t.NOMBRE_ID2));
        tamanos.add(new Tamano(3, t.NOMBRE_ID3));
        tamanos.add(new Tamano(4, t.NOMBRE_ID4));
   
        //ChF: Los nombres de tamaños y demás ahora se obtienen mediante la clase Tamano
        ses.setAttribute("tamanos", tamanos);
        //-----
        RequestDispatcher rd = request.getRequestDispatcher("crearPedidoPersonalizado.jsp");
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
