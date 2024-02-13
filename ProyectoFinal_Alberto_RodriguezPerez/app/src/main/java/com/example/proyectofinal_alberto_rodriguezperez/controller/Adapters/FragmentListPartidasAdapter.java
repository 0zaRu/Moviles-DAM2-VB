package com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

import java.util.ArrayList;

public class FragmentListPartidasAdapter extends ArrayAdapter {
    ArrayList<Partida> partidas;
    Context context;

    public FragmentListPartidasAdapter(@NonNull Context context, ArrayList<Partida> partidas) {
        super(context, R.layout.item_partida_list_view, partidas);
        this.partidas = partidas;
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View vista = inflater.inflate(R.layout.item_partida_list_view, null);
        Partida partida = partidas.get(position);

        TextView jugador1 = vista.findViewById(R.id.tvJugador1);
        TextView jugador2 = vista.findViewById(R.id.tvJugador2);
        TextView resultado = vista.findViewById(R.id.tvResultado);
        TextView torneo = vista.findViewById(R.id.tvTorneo);
        //TextView verJugadas =

        if(partida.getId()!= 0) {
            jugador1.setText(partida.getRefJugadorBlancas());
            jugador2.setText(partida.getRefJugadorNegras());
            resultado.setText(partida.getResultado());

            if (partida.getIdTorneo() != 0)
                torneo.setText(partida.getRefTorneo());
        }

        //Permisos de visualización, modificación y borrado
        //Como esta clase la uso para listar ya sean partidas mias o no, sea admin o no, va a ser un if else que permita
        //Hacer tod-o en función del caso

        //NO ADMIN ================
        //Mis partidas -ver -modificar -eliminar
        //Otras partidas -ver

        //ADMIN ==================
        //Sea lo que sea -ver -modificar -eliminar

        return vista;
    }
}
