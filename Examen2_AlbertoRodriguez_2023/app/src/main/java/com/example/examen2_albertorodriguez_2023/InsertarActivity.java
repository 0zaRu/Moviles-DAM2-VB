package com.example.examen2_albertorodriguez_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InsertarActivity extends AppCompatActivity {

    EditText codigo, nombre, peso;
    RadioGroup grupoTipo;
    RadioButton gato, perro, pajaro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        codigo = findViewById(R.id.codigo);
        nombre = findViewById(R.id.nombre);
        peso = findViewById(R.id.peso);

        grupoTipo = findViewById(R.id.tipo);
        gato = findViewById(R.id.gato);
        perro = findViewById(R.id.perro);
        pajaro = findViewById(R.id.pajaro);

        findViewById(R.id.insertar).setOnClickListener(v -> {
            if(codigo.getText().toString().isEmpty() ||
               nombre.getText().toString().isEmpty() ||
               peso.getText().toString().isEmpty()){
                Toast.makeText(this, "Algun campo está vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!gato.isChecked() && !perro.isChecked() && !pajaro.isChecked()){
                Toast.makeText(this, "No has seleccionado tipo", Toast.LENGTH_SHORT).show();
                return;
            }
            String tipoAnimal = null;

            if(gato.isChecked()) tipoAnimal = "gato";
            else if (perro.isChecked()) tipoAnimal = "perro";
            else if (pajaro.isChecked()) tipoAnimal = "pajaro";

            Animal a = new Animal(Integer.parseInt(codigo.getText().toString()), nombre.getText().toString(), Double.parseDouble(peso.getText().toString()), tipoAnimal);

            SQLHelper db = new SQLHelper(this);
            if(db.insertar(a) == -1)
                Toast.makeText(this, "Error haciendo la inerscción", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Ha sido dado de alta", Toast.LENGTH_SHORT).show();

            finish();
        });

    }
}