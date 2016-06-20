package ulima.edu.pe.beans;

import java.util.List;
//Elimine el atributo tipo y precio, cree una clase tamano

//Chf: Creo que es necesario cambiar esta clase a abstracta
//y crear dos clases PizzaPredeterminada y PizzaPersonalizada
//que hereden de la abstracta
public class Pizza extends Producto {

//    private int id;
//    private String nombre;
    private List<Ingrediente> ing;
    private List<Tamano> tam; //solo para pizzas definidas
    private String tamano; //solo para pizzas personalizadas
//    private float precio;//solo para pizzas personalizadas

    public Pizza() {
    }

    public Pizza(String nombre, List<Ingrediente> ing, String tamano, float precio) {
        super.nombre = nombre;
        this.ing = ing;
        this.tamano = tamano;
        super.precio = precio;
    }

//    public Pizza(String nombre, List<Ingrediente> ing, String tamano, float precio) {
//        super.nombre = nombre;
//        this.ing = ing;
//        this.tamano = tamano;
//        super.precio = precio;
//    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Pizza(int id, String nombre, List<Ingrediente> ing, List<Tamano> tam) {
        this.id = id;
        this.nombre = nombre;
        this.ing = ing;
        this.tam = tam;
    }

    public List<Ingrediente> getIng() {
        return ing;
    }

    public void setIng(List<Ingrediente> ing) {
        this.ing = ing;
    }

    public List<Tamano> getTam() {
        return tam;
    }

    public void setTam(List<Tamano> tam) {
        this.tam = tam;
    }

}
