package ulima.edu.pe.util;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// Verificar si compila
public class Util {
	private Util singleton = null;
	
	private Util {}
	
	public static Util getInstance() {
		if (singleton == null) {
			singleton = new Util;
		}
		return singleton;
	}
	
	public static String obtenerFechaHoraActual() {
		// Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00")); // No funciona
		// c.getTime(); // Sigue saliendo la hora en GMT-00:00.
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, -5);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(c.getTime());
	}
}
