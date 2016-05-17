package ulima.edu.pe.util;

// No sé si compila, verificar
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
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"));
		// Incompleto
	}
	
}
