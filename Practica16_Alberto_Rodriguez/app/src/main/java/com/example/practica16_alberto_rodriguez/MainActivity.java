package com.example.practica16_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.practica16_alberto_rodriguez.Dialogs.DialogModify;
import com.example.practica16_alberto_rodriguez.Dialogs.OnDialogEvent;
import com.example.practica16_alberto_rodriguez.Fragments.OnFragmentEventListener;
import com.example.practica16_alberto_rodriguez.Coche.Coche;

public class MainActivity extends AppCompatActivity implements OnFragmentEventListener, OnDialogEvent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mainButton).setOnClickListener(v -> {
            DialogModify dialog = new DialogModify();
            dialog.show(getSupportFragmentManager(), "ejemplo");
        });

    }

    @Override
    public void estableceCoche(Coche c) {
        //Este se encarga de poner el coche en el fragment details
    }

    @Override
    public void modificar(String t1, String t2) {
        Toast.makeText(this, "Se ha pulsado aceptar "+t1+" "+t2, Toast.LENGTH_SHORT).show();
    }
}