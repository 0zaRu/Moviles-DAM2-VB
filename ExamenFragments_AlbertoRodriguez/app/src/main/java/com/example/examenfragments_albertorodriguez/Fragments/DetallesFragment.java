package com.example.examenfragments_albertorodriguez.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.R;

public class DetallesFragment extends Fragment {
    private static final String ARG_PARAM1 = "libro";

    // TODO: Rename and change types of parameters
    private Libro libro = null;

    public DetallesFragment() {
        // Required empty public constructor
    }
    public static DetallesFragment newInstance(Libro libro) {
        DetallesFragment fragment = new DetallesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, libro);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            libro = (Libro)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_detalles, container, false);

        TextView id = vista.findViewById(R.id.fid);
        TextView titulo = vista.findViewById(R.id.fTitulo);
        TextView autor = vista.findViewById(R.id.fAutor);
        TextView pags = vista.findViewById(R.id.fPags);

        if(libro != null){
            id.setText(libro.getId());
            titulo.setText(libro.getTitulo());
            autor.setText(libro.getAutor());
            pags.setText(""+libro.getNumPags());
        }

        return vista;
    }
}