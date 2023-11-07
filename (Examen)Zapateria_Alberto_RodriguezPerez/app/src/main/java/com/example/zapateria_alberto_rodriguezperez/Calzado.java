package com.example.zapateria_alberto_rodriguezperez;

import java.io.Serializable;

public class Calzado implements Serializable {
    static final long serialVersionUID = 123;
    private String tipo;
    private String descripción;
    private int numero;
    private String codigo;

    public Calzado(String tipo, String descripción, int numero, String codigo) {
        this.tipo = tipo;
        this.descripción = descripción;
        this.numero = numero;
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Calzado{" +
                "tipo='" + tipo + '\'' +
                ", descripción='" + descripción + '\'' +
                ", numero=" + numero +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
