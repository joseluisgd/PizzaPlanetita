package ulima.edu.pe.beans;

public class ProductoPromocion {

    private Producto producto;
    private int cantidad;

    public ProductoPromocion() {
    }

    public ProductoPromocion(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
