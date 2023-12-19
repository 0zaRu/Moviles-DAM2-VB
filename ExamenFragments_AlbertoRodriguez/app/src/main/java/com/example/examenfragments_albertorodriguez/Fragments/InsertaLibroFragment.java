package com.example.examenfragments_albertorodriguez.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examenfragments_albertorodriguez.Controlador.SQLHelper;
import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.R;

public class InsertaLibroFragment extends Fragment {

    SQLHelper db = new SQLHelper(getActivity());
    OnFragmentEventListener listener;

    public InsertaLibroFragment() {
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

        View vista = inflater.inflate(R.layout.fragment_inserta_libro, container, false);


        EditText nombre = vista.findViewById(R.id.fInsertTitle);
        EditText autor = vista.findViewById(R.id.fInsertAutor);
        EditText pags = vista.findViewById(R.id.fInsertPags);

        vista.findViewById(R.id.finsertButton).setOnClickListener(v -> {
            if(nombre.getText().toString().isEmpty() ||
               autor.getText().toString().isEmpty() ||
               pags.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Alguno de los campos está vacío", Toast.LENGTH_SHORT).show();
            }

            db.insertarLibro(new Libro(
                    nombre.getText().toString(),
                    autor.getText().toString(),
                    Integer.parseInt(pags.getText().toString())
            ));

            Toast.makeText(getActivity(), "Libro insertado correctamente", Toast.LENGTH_SHORT).show();

            listener.actualizaListado();
        });


        return vista;
    }
}