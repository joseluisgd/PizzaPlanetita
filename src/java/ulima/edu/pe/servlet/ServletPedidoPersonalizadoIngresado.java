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
//import ulima.edu.pe.beans.Estado;
//import ulima.edu.pe.beans.Ingrediente;
//import ulima.edu.pe.beans.Pizza;
//import ulima.edu.pe.beans.Producto;
//import ulima.edu.pe.beans.Pedido;
//import ulima.edu.pe.beans.Tamano;
//import ulima.edu.pe.dao.LoginDAO;
//import ulima.edu.pe.dao.PedidoDAO;
//ChF: Los imports sí los actualicé :v
import ulima.edu.pe.beans.pedido.Estado;
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.beans.producto.pizza.Pizza;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.pedido.ProductoPedido;
import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.util.Util;

public class ServletPedidoPersonalizadoIngresado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession(true);
        
        //estado
        Estado estado = new Estado();
        estado.setFechaHora(Util.obtenerFechaHoraActual());
        estado.setEstado("En cola");
//        estado.setId(1);
        ses.setAttribute("estado", estado);
        
        //usuario
        String username = String.valueOf(ses.getAttribute("username"));
        LoginDAO logDao= new LoginDAO();
        String direccion = request.getParameter("direccion");
        //ChF: Ahora se necesita tanto calle como distrito para llenar el objeto direccion
//        Direccion direccion = new Direccion();
//        direccion.setCalle("");
//        direccion.setDistrito("");
        ses.setAttribute("direccion", direccion);
        
        //ingredientes
        List<Ingrediente> ingr = (List<Ingrediente>) ses.getAttribute("ingredientesIngresados");
        //precio
        //falta corregir
        float precio = 2.0f;
        for (Ingrediente ingrediente : ingr) {
            precio *= precio;
        }
        //ChF: El siguiente método se debería ejecutar luego de tener cargada la lista de productos
        //del pedido (pizzas, adicionales, promociones)
        //pedido.calcularPrecioPedido();
        ses.setAttribute("precio", precio);
        
        //ChF: A partir de aquí ya no entiendo mucho :v
        
        //pizza
        List<Pizza> pizzas = new ArrayList<>();
        Tamano t = (Tamano)ses.getAttribute("tamanoIngresado");
      
        
        pizzas.add(new Pizza("Predeterminado",ingr,t.getNombre(),precio));
        ses.setAttribute("pizza", pizzas);
        
        //productos
        List<Producto> productos = (List<Producto>) ses.getAttribute("productosIngresados");
        
        Pedido pedido = new Pedido();
        pedido.setEstado(estado);
        pedido.setUsuario(logDao.buscarUsuario(username));
        pedido.setMonto(precio);
        pedido.setDireccion(direccion);
        pedido.setPizzas(pizzas);
        pedido.setProductos(productos);
        
        PedidoDAO daoPedido = new PedidoDAO();
        daoPedido.agregarPedido(pedido,1);

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