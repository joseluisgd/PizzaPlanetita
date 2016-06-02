/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import ulima.edu.pe.beans.Pizza;
import ulima.edu.pe.beans.Tamano;

/**
 *
 * @author Jose Luis
 */
public class ServletPedidoPredeterminado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        String[] idPizzas = request.getParameterValues("pizzaId");
        
        //Estoy en busca de como gettear todos los radio button en el jsp.
        String idTamano= request.getParameter("tamanoId");
        
        List<Pizza> p=(List<Pizza>)ses.getAttribute("pizzas");
        List<Pizza> pAux=new ArrayList<>();
        
        Tamano t=new Tamano();
        
        for (Pizza pizza : p) {
            for (int i = 0; i < idPizzas.length; i++) {
                if(pizza.getId()==Integer.parseInt(idPizzas[i])){
                    pAux.add(pizza);
                    //No puedo avanzar.... Cuando lo jalo, esta como BasicDBObject
                    //y no me deja castearlo a Tamano
                    List<Tamano> listTam = pizza.getTam();
                    for (Tamano tam : listTam) {
                            if(tam.getId()==Integer.parseInt(idTamano)){
                                t.setId(tam.getId());
                                t.setNombre(tam.getNombre());
                                t.setPrecio(tam.getPrecio());
                                t.setSlices(tam.getSlices());
                            }
                    }
                }
            }
        }
        
        ses.setAttribute("pizzasOrdenadas", pAux);
        ses.setAttribute("tamanoEscogico", t);
        
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
