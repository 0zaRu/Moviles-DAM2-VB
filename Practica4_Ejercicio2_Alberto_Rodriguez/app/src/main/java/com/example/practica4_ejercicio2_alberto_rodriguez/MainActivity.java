package com.example.practica4_ejercicio2_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mostrar;
    EditText escrito;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostrar = findViewById(R.id.tMostrado);
        escrito = findViewById(R.id.tEscrito);
        Button boton = findViewById(R.id.boton_medio);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mostrar.setText(escrito.getText());
    }
}