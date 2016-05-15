package ulima.edu.pe.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private static ConexionMLab singleton;
    private final String username = "pizza";
    private final String password = "pizza";
    private final String database = "pizzaplaneta";
    private MongoClient cliente;
    private MongoClientURI uri;

    private ConexionMLab() {
        uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds023442.mlab.com:23442/" + database);
        try {
            cliente = new MongoClient(uri);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    public static ConexionMLab getInstance() {
        if (singleton == null) {
            singleton = new ConexionMLab();
        }
        return singleton;
    }

    public MongoClient getConexion() {
        return cliente;
    }
}
