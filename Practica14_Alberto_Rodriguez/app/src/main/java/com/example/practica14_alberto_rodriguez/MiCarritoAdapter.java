package com.example.practica14_alberto_rodriguez;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practica14_alberto_rodriguez.Modelo.Articulo;
import com.example.practica14_alberto_rodriguez.Modelo.ArticuloContract;
import com.example.practica14_alberto_rodriguez.Modelo.Carrito;

import java.util.ArrayList;

public class MiCarritoAdapter extends ArrayAdapter {

    ArrayList<Carrito> articulos;
    Context context;

    public MiCarritoAdapter(@NonNull Context context, @NonNull ArrayList<Carrito> objects) {
        super(context, R.layout.item_lista, objects);
        this.articulos = objects;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item_carrito, null);

        TextView nombre = vista.findViewById(R.id.carritoNombre);
        TextView precioU = vista.findViewById(R.id.carritoPrecioUnidad);
        TextView cant = vista.findViewById(R.id.carritoCant);
        TextView precioT = vista.findViewById(R.id.carritoPrecioTotal);


        cant.setText(""+articulos.get(position).getNumeroArticulos());

        SQLHelper db = new SQLHelper(context);

        ArrayList<Articulo> articulo = db.selectArticulos(null, ArticuloContract.CODIGO + " LIKE ? ", new String[]{""+articulos.get(position).getArticulo()}, null, null, null);

        nombre.setText(articulo.get(0).getNombre());
        precioU.setText(""+articulo.get(0).getPrecio());
        float precioFinal = articulos.get(position).getNumeroArticulos()*articulo.get(0).getPrecio();

        precioT.setText(""+precioFinal);

        return vista;
    }
}
