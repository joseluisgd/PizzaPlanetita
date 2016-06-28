package ulima.edu.pe.beans;

public class ProductoPedido {
    //ChF: Continuar aquí :V
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

}
