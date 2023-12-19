package com.example.examenfragments_albertorodriguez.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.R;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter {

    private ArrayList<Libro> lista;
    private Context context;
    public Adaptador(@NonNull Context context, ArrayList<Libro> libros) {
        super(context, R.layout.item, libros);
        this.context = context;
        this.lista = libros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null);

        TextView titulo = v.findViewById(R.id.titulo);
        TextView autor = v.findViewById(R.id.autor);

        titulo.setText(lista.get(position).getTitulo());
        autor.setText(lista.get(position).getAutor());

        return v;
    }
}
