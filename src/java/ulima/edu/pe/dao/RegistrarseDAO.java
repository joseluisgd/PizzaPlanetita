package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import ulima.edu.pe.beans.Usuario;
import ulima.edu.pe.beans.Cliente;
import ulima.edu.pe.util.ConexionMLab;

public class RegistrarseDAO {

    public void registrar(Cliente cliente) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");

            BasicDBObject docCliente = new BasicDBObject();

            docCliente.put("id", obtenerSiguienteId());
            docCliente.put("nombre", cliente.getNombre());
            docCliente.put("apellidos", cliente.getApellido());
            docCliente.put("dni", cliente.getDni());
            docCliente.put("telefono", cliente.getTelefono()); // Conversión implícita, arreglar
            docCliente.put("edad", cliente.getEdad());

            BasicDBObject docUsuario = new BasicDBObject();
            docUsuario.put("usu", cliente.getUsuario().getUsername());
            docUsuario.put("pass", cliente.getUsuario().getPassword());
            docUsuario.put("correo", cliente.getUsuario().getCorreo());
            docUsuario.put("puntos", cliente.getUsuario().getPuntos());

            docCliente.put("Usuario", docUsuario);
            coleccion.insert(docCliente);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
    }

    private int obtenerSiguienteId() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int cont = 0;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                cont = cont + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return cont + 1;
    }
}
