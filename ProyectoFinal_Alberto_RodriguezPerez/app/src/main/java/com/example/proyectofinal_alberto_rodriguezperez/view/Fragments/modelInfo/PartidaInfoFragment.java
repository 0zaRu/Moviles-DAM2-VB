package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.modelInfo;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters.FragmentListMovimientosAdapter;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ContextMenuController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.MovimientoController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Movimiento;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartidaInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartidaInfoFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "jugador";
    private static final String ARG_PARAM2 = "partida";
    private static final String ARG_PARAM3 = "modo";
    //puede ser "ver" o "editar"

    private Jugador jugador;
    private Partida partida;
    private String modo;

    MovimientoController mc = new MovimientoController();
    PartidaController pc = new PartidaController();
    JugadorController jc = new JugadorController();
    TorneoController tc = new TorneoController();
    private Calendar calendar;
    TextView verFecha;
    EditText verJugadas, lugar;
    Spinner jugadorB, jugadorN, resultado, torneo;

    public PartidaInfoFragment() {}

    public static PartidaInfoFragment newInstance(Jugador jugador, Partida partida, String modo) {
        PartidaInfoFragment fragment = new PartidaInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, jugador);
        args.putSerializable(ARG_PARAM2, partida);
        args.putString(ARG_PARAM3, modo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jugador = (Jugador) getArguments().getSerializable(ARG_PARAM1);
            partida = (Partida) getArguments().getSerializable(ARG_PARAM2);
            modo = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = null;

        if(modo.equals("ver"))
        {
            vista = inflater.inflate(R.layout.fragment_partida_info, container, false);

            TextView jugadorB = vista.findViewById(R.id.tvJugador1);
            TextView jugadorN = vista.findViewById(R.id.tvJugador2);
            TextView resultado = vista.findViewById(R.id.tvResultado);
            TextView torneo = vista.findViewById(R.id.tvTorneo);
            TextView fecha = vista.findViewById(R.id.tvFechaPartida);
            TextView lugar = vista.findViewById(R.id.tvLugarPartida);
            ListView verJugadas = vista.findViewById(R.id.listViewMovimientos);

            jugadorB.setText(partida.getRefJugadorBlancas());
            jugadorN.setText(partida.getRefJugadorNegras());
            resultado.setText(partida.getResultado());
            torneo.setText(partida.getRefTorneo());
            fecha.setText(partida.getFecha());
            lugar.setText(partida.getLugar());

            mc.getMovimientosByPartida(getContext(), verJugadas, partida.getId());

            if(jugador.getEsAdmin() == 1)
                vista.findViewById(R.id.floatPartidaVerButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PopupMenu popupMenu = new PopupMenu(getContext(), v);
                        popupMenu.getMenuInflater().inflate(R.menu.partidas_view_mod_del_menu, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                ContextMenuController.partidasMenu(partida, jugador, item, getActivity());
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });
        }

        else if(modo.equals("editar") || modo.equals("add"))
        {
            vista = inflater.inflate(R.layout.fragment_partida_editar, container, false);

            ArrayList<String> resultadosV = new ArrayList<>();
            resultadosV.add("1-0");
            resultadosV.add("0-1");
            resultadosV.add("1/2-1/2");

            jugadorB = vista.findViewById(R.id.tvJugador1);
            jugadorN = vista.findViewById(R.id.tvJugador2);
            resultado = vista.findViewById(R.id.tvResultado);
            torneo = vista.findViewById(R.id.tvTorneo);
            lugar = vista.findViewById(R.id.etLugarPartida);
            verJugadas = vista.findViewById(R.id.tvMovimientos);
            verFecha = vista.findViewById(R.id.tvVerFechaSelect);

            vista.findViewById(R.id.floatPartidaModifyButton).setOnClickListener(this);

            jc.setUserSpinner(getContext(), jugadorB, partida, true);
            jc.setUserSpinner(getContext(), jugadorN, partida, false);
            tc.setTorneoSpinner(getContext(), torneo, partida);

            ArrayAdapter<String> adapterR = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, resultadosV);
            resultado.setAdapter(adapterR);

            vista.findViewById(R.id.buttonTimePicker).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
                }
            });

            if(modo.equals("editar"))
            {
                verFecha.setText(partida.getFecha());
                lugar.setText(partida.getLugar());
                resultado.setSelection(resultadosV.indexOf(partida.getResultado()));
                verJugadas.setText("");

                mc.getMovimientosByPartida(getContext(), verJugadas, partida.getId());
            }
            else
                verJugadas.setHint("Formato:  1 e4 e5\n2 c3 c6\n...");
        }

        return vista;
    }


    @Override
    public void onClick(View v) {
        Partida partidaN = new Partida();

        if(modo.equals("editar"))
            partidaN.setId(partida.getId());
        else if(modo.equals("add"))
            partidaN.setId(-1);

        if(verFecha.getText().toString().isEmpty() || lugar.getText().toString().isEmpty() || verJugadas.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
            return;
        }

        partidaN.setFecha(verFecha.getText().toString());
        partidaN.setLugar(lugar.getText().toString());
        partidaN.setResultado(resultado.getSelectedItem().toString());
        partidaN.setRefJugadorBlancas(jugadorB.getSelectedItem().toString());
        partidaN.setRefJugadorNegras(jugadorN.getSelectedItem().toString());
        partidaN.setRefTorneo(torneo.getSelectedItem().toString());

        String[] preMovimientos = verJugadas.getText().toString().split("\\n");
        ArrayList<Movimiento> movimientos = new ArrayList<>();

        for(String lineaMov: preMovimientos){
            String[] valoresLinea = lineaMov.split(" ");
            movimientos.add(new Movimiento(
                    Integer.parseInt(valoresLinea[0]),
                    valoresLinea[1],
                    valoresLinea[2],
                    partida.getId()
            ));
        }
        System.out.println(movimientos);
        partidaN.setMovimientos(movimientos);

        pc.addOrModify(getContext(), partidaN);

    }


    private void showDatePickerDialog() {
        calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String selectedDate = sdf.format(calendar.getTime());
                        verFecha.setText(selectedDate);
                    }
                },

                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}