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
import ulima.edu.pe.beans.Adicional;
import ulima.edu.pe.beans.PizzaCarta;
import ulima.edu.pe.beans.PizzaPedido;
import ulima.edu.pe.beans.ProductoPromocion;
import ulima.edu.pe.beans.Promocion;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.util.ConexionMLab;

public class MostrarCartaDAO {

    public List<PizzaCarta> obtenerPizzas() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<PizzaCarta> pizzas = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pizza");
            DBCursor cursor = coleccion.find();

            //ChF: Declaración de variables necesarias para llenar el objeto pizza.
            int id;
            String nombre;

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

                id = (Integer) dbo.get("id");
                nombre = (String) dbo.get("NombrePizza");

                //ChF: Anteriormente, esto se pasaba al constructor como (List<Ingrediente>) dbo.get("Ingredientes"),
                //ahora se ingresa al array que contiene los documentos ingredientes y se obtienen correctamente
                //sus valores.
                docArrayIngredientes = (BasicDBList) dbo.get("Ingredientes");
                ingredientes = new ArrayList<>();
                for (Object objIngrediente : docArrayIngredientes) {
                    docIngrediente = (BasicDBObject) objIngrediente;
                    ingrediente = new Ingrediente((Integer) docIngrediente.get("id"), (String) docIngrediente.get("nombre"));
                    ingredientes.add(ingrediente);
                }

                docArrayTamanos = (BasicDBList) dbo.get("Tamano");
                tamanos = new ArrayList<>();
                for (Object objTamano : docArrayTamanos) {
                    docTamano = (BasicDBObject) objTamano;
                    tamano = new Tamano((Integer) docTamano.get("id"), (float)((double) docTamano.get("precio")));
                    tamanos.add(tamano);
                }

                pizzas.add(new PizzaCarta(id, nombre, ingredientes, tamanos));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pizzas;
    }

    public List<Adicional> obtenerAdicionales() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Adicional> adicionales = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("producto");
            DBCursor cursor = coleccion.find();

            //ChF: Declaración de variables necesarias para llenar el objeto adicional.
            int id;
            String nombre;
            float precio;

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                id = (Integer) dbo.get("id");
                nombre = (String) dbo.get("nombre");
                precio = (float)((double) dbo.get("precio"));

                adicionales.add(new Adicional(id, nombre, precio));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return adicionales;
    }

    public List<Promocion> obtenerPromociones() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Promocion> promociones = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("promocion");
            DBCursor cursor = coleccion.find();

            //ChF: Declaración de variables necesarias para llenar el objeto promocion.
            int id;
            String nombre;
            float precio;
            String fechaInicio;
            String fechaFin;
            List<ProductoPromocion> productos;
            ProductoPromocion producto;
                PizzaPedido pizza;
                int pizzaId;
                int tamanoId;
                BasicDBList docArrayPizzas;
                BasicDBObject docPizza;
                
                Adicional adicional;
                int adicionalId;
                BasicDBList docArrayAdicionales;
                BasicDBObject docAdicional;
                
                int cantidadProducto;
            String descripcion;
            
            PizzaDAO pDAO = new PizzaDAO();
            AdicionalDAO aDAO = new AdicionalDAO();

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();

                id = (Integer) dbo.get("id");
                nombre = (String) dbo.get("nombre");
                precio = (Float) dbo.get("precio");
                fechaInicio = (String) dbo.get("fechaInicio");
                fechaFin = (String) dbo.get("fechaFin");
                
                //ChF: Se obtienen los productos del array pizzas
                productos = new ArrayList<>();
                
                docArrayPizzas = (BasicDBList) ((DBObject) dbo.get("productos")).get("pizzas");
                for (Object objPizza : docArrayPizzas) {
                    docPizza = (BasicDBObject) objPizza;
                    
                    pizzaId = (Integer) docPizza.get("id");
                    tamanoId = (Integer) docPizza.get("tamano");
                    cantidadProducto = (Integer) docPizza.get("cantidad");
                    pizza = pDAO.obtenerPizza(pizzaId, tamanoId);
                    
                    producto = new ProductoPromocion(pizza, cantidadProducto);
                    productos.add(producto);
                }
                
                //ChF: Se obtienen los productos del array adicionales
                docArrayAdicionales = (BasicDBList) ((DBObject) dbo.get("productos")).get("adicionales");
                for (Object objAdicional : docArrayAdicionales) {
                    docAdicional = (BasicDBObject) objAdicional;
                    
                    adicionalId = (Integer) docAdicional.get("id");
                    cantidadProducto = (Integer) docAdicional.get("cantidad");
                    adicional = aDAO.obtenerAdicional(adicionalId);
                    
                    producto = new ProductoPromocion(adicional, cantidadProducto);
                    productos.add(producto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return promociones;
    }
}
