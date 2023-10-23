package com.example.practicaevaluable_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

public class ActivityMayor18 extends AppCompatActivity {

    RatingBar rateo;
    Button puntuar;
    float puntuacion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayor18);


         puntuar = findViewById(R.id.bPuntua);
         puntuar.setOnClickListener(v -> {
             Intent data = new Intent();
             data.putExtra("nota", Float.toString(puntuacion));

             setResult(RESULT_OK, data);
             finish();
        });


        rateo = findViewById(R.id.rateo);
        rateo.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                puntuacion = rating;
                if(rating != 0)
                    puntuar.setEnabled(true);
            }
        });
    }
}