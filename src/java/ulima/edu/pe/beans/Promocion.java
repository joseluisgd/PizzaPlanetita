package ulima.edu.pe.beans;

import java.util.ArrayList;
import java.util.List;

public class Promocion implements IProducto{
    private int id;
    private String nombre;
    private float precio;
    private String fechaInicio;
    private String fechaFin;
    private List<ProductoPromocion> productos;
    private String descripcion;

    public Promocion() {
        productos = new ArrayList<>();
    }
        
    public Promocion(int id, String nombre, float precio, String fechaInicio, String fechaFin, List<ProductoPromocion> productos, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.productos = productos;
        this.descripcion = descripcion;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<ProductoPromocion> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoPromocion> productos) {
        this.productos = productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
