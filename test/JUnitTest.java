
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import ulima.edu.pe.beans.pedido.Direccion;
import ulima.edu.pe.beans.pedido.Estado;
import ulima.edu.pe.beans.pedido.Pedido;
import ulima.edu.pe.beans.pedido.ProductoPedido;
import ulima.edu.pe.beans.producto.Adicional;
import ulima.edu.pe.beans.producto.pizza.Ingrediente;
import ulima.edu.pe.beans.producto.pizza.PizzaCarta;
import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import ulima.edu.pe.beans.producto.pizza.Tamano;
import ulima.edu.pe.beans.producto.promocion.AdicionalPromocion;
import ulima.edu.pe.beans.producto.promocion.PizzaPromocion;
import ulima.edu.pe.beans.producto.promocion.Promocion;
import ulima.edu.pe.beans.usuario.Usuario;
import ulima.edu.pe.dao.AdicionalDAO;
import ulima.edu.pe.dao.PedidoDAO;
import ulima.edu.pe.dao.PizzaDAO;
import ulima.edu.pe.dao.PromocionDAO;
import ulima.edu.pe.dao.UsuarioDAO;

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
            if (uDAO.login(herver.getUsername(), herver.getPassword()) == false) {
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
        direccionEsperada.setCalle("Jose de la Pinella 169");
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
        pizzaEsperada.setTamano(tamanoEsperado);

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
        PizzaCarta pizzaEsperada;
        
        List<Tamano> tamanosEsperados;
        Tamano tamanoEsperado;
        
        List<Ingrediente> ingredientesEsperados;
        Ingrediente ingredienteEsperado;
        
        //ChF: Pizza 1
        pizzaEsperada = new PizzaCarta();
        pizzaEsperada.setId(1);
        pizzaEsperada.setNombre("Pizza americana");

        tamanosEsperados = new ArrayList<>();

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(1);
        tamanoEsperado.setPrecio(17.99f);
        tamanosEsperados.add(tamanoEsperado);

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(2);
        tamanoEsperado.setPrecio(35.99f);
        tamanosEsperados.add(tamanoEsperado);

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(3);
        tamanoEsperado.setPrecio(70.99f);
        tamanosEsperados.add(tamanoEsperado);

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(4);
        tamanoEsperado.setPrecio(90.99f);
        tamanosEsperados.add(tamanoEsperado);

        pizzaEsperada.setTamanos(tamanosEsperados);

        ingredientesEsperados = new ArrayList<>();

        ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(1);
        ingredienteEsperado.setNombre("Salsa de tomate");
        ingredientesEsperados.add(ingredienteEsperado);

        ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(2);
        ingredienteEsperado.setNombre("Jamón pizzero");
        ingredientesEsperados.add(ingredienteEsperado);

        ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(11);
        ingredienteEsperado.setNombre("Queso Mozzarella");
        ingredientesEsperados.add(ingredienteEsperado);

        pizzaEsperada.setIngredientes(ingredientesEsperados);

        pizzasEsperadas.add(pizzaEsperada);

        //ChF: Pizza 2
        pizzaEsperada = new PizzaCarta();
        pizzaEsperada.setId(2);
        pizzaEsperada.setNombre("Pizza hawaiana");

        tamanosEsperados = new ArrayList<>();

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(1);
        tamanoEsperado.setPrecio(18.99f);
        tamanosEsperados.add(tamanoEsperado);

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(2);
        tamanoEsperado.setPrecio(36.99f);
        tamanosEsperados.add(tamanoEsperado);

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(3);
        tamanoEsperado.setPrecio(72.99f);
        tamanosEsperados.add(tamanoEsperado);

        tamanoEsperado = new Tamano();
        tamanoEsperado.setId(4);
        tamanoEsperado.setPrecio(92.99f);
        tamanosEsperados.add(tamanoEsperado);

        pizzaEsperada.setTamanos(tamanosEsperados);

        ingredientesEsperados = new ArrayList<>();
        
        ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(1);
        ingredienteEsperado.setNombre("Salsa de tomate");
        ingredientesEsperados.add(ingredienteEsperado);

        ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(2);
        ingredienteEsperado.setNombre("Jamón pizzero");
        ingredientesEsperados.add(ingredienteEsperado);

        ingredienteEsperado = new Ingrediente();
        ingredienteEsperado.setId(11);
        ingredienteEsperado.setNombre("Queso Mozzarella");
        ingredientesEsperados.add(ingredienteEsperado);

        ingredienteEsperado = new Ingrediente();
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
        List<Adicional> adicionalesEsperados = new ArrayList<>();
        Adicional adicionalEsperado;
        
        boolean iguales = true;

        //ChF: Adicional 1
        adicionalEsperado = new Adicional();
        adicionalEsperado.setId(1);
        adicionalEsperado.setNombre("Coca Cola 500ml");
        adicionalEsperado.setPrecio(4.99f);
        adicionalesEsperados.add(adicionalEsperado);

        //ChF: Adicional 2
        adicionalEsperado = new Adicional();
        adicionalEsperado.setId(2);
        adicionalEsperado.setNombre("Inca Kola 500ml");
        adicionalEsperado.setPrecio(3.99f);
        adicionalesEsperados.add(adicionalEsperado);

        //ChF: Adicional 3
        adicionalEsperado = new Adicional();
        adicionalEsperado.setId(3);
        adicionalEsperado.setNombre("Sprite 500ml");
        adicionalEsperado.setPrecio(2.99f);
        adicionalesEsperados.add(adicionalEsperado);

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

    @Test
    public void testAgregarPedido() {
        PedidoDAO pDAO = new PedidoDAO();
        boolean iguales = false;

        Pedido pedidoAgregado = new Pedido();
        pedidoAgregado.setId(10);
        pedidoAgregado.setUsername("hcabezas");

        Direccion direccionAgregada = new Direccion();
        direccionAgregada.setCalle("Canaval y Moreyra 380");
        direccionAgregada.setDistrito("San Isidro");
        pedidoAgregado.setDireccion(direccionAgregada);

        Estado estadoAgregado = new Estado();
        estadoAgregado.setId(0);
        estadoAgregado.setFechaHora("13/07/2016 13:30:45");
        estadoAgregado.setUsername("hcabezas");
        pedidoAgregado.setEstado(estadoAgregado);

        List<ProductoPedido> productosAgregados = new ArrayList<>();

        ProductoPedido productoAgregado = new ProductoPedido();

        PizzaPedido pizzaAgregada = new PizzaPedido();
        pizzaAgregada.setId(1);
        pizzaAgregada.setNombre("Pizza americana");

        Tamano tamanoAgregado = new Tamano();
        tamanoAgregado.setId(3);
        tamanoAgregado.setPrecio(70.98f);
        pizzaAgregada.setTamano(tamanoAgregado);

        productoAgregado.setProducto(pizzaAgregada);
        productoAgregado.setCantidad(10);
        productosAgregados.add(productoAgregado);

        pedidoAgregado.setProductos(productosAgregados);

        pedidoAgregado.calcularPrecioPedido();

        pDAO.agregarPedido(pedidoAgregado);

        Pedido pedidoObtenido = pDAO.buscarPedido(10);

        if (pedidoAgregado.esIgualA(pedidoObtenido)) {
            iguales = true;
        }

        assertEquals("Los pedidos no se están ingresando correctamente.", true, iguales);
    }

}
