package com.ulima.sw.pizzaplanetac.beans;

import java.util.Date;

/**
 * Created by fixt on 10/05/16.
 */
public class Estado {
    private String hora;
    private int id;
    private int estado;

    public Estado() {
    }

    public Estado(String hora, int id, int estado) {
        this.hora = hora;
        this.id = id;
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
