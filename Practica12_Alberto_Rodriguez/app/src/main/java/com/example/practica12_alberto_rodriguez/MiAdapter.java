package com.example.practica12_alberto_rodriguez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MiAdapter extends ArrayAdapter {
    ArrayList<Producto> productos;
    Context context;
    public MiAdapter(@NonNull Context context, @NonNull ArrayList<Producto> objects) {
        super(context, R.layout.item, objects);
        this.productos = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item, null);

        TextView nombre = vista.findViewById(R.id.nombre);


        return vista;
    }
}
