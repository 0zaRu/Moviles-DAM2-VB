package com.example.practicaevaluable_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityMenor18 extends AppCompatActivity {

    ConstraintLayout fondo;
    TextView muestra;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menor18);

        fondo = findViewById(R.id.mainLayout);
        muestra = findViewById(R.id.tMuestra);

        String color = getIntent().getStringExtra("color");

        if(color.equals("Rosa"))
            fondo.setBackgroundColor(Color.MAGENTA);
        else if (color.equals("Azul"))
            fondo.setBackgroundColor(Color.BLUE);
        else
            fondo.setBackgroundColor(Color.GREEN);

        muestra.setText("Hola "+ getIntent().getStringExtra("nombre"));
    }
}