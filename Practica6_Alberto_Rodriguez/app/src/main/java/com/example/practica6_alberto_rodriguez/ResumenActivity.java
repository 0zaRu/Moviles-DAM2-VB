package com.example.practica6_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResumenActivity extends AppCompatActivity {

    TextView muestra;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        findViewById(R.id.button).setOnClickListener(v ->{
            finish();
        });


        muestra = findViewById(R.id.tMuestra);
        Bundle dRecogidos = getIntent().getBundleExtra("bolsa");

        if (dRecogidos != null) {
            muestra.setText("\n\nNombre: "+ dRecogidos.getString("nombre")+
                            "\n\nApellidos: "+ dRecogidos.getString("apellidos")+
                            "\n\nNacimiento: "+ dRecogidos.getString("nacimiento")+
                            "\n\nDirección: "+ dRecogidos.getString("direccion")+
                            "\n\nTeléfono: "+ dRecogidos.getString("telefono")+"\n");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Atrás", Toast.LENGTH_SHORT).show();
    }
}