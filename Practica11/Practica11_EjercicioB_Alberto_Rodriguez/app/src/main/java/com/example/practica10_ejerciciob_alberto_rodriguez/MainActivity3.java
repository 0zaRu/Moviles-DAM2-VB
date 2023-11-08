package com.example.practica10_ejerciciob_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView nombre, apellidos, telefono, dni, edad, provincia;
    ImageView imgArriba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Persona p = (Persona)getIntent().getSerializableExtra("persona");

        imgArriba = findViewById(R.id.imagenArriba);
        nombre = findViewById(R.id.mnombre);
        apellidos = findViewById(R.id.mapellidos);
        telefono = findViewById(R.id.mtelefono);
        dni = findViewById(R.id.mdni);
        edad = findViewById(R.id.medad);
        provincia = findViewById(R.id.mprovincias);

        nombre.setText(p.getNombre());
        apellidos.setText(p.getApellidos());
        telefono.setText(p.getTelefono());
        dni.setText(p.getDni());
        edad.setText(p.getEdad());
        provincia.setText(p.getProvincia());

        imgArriba.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.persona256));

        findViewById(R.id.volver).setOnClickListener(v -> {
            finish();
        });
    }
}