package com.example.practica_ejercicio1_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Subscrib_Activity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscrib);

        TextView texto = findViewById(R.id.textView2);
        texto.setText(("Bienvenido "+getIntent().getStringExtra("usuario"))+".\n¿Desea suscribirse?");

        (findViewById(R.id.button2)).setOnClickListener(this);

        (findViewById(R.id.button3)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button2)
            setResult(RESULT_OK);
        else
            setResult(RESULT_CANCELED);

        finish();
    }
}