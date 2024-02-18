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
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FragmentListTorneosAdapter extends ArrayAdapter {

    ArrayList<Torneo> torneos = new ArrayList<>();
    Context context;

    public FragmentListTorneosAdapter(@NonNull Context context, ArrayList<Torneo> torneos) {
        super(context, R.layout.item_partida_list_view, torneos);
        this.torneos = torneos;
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View vista = inflater.inflate(R.layout.item_torneo_list_view, null);
        Torneo torneo = torneos.get(position);

        TextView nombre = vista.findViewById(R.id.tvNombre);
        TextView fechaIni = vista.findViewById(R.id.tvFechaIni);
        TextView fechaFin = vista.findViewById(R.id.tvFechaFin);
        TextView Pais = vista.findViewById(R.id.tvPais);
        TextView estado = vista.findViewById(R.id.tvEstado);

        if(torneo.getId()!= 0) {
            nombre.setText(torneo.getNombre());
            fechaIni.setText("Inicio: "+torneo.getFechaInicio());
            fechaFin.setText("Fin:    "+torneo.getFechaFin());
            Pais.setText(torneo.getLugar());
            estado.setText(torneo.getTipo());
        }

        return vista;
    }
}
