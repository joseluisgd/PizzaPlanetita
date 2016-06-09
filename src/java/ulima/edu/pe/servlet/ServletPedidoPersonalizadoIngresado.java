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
import ulima.edu.pe.beans.Estado;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.Pizza;
import ulima.edu.pe.beans.Producto;
import ulima.edu.pe.beans.Pedido;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.dao.LoginDAO;
import ulima.edu.pe.dao.PedidoDAO;
import ulima.edu.pe.util.Util;

public class ServletPedidoPersonalizadoIngresado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession(true);
        
        //estado
        Estado estado = new Estado();
        estado.setFechaHora(Util.obtenerFechaHoraActual());
        estado.setEstado("En cola");
        ses.setAttribute("estado", estado);
        
        //usuario
        String username = String.valueOf(ses.getAttribute("username"));
        LoginDAO logDao= new LoginDAO();
        String direccion = request.getParameter("direccion");
        
        ses.setAttribute("direccion", direccion);
        
        //ingredientes
        List<Ingrediente> ingr = (List<Ingrediente>) ses.getAttribute("ingredientesIngresados");
        //precio
        //falta corregir
        float precio = 2.0f;
        for (Ingrediente ingrediente : ingr) {
            precio *= precio;
        }
        ses.setAttribute("precio", precio);
        
        //pizza
        List<Pizza> pizzas = new ArrayList<>();
        Tamano t = (Tamano)ses.getAttribute("tamanoIngresado");
        pizzas.add(new Pizza(ingr,t.getNombre(),precio));    
        ses.setAttribute("pizza", pizzas);
        
        //productos
        List<Producto> productos = (List<Producto>) ses.getAttribute("productosIngresados");
        
        Pedido pedido = new Pedido();
        pedido.setEstado(estado);
        pedido.setUsuario(logDao.buscarUsuario(username));
        pedido.setDireccion(direccion);
        pedido.setPizzas(pizzas);
        pedido.setProductos(productos);
        
        PedidoDAO daoPedido = new PedidoDAO();
        daoPedido.agregarPedido(pedido);

        //rd
        RequestDispatcher rd = request.getRequestDispatcher("pedidoRegistrado.jsp");
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
