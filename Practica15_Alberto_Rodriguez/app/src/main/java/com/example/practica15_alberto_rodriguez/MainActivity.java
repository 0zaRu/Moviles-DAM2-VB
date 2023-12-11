package com.example.practica15_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.practica15_alberto_rodriguez.Fragments.MiFragment;
import com.example.practica15_alberto_rodriguez.Fragments.OnFragmentEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnFragmentEventListener {

    Spinner spinerAl;
    TextView tDelegado;
    static int EJEMPLO = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Alberto", "Rodríguez", "70906234V", R.drawable.persona));
        alumnos.add(new Alumno("Antonio", "Hernández", "45683653F", R.drawable.ic_launcher_foreground));

        spinerAl = findViewById(R.id.spinner);
        Button bDatos = findViewById(R.id.bDatos);
        Button bFoto = findViewById(R.id.bFoto);
        tDelegado = findViewById(R.id.textoDelegado);

        ArrayAdapter spinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, alumnos);
        spinerAl.setAdapter(spinner);
        
        bDatos.setOnClickListener(this);
        bFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MiFragment fragmento = null;

        if(v.getId() == R.id.bDatos){
            fragmento = new MiFragment((Alumno)spinerAl.getAdapter().getItem(spinerAl.getSelectedItemPosition()), true);
        }else{
            fragmento = new MiFragment((Alumno)spinerAl.getAdapter().getItem(spinerAl.getSelectedItemPosition()), false);
        }

        transaction.replace(R.id.frameLayout, fragmento);

        transaction.commit();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void nombreDelegado(Alumno alumno) {
        tDelegado.setText("DELEGADO: "+alumno.getNombre()+" "+alumno.getApellidos());
    }
}