package ulima.edu.pe.beans.producto.pizza;

import java.util.List;
import ulima.edu.pe.beans.producto.IProducto;

public class PizzaPedido extends Pizza implements IProducto {

    private Tamano tamano;
    private boolean personalizada;

    public PizzaPedido() {
    }

    public PizzaPedido(int id, String nombre, List<Ingrediente> ingredientes, Tamano tamano, boolean personalizada) {
        super(id, nombre, ingredientes);
        this.tamano = tamano;
        this.personalizada = personalizada;
    }

    @Override
    public float getPrecio() {
        if (personalizada) {
            return ingredientes.size() * tamano.getMultiplicador();
        } else {
            return tamano.getPrecio();
        }
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public boolean esPersonalizada() {
        return personalizada;
    }

    public void setPersonalizada(boolean personalizada) {
        this.personalizada = personalizada;
    }

    @Override
    public boolean esPizza() {
        return true;
    }

    @Override
    public boolean esAdicional() {
        return false;
    }

    @Override
    public boolean esPromocion() {
        return false;
    }

    @Override
    public boolean esIgualA(IProducto producto) {
        if (!producto.esPizza()) {
            return false;
        }
        PizzaPedido pizza = (PizzaPedido) producto;
        if (pizza.id != id) {
            return false;
        }
        if (!pizza.nombre.equals(nombre)) {
            return false;
        }
        if (!pizza.tamano.esIgualA(tamano)) {
            return false;
        }
        if (pizza.ingredientes.size() == ingredientes.size()) {
            for (int i = 0; i < pizza.ingredientes.size(); i++) {
                if (!pizza.ingredientes.get(i).esIgualA(ingredientes.get(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        if (pizza.personalizada != personalizada){
            return false;
        }
        return true;
    }
}
