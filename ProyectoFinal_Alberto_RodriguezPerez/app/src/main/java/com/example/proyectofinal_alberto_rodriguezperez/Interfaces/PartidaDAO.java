package com.example.proyectofinal_alberto_rodriguezperez.Interfaces;

import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PartidaDAO {
    @GET("getPartidasByUser.php")
    public Call<List<Partida>> getPartidasUser(@Query("id_Jugador") int jugadorID);

    @GET("getPartidasByUserFiltroTiempo.php")
    public Call<List<Partida>> getPartidasUserFiltroDias(@Query("id_Jugador") int jugadorID, @Query("numero_dias") int diasAtras);

    @POST("borrarPartidaById.php")
    public Call<Integer> borrarPartidaById(@Query("partidaId") int id);

    @POST("addOrModifyPartida.php")
    Call<Integer> addOrModify(@Body Partida partinaN);

    @GET("getPartidasByTorneo.php")
    Call<List<Partida>> getPartidasByTorneo(@Query("torneoId") int torneoId);
}
