package com.example.practica16_alberto_rodriguez.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.practica16_alberto_rodriguez.Coche.Coche;
import com.example.practica16_alberto_rodriguez.R;

import java.util.ArrayList;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<Coche> coches;
    Context context;
    OnFragmentEventListener listener;

    public ListFragment(ArrayList<Coche> coches) {
        this.coches = coches;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if(context instanceof OnFragmentEventListener)
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
        View vista = inflater.inflate(R.layout.fragment_list, container, false);

        ListView lista = vista.findViewById(R.id.fragmentListView);
        FragmentListAdapter adaptador = new FragmentListAdapter(context, coches);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(this);

        return vista;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.estableceCoche((Coche) parent.getAdapter().getItem(position));
    }
}