package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ContextMenuController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartidasFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PartidasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USUARIO = "usuario";
    private final PartidaController partidaControl = new PartidaController();

    // TODO: Rename and change types of parameters
    private Jugador mParam1;
    ListView listaPartidas;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PartidasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartidasFragment newInstance(Jugador param1) {
        PartidasFragment fragment = new PartidasFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public PartidasFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Jugador) getArguments().getSerializable(ARG_USUARIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_partidas, container, false);

        listaPartidas = vista.findViewById(R.id.listaFragmentPartidas);
        registerForContextMenu(listaPartidas);
        //Un admin debería poder ver el 100%

        partidaControl.getPartidas(getContext(), listaPartidas, mParam1.getId(), 0);

        return vista;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.partidas_view_mod_del_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ContextMenuController.partidasMenu((Partida) listaPartidas.getAdapter().getItem(info.position), item, getContext());

        return super.onContextItemSelected(item);
    }
}