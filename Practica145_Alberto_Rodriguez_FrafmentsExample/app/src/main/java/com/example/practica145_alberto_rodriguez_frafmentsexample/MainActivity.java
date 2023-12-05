package com.example.practica145_alberto_rodriguez_frafmentsexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView muestraMensaje;
    Button fragmento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muestraMensaje = findViewById(R.id.muestraTexto);

        findViewById(R.id.button).setOnClickListener(v -> {
            //CAMBIAR FRAFMENTO
        });


    }


}