package com.example.practica12_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Producto p = (Producto)getIntent().getSerializableExtra("producto");

        ImageView img = findViewById(R.id.imagenProducto);
        TextView nombre = findViewById(R.id.nombreProducto);
        TextView precio = findViewById(R.id.precioProducto);
        TextView descrip = findViewById(R.id.descripcionProducto);

        int idImg = getResources().getIdentifier(p.getNombreImagen(), "drawable", getPackageName());
        img.setImageDrawable(ContextCompat.getDrawable(this, idImg));

        nombre.setText(p.getNombre());
        precio.setText(p.getPrecio()+" €");
        descrip.setText(p.getDescripcion());

        findViewById(R.id.volver).setOnClickListener(v -> {
            finish();
        });
    }
}