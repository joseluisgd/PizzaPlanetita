package ulima.edu.pe.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private final String username = "grupo01";
    private final String password = "progra";
    private final String database = "basededatos";
    //private final String username = "pizza";
    //private final String password = "pizza";
    //private final String database = "pizzaplanetatest";
    private MongoClient cliente;
    private MongoClientURI uri;

    public ConexionMLab() {
        uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds063124.mlab.com:63124/" + database);
        //uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds025742.mlab.com:25742/" + database);
        try {
            cliente = new MongoClient(uri);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    public MongoClient getConexion() {
        return cliente;
    }
}

/*
package ulima.edu.pe.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {
    
    //probar funcionamiento urgente
    private static final String username = "grupo01";
    private static final String password = "progra";
    private static final String database = "basededatos";
    //private static final String username = "pizza";
    //private static final String password = "pizza";
    //private static final String database = "pizzaplanetatest";
    private static final MongoClientURI uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds063124.mlab.com:63124/" + database);
    //private static final MongoClientURI uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds025742.mlab.com:25742/" + database);
    private MongoClient cliente;

    public MongoClient getConexion() {
        try {
            cliente = new MongoClient(uri);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }
}
*/
