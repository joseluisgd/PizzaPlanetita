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
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.PizzaPedido;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.util.ConexionMLab;

public class PizzaDAO {

    public PizzaPedido obtenerPizza(int id, int tamanoId) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        PizzaPedido pizza = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pizza");
            BasicDBObject query = new BasicDBObject();
            query.put("id", id);

            DBCursor cursor = coleccion.find(query);

            //ChF: Declaración de variables necesarias para llenar el objeto pizza.
            String nombre = null;

            List<Ingrediente> ingredientes = new ArrayList<>();
            Ingrediente ingrediente;
            BasicDBList docArrayIngredientes;
            BasicDBObject docIngrediente;
            
            List<Tamano> tamanos = new ArrayList<>();
            Tamano tamano = null;
            float precio;
            BasicDBList docArrayTamanos;
            BasicDBObject docTamano;

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                nombre = (String) dbo.get("NombrePizza"); //ChF: "NombrePizza" cambiará a "nombre"

                docArrayIngredientes = (BasicDBList) dbo.get("Ingredientes"); //ChF: "Ingredientes" cambiará a "ingredientes", de acuerdo a lo siguiente: https://google.github.io/styleguide/jsoncstyleguide.xml
                for (Object objIngrediente : docArrayIngredientes) {
                    docIngrediente = (BasicDBObject) objIngrediente;
                    ingrediente = new Ingrediente((Integer) docIngrediente.get("id"), (String) docIngrediente.get("nombre"));
                    ingredientes.add(ingrediente);
                }

                docArrayTamanos = (BasicDBList) dbo.get("Tamano");
                //ChF: Se busca en el array "Tamano" el id que coincida con el del parámetro (tamanoId)
                for (Object objTamano : docArrayTamanos) {
                    docTamano = (BasicDBObject) objTamano;
                    if (tamanoId == (Integer) docTamano.get("id")){
                        precio = (float)((double) docTamano.get("precio"));
                        tamano = new Tamano(tamanoId, precio);
                        break;
                    }
                }
                pizza = new PizzaPedido(id, nombre, ingredientes, tamano);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pizza;
    }
}
