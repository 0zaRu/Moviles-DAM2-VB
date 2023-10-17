package com.example.practica8_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class ShareActivity extends AppCompatActivity implements TextWatcher {

    EditText mensaje;
    Button bEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mensaje = findViewById(R.id.tmensaje);
        mensaje.addTextChangedListener(this);

        bEnviar = findViewById(R.id.benviar);
        bEnviar.setActivated(false);
        bEnviar.setOnClickListener(v -> {

            if(!appInstalada("com.whatsapp", getApplicationContext())){
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

            Intent intent = new Intent();

            intent.setType("text/plain");
            intent.setAction(Intent.ACTION_SEND);
            intent.setPackage("com.whatsapp");
            intent.putExtra(Intent.EXTRA_TEXT, mensaje.getText().toString());

            startActivity(intent);
        });
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(mensaje.getText().toString().isEmpty())
            bEnviar.setActivated(false);

        else
            bEnviar.setActivated(true);
    }

    boolean appInstalada(String nombrePaquete, Context context) {

        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(nombrePaquete, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}