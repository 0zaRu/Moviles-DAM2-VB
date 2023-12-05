package com.example.practica145_alberto_rodriguez_frafmentsexample;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MiFragment extends androidx.fragment.app.Fragment{

    private OnFragmentEventListener listener;
    private String nombre;

    public MiFragment(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentEventListener)
            listener = (OnFragmentEventListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_layout, container, false);

        Button boton = v.findViewById(R.id.botonFrg);
        boton.setText(nombre);

        boton.setOnClickListener(v1 -> {
            listener.fragmentSaludo();
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}