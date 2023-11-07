package com.example.practica10_ejerciciob_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView nombre, apellidos, telefono, dni, edad, provincia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nombre = findViewById(R.id.mnombre);
        apellidos = findViewById(R.id.mapellidos);
        telefono = findViewById(R.id.mtelefono);
        dni = findViewById(R.id.mdni);
        edad = findViewById(R.id.medad);
        provincia = findViewById(R.id.mprovincias);

        findViewById(R.id.volver).setOnClickListener(v -> {
            finish();
        });
    }
}