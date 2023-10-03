package com.example.practica4_ejercicio1_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonM = findViewById(R.id.bmorado);
        Button botonV = findViewById(R.id.bverde);

        botonM.setOnClickListener(this);
        botonV.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        TextView texto = findViewById(R.id.texto);

        if(v.getId() == R.id.bmorado)
            texto.setText("Has pulsado el botón morado");
        else
            texto.setText("Has pulsado el botón verde");
        /*
        switch(v.getId()){
            case R.id.bmorado:
                texto.setText("Has pulsado el botón morado");
                break;
            case R.id.bverde:
                texto.setText("Has pulsado el botón verde");
                break;
            default:
                //Nada
                break;
           */

        }
    }
