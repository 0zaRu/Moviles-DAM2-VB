package com.example.t3practica2_albertorodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button boton;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.num);
        boton = findViewById(R.id.fact);
        textView = findViewById(R.id.result);
        progressBar = findViewById(R.id.progressBar);


        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fact && !editText.getText().toString().isEmpty()) {
            MiHilo hilo = new MiHilo(Integer.parseInt(editText.getText().toString()));
            hilo.execute();

        }
    }

    /*
    No me queda claro si esta es la forma en la que pides que resolvamos el ejercicio
    * quiero pensar que es así, puesto que habilita pulsar el botón de forma indefinida
    * a la vez que no salta excepción, aunque queden las pulsaciones y cálculo en cola
    * */

    public class MiHilo extends AsyncTask{
        private int n;
        public MiHilo (int n) {
            this.n = n;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            progressBar.setProgress((int)values[0]);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return factorial();
        }

        @Override
        protected void onPostExecute(Object o) {
            textView.setText(String.valueOf( (Double) o ));
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setProgress(0);
        }



        public double factorial () {
            double res=1;
            for (int i=1; i<=this.n; i++){
                res*=i;
                SystemClock.sleep(1000);
                publishProgress((int)i*100/n);
            }
            return res;
        }

    }
}