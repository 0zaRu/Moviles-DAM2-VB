package com.example.practica6_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, apellidos, nacimiento, direccion, telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.tNombre);
        apellidos = findViewById(R.id.tApellidos);
        nacimiento = findViewById(R.id.tNacimiento);
        direccion = findViewById(R.id.tDireccion);
        telefono = findViewById(R.id.tTelefono);

        findViewById(R.id.bAceptar).setOnClickListener(this);

        findViewById(R.id.bLimpiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                apellidos.setText("");
                nacimiento.setText("");
                direccion.setText("");
                telefono.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(nombre.getText().length() != 0 && apellidos.getText().length() != 0 && nacimiento.getText().length() != 0
           && direccion.getText().length() != 0 && telefono.getText().length() != 0){

        }
    }
}