package ulima.edu.pe.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.Pizza;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.util.ConexionMLab;

public class MostrarCartaPizzaDAO {

    public List<Pizza> getPizzas() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Pizza> pizzas = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pizza");
            DBCursor cursor = coleccion.find();
            
            //ChF: Declaración de variables que se necesitan para llenar el objeto pizza.
            int id;
            String nombrePizza;

            List<Ingrediente> ingredientes;
            Ingrediente ingrediente;
            BasicDBList docArrayIngrediente;
            BasicDBObject docIngrediente;

            List<Tamano> tamanos;
            Tamano tamano;
            BasicDBList docArrayTamano;
            BasicDBObject docTamano;
            
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                
                id = (Integer) dbo.get("id");
                nombrePizza = (String) dbo.get("NombrePizza");
                
                //ChF: Anteriormente, esto se pasaba al constructor como (List<Ingrediente>) dbo.get("Ingredientes"),
                //ahora se ingresa al array que contiene los documentos ingredientes y se obtienen correctamente
                //sus valores.
                docArrayIngrediente = (BasicDBList) dbo.get("Ingredientes");
                ingredientes = new ArrayList<>();
                for (Object objIngrediente : docArrayIngrediente) {
                    docIngrediente = (BasicDBObject) objIngrediente;
                    ingrediente = new Ingrediente((Integer) docIngrediente.get("id"), (String) docIngrediente.get("nombre"));
                    ingredientes.add(ingrediente);
                }
                
                docArrayTamano = (BasicDBList) dbo.get("Tamano");
                tamanos = new ArrayList<>();
                for (Object objTamano : docArrayTamano) {
                    docTamano = (BasicDBObject) objTamano;
                    tamano = new Tamano((Integer) docTamano.get("id"), (String) docTamano.get("NombreTamano"), 
                                        (Integer) docTamano.get("Slices"), (Integer) docTamano.get("Precio"));
                    tamanos.add(tamano);
                }
                
                pizzas.add(new Pizza(id, nombrePizza, ingredientes, tamanos));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pizzas;
    }
}
