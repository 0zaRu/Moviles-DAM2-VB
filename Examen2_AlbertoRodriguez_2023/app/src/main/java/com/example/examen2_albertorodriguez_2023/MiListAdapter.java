package com.example.examen2_albertorodriguez_2023;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import java.util.List;

public class MiListAdapter extends ArrayAdapter {
    ArrayList<Animal> animales = new ArrayList<>();
    Context context;

    public MiListAdapter(@NonNull Context context, @NonNull ArrayList<Animal> objects) {
        super(context, R.layout.item,objects);
        this.animales = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item, null);
        int idImagen = 0;
        if(animales.get(position).getTipo().equals("pajaro")){
            idImagen = R.drawable.pajaro;
        }else if(animales.get(position).getTipo().equals("gato")){
            idImagen = R.drawable.gato;
        }else if(animales.get(position).getTipo().equals("perro")){
            idImagen = R.drawable.perro;
        }

        ImageView imagen = vista.findViewById(R.id.muestraImagen);
        imagen.setImageDrawable(ContextCompat.getDrawable(context, idImagen));

        TextView nombre = vista.findViewById(R.id.muestraNombre);
        TextView peso = vista.findViewById(R.id.muestraPeso);

        nombre.setText(animales.get(position).getNombre());
        peso.setText(Double.toString(animales.get(position).getPeso()));


        return vista;
    }
}
