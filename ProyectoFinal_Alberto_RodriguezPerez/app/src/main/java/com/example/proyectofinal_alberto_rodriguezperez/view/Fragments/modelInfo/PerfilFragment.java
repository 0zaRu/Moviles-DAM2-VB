package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.modelInfo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs.RegisterOrEditDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment{
    private static final String ARG_PARAM1 = "jugadorVer";
    private static final String ARG_PARAM2 = "jugadorSer";
    private static final String ARG_PARAM3 = "modo";

    private Jugador jugadorVer, jugadorSer;
    private String modo;
    ImageView foto;

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(Jugador jugadorSer, Jugador jugadorVer, String modo) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, jugadorVer);
        args.putSerializable(ARG_PARAM2, jugadorSer);
        args.putString(ARG_PARAM2, modo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jugadorVer = (Jugador) getArguments().getSerializable(ARG_PARAM1);
            jugadorSer = (Jugador) getArguments().getSerializable(ARG_PARAM2);
            modo = getArguments().getString(ARG_PARAM3);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView id = vista.findViewById(R.id.tvId);
        TextView nombre = vista.findViewById(R.id.tvNombre);
        TextView pais = vista.findViewById(R.id.tvPais);
        TextView elo = vista.findViewById(R.id.tvElo);
        TextView fechaNacimiento = vista.findViewById(R.id.tvFechaNaci);
        TextView correoElectronico = vista.findViewById(R.id.tvCorreo);
        TextView esAdmin = vista.findViewById(R.id.tvAdmin);
        foto = vista.findViewById(R.id.imFotoPerfil);

        if(jugadorVer != null){
            id.setText("" + jugadorVer.getId());
            nombre.setText(jugadorVer.getNombre());
            pais.setText(jugadorVer.getPais());
            elo.setText("" + jugadorVer.getElo());

            if(jugadorVer.getId() == jugadorSer.getId() || jugadorSer.getEsAdmin() == 1) {
                fechaNacimiento.setText(jugadorVer.getFechaNacimiento().toString());
                correoElectronico.setText(jugadorVer.getCorreoElectronico());
                esAdmin.setText("" + jugadorVer.getEsAdmin());
            }
            else{
                fechaNacimiento.setVisibility(View.INVISIBLE);
                correoElectronico.setVisibility(View.INVISIBLE);
                esAdmin.setVisibility(View.INVISIBLE);
            }
        }

        vista.findViewById(R.id.floatEditButton).setOnClickListener(v -> {
            RegisterOrEditDialog dialog =new RegisterOrEditDialog(jugadorVer);
            dialog.show(getActivity().getSupportFragmentManager(), "llamadaEditar");
        });

        return vista;
    }
}