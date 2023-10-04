package com.example.practica5_ejercicio2_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tMostrado;
    EditText tEditado;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tMostrado = findViewById(R.id.tView);
        tMostrado.setTextColor(Color.RED);
        tMostrado.setText("El DNI introducido no es válido");

        tEditado = findViewById(R.id.tEdit);
        tEditado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String texto = String.valueOf(tEditado.getText()).toUpperCase();
                String letraDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

                try {
                    if (texto.length() != 9 || Integer.parseInt(texto.substring(0, texto.length() - 1)) % 23 != letraDNI.indexOf(texto.charAt(texto.length() - 1)))
                        tMostrado.setText("El DNI introducido no es válido");

                    else
                        tMostrado.setText(" ");

                }catch (Exception e){
                    //
                }
            }
        });
    }
}
