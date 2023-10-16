package com.example.practica7_ejercicio2_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText destinatario, asunto, cuerpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        destinatario = findViewById(R.id.Destinatario);
        asunto = findViewById(R.id.Asunto);
        cuerpo = findViewById(R.id.Cuerpo);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_EMAIL, destinatario.getText().toString());
            intent.putExtra(Intent.EXTRA_EMAIL, asunto.getText().toString());
            intent.putExtra(Intent.EXTRA_EMAIL, cuerpo.getText().toString());

            startActivity(intent);
        });
    }
}