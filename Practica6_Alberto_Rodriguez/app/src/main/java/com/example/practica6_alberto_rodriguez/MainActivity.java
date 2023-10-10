package com.example.practica6_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, apellidos, nacimiento, direccion, telefono;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        error = findViewById(R.id.tError);
        nombre = findViewById(R.id.tNombre);
        apellidos = findViewById(R.id.tApellidos);
        nacimiento = findViewById(R.id.tNacimiento);
        direccion = findViewById(R.id.tDireccion);
        telefono = findViewById(R.id.tTelefono);

        findViewById(R.id.bAceptar).setOnClickListener(this);

        findViewById(R.id.bLimpiar).setOnClickListener(v -> {
            nombre.setText("");
            apellidos.setText("");
            nacimiento.setText("");
            direccion.setText("");
            telefono.setText("");
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        boolean valido = true;
        error.setText("");

        if(String.valueOf(nombre.getText()).length() == 0){
            Toast.makeText(this, "Campo nombre vacío", Toast.LENGTH_SHORT).show();
            error.append("Nombre, ");
            valido = false;
        }
        if(String.valueOf(apellidos.getText()).length() == 0){
            Toast.makeText(this, "Campo apellidos vacío", Toast.LENGTH_SHORT).show();
            error.append("Apellidos, ");
            valido = false;
        }
        if(String.valueOf(nacimiento.getText()).length() == 0){
            Toast.makeText(this, "Campo fecha vacío", Toast.LENGTH_SHORT).show();
            error.append("Fecha, ");
            valido = false;
        }
        if(String.valueOf(direccion.getText()).length() == 0){
            Toast.makeText(this, "Campo dirección vacío", Toast.LENGTH_SHORT).show();
            error.append("Dirección, ");
            valido = false;
        }
        try {
            if (String.valueOf(telefono.getText()).length() != 9 && (telefono.getText().charAt(0) == 6 || telefono.getText().charAt(0) == 7)) {
                Toast.makeText(this, "Campo telefono vacío", Toast.LENGTH_SHORT).show();
                error.append("Telefono, ");
                valido = false;
            }
        }catch (Exception e){
            Toast.makeText(this, "Campo telefono vacío", Toast.LENGTH_SHORT).show();
            error.append("Telefono, ");
            valido = false;
        }

        if(!valido){
            error.append("\n|| Campo/s vacío/s o no válidos, revise los datos.");
        }
        else {
            error.setText("");

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            try {
                formatoFecha.parse(String.valueOf(nacimiento.getText()));

            } catch (Exception e) {
                Toast.makeText(this, "Campo fecha. Formato erroneo", Toast.LENGTH_SHORT).show();
                error.setText("La fecha introducida no es válida.");
            }
        }


        if(error.getText().length() == 0){
            Bundle bolsa = new Bundle();
            bolsa.putString("nombre", String.valueOf(nombre.getText()));
            bolsa.putString("apellidos", String.valueOf(apellidos.getText()));
            bolsa.putString("nacimiento", String.valueOf(nacimiento.getText()));
            bolsa.putString("direccion", String.valueOf(direccion.getText()));
            bolsa.putString("telefono", String.valueOf(telefono.getText()));

            Intent intent = new Intent(this, ResumenActivity.class);
            intent.putExtra("bolsa", bolsa);

            startActivity(intent);
            onPause();
        }
    }
}