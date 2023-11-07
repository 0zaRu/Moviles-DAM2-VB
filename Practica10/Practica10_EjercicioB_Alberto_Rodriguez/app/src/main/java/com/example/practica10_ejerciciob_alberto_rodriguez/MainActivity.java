package com.example.practica10_ejerciciob_alberto_rodriguez;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView muestraInfo;
    ArrayList<Persona> personas = new ArrayList<>();
    MiAdapter miAdaptador;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        muestraInfo = findViewById(R.id.muestraInfo);
        muestraInfo.setVisibility(View.INVISIBLE);

        miAdaptador =new MiAdapter(this, personas);
        muestraInfo.setAdapter(miAdaptador);

        findViewById(R.id.addUser).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);

            intentResult.launch(intent);
        });

        findViewById(R.id.viewUsers).setOnClickListener(v -> {
            if(muestraInfo.getVisibility() == View.INVISIBLE)
                muestraInfo.setVisibility(View.VISIBLE);
            else
                muestraInfo.setVisibility(View.INVISIBLE);
        });
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    assert result.getData() != null;
                    personas.add((Persona) result.getData().getSerializableExtra("persona"));

                    miAdaptador =new MiAdapter(context, personas);
                    muestraInfo.setAdapter(miAdaptador);
                    muestraInfo.setVisibility(View.VISIBLE);
                }
            }
        }
    );
}