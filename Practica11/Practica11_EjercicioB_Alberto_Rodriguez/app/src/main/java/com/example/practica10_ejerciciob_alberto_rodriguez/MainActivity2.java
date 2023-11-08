package com.example.practica10_ejerciciob_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nombre, apellidos, dni, telefono, edad;
    Spinner spProvincia;
    String provincia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        dni = findViewById(R.id.dni);
        telefono = findViewById(R.id.telefono);
        edad = findViewById(R.id.edad);
        spProvincia = findViewById(R.id.provincias);

        ArrayAdapter<CharSequence> provinciasAdapter = ArrayAdapter.createFromResource(this, R.array.Provincias, R.layout.spinner_layout);
        spProvincia.setAdapter(provinciasAdapter);
        spProvincia.setOnItemSelectedListener(this);

        if(getIntent().hasExtra("persona")){
            Persona p = (Persona)getIntent().getSerializableExtra("persona");
            assert p != null;

            nombre.setText(p.getNombre());
            apellidos.setText(p.getApellidos());
            telefono.setText(p.getTelefono());
            dni.setText(p.getDni());
            edad.setText(p.getEdad());

            for(int i = 0; i < spProvincia.getCount(); i++)
                if(spProvincia.getItemAtPosition(i).toString().equals(p.getProvincia())) {
                    spProvincia.setSelection(i);
                    break;
                }
        }


        findViewById(R.id.aceptar).setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.putExtra("persona", new Persona(nombre.getText().toString(), apellidos.getText().toString(), telefono.getText().toString(), dni.getText().toString(), provincia, (edad.getText().toString())));

            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        provincia = (parent.getAdapter().getItem(position)).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}