
package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.List;
import ulima.edu.pe.beans.Estado;
import ulima.edu.pe.beans.Pizza;
import ulima.edu.pe.beans.Producto;
import ulima.edu.pe.util.ConexionMLab;


public class PedidoDAO {
    public void agregar(Estado estado,String usuario, String direccion, List<Pizza> pizzas, List<Producto> productos) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            
            BasicDBObject doc = new BasicDBObject();
            
            doc.put("id", contar() + 1);
            
            
            BasicDBObject doc1 = new BasicDBObject();
            doc1.put("fechahora", estado.getHora());
            doc1.put("id", estado.getId());
            doc1.put("estado", estado.getEstado());
            
            doc.put("Estado", doc1);
            
            
            doc.put("usu", usuario);
            
            doc.put("direccion", direccion);
            
            BasicDBObject doc2 = new BasicDBObject();
            for (Pizza pizza : pizzas) {
                doc2.put("ingredientes", pizza.getIng());
                doc2.put("precio", pizza.getPrecio());
            } 
            doc.put("Pizzas", doc2);
            
            BasicDBObject doc3 = new BasicDBObject();
            for (Producto producto : productos) {
                doc3.put("id", producto.getId());
                doc3.put("nombre", producto.getNombre());
                doc3.put("precio", producto.getPrecio());
            }
            doc.put("Productos", doc3);

            coleccion.insert(doc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
    }
    
    private Integer contar() {
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
        return cont;
    }
}
