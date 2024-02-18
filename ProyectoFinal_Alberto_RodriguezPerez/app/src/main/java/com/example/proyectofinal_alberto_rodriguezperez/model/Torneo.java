package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Torneo implements Serializable {
    private int id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String lugar;
    private String tipo;

    public Torneo() {
    }

    public Torneo(String nombre, String fechaInicio, String fechaFin, String lugar, String tipo) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugar = lugar;
        this.tipo = tipo;
    }

    public Torneo(int id, String nombre, String fechaInicio, String fechaFin, String lugar, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugar = lugar;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Torneo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", lugar='" + lugar + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
