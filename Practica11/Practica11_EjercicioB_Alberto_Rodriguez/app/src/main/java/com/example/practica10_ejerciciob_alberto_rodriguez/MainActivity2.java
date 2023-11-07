package com.example.practica10_ejerciciob_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity2 extends AppCompatActivity {

    EditText nombre, apellidos, dni, telefono, edad;
    Spinner provincia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        dni = findViewById(R.id.dni);
        telefono = findViewById(R.id.telefono);
        edad = findViewById(R.id.edad);
        provincia = findViewById(R.id.provincias);

        findViewById(R.id.aceptar).setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.putExtra("persona", new Persona(nombre.getText().toString(), apellidos.getText().toString(), telefono.getText().toString(), dni.getText().toString(), "salamanca", (edad.getText().toString())));

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}