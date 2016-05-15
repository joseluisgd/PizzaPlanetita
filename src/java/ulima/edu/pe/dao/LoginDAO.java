package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.util.ConexionMLab;

public class LoginDAO {

    public Integer login(String usuario, String password) {
        MongoClient mongo = ConexionMLab.getInstance().getConexion();
        int variable = 0;
        Ingrediente ingrediente = new Ingrediente();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            BasicDBObject query = new BasicDBObject("Usuario", new BasicDBObject("$eq", usuario));
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