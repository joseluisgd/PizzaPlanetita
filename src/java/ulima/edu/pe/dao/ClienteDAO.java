package ulima.edu.pe.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Cliente;
import ulima.edu.pe.beans.Direccion;
import ulima.edu.pe.beans.Usuario;
import ulima.edu.pe.util.ConexionMLab;

public class ClienteDAO {

    public void registrarCliente(Cliente cliente) {
        MongoClient mongo = ConexionMLab.getMongoClient();

        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("cliente");

            BasicDBObject docCliente = new BasicDBObject();
            docCliente.put("_id", obtenerSiguienteId());
            docCliente.put("nombres", cliente.getNombres());
            docCliente.put("apellidos", cliente.getApellidos());
            docCliente.put("telefono", cliente.getTelefono());
            docCliente.put("dni", cliente.getDni());
            docCliente.put("edad", cliente.getEdad());

            //ChF: Lista de direcciones del cliente
            BasicDBObject docDireccion;
            ArrayList arrayDirecciones = new ArrayList();
            for (Direccion direccion : cliente.getDirecciones()) {
                docDireccion = new BasicDBObject();
                docDireccion.put("calle", direccion.getCalle());
                docDireccion.put("distrito", direccion.getDistrito());
                arrayDirecciones.add(docDireccion);
            }
            docCliente.put("direcciones", arrayDirecciones);

            docCliente.put("username", cliente.getUsuario().getUsername());
            coleccion.insert(docCliente);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
    }

    public Cliente buscarCliente(int id) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Cliente cliente = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("cliente");

            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);
            DBCursor cursor = coleccion.find(query);

            //ChF: Declaración de variables necesarias para llenar el objeto direcciones.
            List<Direccion> direcciones = new ArrayList<>();
            Direccion direccion;
            BasicDBList docArrayDirecciones;
            BasicDBObject docDireccion;

            //ChF: Declaración de variables necesarias para llenar el objeto usuario.
            Usuario usuario;

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                cliente = new Cliente();
                cliente.setId(id);
                cliente.setNombres((String) dbo.get("nombres"));
                cliente.setApellidos((String) dbo.get("apellidos"));
                cliente.setTelefono((String) dbo.get("telefono"));
                cliente.setDni((String) dbo.get("dni"));
                cliente.setEdad((String) dbo.get("edad"));

                //ChF: Lista de direcciones del cliente
                docArrayDirecciones = (BasicDBList) dbo.get("direcciones");
                for (Object objDireccion : docArrayDirecciones) {
                    docDireccion = (BasicDBObject) objDireccion;
                    direccion = new Direccion((String) docDireccion.get("calle"), (String) docDireccion.get("distrito"));
                    direcciones.add(direccion);
                }
                cliente.setDirecciones(direcciones);

                //ChF: Usuario del cliente
                UsuarioDAO uDAO = new UsuarioDAO();
                usuario = uDAO.buscarUsuario((String) dbo.get("username"));
                cliente.setUsuario(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return cliente;
    }

    private int obtenerSiguienteId() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        int mayor = 0;
        int idRecibido = 0;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("cliente");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                idRecibido = (int) dbo.get("_id");
                if (idRecibido > mayor) {
                    mayor = idRecibido;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return mayor + 1;
    }
}
