package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectofinal_alberto_rodriguezperez.view.DataActivity;
import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.service.JugadorService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugadorController {

    private final JugadorService serviceJugador = new JugadorService();

    public void getJugador(Context activityContext, String nombre, String passwd) {
        serviceJugador.getJugador(nombre, passwd).enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(@NonNull Call<Jugador> call, @NonNull Response<Jugador> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());

                    if(response.body() != null && response.body().getId() != 0){
                        Intent intent = new Intent((Context) activityContext, DataActivity.class);
                        intent.putExtra("jugador", response.body());

                        ((Context) activityContext).startActivity(intent);

                    }else{
                        Toast.makeText((Context) activityContext, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("TAG", "Error");
                    Toast.makeText(activityContext, "Error externo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Jugador> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(activityContext, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addJugador(Context activityContext, Jugador nuevoUser){
        //serviceJugador.addJugador()

                /*
                * Mañana sigo.
                * He pensado que antes de seguir, la unica forma de validar que el usuario se mete correctamnete
                * es que donde lo inserte, también compruebe justo después que existe ese usuario, es decir, crear
                * getUsuario(name), que podré utilizarlo tanto en el paso final de añadir (porque hay veces que el servidor
                * devuelve 200 pero la insercción no se ha hecho), y ya que está hecho, usarla en el register para validar
                * que el nombre de usuario no está en uso
                * */
    }
}
