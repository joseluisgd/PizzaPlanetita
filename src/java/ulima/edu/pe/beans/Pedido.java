package ulima.edu.pe.beans;

import java.util.List;

public class Pedido {
    private int id;
    private String username;
    private Direccion direccion;
    private Estado estado;
    private List<PizzaPedido> pizzas;
    private List<Adicional> adicionales;
    private List<Promocion> promociones;
    private float precioPedido;

    public Pedido() {
    }

    public Pedido(int id, String username, Direccion direccion, Estado estado, List<PizzaPedido> pizzas, List<Adicional> adicionales, List<Promocion> promociones, float precioPedido) {
        this.id = id;
        this.username = username;
        this.direccion = direccion;
        this.estado = estado;
        this.pizzas = pizzas;
        this.adicionales = adicionales;
        this.promociones = promociones;
        this.precioPedido = precioPedido;
    }
    
    

//    public Pedido(int id, Estado estado, String username, Direccion direccion, float monto, List<Pizza> pizzas, List<Adicional> adicionales) {
//        this.id = id;
//        this.estado = estado;
//        //this.usuario = usuario;
//        this.username = username;
//        this.direccion = direccion;
//        this.monto = monto;
//        this.pizzas = pizzas;
//        this.adicionales=adicionales;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Estado getEstado() {
//        return estado;
//    }
//
//    public void setEstado(Estado estado) {
//        this.estado = estado;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    public Direccion getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(Direccion direccion) {
//        this.direccion = direccion;
//    }
//
//    public float getMonto() {
//        return monto;
//    }
//
//    public void setMonto(float monto) {
//        this.monto = monto;
//    }
//
//    public List<Pizza> getPizzas() {
//        return pizzas;
//    }
//
//    public void setPizzas(List<Pizza> pizzas) {
//        this.pizzas = pizzas;
//    }
//
//    public List<Adicional> getAdicionales() {
//        return adicionales;
//    }
//
//    public void setProductos(List<Adicional> adicionales) {
//        this.adicionales = adicionales;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<PizzaPedido> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaPedido> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public float getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(float precioPedido) {
        this.precioPedido = precioPedido;
    }
    
    
}
