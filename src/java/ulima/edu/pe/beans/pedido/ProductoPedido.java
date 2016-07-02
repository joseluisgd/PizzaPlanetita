package ulima.edu.pe.beans.pedido;

import ulima.edu.pe.beans.producto.IProducto;

public class ProductoPedido {

    private IProducto producto;
    private int cantidad;

    public ProductoPedido() {
    }

    public ProductoPedido(IProducto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public IProducto getProducto() {
        return producto;
    }

    public void setProducto(IProducto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return producto.getPrecio();
    }

    public float getPrecioTotal() {
        return producto.getPrecio() * cantidad;
    }

}
