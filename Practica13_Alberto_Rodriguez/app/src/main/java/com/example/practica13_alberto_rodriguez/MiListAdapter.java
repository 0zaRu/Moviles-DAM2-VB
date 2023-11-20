package com.example.practica13_alberto_rodriguez;

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

public class MiListAdapter extends ArrayAdapter {
    ArrayList<Alumno> alumnos;
    Context context;
    public MiListAdapter(@NonNull Context context, @NonNull ArrayList<Alumno> objects) {
        super(context, R.layout.item, objects);
        this.alumnos = objects;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item, null);

        TextView nombre = vista.findViewById(R.id.itemNombre);
        TextView dni = vista.findViewById(R.id.itemDni);


        nombre.setText(alumnos.get(position).getNombre());
        dni.setText(alumnos.get(position).getDni());

        return vista;
    }
}
