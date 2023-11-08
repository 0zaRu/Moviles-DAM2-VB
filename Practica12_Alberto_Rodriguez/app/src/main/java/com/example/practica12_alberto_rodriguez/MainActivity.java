package com.example.practica12_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView logo, carro;
    ArrayList<Producto> productosDisponibles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.amazonLogo);
        carro = findViewById(R.id.carroImg);

        logo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.amazon));
        carro.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carrito));

        productosDisponibles = ProductosDisponibles.rellenar();

    }
}