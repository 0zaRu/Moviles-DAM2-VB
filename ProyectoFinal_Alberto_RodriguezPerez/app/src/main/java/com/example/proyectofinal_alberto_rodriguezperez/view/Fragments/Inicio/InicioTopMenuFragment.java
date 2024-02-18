package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class InicioTopMenuFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "usuario";

    private Jugador mParam1;
    private OnMyEvent inicioActivity;

    public InicioTopMenuFragment() {
    }

    public static InicioTopMenuFragment newInstance(Jugador param1) {
        InicioTopMenuFragment fragment = new InicioTopMenuFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnMyEvent)
            inicioActivity = (OnMyEvent) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        inicioActivity = null;
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
        View vista = inflater.inflate(R.layout.fragment_inicio_top_menu, container, false);

        Button todo = vista.findViewById(R.id.InicioButTodo);
        Button partidas = vista.findViewById(R.id.InicioButPartidas);
        Button torneos = vista.findViewById(R.id.InicioButTorneos);
        ImageButton perfil = vista.findViewById(R.id.InicioButPerfil);

        todo.setOnClickListener(this);
        partidas.setOnClickListener(this);
        torneos.setOnClickListener(this);
        perfil.setOnClickListener(this);


        return vista;
    }

    @Override
    public void onClick(View v) {
        inicioActivity.botoneraInicio(v);
    }
}