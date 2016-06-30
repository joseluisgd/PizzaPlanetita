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
import ulima.edu.pe.beans.pedido.Estado;
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.pedido.ProductoPedido;
import ulima.edu.pe.beans.producto.pizza.Pizza;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import ulima.edu.pe.beans.producto.promocion.Promocion;
import ulima.edu.pe.beans.usuario.Usuario;
import ulima.edu.pe.util.ConexionMLab;

public class PedidoDAO {

    public void agregarPedido(Pedido pedido, int identificador) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        try {
            DB db = mongo.getDB("basededatos");
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
            //ChF: Ya que es un pedido nuevo, el único estado que debería tener es "En camino"
            docEstado.put("id", pedido.getEstados().get(0).getId());
            docEstado.put("fechaHora", pedido.getEstados().get(0).getFechaHora());
            docEstado.put("username", pedido.getEstados().get(0).getUsername()); //pedido.getUsername()
            docPedido.put("estados", docEstado);

            //ChF: Lista de productos del pedido
            BasicDBObject docProductoPedido;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayAdicionales = new ArrayList();
            ArrayList arrayPromociones = new ArrayList();
            for (ProductoPedido productoPedido : pedido.getProductos()) {
                docProductoPedido = new BasicDBObject();
                
                docProductoPedido.put("nombre", productoPedido.getProducto().getNombre());
                docProductoPedido.put("precioUnitario", productoPedido.getPrecioUnitario());
                docProductoPedido.put("cantidad", productoPedido.getCantidad());
                docProductoPedido.put("precioTotal", productoPedido.getPrecioTotal());
                
                if (productoPedido.getClass().getName().equals(PizzaPedido.class.getName())) {
                    docProductoPedido.put("tamanoId", ((PizzaPedido) productoPedido.getProducto()).getTamano().getId());
                    arrayPizzas.add(docProductoPedido);
                } else if (productoPedido.getClass().getName().equals(Adicional.class.getName())) {
                    arrayAdicionales.add(docProductoPedido);
                } else if (productoPedido.getClass().getName().equals(Promocion.class.getName())) {
                    arrayPromociones.add(docProductoPedido);
                }
            }
            
            BasicDBObject docProducto = new BasicDBObject();
            if (arrayPizzas.size() > 0 ) {
                docProducto.put("pizzas", arrayPizzas);
            }
            
            if (arrayAdicionales.size() > 0 ) {
                docProducto.put("adicionales", arrayAdicionales);
            }
            
            if (arrayPromociones.size() > 0 ) {
                docProducto.put("promociones", arrayPromociones);
            }
            docPedido.put("productos", docProducto); 
            
            //pedido.calcularPrecioPedido();
            docPedido.put("precioPedido", pedido.getPrecioPedido());
            
            // Ewwwwwww:
            /*
            docProductoPedido = new BasicDBObject();
            docProductoPedido.put("calle", direccion.getCalle());
            docProductoPedido.put("distrito", direccion.getDistrito());
            arrayPizzas.add(docProductoPedido);

            docCliente.put("direcciones", arrayDirecciones);

            BasicDBObject docPizza;
            BasicDBObject docIngrediente;
            BasicDBObject docIngrediente2;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayIngredientes = new ArrayList();

            //ChF: Me parece que la solución planteada solo soporta el que todas las pizzas
            //del pedido sean personalizadas o predeterminadas, no se pueden mezclar (por ahora).
            //Se me ocurre poner el identificador como un atributo de la pizza, así, al recorrer
            //las pizzas del pedido, se evalúa de qué tipo es y en base a eso se hace una u otra
            //acción.
            List<PizzaPedido> pizzas = pedido.getPizzas();
            int a = 0;
            if (identificador == 1) {
                List<Ingrediente> ingredientes;
                for (Pizza pizza : pizzas) {
                    docPizza = new BasicDBObject();
                    ingredientes = pizza.getIngredientes();
                    //ERROR EN ESTA LINEA PARA INGRESAR UN PEDIDO PREDETERMINADO. FALTA CASTEAR
                    //ChF: Corregido 12/06/16
                    for (Ingrediente ingrediente : ingredientes) {
                        docIngrediente = new BasicDBObject();
                        docIngrediente.put("id", ingrediente.getId());
                        docIngrediente.put("nombre", ingrediente.getNombre());
                        arrayIngredientes.add(docIngrediente);
                    }
                    a++;
                    docPizza.put("idPi", a);
                    docPizza.put("nombrePizza", pizza.getNombre());
                    docPizza.put("url", " ");
                    docPizza.put("Tamano", pizza.getTamano());
                    docPizza.put("precio", pizza.getPrecio());
                    docPizza.put("Ingredientes", arrayIngredientes);

                    arrayPizzas.add(docPizza);
                }
            } else {
                List<Ingrediente> ingredientes = null;
                int i = 0;
                for (Pizza pizza : pizzas) {
                    ingredientes = new ArrayList<>();
                    docPizza = new BasicDBObject();
                    ingredientes = pizza.getIngredientes();
                    //ERROR EN ESTA LINEA PARA INGRESAR UN PEDIDO PREDETERMINADO. FALTA CASTEAR
                    //ChF: Corregido 12/06/16

                    //pepe :me rindo.
                    Ingrediente ing = ingredientes.get(0);
                    Ingrediente ing2 = ingredientes.get(1);
                    docIngrediente = new BasicDBObject();
                    docIngrediente.put("id", ing.getId());
                    docIngrediente.put("nombre", ing.getNombre());
                    docIngrediente2 = new BasicDBObject();
                    docIngrediente2.put("id", ing2.getId());
                    docIngrediente2.put("nombre", ing2.getNombre());
                    arrayIngredientes.add(docIngrediente);
                    arrayIngredientes.add(docIngrediente2);

                    i++;

                    a++;
                    docPizza.put("idPi", a);
                    docPizza.put("nombrePizza", pizza.getNombre());
                    docPizza.put("url", " ");
                    docPizza.put("Tamano", pizza.getTamano());
                    docPizza.put("precio", pizza.getPrecio());
                    docPizza.put("Ingredientes", arrayIngredientes);

                    arrayPizzas.add(docPizza);
                }
            }

            docPedido.put("Pizzas", arrayPizzas);

            List<Adicional> adicionales = pedido.getAdicionales();
            if (adicionales != null) {
                BasicDBObject docProducto;
                ArrayList arrayProductos = new ArrayList();
                for (Adicional producto : adicionales) {
                    docProducto = new BasicDBObject();
                    docProducto.put("id", producto.getId());
                    docProducto.put("nombre", producto.getNombre());
                    docProducto.put("precio", producto.getPrecio());
                    arrayProductos.add(docProducto);
                }
                docPedido.put("Productos", arrayProductos);
            }
            docPedido.put("montoTotal", pedido.getPrecioPedido());*/
            coleccion.insert(docPedido);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }

    }

    public Pedido buscarPedidoPorID(int id) {
        MongoClient mongo = ConexionMLab.getMongoClient();
        Pedido pedido = null;
        Estado estado = null;
        Usuario usuario = null;
        UsuarioDAO login = null;
        List<Pizza> pizzas = null;
        List<Ingrediente> ingredientes = null;
        List<Adicional> productos = null;
        Ingrediente ingred = null;
        Pizza pizzita = null;
        Adicional produ = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject) dbo.get("Estado");
                estado = new Estado();
                estado.setFechaHora((String) dbo2.get("fechahora"));
                estado.setId((Integer) dbo2.get("id"));
                estado.setEstado((String) dbo2.get("estado"));

                pedido = new Pedido();
                pedido.setId((Integer) dbo.get("id"));
                pedido.setEstado(estado);
                usuario = new Usuario();
                login = new UsuarioDAO();
                usuario = login.buscarUsuario((String) dbo.get("usu"));
                pedido.setUsuario(usuario);
                pedido.setDireccion((String) dbo.get("direccion"));
                BasicDBList dbo3 = (BasicDBList) dbo.get("Pizzas");
                pizzas = new ArrayList<>();
                for (Object piz : dbo3) {
                    pizzita = new Pizza();
                    DBObject dbb = DBObject.class
                            .cast(piz);
                    BasicDBList dbo4 = (BasicDBList) dbb.get("Ingredientes");
                    ingredientes = new ArrayList<>();
                    for (Object ing : dbo4) {
                        ingred = new Ingrediente();
                        DBObject dbo5 = DBObject.class
                                .cast(ing);
                        ingred.setId((Integer) dbo5.get("id"));
                        ingred.setNombre((String) dbo5.get("nombre"));
                        ingredientes.add(ingred);
                    }
                    pizzita.setIng(ingredientes);
                    pizzas.add(pizzita);
                }
                BasicDBList dbo6 = (BasicDBList) dbo.get("Productos");
                productos = new ArrayList<>();
                for (Object pro : dbo6) {
                    produ = new Adicional();
                    DBObject dbo7 = DBObject.class
                            .cast(pro);
                    produ.setId((Integer) dbo7.get("id"));
                    produ.setNombre((String) dbo7.get("nombre"));
                    produ.setPrecio((Float) dbo7.get("precio"));
                    productos.add(produ);
                }
                pedido.setPizzas(pizzas);
                pedido.setProductos(productos);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionMLab.closeMongoClient();
        }
        return pedido;

    }

    public List<Pedido> buscarPedidoPorUsuario(String usu) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        Estado estado = null;
        Usuario usuario = null;
        UsuarioDAO login = null;
        List<Pizza> pizzas = null;
        List<Ingrediente> ingredientes = null;
        List<Adicional> productos = null;
        Ingrediente ingred = null;
        Pizza pizzita = null;
        Adicional produ = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", usu);
            query.put("usu", query1);
            DBCursor cursor = coleccion.find(query);
            pedidos = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject) dbo.get("Estado");
                estado = new Estado();
                estado.setFechaHora((String) dbo2.get("fechahora"));
                estado.setId((Integer) dbo2.get("id"));
                estado.setEstado((String) dbo2.get("estado"));

                pedido = new Pedido();
                pedido.setId((Integer) dbo.get("id"));
                pedido.setEstado(estado);
                usuario = new Usuario();
                login = new UsuarioDAO();
                usuario = login.buscarUsuario((String) dbo.get("usu"));
                pedido.setUsuario(usuario);
                pedido.setDireccion((String) dbo.get("direccion"));
                BasicDBList dbo3 = (BasicDBList) dbo.get("Pizzas");
                pizzas = new ArrayList<>();
                for (Object piz : dbo3) {
                    pizzita = new Pizza();
                    DBObject dbb = DBObject.class.cast(piz);
                    BasicDBList dbo4 = (BasicDBList) dbb.get("Ingredientes");
                    ingredientes = new ArrayList<>();
                    for (Object ing : dbo4) {
                        ingred = new Ingrediente();
                        DBObject dbo5 = DBObject.class.cast(ing);
                        ingred.setId((Integer) dbo5.get("id"));
                        ingred.setNombre((String) dbo5.get("nombre"));
                        ingredientes.add(ingred);
                    }
                    pizzita.setIng(ingredientes);
                    pizzas.add(pizzita);
                }
                BasicDBList dbo6 = (BasicDBList) dbo.get("Productos");
                productos = new ArrayList<>();
                for (Object pro : dbo6) {
                    produ = new Adicional();
                    DBObject dbo7 = DBObject.class.cast(pro);
                    produ.setId((Integer) dbo7.get("id"));
                    produ.setNombre((String) dbo7.get("nombre"));
                    produ.setPrecio((Float) dbo7.get("precio"));
                    productos.add(produ);
                }
                pedido.setPizzas(pizzas);
                pedido.setProductos(productos);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedidos;

    }

    public List<Pedido> getPedidos() {
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            pedidos = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                pedido = new Pedido();
                pedido = this.buscarPedidoPorID((Integer) dbo.get("id"));
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedidos;
    }

    public void actualizarEstado() {

    }

    //ChF: Devolver a private
    private int obtenerSiguienteId() {
        MongoClient mongo = ConexionMLab.getMongoClient();
        int mayor = 0;
        int idRecibido = 0;
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
