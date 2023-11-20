package com.example.practicaev_alberto_rodriguez;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MiListAdapter extends ArrayAdapter {
    ArrayList<Libro> libros;
    Context context;
    public MiListAdapter(@NonNull Context context, @NonNull ArrayList<Libro> objects) {
        super(context, R.layout.item_list_view, objects);
        this.libros = objects;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item_list_view, null);

        TextView titulo = vista.findViewById(R.id.itemTitulo);
        TextView autor = vista.findViewById(R.id.itemAutor);

        if(libros.get(position).getPaginas() > 300)
            vista.setBackgroundColor(ContextCompat.getColor(context, R.color.mayor300));
        else
            vista.setBackgroundColor(ContextCompat.getColor(context, R.color.menorIgual300));

        titulo.setText(libros.get(position).getTitulo());
        autor.setText(libros.get(position).getAutor());

        return vista;
    }
}
