package com.example.proyectofinal_alberto_rodriguezperez.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    private static final String ARG_PARAM1 = "usuario";

    private Jugador mParam1;

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(Jugador param1) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Jugador) getArguments().getSerializable(ARG_PARAM1);
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
        TextView passwd = vista.findViewById(R.id.tvPasswd);
        TextView esAdmin = vista.findViewById(R.id.tvAdmin);

        if(mParam1 != null){
            id.setText("" + mParam1.getId());
            nombre.setText(mParam1.getNombre());
            pais.setText(mParam1.getPais());
            elo.setText("" + mParam1.getElo());
            fechaNacimiento.setText(mParam1.getFechaNacimiento().toString());
            correoElectronico.setText(mParam1.getCorreoElectronico());
            passwd.setText(mParam1.getPasswd());
            esAdmin.setText("" + mParam1.getEsAdmin());
        }

        return vista;
    }
}