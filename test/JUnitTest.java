import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
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
}
