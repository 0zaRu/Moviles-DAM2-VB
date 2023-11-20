package com.example.practica13_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText nombre, apellidos, dni, edad;
    SQLHelper db = new SQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        nombre = findViewById(R.id.tNombre);
        apellidos = findViewById(R.id.tApellidos);
        dni = findViewById(R.id.tDni);
        edad = findViewById(R.id.tEdad);


        findViewById(R.id.bFormulario).setOnClickListener(v -> {
            if(nombre.getText().toString().isEmpty() ||
                apellidos.getText().toString().isEmpty() ||
                dni.getText().toString().isEmpty() ||
                edad.getText().toString().isEmpty()){
                Toast.makeText(this, "Alguno de los campos está en blanco", Toast.LENGTH_SHORT).show();
                return;
            }

            Alumno alumno = new Alumno(dni.getText().toString(), nombre.getText().toString(), apellidos.getText().toString(), edad.getText().toString());
            db.insertAlumno(alumno);
            finish();
        });
    }
}