package com.example.proyectofinal_alberto_rodriguezperez.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.BuscarTopMenuFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.InicioTopMenuFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.PartidasFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.PerfilFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.TodoFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.TorneosFragment;

public class DataActivity extends AppCompatActivity implements View.OnClickListener, OnMyEvent {

    Button btBuscar, btInicio;
    static String modoActual = "inicio";
    Jugador jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        assert getIntent() != null;
        jugador = (Jugador) getIntent().getSerializableExtra("jugador");

        ponerTopMenu();


        btBuscar = findViewById(R.id.menuButBuscar);
        btBuscar.setOnClickListener(this);

        btInicio = findViewById(R.id.menuButInicio);
        btInicio.setOnClickListener(this);

    }

    private void ponerTopMenu(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;

        if(modoActual.equals("inicio"))
        {
            fragmentACalocar = new InicioTopMenuFragment();

            //Ponemos fragment principial tod-o por defecto
            View vistaFalsa = new View(this);
            vistaFalsa.setId(R.id.InicioButTodo);
            botoneraInicio(vistaFalsa);

        }
        else if(modoActual.equals("buscar"))
        {
            fragmentACalocar = new BuscarTopMenuFragment();
            //Deberia poner fragment de selección de filtrado de partida/torneo/usuario/texto
        }


        if(fragmentACalocar != null) {
            Bundle args = new Bundle();
            args.putSerializable("usuario", jugador);
            fragmentACalocar.setArguments(args);

            transaction.replace(R.id.topFrame, fragmentACalocar);
        }

        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        //botón Inicio
        if(v.getId() == R.id.menuButInicio)
            modoActual = "inicio";

        //Botón Búsqueda
        else if(v.getId() == R.id.menuButBuscar)
            modoActual = "buscar";

        ponerTopMenu();

    }

    @Override
    public void botoneraInicio(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;

        if(v.getId() == R.id.InicioButTodo)
            fragmentACalocar = new TodoFragment();

        else if(v.getId() == R.id.InicioButPartidas)
            fragmentACalocar = new PartidasFragment();

        else if(v.getId() == R.id.InicioButTorneos)
            fragmentACalocar = new TorneosFragment();

        else if(v.getId() == R.id.InicioButPerfil)
            fragmentACalocar = new PerfilFragment();



        if(fragmentACalocar != null){
            Bundle args = new Bundle();
            args.putSerializable("usuario", jugador);
            fragmentACalocar.setArguments(args);

            transaction.replace(R.id.principalFrame, fragmentACalocar);
        }

        transaction.commit();
    }

    @Override
    public void botoneraBuscar(View v, String txtBuscar) {

        if(txtBuscar.isEmpty()){
            Toast.makeText(this, "Texto a buscar no establecido", Toast.LENGTH_SHORT).show();
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;

        //Poner un fragment que tengo que hacer que tenga lista de partidas por un lado, de torneos por otro?
        //Tal vez mejor un fragment con 4 opciones a filtrar
    }
}