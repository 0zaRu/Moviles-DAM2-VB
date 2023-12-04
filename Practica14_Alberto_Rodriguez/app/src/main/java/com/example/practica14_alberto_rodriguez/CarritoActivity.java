package com.example.practica14_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practica14_alberto_rodriguez.Modelo.Articulo;
import com.example.practica14_alberto_rodriguez.Modelo.ArticuloContract;
import com.example.practica14_alberto_rodriguez.Modelo.Carrito;
import com.example.practica14_alberto_rodriguez.Modelo.Usuario;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {

    ListView muestraCarrito;
    TextView muestraPrecio;
    ArrayList<Carrito> items = new ArrayList<>();

    Usuario recibido = new Usuario();
    SQLHelper db = new SQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        muestraPrecio = findViewById(R.id.precioTotal);
        muestraCarrito = findViewById(R.id.muestraCarrito);

        recibido = (Usuario) getIntent().getSerializableExtra("user");


        recargaLista();
        registerForContextMenu(muestraCarrito);
    }

    @Override
    protected void onResume() {
        recargaLista();
        super.onResume();
    }

    public void recargaLista(){
        items = db.selectCarrito(recibido.getUser());

        MiCarritoAdapter adapter = new MiCarritoAdapter(this, items);
        muestraCarrito.setAdapter(adapter);

        float precioTotal = 0;
        for(Carrito item: items){
            ArrayList<Articulo> articulo = db.selectArticulos(null, ArticuloContract.CODIGO+" LIKE ?", new String[]{""+item.getArticulo()}, null, null, null);
            precioTotal += articulo.get(0).getPrecio()*item.getNumeroArticulos();
        }

        muestraPrecio.setText(precioTotal+" €");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.carrito_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int codeArt = ((Carrito)muestraCarrito.getAdapter().getItem(info.position)).getArticulo();

        if(item.getItemId() == R.id.suma)
            db.updateCantCarrito(recibido.getUser(), codeArt, true);

        else if(item.getItemId() == R.id.resta)
            db.updateCantCarrito(recibido.getUser(), codeArt, false);
            //Quedaría comprobar si está a 0 para que no se pueda -1 y si fuese así que se eliminase

        else if(item.getItemId() == R.id.elimina)
            db.deleteCarrito(codeArt);


        recargaLista();
        return super.onContextItemSelected(item);
    }
}