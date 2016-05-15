package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.List;
import ulima.edu.pe.beans.Ingrediente;

public class PedidoPersonalizadoDAO {
//Lista de ingredientes por usuario. (ingredientes, Usuario usuario)
    public void ingresarPedidoxUsuario(List<Ingrediente> ingredientes) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient(new MongoClientURI("mongodb://grupo01:progra@ds063124.mongolab.com:63124/basededatos"));
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingredientesxusuario");

            BasicDBObject doc = new BasicDBObject();
            doc.put("id", contar()+1);
            BasicDBObject doc2 = new BasicDBObject();
            
            for (Ingrediente ingrediente : ingredientes) {
                doc2.put("id",ingrediente.getId());
                doc2.put("nombre", ingrediente.getNombre());
            }
            
            doc.put("ingrediente",doc2);
            

            coleccion.insert(doc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
    }
    private Integer contar() {
        MongoClient mongo = null;
        int cont = 0;
        try {
            mongo = new MongoClient(new MongoClientURI("mongodb://grupo01:progra@ds063124.mongolab.com:63124/basededatos"));
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingredientesxusuario");
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
        return cont;
    }
    
}
