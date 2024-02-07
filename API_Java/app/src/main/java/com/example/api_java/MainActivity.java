package com.example.api_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.api_java.model.Libro;

public class MainActivity extends AppCompatActivity {

    private final LibroController lc = new LibroController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lc.createLibros(new Libro(0, "X", "T"));

        lc.getLibros();

        lc.getLibro(5);
    }
}