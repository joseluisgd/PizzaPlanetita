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
import ulima.edu.pe.beans.pedido.Direccion;
import ulima.edu.pe.beans.pedido.Estado;
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.pedido.ProductoPedido;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.beans.producto.promocion.AdicionalPromocion;
import ulima.edu.pe.beans.producto.promocion.PizzaPromocion;
import ulima.edu.pe.beans.producto.promocion.Promocion;
import ulima.edu.pe.util.ConexionMLab;

public class PedidoDAO {

    public void agregarPedido(Pedido pedido) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject docPedido = new BasicDBObject();

            docPedido.put("_id", obtenerSiguienteId());
            docPedido.put("username", pedido.getUsername());

            //ChF: Direccion del pedido
            BasicDBObject docDireccion = new BasicDBObject();
            docDireccion.put("calle", pedido.getDireccion().getCalle());
            docDireccion.put("distrito", pedido.getDireccion().getDistrito());
            docPedido.put("direccion", docDireccion);

            //ChF: Estados del pedido
            BasicDBObject docEstado = new BasicDBObject();
            ArrayList arrayEstados = new ArrayList();
            //ChF: Ya que es un pedido nuevo, el único estado que debería tener es el primero
            docEstado.put("id", pedido.getEstado().getId());
            docEstado.put("fechaHora", pedido.getEstado().getFechaHora());
            docEstado.put("username", pedido.getEstado().getUsername()); //pedido.getUsername()
            arrayEstados.add(docEstado);
            docPedido.put("estados", arrayEstados);

            //ChF: Lista de productos del pedido
            BasicDBObject docProductoPedido;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayAdicionales = new ArrayList();
            ArrayList arrayPromociones = new ArrayList();

            List<PizzaPromocion> promocionPizzas;
            ArrayList arrayPromocionPizzas;

            List<AdicionalPromocion> promocionAdicionales;
            ArrayList arrayPromocionAdicionales;

            BasicDBObject docPromocionProducto;

            for (ProductoPedido productoPedido : pedido.getProductos()) {
                docProductoPedido = new BasicDBObject();

                docProductoPedido.put("id", productoPedido.getProducto().getId());
                docProductoPedido.put("nombre", productoPedido.getProducto().getNombre());
                docProductoPedido.put("precioUnitario", productoPedido.getPrecioUnitario());
                docProductoPedido.put("cantidad", productoPedido.getCantidad());
                docProductoPedido.put("precioTotal", productoPedido.getPrecioTotal());

                if (productoPedido.getProducto().esPizza()) {
                    docProductoPedido.put("tamanoId", ((PizzaPedido) productoPedido.getProducto()).getTamano().getId());
                    arrayPizzas.add(docProductoPedido);
                } else if (productoPedido.getProducto().esAdicional()) {
                    arrayAdicionales.add(docProductoPedido);
                } else if (productoPedido.getProducto().esPromocion()) {
                    //ChF: Array pizzas de un documento del array promociones
                    promocionPizzas = ((Promocion) productoPedido.getProducto()).getPizzas();
                    if (promocionPizzas != null) {
                        arrayPromocionPizzas = new ArrayList();
                        for (PizzaPromocion promocionPizza : promocionPizzas) {
                            docPromocionProducto = new BasicDBObject();
                            docPromocionProducto.put("nombre", promocionPizza.getNombre());
                            docPromocionProducto.put("tamanoId", promocionPizza.getTamanoId());
                            docPromocionProducto.put("cantidad", promocionPizza.getCantidad());
                            arrayPromocionPizzas.add(docPromocionProducto);
                        }
                        docProductoPedido.put("pizzas", arrayPromocionPizzas);
                    }
                    //ChF: Array adicionales de un documento del array promociones
                    promocionAdicionales = ((Promocion) productoPedido.getProducto()).getAdicionales();
                    if (promocionAdicionales != null) {
                        arrayPromocionAdicionales = new ArrayList();
                        for (AdicionalPromocion promocionAdicional : promocionAdicionales) {
                            docPromocionProducto = new BasicDBObject();
                            docPromocionProducto.put("nombre", promocionAdicional.getNombre());
                            docPromocionProducto.put("cantidad", promocionAdicional.getCantidad());
                            arrayPromocionAdicionales.add(docPromocionProducto);
                        }
                        docProductoPedido.put("adicionales", arrayPromocionAdicionales);
                    }
                    arrayPromociones.add(docProductoPedido);
                }
            }

            BasicDBObject docProducto = new BasicDBObject();
            if (arrayPizzas.size() > 0) {
                docProducto.put("pizzas", arrayPizzas);
            }

            if (arrayAdicionales.size() > 0) {
                docProducto.put("adicionales", arrayAdicionales);
            }

            if (arrayPromociones.size() > 0) {
                docProducto.put("promociones", arrayPromociones);
            }
            docPedido.put("productos", docProducto);

            //ChF: Quizá la siguiente línea (comentada) debería estar en el servlet
            //pedido.calcularPrecioPedido();
            docPedido.put("precioPedido", pedido.getPrecioPedido());

            coleccion.insert(docPedido);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }

    }

    public Pedido buscarPedido(int id) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Pedido pedido = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);
            DBCursor cursor = coleccion.find(query);

            DBObject dbo;
            if (cursor.hasNext()) {
                dbo = cursor.next();
                pedido = obtenerPedido(dbo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedido;

    }

    public List<Pedido> obtenerPedidosDeUsername(String username) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        List<Pedido> pedidos = null;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            DBCursor cursor = coleccion.find(query);

            pedidos = new ArrayList<>();

            DBObject dbo;
            while (cursor.hasNext()) {
                dbo = cursor.next();
                pedidos.add(obtenerPedido(dbo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedidos;

    }

    private Pedido obtenerPedido(DBObject dbo) {
        Pedido pedido;
        //ChF: Declaración de variables necesarias para llenar el objeto pedido.
        Direccion direccion;

        List<Estado> estados = new ArrayList<>();
        Estado estado;
        BasicDBList docArrayEstados;
        BasicDBObject docEstado;

        List<ProductoPedido> productos = new ArrayList<>();
        ProductoPedido productoPedido;
        PizzaPedido pizza;
        Tamano tamano;
        Adicional adicional;
        Promocion promocion;
        BasicDBList docArrayProductos;
        BasicDBObject docProducto;

        pedido = new Pedido();

        pedido.setId((int) dbo.get("_id"));
        pedido.setUsername((String) dbo.get("username"));

        //ChF: Objeto dirección del objeto pedido.
        direccion = new Direccion();
        direccion.setCalle((String) ((DBObject) dbo.get("direccion")).get("calle"));
        direccion.setDistrito((String) ((DBObject) dbo.get("direccion")).get("distrito"));
        pedido.setDireccion(direccion);

        //ChF: Lista de estados del objeto pedido.
        docArrayEstados = (BasicDBList) dbo.get("estados");
        for (Object objEstado : docArrayEstados) {
            docEstado = (BasicDBObject) objEstado;
            estado = new Estado();
            estado.setId((int) docEstado.get("id"));
            estado.setFechaHora((String) docEstado.get("fechaHora"));
            estado.setUsername((String) docEstado.get("username"));
            estados.add(estado);
        }
        pedido.setEstado(estados.get(estados.size()-1));

        //ChF: Lista de productos del objeto pedido.
        //ChF: Array pizzas del documento productos.
        docArrayProductos = (BasicDBList) ((DBObject) dbo.get("productos")).get("pizzas");
        if (docArrayProductos != null) {
            for (Object objProducto : docArrayProductos) {
                docProducto = (BasicDBObject) objProducto;

                productoPedido = new ProductoPedido();
                pizza = new PizzaPedido();
                pizza.setId((int) docProducto.get("id"));
                pizza.setNombre((String) docProducto.getString("nombre"));
                tamano = new Tamano();
                tamano.setId((int) docProducto.get("tamanoId"));
                tamano.setPrecio((float) ((double) docProducto.get("precioUnitario")));
                pizza.setTamano(tamano);
                productoPedido.setProducto(pizza);
                productoPedido.setCantidad((int) docProducto.get("cantidad"));

                productos.add(productoPedido);
            }
        }

        //ChF: Array adicionales del documento productos.
        docArrayProductos = (BasicDBList) ((DBObject) dbo.get("productos")).get("adicionales");
        if (docArrayProductos != null) {
            for (Object objProducto : docArrayProductos) {
                docProducto = (BasicDBObject) objProducto;

                productoPedido = new ProductoPedido();
                adicional = new Adicional();
                adicional.setId((int) docProducto.get("id"));
                adicional.setNombre((String) docProducto.getString("nombre"));
                adicional.setPrecio((float) ((double) docProducto.get("precioUnitario")));
                productoPedido.setProducto(adicional);
                productoPedido.setCantidad((int) docProducto.get("cantidad"));

                productos.add(productoPedido);
            }
        }

        //ChF: Array promociones del documento productos.
        docArrayProductos = (BasicDBList) ((DBObject) dbo.get("productos")).get("promociones");
        if (docArrayProductos != null) {
            for (Object objProducto : docArrayProductos) {
                docProducto = (BasicDBObject) objProducto;

                productoPedido = new ProductoPedido();
                promocion = new Promocion();
                promocion.setId((int) docProducto.get("id"));
                promocion.setNombre((String) docProducto.getString("nombre"));
                promocion.setPrecio((float) ((double) docProducto.get("precioUnitario")));
                productoPedido.setProducto(promocion);
                productoPedido.setCantidad((int) docProducto.get("cantidad"));

                productos.add(productoPedido);
            }
        }
        //ChF: Son tres for() porque:
        //1. PizzaPedido necesita establecer el precio mediante su objeto Tamano tamano.
        //2. Consecuencia de lo anterior. No puedo añadir setPrecio(float precio) a 
        //   IProducto, ya que sería incompatible con PizzaPedido que requería también 
        //   el parámetro id.
        //3. En la colección pedido hay un array por cada tipo de producto, están separados
        //   así para saber a qué colección recurrir si se necesita más información sobre el
        //   producto (usando el id) como por ejemplo ingredientes en el caso de pizzas o el
        //   contenido de una promoción en el caso de promociones.

        pedido.setProductos(productos);

        pedido.calcularPrecioPedido();

        return pedido;
    }

    private int obtenerSiguienteId() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        int mayor = 0;
        int idRecibido;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                idRecibido = (int) dbo.get("_id");
                if (idRecibido > mayor) {
                    mayor = idRecibido;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return mayor + 1;
    }

}
