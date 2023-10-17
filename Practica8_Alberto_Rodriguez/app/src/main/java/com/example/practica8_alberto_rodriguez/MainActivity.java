package com.example.practica8_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ConstraintLayout panel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.urlvenan).setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.iesvenancioblanco.es/");

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        findViewById(R.id.bparaenviar).setOnClickListener(v -> {
            Intent intent = new Intent(this, ShareActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.panel).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent intent = new Intent(this, DevActivity.class);
        startActivity(intent);

        return false;
    }
}