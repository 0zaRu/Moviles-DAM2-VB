package com.example.practica12_alberto_rodriguez;

import java.io.Serializable;

public class Carrito implements Serializable {
    private int cantidad;
    private Producto producto;

    public Carrito(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "LineaVenta{" +
                "cantidad=" + cantidad +
                ", producto=" + producto +
                '}';
    }
}
