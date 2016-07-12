/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulima.edu.pe.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.dao.AdicionalDAO;
import ulima.edu.pe.dao.PizzaDAO;
import ulima.edu.pe.dao.PromocionDAO;

/**
 *
 * @author Jose Luis
 */
public class ServletPizzasPredeterminadas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        PizzaDAO pizzaDAO = new PizzaDAO();
        AdicionalDAO adicionalDAO = new AdicionalDAO();
        PromocionDAO promocionDAO = new PromocionDAO();
        ses.setAttribute("pizzasCarta", pizzaDAO.obtenerPizzas());
        ses.setAttribute("adicionalesCarta", adicionalDAO.obtenerAdicionales());
        ses.setAttribute("promocionesCarta", promocionDAO.obtenerPromociones());
        RequestDispatcher rd = request.getRequestDispatcher("crearPedidoPredeterminado.jsp");
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
    }

}
