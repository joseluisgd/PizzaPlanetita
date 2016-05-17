package ulima.edu.pe.beans;

import java.util.List;

public class Pedido {
    private int id;
    private Estado estado;
    private String username;
    private String direccion;
    private float monto;
    private List<Pizza> pizzas;
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(int id, Estado estado, String username, String direccion, float monto, List<Pizza> pizzas, List<Producto> productos) {
        this.id = id;
        this.estado = estado;
        this.username = username;
        this.direccion = direccion;
        this.monto = monto;
        this.pizzas = pizzas;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
