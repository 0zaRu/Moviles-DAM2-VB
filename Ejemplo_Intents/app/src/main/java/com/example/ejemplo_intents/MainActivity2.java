package com.example.ejemplo_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.button).setOnClickListener(this);
        TextView texto = findViewById(R.id.texto);
        texto.setText("Por putExtra: "+ getIntent().getStringExtra("nombre")+
                      "\nPor bundle: "+(getIntent().getBundleExtra("bolsa")).getString("bNombre"));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}