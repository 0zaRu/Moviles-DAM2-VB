package com.example.practica10_ejercicioa_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Coche> coches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        coches = new ArrayList<>();
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

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.Eliminar){
            coches.remove(coches.get(info.position));
            MiAdapter miAdaptador = new MiAdapter(this, coches);
            listView.setAdapter(miAdaptador);

        }else if(item.getItemId() == R.id.Detalles){
            Toast.makeText(this, coches.get(info.position).toString(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}