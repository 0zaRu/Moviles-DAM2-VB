package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ContextMenuController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

public class TodoFragment extends Fragment {

    private static final String ARG_USUARIO = "usuario";
    private final PartidaController partidaControl = new PartidaController();
    private final TorneoController torneoControl = new TorneoController();
    ListView listaPartidas, listaTorneos;

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

        listaTorneos = vista.findViewById(R.id.listaFragmentMisTorneosAbiertos);
        registerForContextMenu(listaTorneos);

        listaPartidas = vista.findViewById(R.id.listaFragmentUltimasPartidas);
        registerForContextMenu(listaPartidas);

        torneoControl.getTorneos(getContext(), listaTorneos, mParam1.getId(), true, 2);
        partidaControl.getPartidas(getContext(), listaPartidas, mParam1.getId(), 14);


        return vista;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        if(v.getId() == listaTorneos.getId())
            if(mParam1.getEsAdmin() == 1)
                inflater.inflate(R.menu.torneos_view_mod_del_menu, menu);
            else
                inflater.inflate(R.menu.torneos_view_menu, menu);
        else
            inflater.inflate(R.menu.partidas_view_mod_del_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ListView lvReferencia = (ListView) info.targetView.getParent();

        if (lvReferencia == listaPartidas)
            ContextMenuController.partidasMenu((Partida) listaPartidas.getAdapter().getItem(info.position), mParam1, item, getActivity());
        else
            ContextMenuController.torneosMenu((Torneo) listaTorneos.getAdapter().getItem(info.position), mParam1, item, getActivity());

        return super.onContextItemSelected(item);
    }
}