package com.example.ejemplo_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.boton).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLOVIDA", "onStart actividad 1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CICLOVIDA", "onRestart actividad 1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLOVIDA", "onResume actividad 1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLOVIDA", "onPause actividad 1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLOVIDA", "onStop actividad 1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLOVIDA", "onDestroy actividad 1");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.boton){
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("nombre", "Alberto");

            Bundle bolsa = new Bundle();
            bolsa.putString("bNombre", "Alberto");

            intent.putExtra("bolsa", bolsa);

            startActivity(intent);
            
            finish();
        }
    }
}