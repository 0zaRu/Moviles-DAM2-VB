package com.example.proyectofinal_alberto_rodriguezperez.Interfaces;

import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PartidaDAO {
    @GET("getPartidasByUser.php")
    public Call<List<Partida>> getPartidasUser(@Query("id_Jugador") int jugadorID);

}
