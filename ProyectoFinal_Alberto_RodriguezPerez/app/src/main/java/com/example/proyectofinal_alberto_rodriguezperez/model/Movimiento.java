package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

public class Movimiento {
    private int id;
    private int numeroMovimiento;
    private String movimiento1;
    private String movimiento2;
    private int idPartida;

    public Movimiento() {
    }

    public Movimiento(int numeroMovimiento, String movimiento1, String movimiento2, int idPartida) {
        this.numeroMovimiento = numeroMovimiento;
        this.movimiento1 = movimiento1;
        this.movimiento2 = movimiento2;
        this.idPartida = idPartida;
    }

    public Movimiento(int id, int numeroMovimiento, String movimiento1, String movimiento2, int idPartida) {
        this.id = id;
        this.numeroMovimiento = numeroMovimiento;
        this.movimiento1 = movimiento1;
        this.movimiento2 = movimiento2;
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

    public String getMovimiento1() {
        return movimiento1;
    }

    public void setMovimiento1(String movimiento1) {
        this.movimiento1 = movimiento1;
    }

    public String getMovimiento2() {
        return movimiento2;
    }

    public void setMovimiento2(String movimiento2) {
        this.movimiento2 = movimiento2;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    @Override
    public String toString() {
        return  numeroMovimiento + " " + movimiento1 + " " + movimiento2;
    }
}
