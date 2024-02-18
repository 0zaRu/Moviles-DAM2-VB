package com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters.FragmentListJugadoresAdapter;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.view.DataActivity;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.service.JugadorService;

import java.util.ArrayList;
import java.util.List;
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

    public void introduceJugador(Context activityContext, Jugador nuevoJugador){
        serviceJugador.existeJugador(nuevoJugador.getNombre()).enqueue(new Callback<Integer>() {

            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if(response.isSuccessful()){
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());

                    if(response.body() == 1){
                        Toast.makeText(activityContext, "Nombre de usuario ya registrado", Toast.LENGTH_SHORT).show();

                    }else{

                        addJugador(activityContext, nuevoJugador);
                    }

                }
                else{
                    Log.d("TAG", "Error");
                    Toast.makeText(activityContext, "Error externo", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(activityContext, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addJugador(Context activityContext, Jugador nuevoUser){
        serviceJugador.addJugador(activityContext, nuevoUser);

    }

    public void modificaPerfil(Context contexto, Jugador jugador){
        serviceJugador.modificaJugador(contexto, jugador);
    }

    public void getJugadoresFiltrados(FragmentActivity contextParaLista, ListView listaJugadores, String textoBusqueda){
        serviceJugador.getJugadoresFiltrados(textoBusqueda).enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(@NonNull Call<List<Jugador>> call, @NonNull Response<List<Jugador>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    ArrayList<Jugador> jugadores = new ArrayList<>();
                    for (Jugador jugador : response.body()) {
                        Log.d("TAG", jugador.toString());
                        jugadores.add(jugador);
                    }

                    FragmentListJugadoresAdapter adapter = new FragmentListJugadoresAdapter(contextParaLista, jugadores);
                    listaJugadores.setAdapter(adapter);

                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Jugador>> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void getJugadoresFiltradosByAdmin(FragmentActivity contextParaLista, ListView listaJugadores){
        serviceJugador.getJugadoresFiltradosByAdmin().enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(@NonNull Call<List<Jugador>> call, @NonNull Response<List<Jugador>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    ArrayList<Jugador> jugadores = new ArrayList<>();
                    for (Jugador jugador : response.body()) {
                        Log.d("TAG", jugador.toString());
                        jugadores.add(jugador);
                    }

                    FragmentListJugadoresAdapter adapter = new FragmentListJugadoresAdapter(contextParaLista, jugadores);
                    listaJugadores.setAdapter(adapter);

                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Jugador>> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void getJugadorNameById(TextView tvPonerNombre, int jugadorId){
        serviceJugador.getJugadorNameById(jugadorId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    tvPonerNombre.setText(response.body());
                }
                else
                    System.out.println("error jugador nombre");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("fallo al conectar al php");
            }
        });
    }

    public void setUserSpinner(Context context, Spinner jugadorS, Partida partida, boolean esBlancas) {
        JugadorService js = new JugadorService();

        js.getJugadoresFiltrados("").enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                if(response.isSuccessful()){
                    ArrayList<String> nombres = new ArrayList<>();
                    assert response.body() != null;

                    for(Jugador jugador: response.body())
                        nombres.add(jugador.getNombre());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, nombres);
                    jugadorS.setAdapter(adapter);

                    if(partida != null){
                        if(esBlancas)
                            jugadorS.setSelection(nombres.indexOf(partida.getRefJugadorBlancas()));
                        else
                            jugadorS.setSelection(nombres.indexOf(partida.getRefJugadorNegras()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {

            }
        });
    }

    public void borrarJugador(Context context, int id) {
        JugadorService js = new JugadorService();

        js.borrarJugador(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful() && response.body() == 1)
                    Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void gestionaAdmin(Context context, Jugador jugadorSelect) {
        JugadorService js = new JugadorService();

        js.getNumAdmin().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){

                    if(response.body() == -1)
                        Toast.makeText(context, "Error de información", Toast.LENGTH_SHORT).show();

                    else if((response.body() == 0 || response.body() == 1) && jugadorSelect.getEsAdmin() == 0)
                        js.setAdmin(context, jugadorSelect, 1);

                    else if((response.body() == 0 || response.body() == 1) && jugadorSelect.getEsAdmin() == 1)
                        Toast.makeText(context, "Debe haber siempre 1 admin", Toast.LENGTH_SHORT).show();

                    else if(response.body() >= 2 && jugadorSelect.getEsAdmin() == 1)
                        js.setAdmin(context, jugadorSelect, 0);

                    else if(response.body() >= 2 && jugadorSelect.getEsAdmin() == 0)
                        Toast.makeText(context, "Solo puede haber 2 admin a la vez", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
