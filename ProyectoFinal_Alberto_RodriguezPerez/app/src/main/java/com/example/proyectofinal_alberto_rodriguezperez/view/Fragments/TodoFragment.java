package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class TodoFragment extends Fragment {

    private static final String ARG_USUARIO = "usuario";
    private final PartidaController partidaControl = new PartidaController();
    private final TorneoController torneoControl = new TorneoController();

    private Jugador mParam1;

    public static TodoFragment newInstance(Jugador param1) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public TodoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Jugador) getArguments().getSerializable(ARG_USUARIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista =  inflater.inflate(R.layout.fragment_todo, container, false);

        ListView listaTorneos = vista.findViewById(R.id.listaFragmentMisTorneosAbiertos);
        ListView listaPartidas = vista.findViewById(R.id.listaFragmentUltimasPartidas);

        torneoControl.getTorneos(getContext(), listaTorneos, mParam1.getId(), true, 2);
        partidaControl.getPartidas(getContext(), listaPartidas, mParam1.getId());


        return vista;
    }
}