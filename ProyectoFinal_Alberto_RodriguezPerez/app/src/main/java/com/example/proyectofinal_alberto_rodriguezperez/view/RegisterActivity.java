package com.example.proyectofinal_alberto_rodriguezperez.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

import java.time.Instant;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    TextView nombre, pais, fechaNaci, correo, pass, passRepe;
    Button registrar;
    private final JugadorController jugadorCont = new JugadorController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.etNombre);
        pais = findViewById(R.id.etPais);
        fechaNaci = findViewById(R.id.etFechaNaci);
        correo = findViewById(R.id.etCorreo);
        pass = findViewById(R.id.etPasswd);
        passRepe = findViewById(R.id.etRepePasswd);

        registrar = findViewById(R.id.btSignIn);
        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (nombre.getText().toString().isEmpty() ||
                pais.getText().toString().isEmpty() ||
                fechaNaci.getText().toString().isEmpty() ||
                correo.getText().toString().isEmpty() ||
                pass.getText().toString().isEmpty() ||
                passRepe.getText().toString().isEmpty())
            Toast.makeText(this, "Valores no insertados", Toast.LENGTH_SHORT).show();

        else if (!pass.getText().toString().equals(passRepe.getText().toString())) {
            Toast.makeText(this, "Error en la contraseña, no son iguales", Toast.LENGTH_SHORT).show();
            pass.setText("");
            passRepe.setText("");

        } else {
            Jugador add = new Jugador(nombre.getText().toString(),
                    pais.getText().toString(),
                    1000,
                    "2003-04-01",
                    correo.getText().toString(),
                    pass.getText().toString(),
                    0);

            jugadorCont.introduceJugador(this, add);

        }
    }
}