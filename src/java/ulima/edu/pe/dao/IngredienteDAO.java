
package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.Producto;


public class IngredienteDAO {
    public List<Ingrediente> getIngredientes() {
        MongoClient mongo = null;
        List<Ingrediente> ingrediente = new ArrayList<>();
        try {
            mongo = new MongoClient(new MongoClientURI("mongodb://grupo01:progra@ds063124.mongolab.com:63124/basededatos"));
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingrediente");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                ingrediente.add(new Ingrediente((Integer)dbo.get("id"),(String) dbo.get("nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return ingrediente;
    }
    
    public Ingrediente BuscarIngrediente(int id) {
        MongoClient mongo = null;
        Ingrediente ingrediente = new Ingrediente();
        try {
            mongo = new MongoClient(new MongoClientURI("mongodb://grupo01:progra@ds063124.mongolab.com:63124/basededatos"));
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingrediente");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                ingrediente = new Ingrediente((Integer) dbo.get("id"), (String) dbo.get("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return ingrediente;

        
    }
    
}
