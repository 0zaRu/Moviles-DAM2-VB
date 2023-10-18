package com.example.ejemplospinneryadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] numeros = {"1", "2", "3"};
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numeros);
        spinner.setAdapter(adaptador);

        //SPINNER CON DATOS EN STRING.XML ======================================================
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adaptador2 = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adaptador2);
    }
}