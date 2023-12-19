package com.example.examenfragments_albertorodriguez.Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String user;
    private String pass;
    private String rol;

    public Usuario(String user, String pass, String rol) {
        this.user = user;
        this.pass = pass;
        this.rol = rol;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
