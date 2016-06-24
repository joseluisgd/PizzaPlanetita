import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.beans.PizzaPedido;
import ulima.edu.pe.beans.Tamano;
import ulima.edu.pe.dao.PizzaDAO;
import ulima.edu.pe.util.Util;

/**
 *
 * @author Christopher
 */
public class JUnitTest {
    
    public JUnitTest() {
    }

    @Test
    public void testFechaStringADate() {
        //Utilizando la fecha 31/12/2015 18:37:59
        Date fechaDateEsperada = new Date(2015, 12, 31, 18, 37, 59);
        Date fechaDateReal = Util.fechaStringADate("31/12/2015 18:37:59");
        assertEquals("La fecha no se está convirtiendo correctamente.",fechaDateEsperada, fechaDateReal);
    }
    
    //ChF: No pasa el test pero los objetos parecen tener los mismos datos
    @Test
    public void testObtenerPizza() {
        //Real
        PizzaDAO pDAO = new PizzaDAO();
        PizzaPedido pizzaR = pDAO.obtenerPizza(1, 1);
        
        //Esperado
        Tamano tamanoE = new Tamano(1, 39.9f);
        List<Ingrediente> ingredientesE = new ArrayList<>();
        ingredientesE.add(new Ingrediente(1, "Jamon"));
        ingredientesE.add(new Ingrediente(2, "Queso Mozzarella"));
        PizzaPedido pizzaE = new PizzaPedido(1, "AMERICANA", ingredientesE, tamanoE);

        assertEquals("La pizza no se está obteniendo correctamente.", pizzaE, pizzaR);
    }
}
