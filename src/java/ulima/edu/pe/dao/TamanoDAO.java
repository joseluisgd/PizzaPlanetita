
package ulima.edu.pe.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.util.ConexionMLab;

//ChF: Se eliminará la clase.
public class TamanoDAO {
    public List<Tamano> getTamanos() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Tamano> tamano = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("tamano");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                tamano.add(new Tamano((Integer) dbo.get("id"), String.valueOf(dbo.get("nombrePizza")), (Integer) dbo.get("Slices")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return tamano;
    }
}
