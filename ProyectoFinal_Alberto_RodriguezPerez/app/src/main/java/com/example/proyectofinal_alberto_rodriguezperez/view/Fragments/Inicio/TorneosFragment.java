package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ContextMenuController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

public class TorneosFragment extends Fragment {

    private static final String ARG_USUARIO = "usuario";
    private final TorneoController torneoControl = new TorneoController();
    private ListView listaMisTorneos, listaOtrosTorneos;
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

        listaMisTorneos = vista.findViewById(R.id.listaFragmentMisTorneos);
        listaOtrosTorneos = vista.findViewById(R.id.listaFragmentTorneos);

        torneoControl.getTorneos(getContext(), listaMisTorneos, mParam1.getId(), true, 1);
        torneoControl.getTorneos(getContext(), listaOtrosTorneos, mParam1.getId(), false, 1);

        registerForContextMenu(listaMisTorneos);
        registerForContextMenu(listaOtrosTorneos);

        return vista;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        if(mParam1.getEsAdmin() == 1)
            inflater.inflate(R.menu.torneos_view_mod_del_menu, menu);
        else
            inflater.inflate(R.menu.torneos_view_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ListView lvReferencia = (ListView) info.targetView.getParent();

        if (lvReferencia == listaMisTorneos) {
            ContextMenuController.torneosMenu((Torneo) listaMisTorneos.getAdapter().getItem(info.position), item, getContext());
        } else if (lvReferencia == listaOtrosTorneos) {
            ContextMenuController.torneosMenu((Torneo) listaOtrosTorneos.getAdapter().getItem(info.position), item, getContext());
        }

        return super.onContextItemSelected(item);
    }
}