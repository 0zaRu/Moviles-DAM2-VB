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

public class MiListAdapter extends ArrayAdapter {
    ArrayList<Carrito> carrito;
    Context context;
    public MiListAdapter(@NonNull Context context, @NonNull ArrayList<Carrito> objects) {
        super(context, R.layout.list_item, objects);
        this.carrito = objects;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.list_item, null);

        ImageView imagen = vista.findViewById(R.id.imgLista);
        TextView nombre = vista.findViewById(R.id.nombreLista);
        TextView precio = vista.findViewById(R.id.precioLista);
        TextView descrip = vista.findViewById(R.id.descripLista);
        TextView unidades = vista.findViewById(R.id.unidades);

        int idImg = context.getResources().getIdentifier(carrito.get(position).getProducto().getNombreImagen(), "drawable", context.getPackageName());
        imagen.setImageDrawable(ContextCompat.getDrawable(context, idImg));

        nombre.setText(carrito.get(position).getProducto().getNombre());
        precio.setText(carrito.get(position).getProducto().getPrecio()+"€");
        descrip.setText(carrito.get(position).getProducto().getDescripcion());
        unidades.setText("Unidades: "+carrito.get(position).getCantidad());

        return vista;
    }
}
