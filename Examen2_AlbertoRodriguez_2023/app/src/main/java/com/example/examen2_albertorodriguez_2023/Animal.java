package com.example.examen2_albertorodriguez_2023;

import java.io.Serializable;

public class Animal implements Serializable {

    private int codigo;
    private String nombre;
    private double peso;
    private String tipo;

    public Animal () {
        this.codigo = 0;
        this.nombre = "";
        this.peso = 0;
        this.tipo = "";
    }

    public Animal (int codigo, String nombre, double peso, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.peso = peso;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
