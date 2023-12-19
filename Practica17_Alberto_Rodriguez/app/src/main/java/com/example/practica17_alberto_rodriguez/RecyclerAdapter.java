package com.example.practica17_alberto_rodriguez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica17_alberto_rodriguez.Coche.Coche;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final ArrayList<Coche> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView numBast, marca, modelo, color;
        ImageView img;

        public ViewHolder(View view) {
            super(view);

            numBast = itemView.findViewById(R.id.numBast);
            marca = itemView.findViewById(R.id.marca);
            modelo = itemView.findViewById(R.id.modelo);
            color = itemView.findViewById(R.id.color);
            img = itemView.findViewById(R.id.marcaImg);
        }
    }

    public RecyclerAdapter(ArrayList<Coche> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.numBast.setText(localDataSet.get(position).getNumBastidor());
        viewHolder.marca.setText(localDataSet.get(position).getMarca());
        viewHolder.modelo.setText(localDataSet.get(position).getModelo());
        viewHolder.color.setText(localDataSet.get(position).getColor());
        viewHolder.img.setImageDrawable(ContextCompat.getDrawable(viewHolder.img.getContext(), R.drawable.cachao));

    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}