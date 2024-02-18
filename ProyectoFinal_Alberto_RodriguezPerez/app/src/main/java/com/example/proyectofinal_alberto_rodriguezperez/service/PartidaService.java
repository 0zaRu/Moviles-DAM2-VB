package com.example.proyectofinal_alberto_rodriguezperez.service;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.JugadorDAO;
import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.PartidaDAO;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PartidaService {
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://172.23.86.211/chess-app-proyectoARP/Partida/")
                //.baseUrl("http://10.0.2.2/chess-app-proyectoARP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<Partida>> getPartidasUser(int jugadorId, int filtroDiasAtras) {
        if(filtroDiasAtras == 0)
            return getRetrofit().create(PartidaDAO.class).getPartidasUser(jugadorId);
        else
            return getRetrofit().create(PartidaDAO.class).getPartidasUserFiltroDias(jugadorId, filtroDiasAtras);
    }

    public Call<Integer> borrarPartida(int id) {
        return getRetrofit().create(PartidaDAO.class).borrarPartidaById(id);
    }

    public Call<Integer> addOrModify(Partida partinaN) {
        return getRetrofit().create(PartidaDAO.class).addOrModify(partinaN);
    }

    public Call<List<Partida>> getPartidasByTorneo(int id) {
        return getRetrofit().create(PartidaDAO.class).getPartidasByTorneo(id);
    }
}
