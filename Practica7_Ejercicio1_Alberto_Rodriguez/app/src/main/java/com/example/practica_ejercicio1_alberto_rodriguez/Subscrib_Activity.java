package com.example.practica_ejercicio1_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Subscrib_Activity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscrib);

        TextView texto = findViewById(R.id.textView2);
        texto.setText(("Bienvenido "+getIntent().getStringExtra("usuario"))+".\n¿Desea suscribirse?");

        (findViewById(R.id.button2)).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", getIntent().getStringExtra("usuario"));
            intent.putExtra("resultado", "ACEPTADO");

            startActivity(intent);
            finish();
        });

        (findViewById(R.id.button3)).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", getIntent().getStringExtra("usuario"));
            intent.putExtra("resultado", "CANCELADO");

            startActivity(intent);
            finish();
        });
    }
}