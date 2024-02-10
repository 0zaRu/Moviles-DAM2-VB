package com.example.proyectofinal_alberto_rodriguezperez.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.JugadorDAO;
import com.example.proyectofinal_alberto_rodriguezperez.controller.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JugadorService {

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://172.23.86.211/chess-app-proyectoARP/")
                //.baseUrl("http://10.0.2.2/chess-app-proyectoARP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<Jugador> getJugador(String nombre, String passwd) {
        return getRetrofit().create(JugadorDAO.class).getJugador(nombre, passwd);
    }

    public Call<Integer> existeJugador(String nombre) {
        return getRetrofit().create(JugadorDAO.class).existeJugador(nombre);
    }

    public void addJugador(Context activityContext, Jugador nuevoJugador){
        getRetrofit().create(JugadorDAO.class).addJugador(nuevoJugador).enqueue(
                new Callback<Jugador>() {
                    @Override
                    public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                        Log.d("TAG", "RESPONSE: " + response);
                        Toast.makeText(activityContext, "En principio añadido", Toast.LENGTH_SHORT).show();
                        //1. Comprobamos que realmente se ha metido el usuario
                        //(muchas veces devuelve que tod.o okay pero no hace la insercción)
                        existeJugador(nuevoJugador.getNombre()).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if(response.isSuccessful()){
                                    assert response.body() != null;
                                    if(response.body() == 1 && activityContext != null){
                                        //2. Si existe, y la actividad es no nula, cambios de actividad a la de logIn
                                        Toast.makeText(activityContext, "Jugador registrado", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(activityContext, MainActivity.class);
                                        activityContext.startActivity(intent);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Jugador> call, Throwable t) {
                        Toast.makeText(activityContext, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

}
