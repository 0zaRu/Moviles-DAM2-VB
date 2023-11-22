package com.example.examen2_albertorodriguez_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioButton gato, perro, pajaro;
    RadioGroup grupo;
    Button buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        gato = findViewById(R.id.gatoB);
        perro = findViewById(R.id.perroB);
        pajaro = findViewById(R.id.pajaroB);
        grupo = findViewById(R.id.tipoB);
        grupo.setOnCheckedChangeListener(this);

        buscar = findViewById(R.id.buscarB);
        buscar.setEnabled(false);

        buscar.setOnClickListener(v -> {
            String tipo = null;

            if(gato.isChecked()) tipo = "gato";
            else if (perro.isChecked()) tipo = "perro";
            else if (pajaro.isChecked()) tipo = "pajaro";

            SQLHelper db = new SQLHelper(this);
            ArrayList<Animal> leido = db.selecciona(null,
                    MascotaContract.TIPO+" LIKE ?",
                    new String[]{tipo},
                    null,
                    null,
                    null);

            Intent intent = new Intent(this, MostrarActivity.class);
            intent.putExtra("animales", leido);
            intent.putExtra("filtro", tipo);

            startActivity(intent);
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(gato.isChecked() || perro.isChecked() || pajaro.isChecked()){
            buscar.setEnabled(true);
        }
    }
}