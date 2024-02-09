package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.service.PartidaService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartidaController {
    private final PartidaService servicePartida = new PartidaService();

    public void getPartidas(Context contextParaLista, ListView lvEditando, int jugadorId) {
        servicePartida.getPartidasUser(jugadorId).enqueue(
                new Callback<List<Partida>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Partida>> call, @NonNull Response<List<Partida>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;

                            ArrayList<Partida> partidas = new ArrayList<>();
                            for (Partida partida : response.body()) {
                                Log.d("TAG", partida.toString());
                                partidas.add(partida);
                            }

                            FragmentListPartidasAdapter adapter = new FragmentListPartidasAdapter(contextParaLista, partidas);
                            lvEditando.setAdapter(adapter);

                        } else {
                            Log.d("TAG", "Error");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Partida>> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }
}