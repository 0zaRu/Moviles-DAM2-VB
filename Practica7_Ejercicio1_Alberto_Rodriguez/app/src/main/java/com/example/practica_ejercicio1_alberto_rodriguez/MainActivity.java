package com.example.practica_ejercicio1_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nUsuario;
    Button bAceptar;
    TextView mostrar;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nUsuario = findViewById(R.id.editText);
        bAceptar = findViewById(R.id.button);
        bAceptar.setOnClickListener(v -> {
            Intent intent = new Intent(this, Subscrib_Activity.class);
            intent.putExtra("usuario", String.valueOf(nUsuario.getText()));

            startActivity(intent);
            finish();
        });

        if(getIntent().getStringExtra("usuario") != null){
            mostrar = (findViewById(R.id.textView3));
            mostrar.setText("De acuerdo "+getIntent().getStringExtra("usuario")+
                    ", usted ha "+getIntent().getStringExtra("resultado")+" la subscripción a nuestro servicio.");

            nUsuario.setText(getIntent().getStringExtra("usuario"));
        }
    }
}