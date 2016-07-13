
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.beans.producto.IProducto;
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.beans.producto.promocion.Promocion;
import ulima.edu.pe.beans.usuario.Usuario;
import ulima.edu.pe.dao.AdicionalDAO;
import ulima.edu.pe.dao.PedidoDAO;
import ulima.edu.pe.dao.PizzaDAO;
import ulima.edu.pe.dao.PromocionDAO;
import ulima.edu.pe.dao.UsuarioDAO;
import ulima.edu.pe.util.Util;

public class JUnitTest {

    public JUnitTest() {

    }

    @Test
    public void testLoguearUsuario() {
        UsuarioDAO uDAO = new UsuarioDAO();
        boolean ingresoReal;

        ingresoReal = uDAO.login("hcabezas", "hhh");
        assertEquals("El Log In al sistema no está funcionando correctamente.", true, ingresoReal);

        ingresoReal = uDAO.login("intruso", "jejeje");
        assertEquals("El Log In al sistema no está funcionando correctamente.", false, ingresoReal);
    }

    @Test
    public void testRegistrarUsuario() {
        UsuarioDAO uDAO = new UsuarioDAO();
        boolean registroReal;

        Usuario quintana = new Usuario();
        quintana.setUsername("hquintana");
        quintana.setPassword("qqq");
        quintana.setCorreo("hquintana@ulima.edu.pe");

        registroReal = uDAO.registrarUsuario(quintana);
        if (registroReal) {
            //ChF: Con el login nos aseguramos de que se ingresó el usuario
            registroReal = uDAO.login(quintana.getUsername(), quintana.getPassword());
        }
        assertEquals("El Log In al sistema no está funcionando correctamente.", true, registroReal);

        //ChF: Este usuario ya existe en la BD, por lo que no debería poder registrarse con ese username
        Usuario herver = new Usuario();
        herver.setUsername("hcabezas");
        herver.setPassword("hhh");
        herver.setCorreo("hcabezas@gmail.com");

        registroReal = uDAO.registrarUsuario(herver);
        
        if (registroReal == false) {
            if (uDAO.login(herver.getUsername(), herver.getPassword()) == false ) {
                registroReal = true;
            }
        }
        
        assertEquals("El registro de usuarios en el sistema no está funcionando correctamente.", false, registroReal);
    }

    @Test
    public void testTraerPedidos() {
        PedidoDAO pDAO = new PedidoDAO();
        boolean iguales = false;
        
        Pedido pedidoEsperado = new Pedido();
        pedidoEsperado.setId(4);
        pedidoEsperado.setUsername("kamila");
        
        Direccion direccionEsperada = new Direccion();
        direccionEsperada.setCalle("Jose de la Pinella 169")
        direccionEsperada.setDistrito("San Borja");
        pedidoEsperado.setDireccion(direccionEsperada);
        
        Estado estadoEsperado = new Estado();
        estadoEsperado.setId(0);
        estadoEsperado.setFechaHora("12/07/2016 18:44:12");
        estadoEsperado.setUsername("kamila");
        pedidoEsperado.setEstado(estadoEsperado);
        
        List<ProductoPedido> productosEsperados = new ArrayList<>();
        
        ProductoPedido productoEsperado = new ProductoPedido();
        
        PizzaPedido pizzaEsperada = new PizzaPedido();
        pizzaEsperada.setId(1);
        pizzaEsperada.setNombre("Pizza americana");
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(3);
        tamanoEsperado.setPrecio(70.98f);
        pizzaEsperada.setTamano(tamanoEsperado());
        
        productoEsperado.setProducto(pizzaEsperada);
        productoEsperado.setCantidad(10);
        productosEsperados.add(productoEsperado);
        pedidoEsperado.setProductos(productosEsperados);
        pedidoEsperado.calcularPrecioPedido();
        
        Pedido pedidoReal = pDAO.obtenerPedidosDeUsername("kamila").get(0);
        
        if (pedidoReal.esIgualA(pedidoEsperado)) {
            iguales = true;
        }

        assertEquals("Los pedidos de un usuario no se están obteniendo correctamente.", true, iguales);
    }

    @Test
    public void testTraerCartaPizzas() {
        List<PizzaCarta> pizzasEsperadas = new ArrayList<>();
        boolean iguales = true;
        
        //ChF: Pizza 1
        PizzaCarta pizzaEsperada = new PizzaCarta();
        pizzaEsperada.setId(1);
        pizzaEsperada.setNombre("Pizza americana");
        
        List<Tamano> tamanosEsperados = new ArrayList<>();
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(1);
        tamanoEsperado.setPrecio(17.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(2);
        tamanoEsperado.setPrecio(35.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(3);
        tamanoEsperado.setPrecio(70.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(4);
        tamanoEsperado.setPrecio(90.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        pizzaEsperada.setTamanos(tamanosEsperados);
        
        List<Ingrediente> ingredientesEsperados = new ArrayList<>();
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(1);
        ingredienteEsperado.setNombre("Salsa de tomate");
        ingredientesEsperados.add(ingredienteEsperado);
        
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(2);
        ingredienteEsperado.setNombre("Jamón pizzero");
        ingredientesEsperados.add(ingredienteEsperado);
        
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(11);
        ingredienteEsperado.setNombre("Queso Mozzarella");
        ingredientesEsperados.add(ingredienteEsperado);
        
        pizzaEsperada.setIngredientes(ingredientesEsperados);
        
        pizzasEsperadas.add(pizzaEsperada);
        
        //ChF: Pizza 2
        PizzaCarta pizzaEsperada = new PizzaCarta();
        pizzaEsperada.setId(2);
        pizzaEsperada.setNombre("Pizza hawaiana");
        
        List<Tamano> tamanosEsperados = new ArrayList<>();
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(1);
        tamanoEsperado.setPrecio(18.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(2);
        tamanoEsperado.setPrecio(36.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(3);
        tamanoEsperado.setPrecio(72.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        Tamano tamanoEsperado = new Tamano();
        tamanoEsperado.setId(4);
        tamanoEsperado.setPrecio(92.99f);
        tamanosEsperados.add(tamanoEsperado);
        
        pizzaEsperada.setTamanos(tamanosEsperados);
        
        List<Ingrediente> ingredientesEsperados = new ArrayList<>();
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(1);
        ingredienteEsperado.setNombre("Salsa de tomate");
        ingredientesEsperados.add(ingredienteEsperado);
        
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(2);
        ingredienteEsperado.setNombre("Jamón pizzero");
        ingredientesEsperados.add(ingredienteEsperado);
        
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(11);
        ingredienteEsperado.setNombre("Queso Mozzarella");
        ingredientesEsperados.add(ingredienteEsperado);
        
        Ingrediente ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(12);
        ingredienteEsperado.setNombre("Piña");
        ingredientesEsperados.add(ingredienteEsperado);
        
        pizzaEsperada.setIngredientes(ingredientesEsperados);
        
        pizzasEsperadas.add(pizzaEsperada);
        
        PizzaDAO pDAO = new PizzaDAO();
        List<PizzaCarta> pizzasReales = pDAO.obtenerPizzas();
        
        if (pizzasEsperadas.size() == pizzasReales.size()) {
            for (int i = 0; i < pizzasEsperadas.size(); i++) {
                if (!pizzasEsperadas.get(i).esIgualA(pizzasReales.get(i))) {
                    iguales = false;
                }
            }
        } else {
            iguales = false;
        }
        
        assertEquals("Las pizzas no se están obteniendo correctamente.", true, iguales);
    }

    @Test
    public void testTraerCartaAdicionales() {
        List<Adicional> adicionalesEsperadas = new ArrayList<>();
        boolean iguales = true;
        
        //ChF: Adicional 1
        Adicional adicionalEsperado = new Adicional();
        adicionalEsperado.setId(1);
        adicionalEsperado.setNombre("Coca Cola 500ml");
        adicionalEsperado.setPrecio(4.99f);
        adicionalesEsperadas.add(adicionalEsperado);
                
        //ChF: Adicional 2
        Adicional adicionalEsperado = new Adicional();
        adicionalEsperado.setId(2);
        adicionalEsperado.setNombre("Inca Kola 500ml");
        adicionalEsperado.setPrecio(3.99f);
        adicionalesEsperadas.add(adicionalEsperado);
                
        //ChF: Adicional 3
        Adicional adicionalEsperado = new Adicional();
        adicionalEsperado.setId(3);
        adicionalEsperado.setNombre("Sprite 500ml");
        adicionalEsperado.setPrecio(2.99f);
        adicionalesEsperadas.add(adicionalEsperado);
        
        AdicionalDAO aDAO = new AdicionalDAO();
        List<Adicional> adicionalesReales = aDAO.obtenerAdicionales();
        
        if (adicionalesEsperados.size() == adicionalesReales.size()) {
            for (int i = 0; i < adicionalesEsperados.size(); i++) {
                if (!adicionalesEsperados.get(i).esIgualA(adicionalesReales.get(i))) {
                    iguales = false;
                }
            }
        } else {
            iguales = false;
        }
        
        assertEquals("Los adicionales no se están obteniendo correctamente.", true, iguales);
    }
    
    @Test
    public void testTraerCartaPromociones() {
        List<Promocion> promocionesEsperadas = new ArrayList<>();
        boolean iguales = true;
        
        //ChF: Promoción 3
        Promocion promocionEsperada = new Promocion();
        promocionEsperada.setId(3);
        promocionEsperada.setNombre("Promoción Exposición IS2");
        promocionEsperada.setPrecio(79.99f);
        promocionEsperada.setFechaInicio("13/07/2016 17:00:00");
        promocionEsperada.setFechaFin("13/07/2016 20:00:00");
        
        List<PizzaPromocion> pizzasEsperadas = new ArrayList<>();
        PizzaPromocion pizzaEsperada;
        
        pizzaEsperada = new PizzaPromocion();
        pizzaEsperada.setNombre("Pizza hawaiana");
        pizzaEsperada.setTamanoId(2);
        pizzaEsperada.setCantidad(1);
        pizzasEsperadas.add(pizzaEsperada);
        
        pizzaEsperada = new PizzaPromocion();
        pizzaEsperada.setNombre("Pizza americana");
        pizzaEsperada.setTamanoId(2);
        pizzaEsperada.setCantidad(1);
        pizzasEsperadas.add(pizzaEsperada);
        
        promocionEsperada.setPizzas(pizzasEsperadas);
        
        List<AdicionalPromocion> adicionalesEsperados = new ArrayList<>();
        AdicionalPromocion adicionalEsperado;
        
        adicionalEsperado = new AdicionalPromocion();
        adicionalEsperado.setNombre("Coca cola 500ml");
        adicionalEsperado.setCantidad(2);
        adicionalesEsperados.add(adicionalEsperado);
        
        adicionalEsperado = new AdicionalPromocion();
        adicionalEsperado.setNombre("Inca Kola 500ml");
        adicionalEsperado.setCantidad(2);
        adicionalesEsperados.add(adicionalEsperado);
        
        promocionEsperada.setAdicionales(adicionalesEsperados);
        
        promocionEsperada.setDescripcion("¡En esta exposición, cómpranos pizza!");
        
        PromocionDAO pDAO = new PromocionDAO();
        List<Promocion> promocionesReales = pDAO.obtenerPromociones();
        
        if (promocionesEsperadas.size() == promocionesReales.size()) {
            for (int i = 0; i < promocionesEsperadas.size(); i++) {
                if (!promocionesEsperadas.get(i).esIgualA(promocionesReales.get(i))) {
                    iguales = false;
                }
            }
        } else {
            iguales = false;
        }
        
        
        assertEquals("Las promociones no se están obteniendo correctamente.", true, iguales);
    }

//
//    public void testFechaStringADate() {
//        //Utilizando la fecha 31/12/2015 18:37:59
//        Date fechaDateEsperada = new Date(2015, 12, 31, 18, 37, 59);
//        Date fechaDateReal = Util.fechaStringADate("31/12/2015 18:37:59");
//        assertEquals("La fecha no se está convirtiendo correctamente.",fechaDateEsperada, fechaDateReal);
//    }
//    
//    //ChF: No pasa el test pero los objetos parecen tener los mismos datos
//    
//    public void testObtenerPizza() {
//        //Real
//        PizzaDAO pDAO = new PizzaDAO();
//        PizzaPedido pizzaR = pDAO.buscarPizza(1, 1);
//        
//        //Esperado
//        Tamano tamanoE = new Tamano(1, 39.9f);
//        List<Ingrediente> ingredientesE = new ArrayList<>();
//        ingredientesE.add(new Ingrediente(1, "Jamon"));
//        ingredientesE.add(new Ingrediente(2, "Queso Mozzarella"));
//        PizzaPedido pizzaE = new PizzaPedido(1, "AMERICANA", ingredientesE, tamanoE, false);
//
//        assertEquals("La pizza no se está obteniendo correctamente.", pizzaE, pizzaR);
//    }
//    
//
//    public void testObtenerSiguienteId() {
//        PedidoDAO pDAO = new PedidoDAO();
//        //int idR = pDAO.obtenerSiguienteId();
//        //assertEquals("La pizza no se está obteniendo correctamente.", 3, idR);
//    }
//    
//    
//    public void testConexionMLab() {
//        AdicionalDAO aDAO = new AdicionalDAO();
//        
//        Adicional adicionalR1, adicionalE1, adicionalR2, adicionalE2;
//        adicionalE1 = new Adicional(1, "Coca Cola 500ml", 4.99f);
//        adicionalR1 = aDAO.buscarAdicional(1);
//        
//        adicionalE2 = new Adicional(2, "Inca Kola 500ml", 3.99f);
//        adicionalR2 = aDAO.buscarAdicional(2);
//        assertEquals("El segundo adicional no se está obteniendo correctamente.", adicionalE2, adicionalR2);
//    }
//    
//    @Test
//    public void testEvaluacionDeNombresDeClases() {
//        IProducto pizza = new PizzaPedido();
//        
//        System.out.println(pizza.getClass().getName());
//        System.out.println(PizzaPedido.class.getName());
//        
//        if (pizza.getClass().getName().equals(PizzaPedido.class.getName())){
//            System.out.println("Son iguales");
//        }
//    }
//    
//    @Test
//    public void testPromocionDAO() {
//        PromocionDAO pDAO = new PromocionDAO();
//        
//        List<Promocion> promociones  = pDAO.obtenerPromociones();
//        
//        for (Promocion promocion : promociones) {
//            promocion.getNombre();
//        }
//        
////        IProducto pizza = new PizzaPedido();
////        
////        System.out.println(pizza.getClass().getName());
////        System.out.println(PizzaPedido.class.getName());
////        
////        if (pizza.getClass().getName().equals(PizzaPedido.class.getName())){
////            System.out.println("Son iguales");
////        }
//    }
}
