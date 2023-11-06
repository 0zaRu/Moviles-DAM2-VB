package com.example.practica_ejercicio1_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private static final int CODE = 123;
    EditText nUsuario;
    Button bAceptar;
    TextView mostrar;
    String nombre;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostrar = findViewById(R.id.textView3);
        nUsuario = findViewById(R.id.editText);
        nUsuario.addTextChangedListener(this);

        bAceptar = findViewById(R.id.button);
        bAceptar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Subscrib_Activity.class);
        intent.putExtra("usuario", String.valueOf(nUsuario.getText()));

        startActivityForResult(intent, CODE);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(nUsuario.getText().toString().isEmpty()){
            bAceptar.setActivated(false);

        }else
            bAceptar.setActivated(true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE && resultCode == RESULT_OK){
            mostrar.setText(String.format(getString(R.string.resultado), nombre, "aceptado"));

        }else if (requestCode == CODE && resultCode == RESULT_CANCELED){
            mostrar.setText(String.format(getString(R.string.resultado), nombre, "cancelado"));
        }
        nUsuario.setText("");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        nombre = nUsuario.getText().toString();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}