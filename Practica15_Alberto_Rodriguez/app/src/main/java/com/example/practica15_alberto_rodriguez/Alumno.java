package com.example.practica15_alberto_rodriguez;

public class Alumno {
    private String nombre;
    private String apellidos;
    private String dni;
    private int foto;

    public Alumno(String nombre, String apellidos, String dni, int foto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.foto = foto;
    }

    public Alumno() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
