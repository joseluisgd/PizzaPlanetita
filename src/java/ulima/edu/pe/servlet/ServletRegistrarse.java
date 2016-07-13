package ulima.edu.pe.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ulima.edu.pe.beans.usuario.Usuario;
import ulima.edu.pe.beans.usuario.Cliente;
import ulima.edu.pe.dao.ClienteDAO;
import ulima.edu.pe.dao.UsuarioDAO;

public class ServletRegistrarse extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);

        RequestDispatcher rd;

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getParameter("username"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setPuntos(100);
//        usuario.setTipo(request.getParameter("spc"));
//        usuario.setPuntos(request.getParameter("spc"));

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.registrarUsuario(usuario)) {

            Cliente cliente = new Cliente();
            cliente.setNombres(request.getParameter("nombres"));
            cliente.setApellidos(request.getParameter("apellidos"));
            cliente.setTelefono(request.getParameter("telefono"));
            cliente.setDni(request.getParameter("dni"));
            cliente.setEdad(request.getParameter("edad"));
//        cliente.setDirecciones(request.getParameter("spc"));
            cliente.setUsuario(usuario);

            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.registrarCliente(cliente);
            rd = request.getRequestDispatcher("login.html");
        } else {
            //ChF: 
            rd = request.getRequestDispatcher("registroIncorrecto.html");
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
