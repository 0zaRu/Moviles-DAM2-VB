package com.example.practica10_ejercicioa_alberto_rodriguez;

public class Coche {

    private String marca;
    private String modelo;

    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "marca: '" + marca + '\'' +
                ", modelo: '" + modelo + '\'';
    }
}
