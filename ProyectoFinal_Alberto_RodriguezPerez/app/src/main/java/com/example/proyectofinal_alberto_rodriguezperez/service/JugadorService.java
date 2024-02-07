package com.example.proyectofinal_alberto_rodriguezperez.service;

import com.example.proyectofinal_alberto_rodriguezperez.DAO.JugadorDAO;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JugadorService {

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/chess-app-proyectoARP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<Jugador> getJugador(String nombre, String passwd) {
        return getRetrofit().create(JugadorDAO.class).getJugador(nombre, passwd);
    }

}
