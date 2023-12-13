package com.example.practica16_alberto_rodriguez.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.practica16_alberto_rodriguez.Coche.Coche;
import com.example.practica16_alberto_rodriguez.Dialogs.ConfirmDialog;
import com.example.practica16_alberto_rodriguez.Dialogs.DialogAddOrModify;
import com.example.practica16_alberto_rodriguez.R;

import java.util.ArrayList;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<Coche> coches;
    Context context;
    OnFragmentEventListener listener;
    ListView lista = null;

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

        lista = vista.findViewById(R.id.fragmentListView);
        recargaLista();

        //HACER SOLO SI EL MÓVIL NO ESTÁ TUMBADO
        registerForContextMenu(lista);

        return vista;
    }
    private void recargaLista(){
        FragmentListAdapter adaptador = new FragmentListAdapter(context, coches);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.estableceCoche((Coche) parent.getAdapter().getItem(position));
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(getContext());
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.modificar){
            DialogAddOrModify dialog = new DialogAddOrModify((Coche)lista.getAdapter().getItem(info.position));
            dialog.show(getActivity().getSupportFragmentManager(), "llamadaFragment");

        }else if(item.getItemId() == R.id.eliminar){
            ConfirmDialog dialog = new ConfirmDialog((Coche)lista.getAdapter().getItem(info.position));
            dialog.show(getActivity().getSupportFragmentManager(), "llamadaFragment");
        }


        recargaLista();
        return super.onContextItemSelected(item);
    }
}