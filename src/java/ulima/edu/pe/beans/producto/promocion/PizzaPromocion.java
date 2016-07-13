package ulima.edu.pe.beans.producto.promocion;

import ulima.edu.pe.beans.producto.pizza.Tamano;

public class PizzaPromocion {

    private String nombre;
    private int tamanoId;
    private int cantidad;

    public PizzaPromocion() {
    }

    public PizzaPromocion(String nombre, int tamanoId, int cantidad) {
        this.nombre = nombre;
        this.tamanoId = tamanoId;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamanoNombre() {
        return Tamano.getNombrePorId(tamanoId);
    }

    public int getTamanoId() {
        return tamanoId;
    }

    public void setTamanoId(int tamanoId) {
        this.tamanoId = tamanoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean esIgualA(PizzaPromocion pizza) {
        if (!pizza.nombre.equals(nombre)) {
            return false;
        }
        if (pizza.tamanoId != tamanoId) {
            return false;
        }
        if (pizza.cantidad != cantidad) {
            return false;
        }
        return true;
    }

}
