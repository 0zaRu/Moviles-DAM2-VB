package com.example.factorialsinhilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button boton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.num);
        boton = findViewById(R.id.fact);
        textView = findViewById(R.id.result);

        boton.setOnClickListener(this);

    }

    public double factorial(int n) {
        double res=1;
        for (int i=1; i<=n; i++){
            res*=i;
            SystemClock.sleep(1000);
        }
        return res;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fact) {
            textView.setText(String.valueOf(factorial(Integer.parseInt(editText.getText().toString()))));
        }
    }
}