package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class TorneosFragment extends Fragment {

    private static final String ARG_USUARIO = "usuario";
    private final TorneoController torneoControl = new TorneoController();

    private Jugador mParam1;

    public static TorneosFragment newInstance(Jugador param1) {
        TorneosFragment fragment = new TorneosFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public TorneosFragment() {

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


        View vista =  inflater.inflate(R.layout.fragment_torneos, container, false);

        ListView listaMisTorneos = vista.findViewById(R.id.listaFragmentMisTorneos);
        ListView listaOtrosTorneos = vista.findViewById(R.id.listaFragmentTorneos);

        torneoControl.getTorneos(getContext(), listaMisTorneos, mParam1.getId(), true, 1);
        torneoControl.getTorneos(getContext(), listaOtrosTorneos, mParam1.getId(), false, 1);


        return vista;
    }
}