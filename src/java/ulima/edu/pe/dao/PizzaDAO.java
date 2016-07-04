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
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.beans.producto.pizza.PizzaCarta;
import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.util.ConexionMLab;

public class PizzaDAO {

    public List<PizzaCarta> obtenerPizzas() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        List<PizzaCarta> pizzas = null;
        PizzaCarta pizza;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pizza");
            DBCursor cursor = coleccion.find();

            pizzas = new ArrayList<>();

            //ChF: Declaración de variables necesarias para llenar el objeto pizza.
            List<Ingrediente> ingredientes;
            Ingrediente ingrediente;
            BasicDBList docArrayIngredientes;
            BasicDBObject docIngrediente;

            List<Tamano> tamanos;
            Tamano tamano;
            BasicDBList docArrayTamanos;
            BasicDBObject docTamano;

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                pizza = new PizzaCarta();
                pizza.setId((int) dbo.get("_id"));
                pizza.setNombre((String) dbo.get("nombre"));

                //ChF: Lista de tamaños de la pizza
                docArrayTamanos = (BasicDBList) dbo.get("tamanos");
                tamanos = new ArrayList<>();
                for (Object objTamano : docArrayTamanos) {
                    docTamano = (BasicDBObject) objTamano;
                    tamano = new Tamano();
                    tamano.setId((int) docTamano.get("id"));
                    tamano.setPrecio((float) ((double) docTamano.get("precio")));
                    tamanos.add(tamano);
                }
                pizza.setTamanos(tamanos);

                //ChF: Lista de ingredientes de la pizza
                docArrayIngredientes = (BasicDBList) dbo.get("ingredientes");
                ingredientes = new ArrayList<>();
                for (Object objIngrediente : docArrayIngredientes) {
                    docIngrediente = (BasicDBObject) objIngrediente;
                    ingrediente = new Ingrediente();
                    ingrediente.setId((int) docIngrediente.get("id"));
                    ingrediente.setNombre((String) docIngrediente.get("nombre"));
                    ingredientes.add(ingrediente);
                }
                pizza.setIngredientes(ingredientes);

                pizzas.add(pizza);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return pizzas;
    }

    public PizzaPedido buscarPizza(int id, int tamanoId) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        PizzaPedido pizza = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pizza");
            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);

            DBCursor cursor = coleccion.find(query);

            //ChF: Declaración de variables necesarias para llenar el objeto pizza.

            List<Ingrediente> ingredientes = new ArrayList<>();
            Ingrediente ingrediente;
            BasicDBList docArrayIngredientes;
            BasicDBObject docIngrediente;

            Tamano tamano;
            BasicDBList docArrayTamanos;
            BasicDBObject docTamano;

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                pizza = new PizzaPedido();
                pizza.setId(id);
                pizza.setNombre((String) dbo.get("nombre"));

                //ChF: Lista de tamaños de la pizza
                docArrayTamanos = (BasicDBList) dbo.get("tamanos");
                for (Object objTamano : docArrayTamanos) {
                    docTamano = (BasicDBObject) objTamano;
                    //ChF: Se busca en el array "tamanos" el id que coincida con el del parámetro (tamanoId)
                    if (tamanoId == (int) docTamano.get("id")) {
                        tamano = new Tamano();
                        tamano.setId(tamanoId);
                        tamano.setPrecio((float) ((double) docTamano.get("precio")));
                        break;
                    }
                }

                //ChF: Lista de ingredientes de la pizza
                docArrayIngredientes = (BasicDBList) dbo.get("ingredientes");
                for (Object objIngrediente : docArrayIngredientes) {
                    docIngrediente = (BasicDBObject) objIngrediente;
                    ingrediente = new Ingrediente();
                    ingrediente.setId((int) docIngrediente.get("id"));
                    ingrediente.setNombre((String) docIngrediente.get("nombre"));
                    ingredientes.add(ingrediente);
                }
                pizza.setIngredientes(ingredientes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return pizza;
    }
}
