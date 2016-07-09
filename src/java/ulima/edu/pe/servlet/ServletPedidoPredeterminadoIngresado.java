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
import ulima.edu.pe.beans.producto.pizza.Pizza;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.dao.UsuarioDAO;
import ulima.edu.pe.dao.PedidoDAO;
import ulima.edu.pe.util.Util;

public class ServletPedidoPredeterminadoIngresado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //INGRESAR PEDIDO PREDETERMINADO A LA BD.
        HttpSession ses = request.getSession(true);

        Pedido pedido = (Pedido) ses.getAttribute("pedido");

        Direccion direccion = new Direccion();
        direccion.setCalle((String) request.getParameter("calleDireccionPedido"));
        direccion.setDistrito((String) request.getParameter("distritoDireccionPedido"));
        pedido.setDireccion(direccion);

        Estado estado = new Estado();
        estado.setId(0);
        estado.setFechaHora(Util.obtenerFechaHoraActual());
        estado.setUsername(pedido.getUsername());
        pedido.setEstado(estado);

        PedidoDAO daoPedido = new PedidoDAO();
        daoPedido.agregarPedido(pedido);

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
