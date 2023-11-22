package com.example.examen2_albertorodriguez_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button insertar;
    private Button mostrar;
    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertar = findViewById(R.id.insertar);
        mostrar = findViewById(R.id.mostrar);
        buscar = findViewById(R.id.buscar);

        insertar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.insertar) {
                intent = new Intent(this, InsertarActivity.class);
        } else if (v.getId() == R.id.mostrar) {
                intent = new Intent(this, MostrarActivity.class);
        } else if (v.getId() == R.id.buscar) {
            intent = new Intent(this, BuscarActivity.class);
        }
        
        if (intent != null)
            startActivity(intent);
    }
}