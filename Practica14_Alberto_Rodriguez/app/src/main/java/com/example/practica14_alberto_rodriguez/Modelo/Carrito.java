package com.example.practica14_alberto_rodriguez.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrito implements Serializable {

    private Usuario usuario;
    private Articulo articulo;
    private int numeroArticulos;

    public Carrito(Usuario usuario, Articulo articulo, int numeroArticulos) {
        this.usuario = usuario;
        this.articulo = articulo;
        this.numeroArticulos = numeroArticulos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getNumeroArticulos() {
        return numeroArticulos;
    }

    public void setNumeroArticulos(int numeroArticulos) {
        this.numeroArticulos = numeroArticulos;
    }
}
