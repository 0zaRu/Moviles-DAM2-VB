package com.example.proyectofinal_alberto_rodriguezperez.Interfaces;

import com.example.proyectofinal_alberto_rodriguezperez.model.Movimiento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovimientoDAO {

    @GET("getMovimientosPartida.php")
    public Call<List<Movimiento>> getMovimientosByPartida(@Query("id_partida") int id);

    @POST("addOrModifyMovimientos.php")
    Call<Integer> addOrModify(@Body ArrayList<Movimiento> movimientos);
}
