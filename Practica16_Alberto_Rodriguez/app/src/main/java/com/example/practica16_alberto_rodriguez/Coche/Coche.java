package com.example.practica16_alberto_rodriguez.Coche;

public class Coche {
    // Campos obligatorios
    private String numBastidor;
    private String marca;
    private String modelo;
    private String combustible;
    private String color;
    private int kilometraje;

    private static final String[] COLORES = new String[]{"blanco", "negro", "azul", "rojo", "gris", "verde", "amarillo"};
    private static final String[] COMBUSTIBLES = new String[]{"diésel", "gasolina", "híbrido"};

    public Coche(String numBastidor, String marca, String modelo, String combustible, String color, int kilometraje) {
        setNumBastidor(numBastidor);
        this.marca = marca;
        this.modelo = modelo;
        setCombustible(combustible);
        setColor(color);
        setKilometraje(kilometraje);
    }

    // Métodos getter y setter

    public String getNumBastidor() {
        return numBastidor;
    }

    public void setNumBastidor(String numBastidor) {
        this.numBastidor = numBastidor;
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

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible.toLowerCase();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.toLowerCase();
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }
}

