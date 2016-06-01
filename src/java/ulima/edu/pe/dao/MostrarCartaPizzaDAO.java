
package ulima.edu.pe.dao;

import com.mongodb.BasicDBList;
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
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                
                
                pizzas.add(new Pizza((Integer)dbo.get("id"),
                        (String) dbo.get("NombrePizza"),
                        (List<Ingrediente>)dbo.get("Ingredientes"),
                        (List<Tamano>)dbo.get("Tamano")));
                
                //ingrediente.add(new Ingrediente((Integer) dbo.get("id"), String.valueOf(dbo.get("nombre"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pizzas;
    }
}
