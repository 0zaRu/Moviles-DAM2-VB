package com.example.practica16_alberto_rodriguez.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practica16_alberto_rodriguez.Coche.Coche;
import com.example.practica16_alberto_rodriguez.R;

import java.util.ArrayList;

public class FragmentListAdapter extends ArrayAdapter{

    ArrayList<Coche> coches;
    Context context;

    public FragmentListAdapter(@NonNull Context context, @NonNull ArrayList<Coche> objects) {
        super(context, R.layout.frag_list_item, objects);
        this.coches = objects;
        this.context = context;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.frag_list_item, null);
        Coche coche = coches.get(position);

        TextView texto = vista.findViewById(R.id.fListText);
        texto.setText(coche.getNumBastidor()+" ("+coche.getMarca()+", "+coche.getModelo()+")");

        return vista;
    }
}
