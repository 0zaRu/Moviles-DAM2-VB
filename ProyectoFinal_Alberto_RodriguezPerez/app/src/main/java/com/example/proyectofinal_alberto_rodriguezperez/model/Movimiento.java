package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

public class Movimiento {
    private int id;
    private int numeroMovimiento;
    private String movimiento;
    private int idPartida;

    public Movimiento() {
    }

    public Movimiento(int numeroMovimiento, String movimiento, int idPartida) {
        this.numeroMovimiento = numeroMovimiento;
        this.movimiento = movimiento;
        this.idPartida = idPartida;
    }

    public Movimiento(int id, int numeroMovimiento, String movimiento, int idPartida) {
        this.id = id;
        this.numeroMovimiento = numeroMovimiento;
        this.movimiento = movimiento;
        this.idPartida = idPartida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroMovimiento() {
        return numeroMovimiento;
    }

    public void setNumeroMovimiento(int numeroMovimiento) {
        this.numeroMovimiento = numeroMovimiento;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    @NonNull
    @Override
    public String toString() {
        return "Movimiento{" +
                "id=" + id +
                ", numeroMovimiento=" + numeroMovimiento +
                ", movimiento='" + movimiento + '\'' +
                ", idPartida=" + idPartida +
                '}';
    }
}
