package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class BuscarTopMenuFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "usuario";

    private Jugador mParam1;
    private OnMyEvent buscarActivity;
    EditText etBuscar;

    public BuscarTopMenuFragment() {
    }

    public static BuscarTopMenuFragment newInstance(Jugador param1) {
        BuscarTopMenuFragment fragment = new BuscarTopMenuFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnMyEvent)
            buscarActivity = (OnMyEvent) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        buscarActivity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Jugador) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_buscar_top_menu, container, false);

        etBuscar = vista.findViewById(R.id.BuscarEtBusqueda);

        Button busqueda = vista.findViewById(R.id.BuscarButlupa);
        Button perfil = vista.findViewById(R.id.BuscarButPerfil);
        perfil.setText(mParam1.getNombre());

        busqueda.setOnClickListener(this);
        perfil.setOnClickListener(this);


        return vista;
    }

    @Override
    public void onClick(View v) {
        buscarActivity.botoneraBuscar(v, etBuscar.getText().toString());
    }
}