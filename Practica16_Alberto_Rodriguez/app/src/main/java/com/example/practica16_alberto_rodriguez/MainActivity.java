package com.example.practica16_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.practica16_alberto_rodriguez.Dialogs.DialogModify;
import com.example.practica16_alberto_rodriguez.Dialogs.OnDialogEvent;
import com.example.practica16_alberto_rodriguez.Fragments.DetailFragment;
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
            DialogModify dialog = new DialogModify();
            dialog.show(getSupportFragmentManager(), "ejemplo");
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
    public void modificar(String t1, String t2) {
        Toast.makeText(this, "Se ha pulsado aceptar "+t1+" "+t2, Toast.LENGTH_SHORT).show();
    }
}