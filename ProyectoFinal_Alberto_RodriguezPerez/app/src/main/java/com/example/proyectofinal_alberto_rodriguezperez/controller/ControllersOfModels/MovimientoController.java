package com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters.FragmentListMovimientosAdapter;
import com.example.proyectofinal_alberto_rodriguezperez.model.Movimiento;
import com.example.proyectofinal_alberto_rodriguezperez.service.MovimientoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoController {

    MovimientoService ms = new MovimientoService();

    public void getMovimientosByPartida(Context context, Object listaJugadas, int id) {
        ms.getMovimientosByPartida(id).enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;

                    ArrayList<Movimiento> movimientos = new ArrayList<>();
                    for(Movimiento movimiento: response.body()){
                        movimientos.add(movimiento);
                    }

                    if(listaJugadas instanceof ListView) {
                        FragmentListMovimientosAdapter adapter = new FragmentListMovimientosAdapter(context, movimientos);
                        ((ListView)listaJugadas).setAdapter(adapter);
                    }
                    else if (listaJugadas instanceof TextView){
                        for(Movimiento movimiento: movimientos)
                            ((TextView)listaJugadas).append(movimiento+"\n");
                    }

                }
                else
                    Toast.makeText(context, "Hubo un problema", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                Toast.makeText(context, "Fallo al conectar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
