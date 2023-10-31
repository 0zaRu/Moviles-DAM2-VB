package com.example.zapateria_alberto_rodriguezperez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView tipo, descripcion, numero, codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tipo = findViewById(R.id.tipo);
        descripcion = findViewById(R.id.descripcion);
        numero = findViewById(R.id.numero);
        codigo = findViewById(R.id.codigo);

        Calzado recibido = (Calzado) getIntent().getSerializableExtra("calzado");

        assert recibido != null;
        tipo.setText(recibido.getTipo());
        descripcion.setText(recibido.getDescripción());
        numero.setText(Integer.toString(recibido.getNumero()));
        codigo.setText(recibido.getCodigo());
    }
}