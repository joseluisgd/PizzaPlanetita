
package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Adicional;
import ulima.edu.pe.util.ConexionMLab;

public class ProductoDAO {
    public List<Adicional> getProductos() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Adicional> producto = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("producto");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                producto.add(new Adicional((Integer) dbo.get("id"), String.valueOf(dbo.get("nombre")), (Float) dbo.get("precio")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return producto;
    }
    
     public Adicional buscarProducto(int id) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        Adicional producto = new Adicional();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("producto");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                producto = new Adicional((Integer) dbo.get("id"), String.valueOf(dbo.get("nombre")), (Float) dbo.get("precio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return producto;

    }
}
