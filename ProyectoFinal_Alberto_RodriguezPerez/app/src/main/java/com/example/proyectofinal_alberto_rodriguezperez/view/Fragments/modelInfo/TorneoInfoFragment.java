package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.modelInfo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ContextMenuController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.MovimientoController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Movimiento;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TorneoInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TorneoInfoFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "jugador";
    private static final String ARG_PARAM2 = "torneo";
    private static final String ARG_PARAM3 = "modo";
    //puede ser "ver" o "editar"

    private Jugador jugador;
    private Torneo torneo;
    private String modo;

    MovimientoController mc = new MovimientoController();
    PartidaController pc = new PartidaController();
    JugadorController jc = new JugadorController();
    TorneoController tc = new TorneoController();
    private Calendar calendar;
    Button fechaIni, fechaFin;
    ListView partidasAso;
    EditText nombre, lugar;
    Spinner estado;

    public TorneoInfoFragment() {}

    public static TorneoInfoFragment newInstance(Jugador jugador, Torneo torneo, String modo) {
        TorneoInfoFragment fragment = new TorneoInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, jugador);
        args.putSerializable(ARG_PARAM2, torneo);
        args.putString(ARG_PARAM3, modo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jugador = (Jugador) getArguments().getSerializable(ARG_PARAM1);
            torneo = (Torneo) getArguments().getSerializable(ARG_PARAM2);
            modo = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = null;

        if(modo.equals("ver"))
        {
            vista = inflater.inflate(R.layout.fragment_torneo_info, container, false);

            TextView nombre = vista.findViewById(R.id.tvTorneoNombre);
            TextView estado = vista.findViewById(R.id.tvTorneoEstado);
            TextView fechaIni = vista.findViewById(R.id.tvTorneoFechaIni);
            TextView fechaFin = vista.findViewById(R.id.tvTorneoFechaFin);
            TextView lugar = vista.findViewById(R.id.tvTorneoLugar);
            partidasAso = vista.findViewById(R.id.listViewPartidasAdociadas);

            nombre.setText(torneo.getNombre());
            estado.setText(torneo.getTipo());
            fechaIni.setText(torneo.getFechaInicio());
            fechaFin.setText(torneo.getFechaFin());
            lugar.setText(torneo.getLugar());
            pc.getPartidasByTorneo(getContext(), partidasAso, torneo.getId());
            registerForContextMenu(partidasAso);

            if(jugador.getEsAdmin() == 1)
                vista.findViewById(R.id.floatTorneoVerButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PopupMenu popupMenu = new PopupMenu(getContext(), v);
                        popupMenu.getMenuInflater().inflate(R.menu.torneos_view_mod_del_menu, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                ContextMenuController.torneosMenu(torneo, jugador, item, getActivity());
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });
        }

        else if(modo.equals("editar") || modo.equals("add"))
        {
            vista = inflater.inflate(R.layout.fragment_torneo_editar, container, false);

            ArrayList<String> estadoV = new ArrayList<>();
            estadoV.add("Abierto");
            estadoV.add("Cerrado");

            nombre = vista.findViewById(R.id.etTorneoNombre);
            estado = vista.findViewById(R.id.spTorneoEstado);
            fechaIni = vista.findViewById(R.id.etTorneoFechaIni);
            fechaFin = vista.findViewById(R.id.etTorneoFechaFin);
            lugar = vista.findViewById(R.id.etTorneoLugar);
            partidasAso = vista.findViewById(R.id.listViewPartidasAdociadas);

            vista.findViewById(R.id.floatTorneoEditButton).setOnClickListener(this);

            ArrayAdapter<String> adapterE = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, estadoV);
            estado.setAdapter(adapterE);

            fechaIni.setOnClickListener(v -> {
                showDatePickerDialog(v);
            });

            fechaFin.setOnClickListener(v -> {
                showDatePickerDialog(v);
            });

            if(modo.equals("editar"))
            {
                estado.setSelection(estadoV.indexOf(torneo.getTipo()));
                nombre.setText(torneo.getNombre());
                fechaIni.setText(torneo.getFechaInicio());
                fechaFin.setText(torneo.getFechaFin());
                lugar.setText(torneo.getLugar());

                pc.getPartidasByTorneo(getContext(), partidasAso, torneo.getId());
                registerForContextMenu(partidasAso);
            }
            else {
                ArrayList<String> falso = new ArrayList<>();
                falso.add("Sin partidas asociadas");

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, falso);
                partidasAso.setAdapter(adapter);
            }

        }

        return vista;
    }


    @Override
    public void onClick(View v) {

        Torneo torneoN = new Torneo();

        if(modo.equals("editar"))
            torneoN.setId(torneo.getId());
        else if(modo.equals("add"))
            torneoN.setId(-1);

        if(nombre.getText().toString().isEmpty() || lugar.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
            return;
        }

        torneoN.setNombre(nombre.getText().toString());
        torneoN.setFechaInicio(fechaIni.getText().toString());
        torneoN.setFechaFin(fechaFin.getText().toString());
        torneoN.setLugar(lugar.getText().toString());
        torneoN.setTipo(estado.getSelectedItem().toString());

        tc.addOrModify(getContext(), torneoN);

    }

    private void showDatePickerDialog(View v) {
        calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String selectedDate = sdf.format(calendar.getTime());

                        if (v.getId() == R.id.etTorneoFechaIni)
                            fechaIni.setText(selectedDate);
                        else
                            fechaFin.setText(selectedDate);

                    }
                },

                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();

        if(jugador.getEsAdmin() == 1)
            inflater.inflate(R.menu.partidas_view_mod_del_menu, menu);
        else
            inflater.inflate(R.menu.partidas_view_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ContextMenuController.partidasMenu((Partida) partidasAso.getAdapter().getItem(info.position), jugador, item, getActivity());

        return super.onContextItemSelected(item);
    }
}