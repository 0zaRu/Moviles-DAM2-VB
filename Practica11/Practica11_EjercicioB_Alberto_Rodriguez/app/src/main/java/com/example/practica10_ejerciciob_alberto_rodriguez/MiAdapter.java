package com.example.practica10_ejerciciob_alberto_rodriguez;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        TextView dni = vista.findViewById(R.id.dni);
        TextView edad = vista.findViewById(R.id.edad);
        TextView telefono = vista.findViewById(R.id.telefono);
        TextView provincia = vista.findViewById(R.id.provincia);


        nombreApellidos.setText(this.personas.get(position).getNombre() +" "+ this.personas.get(position).getApellidos());
        dni.setText(this.personas.get(position).getDni());
        edad.setText(this.personas.get(position).getEdad());
        telefono.setText(this.personas.get(position).getTelefono());
        provincia.setText(this.personas.get(position).getProvincia());

        return vista;
    }
}
