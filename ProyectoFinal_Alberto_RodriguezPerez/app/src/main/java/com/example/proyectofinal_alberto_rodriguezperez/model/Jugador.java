package com.example.proyectofinal_alberto_rodriguezperez.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Jugador implements Serializable {
    private int id;
    private byte[] imagen;
    private String nombre;
    private String pais;
    private int elo;
    private String fechaNacimiento;
    private String correoElectronico;
    private String passwd;
    private int esAdmin;

    public Jugador() {
    }

    public Jugador(byte[] imagen, String nombre, String pais, int elo, String fechaNacimiento, String correoElectronico, String passwd, int esAdmin) {
        this.nombre = nombre;
        this.pais = pais;
        this.elo = elo;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.passwd = passwd;
        this.esAdmin = esAdmin;
        this.imagen = imagen;
    }

    public Jugador(int id, byte[] imagen, String nombre, String pais, int elo, String fechaNacimiento, String correoElectronico, String passwd, int esAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.elo = elo;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.passwd = passwd;
        this.esAdmin = esAdmin;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
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

    public int getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(int esAdmin) {
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
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", passwd='" + passwd + '\'' +
                ", esAdmin=" + esAdmin +
                '}';
    }
}
