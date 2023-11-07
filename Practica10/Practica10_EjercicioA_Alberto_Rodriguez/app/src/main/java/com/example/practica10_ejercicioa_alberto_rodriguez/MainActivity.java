package com.example.practica10_ejercicioa_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ArrayList<Coche> coches = new ArrayList<>();
        coches.add(new Coche("Audi", "A1"));
        coches.add(new Coche("Audi", "A2"));
        coches.add(new Coche("Audi", "A3"));
        coches.add(new Coche("Peugeout", "208"));
        coches.add(new Coche("Peugeout", "308"));
        coches.add(new Coche("Peugeout", "3008"));
        coches.add(new Coche("Seat", "león"));
        coches.add(new Coche("Seat", "Ibiza"));

        MiAdapter miAdaptador = new MiAdapter(this, coches);
        listView.setAdapter(miAdaptador);
    }
}