package com.example.practica13_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar edadMin, edadMax;
    TextView tvEMin, tvEMax, textoComparar;
    RadioButton empiezaPor, contiene, buscar;
    RadioGroup grupoFiltro;

    SQLHelper db = new SQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        textoComparar = findViewById(R.id.etComparaNombre);
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

        findViewById(R.id.bParaBuscar).setOnClickListener(v -> {
            if(!empiezaPor.isChecked() && !contiene.isChecked() && !buscar.isChecked()){
                Toast.makeText(this, "Selecciona estilo de filtrado", Toast.LENGTH_SHORT).show();
                return;
            }
            if(textoComparar.getText().toString().isEmpty()){
                Toast.makeText(this, "No has especificado texto de búsqueda", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<Alumno> comparado = new ArrayList<>();

            if(empiezaPor.isChecked()){
                comparado.addAll( db.extraerDB(null,
                        AlumnosContract.NOMBRE + " LIKE ? AND "+
                                AlumnosContract.EDAD+" > ? AND "+
                                AlumnosContract.EDAD+" < ?",

                        new String[]{textoComparar.getText().toString()+"%",
                                     String.valueOf(edadMin.getProgress()),
                                     String.valueOf(edadMax.getProgress())},
                        null,
                        null,
                        null));

            }else if (contiene.isChecked())
                comparado.addAll( db.extraerDB(null,
                        AlumnosContract.NOMBRE + " LIKE ? AND "+
                                AlumnosContract.EDAD+" > ? AND "+
                                AlumnosContract.EDAD+" < ?",

                        new String[]{"%"+textoComparar.getText().toString()+"%",
                                String.valueOf(edadMin.getProgress()),
                                String.valueOf(edadMax.getProgress())},
                        null,
                        null,
                        null));

            else if (buscar.isChecked())
                comparado.addAll( db.extraerDB(null,
                        AlumnosContract.NOMBRE + " LIKE ? AND "+
                                AlumnosContract.EDAD+" > ? AND "+
                                AlumnosContract.EDAD+" < ?",

                        new String[]{textoComparar.getText().toString(),
                                String.valueOf(edadMin.getProgress()),
                                String.valueOf(edadMax.getProgress())},
                        null,
                        null,
                        null));

            else {
                Toast.makeText(this, "No se especificó tipo de búsqueda", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra("recibido", comparado);

            startActivity(intent);

        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getId() == R.id.sbEMax){
            if(progress < edadMin.getProgress())
                edadMax.setProgress(edadMin.getProgress());

            tvEMax.setText("Edad máxima: "+progress);

        }else if(seekBar.getId() == R.id.sbEMin){
            if(progress > edadMax.getProgress())
                edadMin.setProgress(edadMax.getProgress());

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