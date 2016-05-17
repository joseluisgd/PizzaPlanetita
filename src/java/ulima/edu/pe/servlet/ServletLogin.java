package ulima.edu.pe.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.dao.LoginDAO;

public class ServletLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);

        String username = request.getParameter("usuario");
        String password = request.getParameter("password");

        LoginDAO daoLogin = new LoginDAO();

        RequestDispatcher rd = null;
        if (daoLogin.login(username, password) == 1) {
            rd = request.getRequestDispatcher("logincorrecto.html");
            ses.setAttribute("username", username);
        } else {
            rd = request.getRequestDispatcher("errorlogin.html");
        }
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
