package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.modelInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartidaInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartidaInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "jugador";
    private static final String ARG_PARAM2 = "partida";

    private Jugador jugador;
    private Partida partida;

    public PartidaInfoFragment() {}

    public static PartidaInfoFragment newInstance(Jugador jugador, Partida partida) {
        PartidaInfoFragment fragment = new PartidaInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, jugador);
        args.putSerializable(ARG_PARAM2, partida);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jugador = (Jugador) getArguments().getSerializable(ARG_PARAM1);
            partida = (Partida) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_partida_info, container, false);



        return vista;
    }
}