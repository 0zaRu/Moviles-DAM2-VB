package com.example.practica14_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practica14_alberto_rodriguez.Modelo.Articulo;
import com.example.practica14_alberto_rodriguez.Modelo.Carrito;
import com.example.practica14_alberto_rodriguez.Modelo.Usuario;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    TextView muestraNombreUsr, contadorCarrito;
    ListView muestraCatalog;
    ArrayList<Articulo> articulos;
    ArrayList<Carrito> carrito = new ArrayList<>();
    SQLHelper db = new SQLHelper(this);
    Usuario usr;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        muestraNombreUsr = findViewById(R.id.muestraUser);
        usr = (Usuario) getIntent().getSerializableExtra("usr");

        contadorCarrito = findViewById(R.id.carritoCont);
        contadorCarrito.setText("0");

        assert usr != null;
        muestraNombreUsr.setText("Bienvenido, "+usr.getNombre());

        articulos = db.listaArticulos(null, null, null, null, null, null);

        muestraCatalog = findViewById(R.id.ListadoProductos);
        MiListAdapter adapter = new MiListAdapter(this, articulos);
        muestraCatalog.setAdapter(adapter);

        registerForContextMenu(muestraCatalog);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_view_context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        boolean existe = false;

        if(item.getItemId() == R.id.addCarrito){
            contadorCarrito.setText(""+db.actualizaCarrito(usr.getUser(), ((Articulo)muestraCatalog.getAdapter().getItem(info.position)).getCodigo()));

        }

        return super.onContextItemSelected(item);
    }
}