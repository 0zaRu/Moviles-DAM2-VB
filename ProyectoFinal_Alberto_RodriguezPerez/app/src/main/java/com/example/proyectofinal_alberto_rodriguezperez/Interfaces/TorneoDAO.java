package com.example.proyectofinal_alberto_rodriguezperez.Interfaces;

import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TorneoDAO {
    @GET("getTorneosByUser.php")
    public Call<List<Torneo>> getTorneosById(@Query("id") int jugadorID);
    @GET("getAllTorneosSinUser.php")
    public Call<List<Torneo>> getTorneosByIdSinUser(@Query("id") int jugadorID);
    @GET("getTorneosByUserFiltrado.php")
    public Call<List<Torneo>> getTorneosByIdYFiltro(@Query("id") int jugadorID, @Query("estado") String estado);
    @GET("getTorneosFiltrados.php")
    public Call<List<Torneo>> getTorneosFiltrados(@Query("filtro") String txtFiltro);
    @GET("getNombreTorneoById.php")
    public Call<String> getNombreTorneoById(@Query("id") int torneoId);

    @POST("borrarTorneoById.php")
    Call<Integer> borrarTorneo(@Query("torneoId") int id);

    @POST("addOrModifyTorneo.php")
    Call<Integer> addOrModify(@Body Torneo torneo);
}
