package ulima.edu.pe.beans.producto.promocion;

import java.util.Date;
import java.util.List;
import ulima.edu.pe.beans.producto.IProducto;
import ulima.edu.pe.util.Util;

public class Promocion implements IProducto {

    private int id;
    private String nombre;
    private float precio;
    private String fechaInicio;
    private String fechaFin;
    private List<PizzaPromocion> pizzas;
    private List<AdicionalPromocion> adicionales;
    private String descripcion;

    public Promocion() {
    }

    public Promocion(int id, String nombre, float precio, String fechaInicio, String fechaFin, List<PizzaPromocion> pizzas, List<AdicionalPromocion> adicionales, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pizzas = pizzas;
        this.adicionales = adicionales;
        this.descripcion = descripcion;
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

    public List<PizzaPromocion> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaPromocion> pizzas) {
        this.pizzas = pizzas;
    }

    public List<AdicionalPromocion> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<AdicionalPromocion> adicionales) {
        this.adicionales = adicionales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Promocion clonar() {
        return new Promocion(id, nombre, precio, fechaInicio, fechaFin, pizzas, adicionales, descripcion);
    }

    @Override
    public boolean esPizza() {
        return false;
    }

    @Override
    public boolean esAdicional() {
        return false;
    }

    @Override
    public boolean esPromocion() {
        return true;
    }

    public boolean estaVigente() {
        boolean vigente = false;
        Date fechaActual = Util.fechaStringADate(Util.obtenerFechaHoraActual());

        if (Util.fechaStringADate(fechaInicio).before(fechaActual) && Util.fechaStringADate(fechaFin).after(fechaActual)) {
            vigente = true;
        }
        return vigente;
    }

    @Override
    public boolean esIgualA(IProducto producto) {
        if(!producto.esPromocion()){
            return false;
        }
        Promocion promocion = (Promocion) producto;
        if (promocion.id != id){
            return false;
        }
        if (promocion.nombre.equals(nombre)){
            return false;
        }
        if (promocion.fechaInicio.equals(fechaInicio)){
            return false;
        }
        if (promocion.fechaFin.equals(fechaFin)){
            return false;
        }
        if (promocion.pizzas.size() == pizzas.size()) {
            for (int i = 0; i < promocion.pizzas.size(); i++) {
                if (!promocion.pizzas.get(i).esIgualA(pizzas.get(i))){
                    return false;
                }
            }
        } else {
            return false;
        }
        if (promocion.adicionales.size() == adicionales.size()) {
            for (int i = 0; i < promocion.adicionales.size(); i++) {
                if (!promocion.adicionales.get(i).esIgualA(adicionales.get(i))){
                    return false;
                }
            }
        } else {
            return false;
        }
        if (promocion.descripcion.equals(descripcion)){
            return false;
        }
        return true;
    }


}
