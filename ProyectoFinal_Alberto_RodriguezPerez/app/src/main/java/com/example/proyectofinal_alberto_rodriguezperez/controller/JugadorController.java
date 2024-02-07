package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.util.Log;
import androidx.annotation.NonNull;

import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.service.JugadorService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugadorController {

    private final JugadorService serviceJugador = new JugadorService();

    public void getJugador(String nombre, String passwd) {
        serviceJugador.getJugador(nombre, passwd).enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(@NonNull Call<Jugador> call, @NonNull Response<Jugador> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());
                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Jugador> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
