package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import ulima.edu.pe.beans.usuario.Usuario;
import ulima.edu.pe.util.ConexionMLab;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        boolean registrado = false;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("usuario");

            BasicDBObject docUsuario = new BasicDBObject();

            if (existeUsername(usuario.getUsername()) == false) {
                docUsuario.put("_id", obtenerSiguienteId());
                docUsuario.put("username", usuario.getUsername());
                docUsuario.put("password", usuario.getPassword());
                docUsuario.put("correo", usuario.getCorreo());
                coleccion.insert(docUsuario);
                registrado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return registrado;
    }

    public boolean login(String username, String password) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        boolean ingreso = false;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("usuario");
            BasicDBObject query = new BasicDBObject();

            query.put("username", username);
            query.put("password", password);

            DBCursor cursor = coleccion.find(query);
            if (cursor.hasNext()) {
                ingreso = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return ingreso;
    }

    public Usuario buscarUsuario(String username) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Usuario usuario = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("usuario");
            BasicDBObject query = new BasicDBObject();

            query.put("username", username);
            DBCursor cursor = coleccion.find(query);

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                usuario = new Usuario();
                usuario.setUsername(username);
                usuario.setPassword((String) dbo.get("password")); //ChF: Considerar no hacer esto
                usuario.setCorreo((String) dbo.get("correo"));
                usuario.setPuntos((int) dbo.get("puntos"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return usuario;
    }

    public boolean existeUsername(String username) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        boolean existe = false;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("usuario");
            BasicDBObject query = new BasicDBObject();

            query.put("username", username);
            DBCursor cursor = coleccion.find(query);

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();
                existe = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return existe;
    }

    private int obtenerSiguienteId() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        int mayor = 0;
        int idRecibido = 0;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("usuario");
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

//    public Cliente buscarCliente(int id) {
//        MongoClient mongo = ConexionMLab.getMongoClient();
//        Cliente cliente = null;
//        Usuario usuario;
//        try {
//            DB db = mongo.getDB("pizzaplaneta");
//            DBCollection coleccion = db.getCollection("cliente");
//            BasicDBObject query = new BasicDBObject();
//            query.put("id", id);
//            DBCursor cursor = coleccion.find(query);
//            while (cursor.hasNext()) {
//                DBObject dbo = cursor.next();
//                DBObject dbo2 = (BasicDBObject) dbo.get("Usuario");
//                usuario = new Usuario();
//                usuario = this.buscarUsuario((String) dbo2.get("usu"));
//                cliente = new Cliente();
//
//                cliente.setId((Integer) dbo.get("id"));
//                cliente.setNombre((String) dbo.get("nombre"));
//                cliente.setApellido((String) dbo.get("apellidos"));
//                cliente.setDni((String) dbo.get("dni"));
//                cliente.setTelefono((Integer) dbo.get("telefono"));
//                cliente.setEdad((String) dbo.get("edad"));
//                cliente.setUsuario(usuario);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ConexionMLab.closeMongoClient();
//        }
//        return cliente;
//
//    }
}
