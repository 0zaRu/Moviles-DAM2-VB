package com.example.examenfragments_albertorodriguez.Modelo;

import java.io.Serializable;

public class Libro implements Serializable {
    private int id;
    private String titulo;
    private String autor;
    private int numPags;

    public Libro(String titulo, String autor, int numPags) {
        this.titulo = titulo;
        this.autor = autor;
        this.numPags = numPags;
    }

    public Libro(int id, String titulo, String autor, int numPags) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.numPags = numPags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPags() {
        return numPags;
    }

    public void setNumPags(int numPags) {
        this.numPags = numPags;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", numPags=" + numPags +
                '}';
    }
}
