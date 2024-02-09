package com.example.proyectofinal_alberto_rodriguezperez.Interfaces;

import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JugadorDAO {

    @GET("getJugador.php")
    public Call<Jugador> getJugador(@Query("Nombre") String nombre, @Query("Passwd") String passwd);

}
