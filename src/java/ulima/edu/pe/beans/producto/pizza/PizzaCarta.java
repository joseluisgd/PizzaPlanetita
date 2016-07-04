package ulima.edu.pe.beans.producto.pizza;

import java.util.List;

public class PizzaCarta extends Pizza {

    List<Tamano> tamanos;

    public PizzaCarta() {
    }

    public PizzaCarta(int id, String nombre, List<Ingrediente> ingredientes, List<Tamano> tamanos) {
        super(id, nombre, ingredientes);
        this.tamanos = tamanos;
    }

    public List<Tamano> getTamanos() {
        return tamanos;
    }

    public void setTamanos(List<Tamano> tamanos) {
        this.tamanos = tamanos;
    }

    //ChF: Implementada porque quizá sea útil
    public PizzaPedido convertirEnPizzaPedido(int tamanoId) {
        PizzaPedido pizza = new PizzaPedido();
        
        pizza.setId(id);
        pizza.setNombre(nombre);
        pizza.setIngredientes(ingredientes);
        for (Tamano tamano : tamanos) {
            if (tamanoId == tamano.getId()) {
                pizza.setTamano(new Tamano(tamanoId, tamano.getPrecio()));
                break;
            }
        }
        pizza.setPersonalizada(false);
        
        return pizza;
    }
}
