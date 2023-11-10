package com.example.practica12_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityPurchase extends AppCompatActivity {

    ImageView logoAmazon;
    ListView listadoCarro;
    TextView valorCompra;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        logoAmazon = findViewById(R.id.amazonLogoLista);
        logoAmazon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.amazon));

        ArrayList<Producto> productos = (ArrayList<Producto>)getIntent().getSerializableExtra("carrito");

        ArrayList<Carrito> carrito = new ArrayList<>();

        for(int i=0, cantidad; i<productos.size(); i++) {
            cantidad = 0;
            for (int j = 0; j < productos.size(); j++) {

                if(productos.get(i).getNombre().equals(productos.get(j).getNombre())
                && productos.get(i).getNombreImagen().equals(productos.get(j).getNombreImagen())
                && productos.get(i).getPrecio() == productos.get(j).getPrecio()
                && productos.get(i).getDescripcion().equals(productos.get(j).getDescripcion())) {
                    if (j != i)
                        productos.remove(productos.get(j));

                    cantidad++;
                }
            }
            carrito.add(new Carrito(cantidad, productos.get(i)));
        }

        MiListAdapter adaptador = new MiListAdapter(this, carrito);
        listadoCarro = findViewById(R.id.listaCompra);
        listadoCarro.setAdapter(adaptador);

        valorCompra = findViewById(R.id.precioLista);

        String numeroPrevio = "";
        double nuevoNumero = 0;

        for(int i = 0; i < productos.size(); i++){
            numeroPrevio = (String)valorCompra.getText();
            nuevoNumero = Double.parseDouble(numeroPrevio)+productos.get(i).getPrecio();
            valorCompra.setText(""+nuevoNumero);
        }

        valorCompra.setText(valorCompra.getText()+" €");

        findViewById(R.id.volverDelCarro).setOnClickListener(v -> {
            finish();
        });
    }
}
