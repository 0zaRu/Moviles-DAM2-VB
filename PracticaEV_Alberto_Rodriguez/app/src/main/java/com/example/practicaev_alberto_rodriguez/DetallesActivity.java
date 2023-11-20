package com.example.practicaev_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetallesActivity extends AppCompatActivity {

    TextView titulo, autor, paginas, categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        paginas = findViewById(R.id.paginas);
        categoria = findViewById(R.id.categoria);

        Libro recogido = (Libro) getIntent().getSerializableExtra("libro");

        titulo.setText(recogido.getTitulo());
        autor.setText(recogido.getAutor());
        paginas.setText(""+recogido.getPaginas());
        categoria.setText(recogido.getCategoria());
    }
}