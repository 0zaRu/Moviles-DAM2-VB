package com.example.ejercicio13_alberto_rodriguez;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText nombre, apellidos, dni, edad;
    SQLHelper db = new SQLHelper(this);
    Button formulario;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Alumno recibido = (Alumno) getIntent().getSerializableExtra("alumno");

        nombre = findViewById(R.id.tNombre);
        apellidos = findViewById(R.id.tApellidos);
        dni = findViewById(R.id.tDni);
        dni.setEnabled(false);
        edad = findViewById(R.id.tEdad);

        assert recibido != null;
        nombre.setText(recibido.getNombre());
        apellidos.setText(recibido.getApellidos());
        dni.setText(recibido.getDni());
        edad.setText(recibido.getEdad());

        formulario = findViewById(R.id.bFormulario);
        formulario.setText(R.string.modifica);

        formulario.setOnClickListener(v -> {
            if(nombre.getText().toString().isEmpty() ||
                apellidos.getText().toString().isEmpty() ||
                edad.getText().toString().isEmpty()){
                Toast.makeText(this, "Alguno de los campos está en blanco", Toast.LENGTH_SHORT).show();
                return;
            }

            recibido.setNombre(nombre.getText().toString());
            recibido.setApellidos(apellidos.getText().toString());
            recibido.setEdad(edad.getText().toString());

            db.updateAlumno(recibido);
            finish();
        });
    }
}