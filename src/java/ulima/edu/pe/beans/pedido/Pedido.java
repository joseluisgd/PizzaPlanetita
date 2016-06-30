package ulima.edu.pe.beans.pedido;

//import ulima.edu.pe.beans.producto.Adicional;
//import ulima.edu.pe.beans.producto.promocion.Promocion;
//import ulima.edu.pe.beans.producto.pizza.PizzaPedido;
import java.util.List;

public class Pedido {

    private int id;
    private String username;
    private Direccion direccion;
    private List<Estado> estados;
    private List<ProductoPedido> productos;
    private float precioPedido;

    public Pedido() {
    }

    public Pedido(int id, String username, Direccion direccion, List<Estado> estados, List<ProductoPedido> productos,/*List<PizzaPedido> pizzas, List<Adicional> adicionales, List<Promocion> promociones,*/ float precioPedido) {
        this.id = id;
        this.username = username;
        this.direccion = direccion;
        this.estados = estados;
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

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<ProductoPedido> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
    }

    public float getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(float precioPedido) {
        this.precioPedido = precioPedido;
    }

    public void calcularPrecioPedido() {
        for (ProductoPedido productoPedido : productos) {
            precioPedido = precioPedido + productoPedido.getPrecioTotal();
        }
    }
}
