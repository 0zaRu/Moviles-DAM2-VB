package com.example.examen2_albertorodriguez_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<Animal> animales = new ArrayList<>();
    SQLHelper db = new SQLHelper(this);
    String filtro = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        if(getIntent().getExtras() != null){
            animales = (ArrayList<Animal>)getIntent().getSerializableExtra("animales");
            filtro = getIntent().getStringExtra("filtro");
        }

        else {
            animales = db.selecciona(null, null, null, null, null, null);
        }

        if(animales == null){
            Toast.makeText(this, "No se han encontrado animales", Toast.LENGTH_SHORT).show();
            finish();
        }

        lista = findViewById(R.id.lista);
        recargaLista();

        registerForContextMenu(lista);
    }

    public void recargaLista(){
        ArrayList<Animal> leido = new ArrayList<>();
        if(filtro.isEmpty())
            leido = db.selecciona(null, null, null, null, null, null);

        else{
            SQLHelper db = new SQLHelper(this);
            leido = db.selecciona(null,
                    MascotaContract.TIPO+" LIKE ?",
                    new String[]{filtro},
                    null,
                    null,
                    null);
        }

        MiListAdapter adaptaLista = new MiListAdapter(this, leido);
        lista.setAdapter(adaptaLista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.detalles){


        }else if(item.getItemId() == R.id.eliminar){
            db.borrar(((Animal)lista.getAdapter().getItem(info.position)).getCodigo());
            recargaLista();
        }

        return super.onContextItemSelected(item);
    }
}