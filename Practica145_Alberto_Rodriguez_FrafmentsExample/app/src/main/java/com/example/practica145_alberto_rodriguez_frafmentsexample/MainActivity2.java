package com.example.practica145_alberto_rodriguez_frafmentsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements OnFragmentEventListener {

    TextView saludo;
    EditText nombre;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        saludo = findViewById(R.id.muestraTexto);
        nombre = findViewById(R.id.nombre);
        boton = findViewById(R.id.button);

        boton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            MiFragment fragment = new MiFragment(nombre.getText().toString());
            transaction.replace(R.id.frameFragment, fragment);

            transaction.commit();
        });

        findViewById(R.id.cambiaLayout).setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void fragmentSaludo() {
        saludo.setText("OUUU, main 2 hermano");
    }
}