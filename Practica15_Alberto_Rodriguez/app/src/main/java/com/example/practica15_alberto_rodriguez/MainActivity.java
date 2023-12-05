package com.example.practica15_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Alumno> alumnos = new ArrayList<>();

    Spinner spinerAl;
    Button bDatos, bFoto;
    TextView tDelegado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alumnos.add(new Alumno("Alberto", "Rodríguez", "70906234V", R.drawable.persona));
        alumnos.add(new Alumno("Antonio", "Hernández", "45683653F", R.drawable.persona));

        spinerAl = findViewById(R.id.spinner);
        bDatos = findViewById(R.id.bDatos);
        bFoto = findViewById(R.id.bFoto);
        tDelegado = findViewById(R.id.textoDelegado);



    }
}