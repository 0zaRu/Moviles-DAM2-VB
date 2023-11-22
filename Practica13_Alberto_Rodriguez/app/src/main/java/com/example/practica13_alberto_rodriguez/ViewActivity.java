package com.example.practica13_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView verAlumnos;
    SQLHelper db = new SQLHelper(this);
    ArrayList<Alumno> listadoAlumnos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        verAlumnos = findViewById(R.id.vistaAlumnos);
        registerForContextMenu(verAlumnos);
        listadoAlumnos = new ArrayList<>();

        findViewById(R.id.bVonverMostrar).setOnClickListener(v -> {finish();});

        if(getIntent().getExtras() == null) {
            listadoAlumnos = db.extraerDB(null, null, null, null, null, null);

        }else if(((ArrayList<Alumno>) getIntent().getSerializableExtra("recibido")).isEmpty()) {
            listadoAlumnos = db.extraerDB(null, null, null, null, null, null);
            Toast.makeText(this, "Se ha cargado toda la base de datos porque no se encotraron coincidencias", Toast.LENGTH_SHORT).show();

        }else
            listadoAlumnos = (ArrayList<Alumno>) getIntent().getSerializableExtra("recibido");


        MiListAdapter adaptador = new MiListAdapter(this, listadoAlumnos);
        verAlumnos.setAdapter(adaptador);
        }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.listview_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.eliminarMenu){
            db.deleteAlumno((Alumno) verAlumnos.getAdapter().getItem(info.position));

        }else if(item.getItemId() == R.id.modificarMenu){
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("alumno", (Alumno) verAlumnos.getAdapter().getItem(info.position));

            startActivity(intent);
        }
        onResume();

        return super.onContextItemSelected(item);
    }
}