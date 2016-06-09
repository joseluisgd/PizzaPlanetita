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
import ulima.edu.pe.beans.Pedido;
import ulima.edu.pe.util.ConexionMLab;

public class PedidoDAO {

    public void agregarPedido(Pedido pedido) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject docPedido = new BasicDBObject();

            docPedido.put("id", obtenerSiguienteId());

            BasicDBObject docEstado = new BasicDBObject();
            docEstado.put("fechahora", pedido.getEstado().getFechaHora());
            docEstado.put("id", pedido.getEstado().getId());
            docEstado.put("estado", pedido.getEstado().getEstado());

            docPedido.put("Estado", docEstado);
            docPedido.put("usu", pedido.getUsuario().getUsuario());
            docPedido.put("direccion", pedido.getDireccion());

            BasicDBObject docPizza;
            BasicDBObject docIngrediente;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayIngredientes = new ArrayList();
            List<Ingrediente> ingredientes;
            List<Pizza> pizzas = pedido.getPizzas();
            for (Pizza pizza : pizzas) {
                docPizza = new BasicDBObject();
                ingredientes = pizza.getIng();
                for (Ingrediente ingrediente : ingredientes) {
                    docIngrediente = new BasicDBObject();
                    docIngrediente.put("id", ingrediente.getId());
                    docIngrediente.put("nombre", ingrediente.getNombre());
                    arrayIngredientes.add(docIngrediente);
                }
                docPizza.put("Ingredientes", arrayIngredientes);
                docPizza.put("Tamano", pizza.getTamano());
                docPizza.put("precio", pizza.getPrecio());
                arrayPizzas.add(docPizza);
            }
            docPedido.put("Pizzas", arrayPizzas);

            BasicDBObject docProducto;
            ArrayList arrayProductos = new ArrayList();
            List<Producto> productos = pedido.getProductos();
            for (Producto producto : productos) {
                docProducto = new BasicDBObject();
                docProducto.put("id", producto.getId());
                docProducto.put("nombre", producto.getNombre());
                docProducto.put("precio", producto.getPrecio());
                arrayProductos.add(docProducto);
            }
            docPedido.put("Productos", arrayProductos);

            coleccion.insert(docPedido);
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
