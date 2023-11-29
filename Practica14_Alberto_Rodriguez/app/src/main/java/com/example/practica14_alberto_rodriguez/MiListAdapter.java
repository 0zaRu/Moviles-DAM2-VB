package com.example.practica14_alberto_rodriguez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practica14_alberto_rodriguez.Modelo.Articulo;

import java.util.ArrayList;
import java.util.List;

public class MiListAdapter extends ArrayAdapter {

    ArrayList<Articulo> articulos;
    Context context;

    public MiListAdapter(@NonNull Context context, @NonNull ArrayList<Articulo> objects) {
        super(context, R.layout.item_lista, objects);
        this.articulos = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item_lista, null);

        TextView codigo = vista.findViewById(R.id.itemCodigo);
        TextView nombre = vista.findViewById(R.id.itemNombre);
        TextView descrip = vista.findViewById(R.id.itemDescrip);
        TextView color = vista.findViewById(R.id.itemColor);
        TextView precio = vista.findViewById(R.id.itemPrecio);

        codigo.setText(articulos.get(position).getCodigo());
        nombre.setText(articulos.get(position).getNombre());
        descrip.setText(articulos.get(position).getDescripcion());
        color.setText(articulos.get(position).getColor());
        precio.setText(""+articulos.get(position).getPrecio()+" €");


        return vista;
    }
}
