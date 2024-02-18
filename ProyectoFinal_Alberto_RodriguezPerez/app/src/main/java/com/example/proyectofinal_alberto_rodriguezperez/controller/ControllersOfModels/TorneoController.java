package com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters.FragmentListTorneosAdapter;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
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

    public void setTorneoSpinner(Context context, Spinner torneo, Partida partida) {
        TorneoService ts = new TorneoService();

        ts.getTorneosFiltrados("").enqueue(new Callback<List<Torneo>>() {
            @Override
            public void onResponse(Call<List<Torneo>> call, Response<List<Torneo>> response) {
                if(response.isSuccessful()){
                    ArrayList<String> nombres = new ArrayList<>();
                    assert response.body() != null;

                    for(Torneo torneo: response.body())
                        nombres.add(torneo.getNombre());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, nombres);
                    torneo.setAdapter(adapter);

                    if(partida != null)
                        torneo.setSelection(nombres.indexOf(partida.getRefTorneo()));
                }
            }

            @Override
            public void onFailure(Call<List<Torneo>> call, Throwable t) {

            }
        });
    }

    public void borrarTorneo(Context context, int id) {
        TorneoService ts = new TorneoService();

        ts.borrarTorneo(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful())
                    ((OnMyEvent)context).actualizaFragment();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void addOrModify(Context context, Torneo torneoN) {
        TorneoService ts = new TorneoService();

        ts.addOrModify(torneoN).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful() && response.body() == 1){
                    Toast.makeText(context, "Acción completada", Toast.LENGTH_SHORT).show();
                    ((OnMyEvent)context).actualizaFragment();
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
