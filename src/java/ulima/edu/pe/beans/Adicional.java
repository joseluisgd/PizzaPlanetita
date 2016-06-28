package ulima.edu.pe.beans;

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

    @Override
    public float getPrecio() {
        return precio;
    }

}
