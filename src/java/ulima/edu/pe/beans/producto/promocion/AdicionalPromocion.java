package ulima.edu.pe.beans.producto.promocion;

public class AdicionalPromocion {

    private String nombre;
    private int cantidad;

    public AdicionalPromocion() {
    }

    public AdicionalPromocion(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
