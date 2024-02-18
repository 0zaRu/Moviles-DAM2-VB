package com.example.proyectofinal_alberto_rodriguezperez.service;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.JugadorDAO;
import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.MovimientoDAO;
import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.PartidaDAO;
import com.example.proyectofinal_alberto_rodriguezperez.model.Movimiento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovimientoService {
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://172.23.86.211/chess-app-proyectoARP/Movimiento/")
                //.baseUrl("http://10.0.2.2/chess-app-proyectoARP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Call<List<Movimiento>> getMovimientosByPartida(int id) {
        return getRetrofit().create(MovimientoDAO.class).getMovimientosByPartida(id);
    }

    public Call<Integer> addOrModify(ArrayList<Movimiento> movimientos) {
        return getRetrofit().create(MovimientoDAO.class).addOrModify(movimientos);
    }
}
