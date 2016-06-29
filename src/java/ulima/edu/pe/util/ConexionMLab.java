package ulima.edu.pe.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private static final String USERNAME = "pizza";
    private static final String PASSWORD = "planeta";
    private static final String ADDRESS = "ds023714.mlab.com";
    private static final String PORT = "23714";
    private static final String DATABASE = "pizzaplaneta";

    private static final MongoClientURI URI = new MongoClientURI("mongodb://" + USERNAME + ":" + PASSWORD + "@" + ADDRESS + ":" + PORT + "/" + DATABASE);

    private static MongoClient cliente;

    private ConexionMLab() {
    }

    public static MongoClient getMongoClient() {
        try {
            cliente = new MongoClient(URI);
            return cliente;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void closeMongoClient() {
        cliente.close();
        cliente = null;
    }
}