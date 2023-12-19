package com.example.examenfragments_albertorodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examenfragments_albertorodriguez.Controlador.SQLHelper;
import com.example.examenfragments_albertorodriguez.Modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    Button login;

    SQLHelper db = new SQLHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            user = findViewById(R.id.userLog);
            pass = findViewById(R.id.passLog);
            login = findViewById(R.id.inicioLog);

            login.setOnClickListener(v -> {

                Usuario usrCompr = db.iniciarSesion(user.getText().toString(), pass.getText().toString());

                if(usrCompr != null){
                    Intent intent = new Intent(this, LibrosActivity.class);
                    intent.putExtra("usuario", usrCompr);

                    startActivity(intent);

                }else{
                    Toast.makeText(this, "No hay usuarios coincidentes", Toast.LENGTH_SHORT).show();
                }

            });

    }
}