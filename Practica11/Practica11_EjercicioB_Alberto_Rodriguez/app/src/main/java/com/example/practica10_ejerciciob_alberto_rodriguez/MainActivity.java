package com.example.practica10_ejerciciob_alberto_rodriguez;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView muestraInfo;
    static ArrayList<Persona> personas = new ArrayList<>();
    MiAdapter miAdaptador;
    Context context;
    int posicionModificada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        instaciaPersonasIniciales();

        muestraInfo = findViewById(R.id.muestraInfo);

        miAdaptador =new MiAdapter(this, personas);
        muestraInfo.setAdapter(miAdaptador);

        findViewById(R.id.addUser).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            intentResult.launch(intent);
        });

        findViewById(R.id.viewUsers).setOnClickListener(v -> {
            if(muestraInfo.getVisibility() == View.INVISIBLE)
                muestraInfo.setVisibility(View.VISIBLE);
            else
                muestraInfo.setVisibility(View.INVISIBLE);
        });

        registerForContextMenu(muestraInfo);
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && posicionModificada == -1){
                    assert result.getData() != null;
                    personas.add((Persona) result.getData().getSerializableExtra("persona"));

                    miAdaptador =new MiAdapter(context, personas);
                    muestraInfo.setAdapter(miAdaptador);
                    muestraInfo.setVisibility(View.VISIBLE);

                }else if(result.getResultCode() == RESULT_OK && posicionModificada != -1){
                    assert result.getData() != null;
                    personas.set(posicionModificada, (Persona) result.getData().getSerializableExtra("persona"));

                    miAdaptador =new MiAdapter(context, personas);
                    muestraInfo.setAdapter(miAdaptador);
                    muestraInfo.setVisibility(View.VISIBLE);

                    posicionModificada = -1;
                }
            }
        }
    );

    public static void instaciaPersonasIniciales(){
        personas = new ArrayList<>();
        personas.add(new Persona("Alberto", "Rodríguez", "722633290", "70906234V", "Salamanca", "20"));
        personas.add(new Persona("Victoria", "Rodríguez", "876543210", "70906432V", "Salamanca", "18"));
        personas.add(new Persona("David", "Carro", "123456789", "70832956F", "Cáceres", "30"));
        personas.add(new Persona("Maria", "De la Luz Divina", "654987312", "65478932N", "Zamora", "25"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent intent = new Intent(this, MainActivity2.class);
            intentResult.launch(intent);

        }else if(item.getItemId() == R.id.restablecer){
            personas = new ArrayList<>();
            instaciaPersonasIniciales();

            miAdaptador =new MiAdapter(this, personas);
            muestraInfo.setAdapter(miAdaptador);

            Toast.makeText(this, "Lista restablecida", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.Detalles){
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("persona", personas.get(info.position));

            startActivity(intent);

        }else if(item.getItemId() == R.id.Eliminar){
            personas.remove(personas.get(info.position));

            miAdaptador =new MiAdapter(this, personas);
            muestraInfo.setAdapter(miAdaptador);

        }else if(item.getItemId() == R.id.Modificar) {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("persona", personas.get(info.position));
            posicionModificada = info.position;

            intentResult.launch(intent);
        }

        return super.onContextItemSelected(item);
    }
}