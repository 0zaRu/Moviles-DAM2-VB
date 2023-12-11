package com.example.practica16_alberto_rodriguez.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practica16_alberto_rodriguez.Coche.Coche;
import com.example.practica16_alberto_rodriguez.R;

public class DetailFragment extends Fragment {
    Coche coche;
    public DetailFragment(Coche coche) {
        this.coche = coche;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView tnumBastidor = vista.findViewById(R.id.detBastidor);
        TextView tmarca = vista.findViewById(R.id.detMarca);
        TextView tmodelo = vista.findViewById(R.id.detModelo);
        TextView tcombustible = vista.findViewById(R.id.detCombustible);
        TextView tcolor = vista.findViewById(R.id.detColor);
        TextView tkilometraje = vista.findViewById(R.id.detKilometraje);

        tnumBastidor.setText(coche.getNumBastidor());
        tmarca.setText(coche.getMarca());
        tmodelo.setText(coche.getModelo());
        tcombustible.setText(coche.getCombustible());
        tcolor.setText(coche.getColor());
        tkilometraje.setText(Integer.toString(coche.getKilometraje()));

        return vista;
    }
}