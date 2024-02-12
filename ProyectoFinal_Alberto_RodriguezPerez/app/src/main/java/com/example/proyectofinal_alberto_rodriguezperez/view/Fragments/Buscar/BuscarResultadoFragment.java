package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Buscar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class BuscarResultadoFragment extends Fragment {

    private static final String ARG_PARAM1 = "usuario";
    private static final String ARG_PARAM2 = "estiloBusqueda";
    private static final String ARG_PARAM3 = "textoBusqueda";


    private Jugador jugador;
    private String estilobusqueda;
    private String textoBusqueda;

    TorneoController tc = new TorneoController();
    JugadorController jc = new JugadorController();

    public BuscarResultadoFragment() {}

    // TODO: Rename and change types and number of parameters
    public static BuscarResultadoFragment newInstance(Jugador param1, String param2, String param3) {
        BuscarResultadoFragment fragment = new BuscarResultadoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jugador = (Jugador) getArguments().getSerializable(ARG_PARAM1);
            estilobusqueda = getArguments().getString(ARG_PARAM2);
            textoBusqueda = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_buscar_resultado, container, false);

        ListView lista = vista.findViewById(R.id.BusquedaResultLst);

        //Empieza el filtrado por saber si tengo que buscar torneo, jugadores o partida(Blancas o negras)
        if(estilobusqueda.equals("Torneos"))
        {
            tc.getTorneosFiltrados(getContext(), lista, textoBusqueda);
        }
        else if(estilobusqueda.equals("Jugadores"))
        {
            jc.getJugadoresFiltrados(getContext(), lista, textoBusqueda);
        }

        return vista;
    }
}