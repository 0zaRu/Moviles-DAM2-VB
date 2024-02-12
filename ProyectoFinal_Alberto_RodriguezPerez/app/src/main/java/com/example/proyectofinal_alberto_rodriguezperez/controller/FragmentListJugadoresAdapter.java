package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

import java.util.ArrayList;

public class FragmentListJugadoresAdapter extends ArrayAdapter {

    ArrayList<Jugador> jugadores = new ArrayList<>();
    Context context;
    ImageView foto;

    public FragmentListJugadoresAdapter(@NonNull Context context, ArrayList<Jugador> jugadores) {
        super(context, R.layout.item_partida_list_view, jugadores);
        this.jugadores = jugadores;
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View vista = inflater.inflate(R.layout.item_jugador_list_view, null);

        Jugador jugador = jugadores.get(position);

        TextView id = vista.findViewById(R.id.itemtvId);
        TextView nombre = vista.findViewById(R.id.itemtvNombre);
        TextView pais = vista.findViewById(R.id.itemtvPais);
        TextView elo = vista.findViewById(R.id.itemtvElo);
        foto = vista.findViewById(R.id.itemimFotoPerfil);


        id.setText("#" + jugador.getId());
        nombre.setText(jugador.getNombre());
        pais.setText(jugador.getPais());
        elo.setText("" + jugador.getElo());

        return vista;
    }
}
