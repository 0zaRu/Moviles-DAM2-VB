package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Partida implements Serializable {
    private int id;
    private Date fecha;
    private String lugar;
    private String resultado;
    private int idJugadorBlancas;
    private int idJugadorNegras;
    private int idTorneo;

    // Nuevos campos con nombres de referencia modificados
    private String refJugadorBlancas;
    private String refJugadorNegras;
    private String refTorneo;

    public Partida() {
    }

    public Partida(Date fecha, String lugar, String resultado, int idJugadorBlancas, int idJugadorNegras, int idTorneo) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.resultado = resultado;
        this.idJugadorBlancas = idJugadorBlancas;
        this.idJugadorNegras = idJugadorNegras;
        this.idTorneo = idTorneo;

        // Inicializar los campos de referencia modificados
        this.refJugadorBlancas = "ref" + idJugadorBlancas;
        this.refJugadorNegras = "ref" + idJugadorNegras;
        this.refTorneo = "ref" + idTorneo;
    }

    public Partida(int id, Date fecha, String lugar, String resultado, int idJugadorBlancas, int idJugadorNegras, int idTorneo) {
        this.id = id;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resultado = resultado;
        this.idJugadorBlancas = idJugadorBlancas;
        this.idJugadorNegras = idJugadorNegras;
        this.idTorneo = idTorneo;

        // Inicializar los campos de referencia modificados
        this.refJugadorBlancas = "ref" + idJugadorBlancas;
        this.refJugadorNegras = "ref" + idJugadorNegras;
        this.refTorneo = "ref" + idTorneo;
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
        this.refJugadorBlancas = "ref" + idJugadorBlancas;
    }

    public int getIdJugadorNegras() {
        return idJugadorNegras;
    }

    public void setIdJugadorNegras(int idJugadorNegras) {
        this.idJugadorNegras = idJugadorNegras;
        this.refJugadorNegras = "ref" + idJugadorNegras;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
        this.refTorneo = "ref" + idTorneo;
    }

    // Nuevos métodos getters y setters para los campos de referencia modificados
    public String getRefJugadorBlancas() {
        return refJugadorBlancas;
    }

    public void setRefJugadorBlancas(String refJugadorBlancas) {
        this.refJugadorBlancas = refJugadorBlancas;
    }

    public String getRefJugadorNegras() {
        return refJugadorNegras;
    }

    public void setRefJugadorNegras(String refJugadorNegras) {
        this.refJugadorNegras = refJugadorNegras;
    }

    public String getRefTorneo() {
        return refTorneo;
    }

    public void setRefTorneo(String refTorneo) {
        this.refTorneo = refTorneo;
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
