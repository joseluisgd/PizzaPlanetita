package ulima.edu.pe.util;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Verificar si compila
public class Util {
    //private static Util singleton = null;

    private Util() {
    }

    //No es necesario ni siquiera instanciarlo una vez (al menos por ahora)
    //public static Util getInstance() {
    //	if (singleton == null) {
    //		singleton = new Util();
    //	}
    //	return singleton;
    //}
    public static String obtenerFechaHoraActual() {
        // Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00")); // No funciona
        // c.getTime(); // Sigue saliendo la hora en GMT-00:00.
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -5);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(c.getTime());
    }

    //Quizá sea útil para comparaciones de fecha como en
    //la evaluación de la validez/vigencia de una promoción
    public static Date fechaStringADate(String fechaString) {
        //Asumiendo un formato de fecha y hora:
        //dd/MM/yyyy HH:mm:ss
        int dia = Integer.parseInt(fechaString.substring(0, 2));
        int mes = Integer.parseInt(fechaString.substring(3, 5));
        int ano = Integer.parseInt(fechaString.substring(6, 10));
        int hora = Integer.parseInt(fechaString.substring(11, 13));
        int minuto = Integer.parseInt(fechaString.substring(14, 16));
        int segundo = Integer.parseInt(fechaString.substring(17, 19));
        
        Date fechaDate = new Date(ano, mes, dia, hora, minuto, segundo);

        return fechaDate;
    }
}
