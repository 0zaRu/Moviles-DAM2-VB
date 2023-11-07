package com.example.zapateria_alberto_rodriguezperez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] tipoCalzado = new String[]{"Selecciona un calzado ...", "Bota", "Zapato", "Sandalia", "Zapatilla"};
    Spinner spTipos;
    ArrayAdapter<CharSequence> tiposAdapter;
    String tipo;
    EditText etDescripcion, etNumero, etCodigo;
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spTipos = findViewById(R.id.spTipos);

        tiposAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoCalzado);
        spTipos.setAdapter(tiposAdapter);
        spTipos.setOnItemSelectedListener(this);

        etDescripcion = findViewById(R.id.etDesc);
        etNumero = findViewById(R.id.etNumero);
        etCodigo = findViewById(R.id.etCodigo);

        aceptar = findViewById(R.id.aceptar);
        aceptar.setOnClickListener(v -> {
            boolean valido = true;

            if(tipo.isEmpty()){
               Toast.makeText(this, "Tipo de calzado no seleccionado", Toast.LENGTH_SHORT).show();
               valido = false;
            }
            if(etDescripcion.getText().toString().isEmpty()){
               Toast.makeText(this, "Descripción no escrita", Toast.LENGTH_SHORT).show();
                valido = false;
            }

            if(etNumero.getText().toString().isEmpty()) {
                Toast.makeText(this, "Numero de pie no introducido", Toast.LENGTH_SHORT).show();
                valido = false;
            }

            if(etCodigo.getText().toString().isEmpty()) {
               Toast.makeText(this, "Codigo no escrito", Toast.LENGTH_SHORT).show();
                valido = false;
            }

            if(!valido)
                return;

            Intent intent = new Intent();
            intent.putExtra("calzado", new Calzado(tipo, etDescripcion.getText().toString(), Integer.parseInt(etNumero.getText().toString()), etCodigo.getText().toString()));

            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getAdapter().getItem(position).equals("Selecciona un calzado ...")){

        }else
            tipo = parent.getAdapter().getItem(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}