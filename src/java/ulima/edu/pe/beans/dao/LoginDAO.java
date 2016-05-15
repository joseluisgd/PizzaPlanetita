
package ulima.edu.pe.beans.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import ulima.edu.pe.beans.Ingrediente;




public class LoginDAO {
    
public Integer login(String usuario, String password) {
        MongoClient mongo = null;
        int variable = 0;
        Ingrediente ingrediente = new Ingrediente();
        try {
            mongo = new MongoClient(new MongoClientURI("mongodb://grupo01:progra@ds063124.mongolab.com:63124/basededatos"));
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            BasicDBObject query = new BasicDBObject("Usuario", new BasicDBObject("$eq",usuario));
//            BasicDBObject subquery = new BasicDBObject();
//            BasicDBObject where1 = new BasicDBObject();
//            BasicDBObject where2 = new BasicDBObject();
//            where1.put("$eq", usuario);
//            where2.put("$eq", password);
//            query.put("usu", where1);
//            query.put("pass", where2);
            
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
    
}
 