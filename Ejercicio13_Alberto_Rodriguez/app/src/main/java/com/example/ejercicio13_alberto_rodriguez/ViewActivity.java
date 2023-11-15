package com.example.ejercicio13_alberto_rodriguez;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        listadoAlumnos = extraerDB();

        MiListAdapter adaptador = new MiListAdapter(this, listadoAlumnos);
        verAlumnos.setAdapter(adaptador);
        }


    private ArrayList<Alumno> extraerDB() {
        Cursor alumnos = db.select();
        ArrayList<Alumno> recogidos = new ArrayList<>();

        while(alumnos.moveToNext()){
            recogidos.add(new Alumno(
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.DNI)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.NOMBRE)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.APELLIDOS)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.EDAD))
            ));
        }

        MiListAdapter adaptador = new MiListAdapter(this, recogidos);
        verAlumnos.setAdapter(adaptador);

        return recogidos;
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
        listadoAlumnos = extraerDB();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listadoAlumnos = extraerDB();
    }
}