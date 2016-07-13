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
import ulima.edu.pe.beans.producto.promocion.AdicionalPromocion;
import ulima.edu.pe.beans.producto.promocion.PizzaPromocion;
import ulima.edu.pe.beans.producto.promocion.Promocion;
import ulima.edu.pe.util.ConexionMLab;

public class PromocionDAO {

    public List<Promocion> obtenerPromociones() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        List<Promocion> promociones = null;
        Promocion promocion;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("promocion");
            DBCursor cursor = coleccion.find();

            //ChF: Declaración de variables necesarias para llenar el objeto promocion.
            List<PizzaPromocion> pizzas;
            PizzaPromocion pizza;
            BasicDBList docArrayPizzas;
            BasicDBObject docPizza;

            List<AdicionalPromocion> adicionales;
            AdicionalPromocion adicional;
            BasicDBList docArrayAdicionales;
            BasicDBObject docAdicional;

            promociones = new ArrayList<>();

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                promocion = new Promocion();
                //ChF: Antes de nada, se valida la vigencia de la promocion
                promocion.setFechaInicio((String) dbo.get("fechaInicio"));
                promocion.setFechaFin((String) dbo.get("fechaFin"));
                if (promocion.estaVigente()) {
                    promocion.setId((int) dbo.get("_id"));
                    promocion.setNombre((String) dbo.get("nombre"));
                    promocion.setPrecio((float) ((double) dbo.get("precio")));
    
                    //ChF: Se obtienen los documentos del array pizzas del documento productos
                    pizzas = new ArrayList<>();
                    docArrayPizzas = (BasicDBList) ((DBObject) dbo.get("productos")).get("pizzas");
                    for (Object objPizza : docArrayPizzas) {
                        docPizza = (BasicDBObject) objPizza;
    
                        pizza = new PizzaPromocion();
                        pizza.setNombre((String) docPizza.get("nombre"));
                        pizza.setTamanoId((int) docPizza.get("tamanoId"));
                        pizza.setCantidad((int) docPizza.get("cantidad"));
    
                        pizzas.add(pizza);
                    }
                    promocion.setPizzas(pizzas);
                }
                //ChF: Se obtienen los documentos del array adicionales del documento productos
                adicionales = new ArrayList<>();
                docArrayAdicionales = (BasicDBList) ((DBObject) dbo.get("productos")).get("adicionales");
                for (Object objAdicional : docArrayAdicionales) {
                    docAdicional = (BasicDBObject) objAdicional;

                    adicional = new AdicionalPromocion();
                    adicional.setNombre((String) docAdicional.get("nombre"));
                    adicional.setCantidad((int) docAdicional.get("cantidad"));

                    adicionales.add(adicional);

                    promocion.setAdicionales(adicionales);
                }
                promocion.setDescripcion((String) dbo.get("descripcion"));

                promociones.add(promocion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return promociones;
    }
}
