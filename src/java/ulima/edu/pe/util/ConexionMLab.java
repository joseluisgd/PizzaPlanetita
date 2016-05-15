package ulima.edu.pe.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private static ConexionMLab singleton;
    private final String username = "grupo01";
    private final String password = "progra";
    private final String database = "basededatos";
    private MongoClient cliente;
    private MongoClientURI uri;

    private ConexionMLab() {
        uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds063124.mongolab.com:63124/" + database);
        //mongodb://grupo01:progra@ds063124.mongolab.com:63124/basededatos
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
