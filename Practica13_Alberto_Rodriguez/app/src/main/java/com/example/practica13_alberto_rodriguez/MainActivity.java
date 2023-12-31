package com.example.practica13_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bInsertar).setOnClickListener(v -> {
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.bMostrar).setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.bBuscar).setOnClickListener(v -> {
            Intent intent = new Intent(this, FindActivity.class);
            startActivity(intent);
        });
    }
}