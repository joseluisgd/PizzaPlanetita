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

        String productos[] = request.getParameterValues("productos");
        String ingredientes[] = request.getParameterValues("ingredientes");
        
        //Productos
        ProductoDAO daoProducto= new ProductoDAO();
        List<Producto> listaProductos = new ArrayList<>();
        for (String producto : productos) {
            listaProductos.add(daoProducto.buscarProducto(Integer.parseInt(producto)));
        }
        ses.setAttribute("productosIngresados", listaProductos);
        
        //Ingredientes
        IngredienteDAO daoIngrediente = new IngredienteDAO();
        List<Ingrediente> listaIngredientes = new ArrayList<>();
        for (String ingrediente : ingredientes) {
            listaIngredientes.add(daoIngrediente.buscarIngrediente((Integer.parseInt(ingrediente))));
        }
        ses.setAttribute("ingredientesIngresados", listaIngredientes);
        //usuario ???

        PedidoPersonalizadoDAO daoPedidoPersonalizado = new PedidoPersonalizadoDAO();
        daoPedidoPersonalizado.ingresarPedidoxUsuario(listaIngredientes, String.valueOf(ses.getAttribute("username")));

        RequestDispatcher rd = request.getRequestDispatcher("pedidoingresado.jsp");
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
