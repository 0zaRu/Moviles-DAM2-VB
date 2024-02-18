package com.example.proyectofinal_alberto_rodriguezperez.service;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.PartidaDAO;
import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.TorneoDAO;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TorneoService {
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://172.23.86.211/chess-app-proyectoARP/")
                //.baseUrl("http://10.0.2.2/chess-app-proyectoARP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<Torneo>> getMisTorneos(int jugadorId, boolean participaUesr, int filtroAbierto) {
        //Filtro de torneo abierto/cerrado
        //1 - Todos
        //2 - Abiertos
        //3 - Cerrados

        if(filtroAbierto == 1) {
            if (participaUesr)
                return getRetrofit().create(TorneoDAO.class).getTorneosById(jugadorId);
            else
                return getRetrofit().create(TorneoDAO.class).getTorneosByIdSinUser(jugadorId);
        }
        else if(filtroAbierto == 2){
            return getRetrofit().create(TorneoDAO.class).getTorneosByIdYFiltro(jugadorId, "Abierto");
        }
        else
            return getRetrofit().create(TorneoDAO.class).getTorneosByIdYFiltro(jugadorId, "Cerrado");
    }

    public Call<List<Torneo>> getTorneosFiltrados(String txtFiltrar){
        return getRetrofit().create(TorneoDAO.class).getTorneosFiltrados(txtFiltrar);
    }

    public Call<String> getNombreTorneoById(int torneoId){
        return getRetrofit().create(TorneoDAO.class).getNombreTorneoById(torneoId);
    }

    public Call<Integer> borrarTorneo(int id) {
        return getRetrofit().create(TorneoDAO.class).borrarTorneo(id);
    }

    public Call<Integer> addOrModify(Torneo torneo) {
        return getRetrofit().create(TorneoDAO.class).addOrModify(torneo);
    }
}
