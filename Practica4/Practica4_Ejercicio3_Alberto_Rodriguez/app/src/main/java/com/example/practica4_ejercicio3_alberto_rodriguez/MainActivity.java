package com.example.practica4_ejercicio3_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton bBoton;
    TextView tTexto;
    CheckBox cCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tTexto = findViewById(R.id.texto);
        bBoton = findViewById(R.id.boton);
        bBoton.setOnClickListener(this);
        cCheck = findViewById(R.id.check);
        cCheck.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.boton) {
            if (bBoton.isChecked())
                tTexto.setText("Botón Pulsasdo");
            else
                tTexto.setText("Pulsa el botón");
        }

        if (v.getId() == R.id.check && cCheck.isChecked() && bBoton.isChecked()) {
            bBoton.setChecked(false);
            bBoton.callOnClick();

        }else if(v.getId() == R.id.check && !cCheck.isChecked() && !bBoton.isChecked()) {
            bBoton.setChecked(true);
            bBoton.callOnClick();
        }
    }
}