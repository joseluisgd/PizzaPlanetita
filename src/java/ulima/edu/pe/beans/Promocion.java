package ulima.edu.pe.beans;

import java.util.List;

public class Promocion {
    private int id;
    private String nombre;
    private float precio_unitario;
    private String fecha_inicio;
    private String fecha_fin;
    private List<Pizza> pizzas;
    private List<Adicional> adicionales;
    private String descripcion;

    public Promocion() {
    }
        
    public Promocion(int id, String nombre, float precio_unitario, String fecha_inicio, String fecha_fin, List<Pizza> pizzas, List<Adicional> adicionales, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.pizzas = pizzas;
        this.adicionales = adicionales;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
