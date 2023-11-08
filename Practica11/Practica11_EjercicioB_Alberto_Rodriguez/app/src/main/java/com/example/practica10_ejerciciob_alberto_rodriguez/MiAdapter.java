package com.example.practica10_ejerciciob_alberto_rodriguez;

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
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MiAdapter extends ArrayAdapter {
    ArrayList<Persona> personas;
    Context context;

    public MiAdapter(Context context, ArrayList<Persona> personas){
        super(context, R.layout.item1, personas);
        this.personas = new ArrayList<>();
        this.personas = personas;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = null;
        if(position % 2 == 0)
            vista = inflater.inflate(R.layout.item1, null);
        else
            vista = inflater.inflate(R.layout.item2, null);

        TextView nombreApellidos = vista.findViewById(R.id.nombre);
        ImageView muestraImagen = vista.findViewById(R.id.imagen);

        muestraImagen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.persona));
        nombreApellidos.setText(this.personas.get(position).getNombre() +"\n"+ this.personas.get(position).getApellidos());


        return vista;
    }
}
