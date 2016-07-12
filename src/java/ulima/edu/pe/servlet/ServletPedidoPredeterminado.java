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
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.pedido.ProductoPedido;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.beans.producto.pizza.PizzaCarta;
import ulima.edu.pe.beans.producto.promocion.Promocion;

/**
 *
 * @author Jose Luis
 */
public class ServletPedidoPredeterminado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);

        Pedido pedido = new Pedido();

        pedido.setUsername(ses.getAttribute("username").toString());

        List<ProductoPedido> productosPedido = new ArrayList<>();
        ProductoPedido productoPedido;

        //ChF: Obtención de las pizzas seleccionadas para el pedido
        if (request.getParameterValues("pizzasId") != null) {
            String[] idPizzas = request.getParameterValues("pizzasId");
            List<PizzaCarta> pizzasCarta = (List<PizzaCarta>) ses.getAttribute("pizzasCarta");
            int tamanoId;
            for (String pizzaId : idPizzas) {
                productoPedido = new ProductoPedido();
                tamanoId = Integer.parseInt(request.getParameter("pizza" + pizzaId + "tamano"));
                for (PizzaCarta pizzaCarta : pizzasCarta) {
                    if (pizzaCarta.getId() == Integer.parseInt(pizzaId)) {
                        productoPedido.setProducto(pizzaCarta.convertirEnPizzaPedido(tamanoId));
                        break;
                    }
                }
                productoPedido.setCantidad(Integer.parseInt(request.getParameter("pizza" + pizzaId + "cantidad")));
                productosPedido.add(productoPedido);
            }
        }

        if (request.getParameterValues("adicionalesId") != null) {
            //ChF: Obtención de los adicionales seleccionadas para el pedido
            String[] idAdicionales = request.getParameterValues("adicionalesId");
            List<Adicional> adicionalesCarta = (List<Adicional>) ses.getAttribute("adicionalesCarta");
            for (String adicionalId : idAdicionales) {
                productoPedido = new ProductoPedido();
                for (Adicional adicional : adicionalesCarta) {
                    if (adicional.getId() == Integer.parseInt(adicionalId)) {
                        //ChF: Clono porque me da miedo que, ya que Java maneja todo en referencias, se vaya a 
                        //joder la lista de adicionales de la carta por manipular la lista de adicionales del pedido.
                        //En pizzas no clono porque el método convertirEnPizzaPedido retorna un objeto nuevo.
                        productoPedido.setProducto(adicional.clonar());
                        break;
                    }
                }
                productoPedido.setCantidad(Integer.parseInt(request.getParameter("adicional" + adicionalId + "cantidad")));
                productosPedido.add(productoPedido);
            }
        }

        if (request.getParameterValues("promocionesId") != null) {
            //ChF: Obtención de los adicionales seleccionadas para el pedido
            String[] idPromociones = request.getParameterValues("promocionesId");
            List<Promocion> promocionesCarta = (List<Promocion>) ses.getAttribute("promocionesCarta");
            for (String promocionId : idPromociones) {
                productoPedido = new ProductoPedido();
                for (Promocion promocion : promocionesCarta) {
                    if (promocion.getId() == Integer.parseInt(promocionId)) {
                        //ChF: La razón de la clonación está arriba.
                        productoPedido.setProducto(promocion.clonar());
                        break;
                    }
                }
                productoPedido.setCantidad(Integer.parseInt(request.getParameter("promocion" + promocionId + "cantidad")));
                productosPedido.add(productoPedido);
            }
        }

        pedido.setProductos(productosPedido);

        //ChF: La dirección se añade en la siguiente página, cuando se confirma el pedido.
        ses.setAttribute("pedido", pedido);

        RequestDispatcher rd = request.getRequestDispatcher("pedidoPredeterminadoIngresado.jsp");
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
