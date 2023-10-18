package com.example.practica8_alberto_rodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.Manifest;
import android.widget.Toast;

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

        findViewById(R.id.bLlamar).setOnClickListener(v -> {

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 12345);

            } else {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 722633290));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent intent = new Intent(this, DevActivity.class);
        startActivity(intent);

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 12345 && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 722633290));
            startActivity(intent);
        }
    }
}