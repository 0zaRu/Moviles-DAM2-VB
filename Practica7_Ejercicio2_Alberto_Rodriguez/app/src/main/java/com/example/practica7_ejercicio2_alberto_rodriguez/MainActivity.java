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

        String correos[] = new String[1];

        destinatario = findViewById(R.id.Destinatario);
        asunto = findViewById(R.id.Asunto);
        cuerpo = findViewById(R.id.Cuerpo);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            correos[0] = destinatario.getText().toString();
            intent.putExtra(Intent.EXTRA_EMAIL, correos);
            intent.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, cuerpo.getText().toString());

            Intent compartido = Intent.createChooser(intent, "Nuevo correo");
            startActivity(compartido);
        });
    }
}