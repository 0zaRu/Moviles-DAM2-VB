package com.example.proyectofinal_alberto_rodriguezperez.controller;

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

    ArrayList<Partida> partidas = new ArrayList<>();
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
            jugador1.setText(""+partida.getIdJugadorBlancas());
            jugador2.setText(""+partida.getIdJugadorNegras());
            resultado.setText(""+partida.getResultado());
            if (partida.getIdTorneo() != 0) torneo.setText(""+partida.getIdTorneo());
        }

        return vista;
    }
}