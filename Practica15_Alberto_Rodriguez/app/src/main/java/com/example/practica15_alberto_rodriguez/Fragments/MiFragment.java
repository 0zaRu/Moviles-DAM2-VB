package com.example.practica15_alberto_rodriguez.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica15_alberto_rodriguez.Alumno;
import com.example.practica15_alberto_rodriguez.R;

public class MiFragment extends Fragment {

    OnFragmentEventListener listener;
    Context context;
    Alumno alumno;
    boolean esDato;

    public MiFragment(Alumno item, boolean datoOFoto) {
        this.alumno = item;
        this.esDato = datoOFoto;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        if(context instanceof OnFragmentEventListener)
            listener = (OnFragmentEventListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;

        if(esDato) {
            v = inflater.inflate(R.layout.fragment_dato, container, false);
            TextView nombre = v.findViewById(R.id.fNombre);
            TextView apellido = v.findViewById(R.id.fApellido);
            TextView dni = v.findViewById(R.id.fDni);

            nombre.setText(alumno.getNombre());
            apellido.setText(alumno.getApellidos());
            dni.setText(alumno.getDni());

            v.findViewById(R.id.bSetDelegado).setOnClickListener(v1 -> {
                listener.nombreDelegado(alumno);
            });
        }
        else{
            v = inflater.inflate(R.layout.fragment_img, container, false);
            ImageView img = v.findViewById(R.id.fImg);

            img.setImageDrawable(ContextCompat.getDrawable(context, alumno.getFoto()));
        }

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}