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

    public int login(String usuario, String password) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int variable = 0;
        Ingrediente ingrediente = new Ingrediente();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");
            BasicDBObject query = new BasicDBObject();
            
            BasicDBObject subquery = new BasicDBObject();
            BasicDBObject where1 = new BasicDBObject();
            BasicDBObject where2 = new BasicDBObject();
            subquery.put("Usuario.usu", usuario);
            subquery.put("Usuario.pass", password);
            
            DBCursor cursor = coleccion.find(subquery);
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
