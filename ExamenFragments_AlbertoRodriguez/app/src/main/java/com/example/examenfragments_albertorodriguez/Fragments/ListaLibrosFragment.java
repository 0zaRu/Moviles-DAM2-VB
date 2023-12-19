package com.example.examenfragments_albertorodriguez.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.examenfragments_albertorodriguez.Controlador.Adaptador;
import com.example.examenfragments_albertorodriguez.Controlador.SQLHelper;
import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.R;

import java.util.ArrayList;

public class ListaLibrosFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<Libro> libros = new ArrayList<>();

    OnFragmentEventListener listener;

    public ListaLibrosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentEventListener && context != null)
            listener = (OnFragmentEventListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_lista_libros, container, false);
        SQLHelper db = new SQLHelper(getActivity());
        libros = db.getLibros();
        ListView listado = vista.findViewById(R.id.fListaLibros);

        Adaptador miAdapter = new Adaptador(getActivity(), libros);
        listado.setAdapter(miAdapter);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            listado.setOnItemClickListener(this);

        return vista;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.libroSeleccionado((Libro) parent.getAdapter().getItem(position));
    }
}