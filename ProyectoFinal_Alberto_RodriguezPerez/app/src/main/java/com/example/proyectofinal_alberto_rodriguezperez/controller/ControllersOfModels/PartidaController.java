package com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters.FragmentListPartidasAdapter;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.service.MovimientoService;
import com.example.proyectofinal_alberto_rodriguezperez.service.PartidaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartidaController {
    private final PartidaService servicePartida = new PartidaService();

    public void getPartidas(Context contextParaLista, ListView lvEditando, int jugadorId, int filtroDiasAtras) {
        //Filtro días
        //0 - Sin filtro
        //otro - ese numero de días hacia atrás

        servicePartida.getPartidasUser(jugadorId, filtroDiasAtras).enqueue(
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

    public void borrarPartida(Context context, int id) {
        PartidaService ps = new PartidaService();
        ps.borrarPartida(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() == 1)
                        Toast.makeText(context, "Se ha borrado correctamente", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Fallo al hacer el borrado", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(context, "Error de borrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Error a la hora de conectar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addOrModify(Context context, Partida partinaN) {
        PartidaService ps = new PartidaService();

        ps.addOrModify(partinaN).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful() && response.body() == 1){
                    MovimientoService ms = new MovimientoService();

                    ms.addOrModify(partinaN.getMovimientos()).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if(response.isSuccessful() && response.body() == 1){
                                Toast.makeText(context, "Resultado exitoso", Toast.LENGTH_SHORT).show();
                                ((OnMyEvent)context).actualizaFragment();
                            }
                            else
                                Toast.makeText(context, "Error al cambiar movimientos", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(context, "Error llegando al php", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                    Toast.makeText(context, "Fallo al insertar la partida", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Fallo intentando acceder", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPartidasByTorneo(Context context, ListView partidasAso, int id) {
        PartidaService ps = new PartidaService();

        ps.getPartidasByTorneo(id).enqueue(new Callback<List<Partida>>() {
            @Override
            public void onResponse(Call<List<Partida>> call, Response<List<Partida>> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    ArrayList<Partida> partidas = new ArrayList<>();

                    for(Partida partida: response.body()){
                        partidas.add(partida);
                    }

                    FragmentListPartidasAdapter adapter = new FragmentListPartidasAdapter(context, partidas);
                    partidasAso.setAdapter(adapter);
                }
                else
                    Toast.makeText(context, "Fallo recibiendo partidas", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Partida>> call, Throwable t) {
                Toast.makeText(context, "Error contactando", Toast.LENGTH_SHORT).show();
            }
        });
    }
}