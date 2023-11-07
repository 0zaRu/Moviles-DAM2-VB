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
    ArrayList<Persona> personas = new ArrayList<>();
    MiAdapter miAdaptador;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        muestraInfo = findViewById(R.id.muestraInfo);
        muestraInfo.setVisibility(View.INVISIBLE);

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
                if(result.getResultCode() == RESULT_OK){
                    assert result.getData() != null;
                    personas.add((Persona) result.getData().getSerializableExtra("persona"));

                    miAdaptador =new MiAdapter(context, personas);
                    muestraInfo.setAdapter(miAdaptador);
                    muestraInfo.setVisibility(View.VISIBLE);
                }
            }
        }
    );


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
            Toast.makeText(this, "Seleccionado: Configuración", Toast.LENGTH_SHORT).show();
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

        }else if(item.getItemId() == R.id.Eliminar){
            personas.remove(personas.get(info.position));

            miAdaptador =new MiAdapter(this, personas);
            muestraInfo.setAdapter(miAdaptador);

        }else if(item.getItemId() == R.id.Modificar) {


        }

        return super.onContextItemSelected(item);
    }
}