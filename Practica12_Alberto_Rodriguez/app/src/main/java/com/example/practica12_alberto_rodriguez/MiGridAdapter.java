package com.example.practica12_alberto_rodriguez;

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

public class MiGridAdapter extends ArrayAdapter {
    ArrayList<Producto> productos;
    Context context;
    public MiGridAdapter(@NonNull Context context, @NonNull ArrayList<Producto> objects) {
        super(context, R.layout.grid_item, objects);
        this.productos = objects;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.grid_item, null);

        ImageView imagen = vista.findViewById(R.id.imgProducto);
        TextView nombre = vista.findViewById(R.id.nombre);
        TextView precio = vista.findViewById(R.id.precio);

        int idImg = context.getResources().getIdentifier(productos.get(position).getNombreImagen(), "drawable", context.getPackageName());
        imagen.setImageDrawable(ContextCompat.getDrawable(context, idImg));

        nombre.setText(productos.get(position).getNombre());
        precio.setText(productos.get(position).getPrecio()+"€");

        return vista;
    }
}
