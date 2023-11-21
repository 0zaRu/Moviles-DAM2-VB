package com.example.practica13_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class FindActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar edadMin, edadMax;
    TextView tvEMin, tvEMax;
    RadioButton empiezaPor, contiene, buscar;
    RadioGroup grupoFiltro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        tvEMax = findViewById(R.id.tvEdadMax);
        tvEMin = findViewById(R.id.tvEdadMin);
        empiezaPor = findViewById(R.id.rbEmpiezaPor);
        contiene = findViewById(R.id.rbContiene);
        buscar = findViewById(R.id.rbBuscar);
        grupoFiltro = findViewById(R.id.grupoFiltro);

        edadMin = findViewById(R.id.sbEMin);
        edadMin.setOnSeekBarChangeListener(this);

        edadMax = findViewById(R.id.sbEMax);
        edadMax.setOnSeekBarChangeListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getId() == R.id.sbEMax){
            tvEMax.setText("Edad máxima: "+progress);

        }else if(seekBar.getId() == R.id.sbEMin){
            tvEMin.setText("Edad mínima: "+progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}