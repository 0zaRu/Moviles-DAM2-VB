package com.example.proyectofinal_alberto_rodriguezperez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.controller.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.LibroController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private final LibroController lc = new LibroController();
    private final JugadorController jugadorCont = new JugadorController();
    Button logIn;
    EditText nombre, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        lc.createLibros(new Libro(0, "X", "T"));
        lc.getLibros();
        lc.getLibro(5);
         */
        nombre = findViewById(R.id.tfNombreUser);
        passwd = findViewById(R.id.tfpasswd);
        logIn = findViewById(R.id.btnLogIn);
        logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Coger nombre y contraseña de usuario
        if(!nombre.getText().toString().isEmpty() && !passwd.getText().toString().isEmpty()){
            //Enviar hacia el php
            jugadorCont.getJugador(nombre.getText().toString(), passwd.getText().toString());

        }else{
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}