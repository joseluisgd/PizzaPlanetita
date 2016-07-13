package ulima.edu.pe.beans.pedido;

import java.util.List;

public class Pedido {

    private int id;
    private String username;
    private Direccion direccion;
    private Estado estado;
    private List<ProductoPedido> productos;
    private float precioPedido;

    public Pedido() {
    }

    public Pedido(int id, String username, Direccion direccion, Estado estado, List<ProductoPedido> productos, float precioPedido) {
        this.id = id;
        this.username = username;
        this.direccion = direccion;
        this.estado = estado;
        this.productos = productos;
        this.precioPedido = precioPedido;
    }

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

    public List<ProductoPedido> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
        calcularPrecioPedido();
    }

    public float getPrecioPedido() {
        return precioPedido;
    }

//    public void setPrecioPedido(float precioPedido) {
//        this.precioPedido = precioPedido;
//    }

    public void calcularPrecioPedido() {
        for (ProductoPedido productoPedido : productos) {
            precioPedido = precioPedido + productoPedido.getPrecioTotal();
        }
    }
    
    public boolean esIgualA(Pedido pedido) {
        if (pedido.id != this.id) {
            return false;
        }
        if (pedido.username.equals(this.username)) {
            return false;
        }
        if (pedido.estado != this.estado) {
            return false;
        }
        if (pedido.direccion != this.direccion) {
            return false;
        }
        if (pedido.productos != this.productos) {
            return false;
        }
        if (pedido.precioPedido != this.precioPedido) {
            return false;
        }
        return true;
    }
}
