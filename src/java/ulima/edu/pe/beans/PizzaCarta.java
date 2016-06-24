package ulima.edu.pe.beans;

import java.util.ArrayList;
import java.util.List;

public class PizzaCarta extends Pizza {
    List<Tamano> tamanos;

    public PizzaCarta() {
        tamanos = new ArrayList<>();
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
    
    
}
