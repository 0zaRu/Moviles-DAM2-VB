package com.example.proyectofinal_alberto_rodriguezperez.controller.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Movimiento;

import java.util.ArrayList;

public class FragmentListMovimientosAdapter extends ArrayAdapter {

    ArrayList<Movimiento> movimientos = new ArrayList<>();
    Context context;
    ImageView foto;

    public FragmentListMovimientosAdapter(@NonNull Context context, ArrayList<Movimiento> movimientos) {
        super(context, R.layout.item_movimiento_list_view, movimientos);
        this.movimientos = movimientos;
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View vista = inflater.inflate(R.layout.item_movimiento_list_view, null);

        Movimiento movimiento = movimientos.get(position);

        TextView nMovimiento = vista.findViewById(R.id.nMovimiento);
        TextView movBlancas = vista.findViewById(R.id.movBlancas);
        TextView movNegras = vista.findViewById(R.id.movNegras);


        nMovimiento.setText(""+movimiento.getNumeroMovimiento());
        movBlancas.setText(movimiento.getMovimiento1());
        movNegras.setText(movimiento.getMovimiento2());

        return vista;
    }
}
