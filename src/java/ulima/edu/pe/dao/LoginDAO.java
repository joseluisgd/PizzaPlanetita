package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import ulima.edu.pe.beans.Cliente;
import ulima.edu.pe.beans.Usuario;
import ulima.edu.pe.util.ConexionMLab;

public class LoginDAO {

    public Integer login(String usuario, String password) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int variable = 0;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            BasicDBObject query = new BasicDBObject();

            query.put("Usuario.usu", usuario);
            query.put("Usuario.pass", password);

            DBCursor cursor = coleccion.find(query);
            if (cursor.hasNext()) {
                variable = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return variable;

    }

    public Usuario buscarUsuario(String username) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        Usuario usuario = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            BasicDBObject query = new BasicDBObject();
            query.put("Usuario.usu", username);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject) dbo.get("Usuario");
                usuario = new Usuario();

                usuario.setUsername((String) dbo2.get("usu"));
                usuario.setPassword((String) dbo2.get("pass"));
                usuario.setCorreo((String) dbo2.get("correo"));
                usuario.setPuntos((Integer) dbo2.get("puntos"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return usuario;

    }

    public Cliente buscarCliente(int id) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        Cliente cliente = null;
        Usuario usuario = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            BasicDBObject query = new BasicDBObject();
            query.put("id", id);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject) dbo.get("Usuario");
                usuario = new Usuario();
                usuario = this.buscarUsuario((String) dbo2.get("usu"));
                cliente = new Cliente();

                cliente.setId((Integer) dbo.get("id"));
                cliente.setNombre((String) dbo.get("nombre"));
                cliente.setApellido((String) dbo.get("apellidos"));
                cliente.setDni((String) dbo.get("dni"));
                cliente.setTelefono((Integer) dbo.get("telefono"));
                cliente.setEdad((String) dbo.get("edad"));
                cliente.setUsuario(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return cliente;

    }
}
