package com.example.practica16_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.practica16_alberto_rodriguez.Dialogs.DialogAddOrModify;
import com.example.practica16_alberto_rodriguez.Dialogs.OnDialogEvent;
import com.example.practica16_alberto_rodriguez.Fragments.ListFragment;
import com.example.practica16_alberto_rodriguez.Fragments.OnFragmentEventListener;
import com.example.practica16_alberto_rodriguez.Coche.Coche;

public class MainActivity extends AppCompatActivity implements OnFragmentEventListener, OnDialogEvent {

    SQLHelper db = new SQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estableceFrameList();

        findViewById(R.id.floatAddButton).setOnClickListener(v -> {
            DialogAddOrModify dialog = new DialogAddOrModify(null);
            dialog.show(getSupportFragmentManager(), "llamadaMain");
        });
    }

    public void estableceFrameList(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ListFragment lf = new ListFragment(db.selectCoches(null));
        transaction.replace(R.id.frameList, lf);

        transaction.commit();


    }

    @Override
    public void estableceCoche(Coche c) {
        /*if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            DetailFragment df = new DetailFragment(db.selectCoches(c.getNumBastidor()).get(0));
            transaction.replace(R.id.frameDetalles, df);

            transaction.commit();
        }
         */
    }

    @Override
    public void addCoche(Coche coche) {
        db.insertaCoche(null, coche);
        estableceFrameList();
        //Hacer que si está tumbado se llame a estableceCoche
    }

    @Override
    public void modificarCoche(Coche coche) {
        db.modificarCoche(coche);
        estableceFrameList();
    }

    @Override
    public void eliminaCoche(Coche coche) {
        db.eliminaCoche(coche.getNumBastidor());
        estableceFrameList();
    }
}