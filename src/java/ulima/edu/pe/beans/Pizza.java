package ulima.edu.pe.beans;

import java.util.ArrayList;
import java.util.List;
//Elimine el atributo tipo y precio, cree una clase tamano

//Chf: Creo que es necesario cambiar esta clase a abstracta
//y crear dos clases PizzaPredeterminada y PizzaPersonalizada
//que hereden de la abstracta
public abstract class Pizza {

    private int id;
    private String nombre;
    private List<Ingrediente> ingredientes;
    
    /*
    private List<Tamano> tam; //solo para pizzas definidas
    private String tamano; //solo para pizzas personalizadas
    private float precio;//solo para pizzas personalizadas
    */

    public Pizza() {
        ingredientes = new ArrayList<>();
    }

    public Pizza(int id, String nombre, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

//    public Pizza(String nombre, List<Ingrediente> ing, String tamano, float precio) {
//        super.nombre = nombre;
//        this.ing = ing;
//        this.tamano = tamano;
//        super.precio = precio;
//    }
/*
    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Pizza(int id, String nombre, List<Ingrediente> ingredientes, List<Tamano> tam) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tam = tam;
    }
*/
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
    
/*
    public List<Tamano> getTam() {
        return tam;
    }

    public void setTam(List<Tamano> tam) {
        this.tam = tam;
    }
*/
}
