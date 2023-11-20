package com.example.practicaev_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText titulo, autor, paginas;
    Spinner categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        categoria = findViewById(R.id.sp_categoria);
        ArrayAdapter<CharSequence> adaptaSpinner = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        categoria.setAdapter(adaptaSpinner);

        titulo = findViewById(R.id.et_titulo);
        autor = findViewById(R.id.et_autor);
        paginas = findViewById(R.id.et_paginas);


        findViewById(R.id.guardar).setOnClickListener(v -> {

            if(categoria.getSelectedItemId() == 0){
                Toast.makeText(this, "No se permite la categoría TODOS", Toast.LENGTH_SHORT).show();
                return;
            }
            if(titulo.getText().toString().isEmpty() ||
                autor.getText().toString().isEmpty() ||
                paginas.getText().toString().isEmpty()){
                Toast.makeText(this, "Alguno de los campos está vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent();

            intent.putExtra("libro", new Libro(titulo.getText().toString(), autor.getText().toString(), categoria.getAdapter().getItem((int) categoria.getSelectedItemId()).toString(), Integer.parseInt(paginas.getText().toString())));
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}