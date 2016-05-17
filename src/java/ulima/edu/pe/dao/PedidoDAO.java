package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Estado;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.Pizza;
import ulima.edu.pe.beans.Producto;
import ulima.edu.pe.util.ConexionMLab;

public class PedidoDAO {

    public void agregar(Estado estado, String usuario, String direccion, List<Pizza> pizzas, List<Producto> productos) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject doc = new BasicDBObject();

            doc.put("id", obtenerSiguienteId());

            BasicDBObject doc1 = new BasicDBObject();
            doc1.put("fechahora", estado.getHora());
            doc1.put("id", estado.getId());
            doc1.put("estado", estado.getEstado());

            doc.put("Estado", doc1);

            doc.put("usu", usuario);

            doc.put("direccion", direccion);

            BasicDBObject doc2;
            BasicDBObject doc4;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayIngredientes = new ArrayList();
            List<Ingrediente> ingredientes;
            for (Pizza pizza : pizzas) {
                doc2 = new BasicDBObject();
                ingredientes = pizza.getIng();
                for (Ingrediente ingrediente : ingredientes) {
                    doc4 = new BasicDBObject();
                    doc4.put("id", ingrediente.getId());
                    doc4.put("nombre", ingrediente.getNombre());
                    arrayIngredientes.add(doc4);
                }
                doc2.put("Ingredientes", arrayIngredientes);
                doc2.put("precio", pizza.getPrecio());
                arrayPizzas.add(doc2);
            }
            doc.put("Pizzas", arrayPizzas);

            BasicDBObject doc3;
            ArrayList arrayProductos = new ArrayList();
            for (Producto producto : productos) {
                doc3 = new BasicDBObject();
                doc3.put("id", producto.getId());
                doc3.put("nombre", producto.getNombre());
                doc3.put("precio", producto.getPrecio());
                arrayProductos.add(doc3);
            }
            doc.put("Productos", arrayProductos);

            coleccion.insert(doc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
    }

    private int obtenerSiguienteId() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int cont = 0;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                cont = cont + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return cont + 1;
    }
}
