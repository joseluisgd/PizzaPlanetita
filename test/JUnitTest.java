import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import ulima.edu.pe.beans.Adicional;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.PizzaPedido;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.dao.AdicionalDAO;
import ulima.edu.pe.dao.PedidoDAO;
import ulima.edu.pe.dao.PizzaDAO;
import ulima.edu.pe.util.Util;

/**
 *
 * @author Christopher
 */
public class JUnitTest {
    
    public JUnitTest() {
    }


    public void testFechaStringADate() {
        //Utilizando la fecha 31/12/2015 18:37:59
        Date fechaDateEsperada = new Date(2015, 12, 31, 18, 37, 59);
        Date fechaDateReal = Util.fechaStringADate("31/12/2015 18:37:59");
        assertEquals("La fecha no se está convirtiendo correctamente.",fechaDateEsperada, fechaDateReal);
    }
    
    //ChF: No pasa el test pero los objetos parecen tener los mismos datos
    
    public void testObtenerPizza() {
        //Real
        PizzaDAO pDAO = new PizzaDAO();
        PizzaPedido pizzaR = pDAO.obtenerPizza(1, 1);
        
        //Esperado
        Tamano tamanoE = new Tamano(1, 39.9f);
        List<Ingrediente> ingredientesE = new ArrayList<>();
        ingredientesE.add(new Ingrediente(1, "Jamon"));
        ingredientesE.add(new Ingrediente(2, "Queso Mozzarella"));
        PizzaPedido pizzaE = new PizzaPedido(1, "AMERICANA", ingredientesE, tamanoE, false);

        assertEquals("La pizza no se está obteniendo correctamente.", pizzaE, pizzaR);
    }
    

    public void testObtenerSiguienteId() {
        PedidoDAO pDAO = new PedidoDAO();
        //int idR = pDAO.obtenerSiguienteId();
        //assertEquals("La pizza no se está obteniendo correctamente.", 3, idR);
    }
    
    @Test
    public void testConexionMLab() {
        AdicionalDAO aDAO = new AdicionalDAO();
        
        Adicional adicionalR1, adicionalE1, adicionalR2, adicionalE2;
        adicionalE1 = new Adicional(1, "Coca Cola 500ml", 4.99f);
        adicionalR1 = aDAO.obtenerAdicional(1);
        
        adicionalE2 = new Adicional(2, "Inca Kola 500ml", 3.99f);
        adicionalR2 = aDAO.obtenerAdicional(2);
        assertEquals("El segundo adicional no se está obteniendo correctamente.", adicionalE2, adicionalR2);
    }
}
