package ulima.edu.pe.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Adicional;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.PizzaPedido;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.util.ConexionMLab;

public class AdicionalDAO {

    public Adicional obtenerAdicional(int id) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        Adicional adicional = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("producto"); //ChF: Colección "producto" cambiará a "adicional"
            BasicDBObject query = new BasicDBObject();
            query.put("id", id);

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
            mongo.close();
        }
        return adicional;
    }
}
