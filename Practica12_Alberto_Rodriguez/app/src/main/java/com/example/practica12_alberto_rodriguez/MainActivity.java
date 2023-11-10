package com.example.practica12_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView logo, carro;
    TextView nObjectosCarro;
    ArrayList<Producto> productosDisponibles = new ArrayList<>(), carrito = new ArrayList<>();
    GridView vistaProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Rellenar listado de productos en venta
        productosDisponibles = ProductosDisponibles.rellenar();

        //Coger y asignar imagenes y datos del panel
        logo = findViewById(R.id.amazonLogo);
        carro = findViewById(R.id.carroImg);
        nObjectosCarro = findViewById(R.id.nObjetosCarro);
        logo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.amazon));
        carro.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carrito));

        //Crear y poner adaptador al gridview y el menu
        vistaProductos = findViewById(R.id.gridProductos);
        MiGridAdapter adaptadorGrid = new MiGridAdapter(this, productosDisponibles);
        vistaProductos.setAdapter(adaptadorGrid);

        registerForContextMenu(vistaProductos);


        findViewById(R.id.comprar).setOnClickListener(v -> {
            if(carrito.isEmpty()){
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ActivityPurchase.class);
            intent.putExtra("carrito", carrito);

            startActivity(intent);
            onPause();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.config){
            Toast.makeText(this, "Implementa el botón, vago", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.grid_context_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.details){
            Intent intent = new Intent(this, ActivityDetails.class);
            intent.putExtra("producto", (Producto)vistaProductos.getAdapter().getItem(info.position));

            startActivity(intent);
            onPause();
        
        } else if (item.getItemId() == R.id.add) {
            carrito.add((Producto) vistaProductos.getAdapter().getItem(info.position));
            String numeroPrevio = (String)nObjectosCarro.getText();
            int nuevoNumero = Integer.parseInt(numeroPrevio)+1;

            nObjectosCarro.setText(""+nuevoNumero);
        }

        return super.onContextItemSelected(item);
    }
}