package ulima.edu.pe.beans;

import java.util.List;

public class PizzaPedido extends Pizza implements IProducto {
    Tamano tamano;

    public PizzaPedido() {
    }

    public PizzaPedido(int id, String nombre, List<Ingrediente> ingredientes, Tamano tamano) {
        super(id, nombre, ingredientes);
        this.tamano = tamano;
    }

    @Override
    public float getPrecio() {
        return tamano.getPrecio();
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }
    
    
}
