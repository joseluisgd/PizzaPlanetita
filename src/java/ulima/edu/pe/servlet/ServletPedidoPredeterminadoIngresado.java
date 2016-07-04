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
import ulima.edu.pe.beans.pedido.Direccion;
import ulima.edu.pe.beans.pedido.Estado;
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.pedido.ProductoPedido;
import ulima.edu.pe.beans.producto.pizza.Pizza;
import ulima.edu.pe.beans.producto.pizza.PizzaCarta;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.dao.UsuarioDAO;
import ulima.edu.pe.dao.PedidoDAO;
import ulima.edu.pe.util.Util;

public class ServletPedidoPredeterminadoIngresado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //INGRESAR PEDIDO PREDETERMINADO A LA BD.
        HttpSession ses = request.getSession(true);

        //pizza,ingredientes
        //esta lista es de pizzas predeterminadas. Tiene los ingredientes aqui.
        //de misma manera el precio.
        //-----pizzas
        List<Pizza> pAux = (List<Pizza>) ses.getAttribute("pizzasOrdenadas");
        //------ingredientes de cada pizza
        List<List<Ingrediente>> ingAux = new ArrayList<>();
        for (Pizza pizza : pAux) {
            ingAux.add(pizza.getIngredientes());
        }
        
        ses.setAttribute("ingredientes", ingAux);

        //tamano, precio
        List<Tamano> tLista = (List<Tamano>) ses.getAttribute("tamanoEscogico");
        List<Float> precios = new ArrayList<>();
        List<String> tamanos = new ArrayList<>();
        for (Tamano tamano : tLista) {
            tamanos.add(tamano.getNombre());
            precios.add(tamano.getPrecio());
        }
        ses.setAttribute("tamanoIngresado", tamanos);
        //organizadas para el pedido DAO
        List<ProductoPedido> pizzas = new ArrayList<>();
        Pizza p = null;
        int a = 0;
        for (Pizza pizza1 : pAux) {
            p= new PizzaCarta();
            p.setNombre(pizza1.getNombre());
            p.setIngredientes(ingAux.get(a));
            pizzas.add(1,p);
            a++;
        }
        ses.setAttribute("pizza", pizzas);
        //estado
        Estado estado = new Estado();
        estado.setFechaHora(Util.obtenerFechaHoraActual());        
        
        ses.setAttribute("estado", estado.getNombrePorId(1));
        //usuario
        String username = String.valueOf(ses.getAttribute("username"));

        //direccion
        String direccion = request.getParameter("direccion");
        String distrito = request.getParameter("distrito");
        ses.setAttribute("direccion", direccion);
        Direccion di= new Direccion();
        di.setCalle(direccion);
        di.setCalle(distrito);

        
        //monto
        float monto=0;
        for (int i = 0; i < precios.size(); i++) {
            monto=monto + precios.get(i);
        }
        ses.setAttribute("precio", monto);
        
        Pedido pedido = new Pedido();
        pedido.setEstados(estado.getNombrePorId(1));
        pedido.setUsername(username);
        pedido.setPrecioPedido(monto);
        pedido.setDireccion(di);
        pedido.setProductos(pizzas);
        pedido.setProductos(null);
        //Estructura tiene que ser iugal a la de pedidos personalizados.
        //Si un campo no se llena, pongan null y a la hora de llamarlo
        // para mostrar los datos, no consideramos esos campos.
        //Ingresen estos pedidso en el mismo documento que los personalizados.

        PedidoDAO daoPedido = new PedidoDAO();
        daoPedido.agregarPedido(pedido,2);

        RequestDispatcher rd = request.getRequestDispatcher("pedidoRegistradoPredeterminado.jsp");
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
