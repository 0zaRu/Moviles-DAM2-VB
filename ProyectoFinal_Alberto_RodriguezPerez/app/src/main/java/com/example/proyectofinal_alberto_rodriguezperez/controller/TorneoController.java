package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;
import com.example.proyectofinal_alberto_rodriguezperez.service.TorneoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TorneoController {
    private final TorneoService serviceTorneo = new TorneoService();

    public void getTorneos(Context contextParaLista, ListView listaTorneos, int jugadorId, boolean participaUser, int filtrarPorAbierto){
        //Filtro de torneo abierto/cerrado
        //1 - Todos
        //2 - Abiertos
        //3 - Cerrados
        serviceTorneo.getMisTorneos(jugadorId, participaUser, filtrarPorAbierto).enqueue(new Callback<List<Torneo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Torneo>> call, @NonNull Response<List<Torneo>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    ArrayList<Torneo> torneos = new ArrayList<>();
                    for (Torneo torneo : response.body()) {
                        Log.d("TAG", torneo.toString());
                        torneos.add(torneo);
                    }

                    FragmentListTorneosAdapter adapter = new FragmentListTorneosAdapter(contextParaLista, torneos);
                    listaTorneos.setAdapter(adapter);

                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Torneo>> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void getTorneosFiltrados(Context contextParaLista, ListView listaTorneos, String textoBusqueda){
        serviceTorneo.getTorneosFiltrados(textoBusqueda).enqueue(new Callback<List<Torneo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Torneo>> call, @NonNull Response<List<Torneo>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    ArrayList<Torneo> torneos = new ArrayList<>();
                    for (Torneo torneo : response.body()) {
                        Log.d("TAG", torneo.toString());
                        torneos.add(torneo);
                    }

                    FragmentListTorneosAdapter adapter = new FragmentListTorneosAdapter(contextParaLista, torneos);
                    listaTorneos.setAdapter(adapter);

                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Torneo>> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void getNombreTorneoById(TextView tvPonerNombre, int torneoId){
        serviceTorneo.getNombreTorneoById(torneoId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    tvPonerNombre.setText(response.body());

                }else{
                    System.out.println("Error");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("Error conexión php");
            }
        });
    }
}
