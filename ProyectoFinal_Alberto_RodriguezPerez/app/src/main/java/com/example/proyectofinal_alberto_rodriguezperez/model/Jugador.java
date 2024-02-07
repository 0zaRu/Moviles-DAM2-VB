package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Jugador {
    private int id;
    private String nombre;
    private String pais;
    private int elo;
    private Date fechaNacimiento;
    private String correoElectronico;
    private String passwd;
    private boolean esAdmin;

    public Jugador() {
    }

    public Jugador(String nombre, String pais, int elo, Date fechaNacimiento, String correoElectronico, String passwd, boolean esAdmin) {
        this.nombre = nombre;
        this.pais = pais;
        this.elo = elo;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.passwd = passwd;
        this.esAdmin = esAdmin;
    }

    public Jugador(int id, String nombre, String pais, int elo, Date fechaNacimiento, String correoElectronico, String passwd, boolean esAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.elo = elo;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.passwd = passwd;
        this.esAdmin = esAdmin;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @NonNull
    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", elo=" + elo +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", passwd='" + passwd + '\'' +
                ", esAdmin=" + esAdmin +
                '}';
    }
}
