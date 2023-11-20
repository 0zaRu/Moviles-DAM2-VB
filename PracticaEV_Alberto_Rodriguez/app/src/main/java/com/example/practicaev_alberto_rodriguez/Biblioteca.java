package com.example.practicaev_alberto_rodriguez;

import java.util.ArrayList;

public class Biblioteca {
    public static ArrayList<Libro> rellenar () {
        ArrayList<Libro> lista = new ArrayList<Libro>();

        lista.add(new Libro("Harry Potter y la piedra filosofal", "JK Rowling", "Fantasía", 123));
        lista.add(new Libro("La chica de nieve", "Javier Castillo", "Thriller", 623));
        lista.add(new Libro("Belle Morte", "Bella Higgin", "Romántica", 123));
        lista.add(new Libro("Roma soy yo", "Santiago Posteguillo", "Histórica", 123));
        lista.add(new Libro("Trilogía de la fundación", "Isaac Asimov", "Ciencia Ficción", 123));
        lista.add(new Libro("La tinta que nos une", "Laura Gallego", "Aventura", 523));
        lista.add(new Libro("Cuentos de hadas", "Stephen King", "Terror", 123));

        return lista;
    }
}
