package ulima.edu.pe.beans.producto;

public class Adicional implements IProducto {

    private int id;
    private String nombre;
    private float precio;

    public Adicional() {
    }

    public Adicional(int id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Adicional clonar() {
        return new Adicional(id, nombre, precio);
    }

    @Override
    public boolean esPizza() {
        return false;
    }

    @Override
    public boolean esAdicional() {
        return true;
    }

    @Override
    public boolean esPromocion() {
        return false;
    }

    @Override
    public boolean esIgualA(IProducto producto) {
        if (!producto.esAdicional()){
            return false;
        }
        Adicional adicional = (Adicional) producto;
        if (adicional.id != id){
            return false;
        }
        if (!adicional.nombre.equals(nombre)){
            return false;
        }
        if (adicional.precio != precio){
            return false;
        }
        return true;
    }

}
