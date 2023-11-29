package com.example.practica14_alberto_rodriguez.Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String user;
    private String pass;
    private String nombre;

    public Usuario(String user, String pass, String nombre) {
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
    }

    public Usuario() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
