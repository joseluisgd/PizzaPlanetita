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

public class AdicionalDAO {

    public Adicional obtenerAdicional(int id) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Adicional adicional = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("adicional");
            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);

            DBCursor cursor = coleccion.find(query);

            //ChF: Declaración de variables necesarias para llenar el objeto adicional.
            String nombre;
            float precio;

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                nombre = (String) dbo.get("nombre");
                precio = (float) ((double) dbo.get("precio"));
                adicional = new Adicional(id, nombre, precio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return adicional;
    }

    public List<Adicional> obtenerAdicionales() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Adicional adicional;
        List<Adicional> adicionales = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("adicional");
            DBCursor cursor = coleccion.find();

            int id;
            String nombre;
            float precio;

            adicionales = new ArrayList<>();

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                id = (int) dbo.get("_id");
                nombre = (String) dbo.get("nombre");
                precio = (float) ((double) dbo.get("precio"));
                adicional = new Adicional(id, nombre, precio);
                adicionales.add(adicional);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
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
