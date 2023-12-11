package com.example.practica16_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.practica16_alberto_rodriguez.Fragments.OnFragmentEventListener;
import com.example.practica16_alberto_rodriguez.Coche.Coche;

public class MainActivity extends AppCompatActivity implements OnFragmentEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void estableceCoche(Coche c) {
        //Este se encarga de poner el coche en el fragment details
    }
}