package com.example.practica14_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

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
            String usuarioLog = db.compruebaUser(this, user.getText().toString(), pass.getText().toString());

            if(usuarioLog.isEmpty()){
                Toast.makeText(this, "Usuario o contraeña no registrados", Toast.LENGTH_SHORT).show();

            }else{
                Intent intent = new Intent(this, CatalogActivity.class);
                intent.putExtra("nombreUser", usuarioLog);

                startActivity(intent);
            }

        });
    }
}