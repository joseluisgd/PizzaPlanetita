package com.ulima.sw.pizzaplanetac.beans;

import java.util.List;

/**
 * Created by fixt on 05/05/16.
 */
public class Pizza {
    private int id, tipo;
    private String nombre, tamaño;
    private float precio;
    private List<Ingrediente> ing;
    private String img;

    public Pizza() {
    }

    public Pizza(int id, int tipo, String nombre, String tamaño, float precio, List<Ingrediente> ing, String img) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.precio = precio;
        this.ing = ing;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Ingrediente> getIng() {
        return ing;
    }

    public void setIng(List<Ingrediente> ing) {
        this.ing = ing;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
