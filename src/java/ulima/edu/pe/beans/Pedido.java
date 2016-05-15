package com.ulima.sw.pizzaplanetac.beans;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 9/05/2016.
 */
public class Pedido {
    private int id;
    private Estado estado;
    private Cliente cli;
    private String direccion;
    private float monto;
    private List<Pizza> pizzas;
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(int id, Estado estado, Cliente cli, String direccion, float monto, List<Pizza> pizzas) {
        this.id = id;
        this.estado = estado;
        this.cli = cli;
        this.direccion = direccion;
        this.monto = monto;
        this.pizzas = pizzas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
