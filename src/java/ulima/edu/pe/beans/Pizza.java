package ulima.edu.pe.beans;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {

    protected int id;
    protected String nombre;
    protected List<Ingrediente> ingredientes;

    public Pizza() {
        ingredientes = new ArrayList<>();
    }

    public Pizza(int id, String nombre, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
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

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
