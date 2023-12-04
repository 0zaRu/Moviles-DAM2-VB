package com.example.practica14_alberto_rodriguez.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrito implements Serializable {

    private String usuario;
    private int articulo;
    private int numeroArticulos;

    public Carrito(String usuario, int articulo, int numeroArticulos) {
        this.usuario = usuario;
        this.articulo = articulo;
        this.numeroArticulos = numeroArticulos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public int getNumeroArticulos() {
        return numeroArticulos;
    }

    public void setNumeroArticulos(int numeroArticulos) {
        this.numeroArticulos = numeroArticulos;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "usuario='" + usuario + '\'' +
                ", articulo=" + articulo +
                ", numeroArticulos=" + numeroArticulos +
                '}';
    }
}
