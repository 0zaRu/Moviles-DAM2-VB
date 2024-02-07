package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Partida {
    private int id;
    private Date fecha;
    private String lugar;
    private String resultado;
    private int idJugadorBlancas;
    private int idJugadorNegras;
    private int idTorneo;

    public Partida() {
    }

    public Partida(Date fecha, String lugar, String resultado, int idJugadorBlancas, int idJugadorNegras, int idTorneo) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.resultado = resultado;
        this.idJugadorBlancas = idJugadorBlancas;
        this.idJugadorNegras = idJugadorNegras;
        this.idTorneo = idTorneo;
    }

    public Partida(int id, Date fecha, String lugar, String resultado, int idJugadorBlancas, int idJugadorNegras, int idTorneo) {
        this.id = id;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resultado = resultado;
        this.idJugadorBlancas = idJugadorBlancas;
        this.idJugadorNegras = idJugadorNegras;
        this.idTorneo = idTorneo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getIdJugadorBlancas() {
        return idJugadorBlancas;
    }

    public void setIdJugadorBlancas(int idJugadorBlancas) {
        this.idJugadorBlancas = idJugadorBlancas;
    }

    public int getIdJugadorNegras() {
        return idJugadorNegras;
    }

    public void setIdJugadorNegras(int idJugadorNegras) {
        this.idJugadorNegras = idJugadorNegras;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", lugar='" + lugar + '\'' +
                ", resultado='" + resultado + '\'' +
                ", idJugadorBlancas=" + idJugadorBlancas +
                ", idJugadorNegras=" + idJugadorNegras +
                ", idTorneo=" + idTorneo +
                '}';
    }
}