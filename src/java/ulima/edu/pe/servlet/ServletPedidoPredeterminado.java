/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulima.edu.pe.servlet;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
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
//<editor-fold defaultstate="collapsed" desc="... Just in case.">
        //Estoy en busca de como gettear todos los radio button en el jsp.
//        String idTamano1 = request.getParameter("tamanoId1");
//        String idTamano2 = request.getParameter("tamanoId2");
//        String idTamano3 = request.getParameter("tamanoId3");
//        
// </editor-fold>
        List<String> tamanos = new ArrayList<>();
        for (int i = 0; i <= idPizzas.length; i++) {
            if (request.getParameter("tamanoId" + i) != null) {
                tamanos.add(request.getParameter("tamanoId" + i));
            }
        }
        /* <editor-fold defaultstate="collapsed" desc="Codigo porsiacaso vaya a servir...">
        int[] idTamanos = new int[idPizzas.length];>>
//        int a = 0;
//        for (int i = 0; i < idPizzas.length; i++) {
//            for (int j = 1; j <= 3; j++) {
//                if (Integer.parseInt(idPizzas[i]) == j) {
//                    idTamanos[a] = j;
//                    a++;
//                }
//            }
//        }
         */
// </editor-fold>
        List<Pizza> p = (List<Pizza>) ses.getAttribute("pizzas");
        List<Pizza> pAux = new ArrayList<>();

        Tamano t = null;
        List<Tamano> tLista = new ArrayList<>();
        int b = 0;
        //ChF: No entiendo bien qué se hace aquí, así que he cambiado y agregado
        //cosas puntuales de acuerdo al cambio que hice en MostrarCartaPizzaDAO.java
        //Líneas: 72, 75, 76
        for (Pizza pizza : p) {
            for (int i = 0; i < idPizzas.length; i++) {
                if (pizza.getId() == Integer.parseInt(idPizzas[i])) {
                    List<Tamano> listTam = pizza.getTam();
//                    for (Object tam : listTam) {
                    for (Tamano tamano : listTam) {
//                        DBObject dbo = DBObject.class.cast(tam);
//                        if ((Integer) dbo.get("id") == Integer.parseInt(tamanos.get(b))) {
                        if (tamano.getId() == Integer.parseInt(tamanos.get(b))) {
                            t = tamano.clonar();
//                            t = new Tamano();
//                            t.setId((Integer) dbo.get("id"));
//                            t.setNombre((String) dbo.get("NombreTamano"));
//                            t.setPrecio((Integer) dbo.get("Slices"));
//                            t.setSlices((Integer) dbo.get("Precio"));
                            pAux.add(pizza);
                            tLista.add(t);
                        }
                    }
                    b++;
                }
            }

        }

        ses.setAttribute("pizzasOrdenadas", pAux);
        ses.setAttribute("tamanoEscogico", tLista);

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
