package com.example.practica5_ejercicio3_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup grupo;
    RadioButton bRojo, bVerde, bAzul;

    Button bLimpiar;

    ConstraintLayout fondo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo = findViewById(R.id.fondo);

        grupo = findViewById(R.id.grupo);
        grupo.setOnCheckedChangeListener(this);

        bRojo = findViewById(R.id.rbRojo);
        bAzul = findViewById(R.id.rbAzul);
        bVerde = findViewById(R.id.rbVerde);

        bLimpiar = findViewById(R.id.bLimpiar);
        bLimpiar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        grupo.clearCheck();
        fondo.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_background));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.rbRojo)
            fondo.setBackgroundColor(Color.RED);

        else if(checkedId == R.id.rbVerde)
            fondo.setBackgroundColor(Color.GREEN);

        else if(checkedId == R.id.rbAzul)
            fondo.setBackgroundColor(Color.BLUE);
    }
}