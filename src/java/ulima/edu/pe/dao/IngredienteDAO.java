package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.util.ConexionMLab;

public class IngredienteDAO {

    public List<Ingrediente> obtenerIngredientes() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        List<Ingrediente> ingrediente = new ArrayList<>();
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("ingrediente");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                ingrediente.add(new Ingrediente((Integer) dbo.get("id"), String.valueOf(dbo.get("nombre"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return ingrediente;
    }

    public Ingrediente buscarIngrediente(int id) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Ingrediente ingrediente = new Ingrediente();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingrediente");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            // En vez de utilizar query1, ¿Se puede hacer lo siguiente?
            // query.put("id", id);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                ingrediente = new Ingrediente((Integer) dbo.get("id"), String.valueOf(dbo.get("nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return ingrediente;
    }
}
