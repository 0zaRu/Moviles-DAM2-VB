package com.example.proyectofinal_alberto_rodriguezperez.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.Security;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs.RegisterOrEditDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final JugadorController jugadorCont = new JugadorController();
    static Jugador user = null;
    Button logIn;
    EditText nombre, passwd;
    TextView tvRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.tfNombreUser);
        passwd = findViewById(R.id.tfpasswd);

        logIn = findViewById(R.id.btnLogIn);
        logIn.setOnClickListener(this);

        tvRegistro = findViewById(R.id.tvRegistro);
        tvRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogIn)
        {
            //Coger nombre y contraseña de usuario
            if (!nombre.getText().toString().isEmpty() && !passwd.getText().toString().isEmpty()) {
                //Enviar hacia el php
                jugadorCont.getJugador(this, nombre.getText().toString(), Security.getMD5(passwd.getText().toString()));
                //Se recogerá mediante el método de la interfaz ComunicaciónPruebas

            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }

        }
        else if(v.getId() == R.id.tvRegistro)
        {

            RegisterOrEditDialog dialog =new RegisterOrEditDialog(null);
            dialog.show(getSupportFragmentManager(), "llamadaRegistrar");
            //Aquí puedo hacer que sea una clase de ida con vuelta de datos, para asignar directamente a los campos el usuario
            //que haya agregado al registrarse
        }
        else
            Toast.makeText(this, "??????", Toast.LENGTH_SHORT).show();
    }
}