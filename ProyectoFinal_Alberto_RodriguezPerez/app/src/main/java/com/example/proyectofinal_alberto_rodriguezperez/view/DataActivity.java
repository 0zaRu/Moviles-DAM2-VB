package com.example.proyectofinal_alberto_rodriguezperez.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    Button btMiInfo, btPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        btMiInfo = findViewById(R.id.fragmentBut1);
        btMiInfo.setOnClickListener(this);

        btPerfil = findViewById(R.id.fragmentBut2);
        btPerfil.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;

        //Botón Datos Jugador
        if(v.getId() == R.id.fragmentBut1)
        {
            fragmentACalocar = new PartidasFragment();
            Bundle args = new Bundle();
            args.putSerializable("usuario", ((Jugador) getIntent().getSerializableExtra("jugador")));
            fragmentACalocar.setArguments(args);

        }
        //Botón Perfil Jugador
        else if(v.getId() == R.id.fragmentBut2)
        {
            fragmentACalocar = new PerfilFragment();
            Bundle args = new Bundle();
            args.putSerializable("usuario", ((Jugador) getIntent().getSerializableExtra("jugador")));
            fragmentACalocar.setArguments(args);
        }


        if(fragmentACalocar != null)
            transaction.add(R.id.principalFrame, fragmentACalocar);

        transaction.commit();
    }
}