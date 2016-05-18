package ulima.edu.pe.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import ulima.edu.pe.beans.Ingrediente;
import ulima.edu.pe.util.ConexionMLab;

public class PedidoPersonalizadoDAO {
//Lista de ingredientes por usuario. (ingredientes, Usuario usuario)

    public void ingresarPedidoxUsuario(List<Ingrediente> ingredientes, String usuario) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingredientesxusuario");

            BasicDBObject docIngredienteXUsuario = new BasicDBObject();

            docIngredienteXUsuario.put("id", obtenerSiguienteId());
            docIngredienteXUsuario.put("usu", usuario);
            
            BasicDBObject docIngrediente;
            ArrayList arrayIngredientes = new ArrayList();
            for (Ingrediente ingrediente : ingredientes) {
                docIngrediente = new BasicDBObject();
                docIngrediente.put("id", ingrediente.getId());
                docIngrediente.put("nombre", ingrediente.getNombre());
                arrayIngredientes.add(docIngrediente);
            }
            docIngredienteXUsuario.put("ingredientes", arrayIngredientes);

            coleccion.insert(docIngredienteXUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
    }

    private int obtenerSiguienteId() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int cont = 0;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingredientesxusuario");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                cont = cont + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return cont + 1;
    }

}
