package com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Buscar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class BuscarFormatoFragment extends Fragment {
    public BuscarFormatoFragment() {}

    private OnMyEvent dataActivity;
    RadioGroup radioGroup;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnMyEvent)
            dataActivity = (OnMyEvent) context;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(radioGroup != null){
            radioGroup.clearCheck();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataActivity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_buscar_formato, container, false);

        radioGroup = vista.findViewById(R.id.grupoFiltradoBusqueda);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = vista.findViewById(checkedId);
            String textoSeleccionado = "";

            if(group.getCheckedRadioButtonId() != -1)
                textoSeleccionado = radioButton.getText().toString();

            dataActivity.tipoFiltradoBuscar(textoSeleccionado);
        });


        //Cuando trabaje con partidas debo asignar el contextMenu de solo visionado EN CASO de que vean un torneo y vean sus partidas asociadas
        return vista;
    }
}