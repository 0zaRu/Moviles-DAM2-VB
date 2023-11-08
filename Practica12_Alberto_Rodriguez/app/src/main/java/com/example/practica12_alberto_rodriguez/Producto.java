package com.example.practica12_alberto_rodriguez;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private String nombreImagen;
    private String descripcion;
    private double precio;

    public Producto(String nombre, String nombreImagen, double precio, String descripcion) {
        this.nombre = nombre;
        this.nombreImagen = nombreImagen;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", nombreImagen='" + nombreImagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
