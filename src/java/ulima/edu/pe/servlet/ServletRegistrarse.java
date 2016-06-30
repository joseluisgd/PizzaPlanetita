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

        String nombres = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        //int telefono = Integer.parseInt(request.getParameter("telefono"));
        String telefono = request.getParameter("telefono");
        String edad = request.getParameter("edad");
        Usuario usuario = new Usuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("correo"));

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.registrarUsuario(usuario)) {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setDni(dni);
            //cliente.setTelefono(telefono);
            cliente.setTelefono(telefono);
            cliente.setEdad(edad);
            cliente.setUsuario(usuario);
            
            clienteDAO.registrarCliente(cliente);
            rd = request.getRequestDispatcher("login.html");
        } else {
            //ChF: 
            rd = request.getRequestDispatcher("algunaOtraPaginaDeErrorQueAunNoEstaCreada.html");
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
