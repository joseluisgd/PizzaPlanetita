package ulima.edu.pe.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private static final String USERNAME = "pizza";
    private static final String PASSWORD = "planeta";
    private static final String HOST = "ds023714.mlab.com";
    private static final String PORT = "23714";
    private static final String DATABASE = "pizzaplaneta";

    private static final MongoClientURI URI = new MongoClientURI("mongodb://" + USERNAME + ":" + PASSWORD + "@" + HOST + ":" + PORT + "/" + DATABASE);

//    private static MongoClient cliente = null;

    private ConexionMLab() {
    }

    public static MongoClient getMongoClient() {
        try {
            return new MongoClient(URI);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
//    public static void closeMongoClient() {
//        cliente.close();
//        cliente = null;
//    }
}