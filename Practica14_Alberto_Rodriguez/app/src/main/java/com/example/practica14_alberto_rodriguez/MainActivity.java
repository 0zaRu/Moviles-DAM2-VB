package com.example.practica14_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practica14_alberto_rodriguez.Modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    SQLHelper db = new SQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.nombreIntroducido);
        pass = findViewById(R.id.passIntroducida);

        findViewById(R.id.botonLogin).setOnClickListener(v -> {
            Usuario usuarioLog = db.findUser(this, user.getText().toString(), pass.getText().toString());

            if(usuarioLog == null){
                Toast.makeText(this, "Usuario o contraseña no registrados", Toast.LENGTH_SHORT).show();

            }else{
                Intent intent = new Intent(this, CatalogActivity.class);
                intent.putExtra("user", usuarioLog);

                startActivity(intent);
            }

        });
    }
}