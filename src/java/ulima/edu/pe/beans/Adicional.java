package ulima.edu.pe.beans;

public class Adicional implements Producto {
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
    public String getNombre() {
        return nombre;
    }

    @Override
    public float getPrecio() {
        return precio;
    }

}
