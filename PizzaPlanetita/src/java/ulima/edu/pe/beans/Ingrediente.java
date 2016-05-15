package ulima.edu.pe.beans;

/**
 * Created by fixt on 05/05/16.
 */
public class Ingrediente {
    private int id;
    private String nombre;

    public Ingrediente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Ingrediente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
