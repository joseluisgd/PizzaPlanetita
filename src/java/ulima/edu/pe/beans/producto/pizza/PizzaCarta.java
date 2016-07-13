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
    
    public boolean esIgualA(PizzaCarta pizza) {
        if (pizza.id != id) {
            return false;
        }
        if (!pizza.nombre.equals(nombre)) {
            return false;
        }
        if (pizza.ingredientes.size() == ingredientes.size()){
            for (int i = 0; i < pizza.ingredientes.size(); i++) {
                if (!pizza.ingredientes.get(i).esIgualA(ingredientes.size().get(i))){
                    return false;
                }
            }
        } else {
            return false;
        }
        if (pizza.tamanos.size() == tamanos.size()){
            for (int i = 0; i < pizza.tamanos.size(); i++) {
                if (!pizza.tamanos.get(i).esIgualA(tamanos.size().get(i))){
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
