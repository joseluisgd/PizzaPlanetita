package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.util.ConexionMLab;

public class AdicionalDAO {

    public Adicional buscarAdicional(int id) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Adicional adicional = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("adicional");
            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);

            DBCursor cursor = coleccion.find(query);

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                adicional = new Adicional();
                adicional.setId(id);
                adicional.setNombre((String) dbo.get("nombre"));
                adicional.setPrecio((float) ((double) dbo.get("precio")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return adicional;
    }

    public List<Adicional> obtenerAdicionales() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        List<Adicional> adicionales = null;
        Adicional adicional;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("adicional");
            DBCursor cursor = coleccion.find();

            adicionales = new ArrayList<>();

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();
                
                adicional = new Adicional();
                adicional.setId((int) dbo.get("_id"));
                adicional.setNombre((String) dbo.get("nombre"));
                adicional.setPrecio((float) ((double) dbo.get("precio")));

                adicionales.add(adicional);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return adicionales;
    }

//     public Adicional buscarProducto(int id) {
//        MongoClient mongo = ConexionMLab.getMongoClient();
//        Adicional producto = new Adicional();
//        try {
//            DB db = mongo.getDB("basededatos");
//            DBCollection coleccion = db.getCollection("producto");
//            BasicDBObject query = new BasicDBObject();
//            BasicDBObject query1 = new BasicDBObject();
//            query1.put("$eq", id);
//            query.put("id", query1);
//            DBCursor cursor = coleccion.find(query);
//            while (cursor.hasNext()) {
//                DBObject dbo = cursor.next();
//                producto = new Adicional((Integer) dbo.get("id"), String.valueOf(dbo.get("nombre")), (Float) dbo.get("precio"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ConexionMLab.closeMongoClient();
//        }
//        return producto;
//    }
}
