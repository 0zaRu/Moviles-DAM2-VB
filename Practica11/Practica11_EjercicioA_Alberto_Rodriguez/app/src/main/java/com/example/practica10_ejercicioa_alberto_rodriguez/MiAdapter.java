package com.example.practica10_ejercicioa_alberto_rodriguez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;

public class MiAdapter extends ArrayAdapter {
    ArrayList<Coche> coches;
    Context context;

    public MiAdapter(Context context, ArrayList<Coche> coches){
        super(context, R.layout.item, coches);
        this.coches = new ArrayList<>();
        this.coches = coches;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.item, null);

        TextView tVMarca = vista.findViewById(R.id.marca);
        TextView tVModelo = vista.findViewById(R.id.modelo);

        tVMarca.setText(this.coches.get(position).getMarca());
        tVModelo.setText(this.coches.get(position).getModelo());

        return vista;
    }
}
