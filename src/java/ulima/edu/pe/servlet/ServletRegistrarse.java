package ulima.edu.pe.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.beans.Usuario;
import ulima.edu.pe.beans.Cliente;
import ulima.edu.pe.dao.RegistrarseDAO;

public class ServletRegistrarse extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        String edad = request.getParameter("edad");
        Usuario usuario = new Usuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("correo"));

        RegistrarseDAO daoRegistro = new RegistrarseDAO();
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellidos);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);
        cliente.setEdad(edad);
        cliente.setUsuario(usuario);
        daoRegistro.registrar(cliente);
        
        RequestDispatcher rd = request.getRequestDispatcher("login.html");
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
