package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Admin;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ContextMenuController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

public class AdminUsersFragment extends Fragment {

    private static final String ARG_PARAM1 = "usuario";

    private Jugador jugador;
    JugadorController jc = new JugadorController();
    ListView lista;

    public AdminUsersFragment() {}

    // TODO: Rename and change types and number of parameters
    public static AdminUsersFragment newInstance(Jugador param1) {
        AdminUsersFragment fragment = new AdminUsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jugador = (Jugador) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_buscar_resultado, container, false);

        lista = vista.findViewById(R.id.BusquedaResultLst);
        jc.getJugadoresFiltradosByAdmin(getActivity(), lista);

        registerForContextMenu(lista);

        return vista;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        if(jugador.getEsAdmin() == 1)
            inflater.inflate(R.menu.jugadores_view_mod_del_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(jugador.getEsAdmin() == 1)
            ContextMenuController.jugadoresMenu((Jugador) lista.getAdapter().getItem(info.position), jugador, item, getActivity());

        return super.onContextItemSelected(item);
    }

}