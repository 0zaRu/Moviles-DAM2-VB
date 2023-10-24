package com.example.practicaevaluable_alberto_rodriguez;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int CODE = 123;
    EditText nombre, edad;
    RadioGroup grupoColor;
    RadioButton rRosa, rAzul, rVerde;
    Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.tNombre);
        edad = findViewById(R.id.tEdad);
        grupoColor = findViewById(R.id.radioGroup);
        rRosa = findViewById(R.id.rRosa);
        rAzul = findViewById(R.id.rAzul);
        rVerde = findViewById(R.id.rVerde);

        enviar = findViewById(R.id.bEnviar);
        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean campoVacio = false;

        if (nombre.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo nombre vacío", Toast.LENGTH_SHORT).show();
            campoVacio = true;
        }

        if (edad.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo edad vacío", Toast.LENGTH_SHORT).show();
            campoVacio = true;
        }

        if (grupoColor.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Campo color favorito vacío", Toast.LENGTH_SHORT).show();
            campoVacio = true;
        }

        if (!campoVacio) {

            try {
                int iEdad = Integer.parseInt(edad.getText().toString());
                if (iEdad < 1 || iEdad > 110) {
                    Toast.makeText(this, "Edad no válida", Toast.LENGTH_SHORT).show();
                    edad.setText("");
                    return;
                }

                if (iEdad >= 18) {
                    Intent intent = new Intent(this, ActivityMayor18.class);
                    intentResult.launch(intent);

                } else {
                    String color = "";
                    if (grupoColor.getCheckedRadioButtonId() == rRosa.getId())
                        color = "Rosa";

                    else if (grupoColor.getCheckedRadioButtonId() == rAzul.getId())
                        color = "Azul";
                    else
                        color = "Verde";


                    Intent intent = new Intent(this, ActivityMenor18.class);
                    intent.putExtra("nombre", nombre.getText().toString());
                    intent.putExtra("color", color);

                    startActivity(intent);
                }


            } catch (NumberFormatException e) {
                Toast.makeText(this, "Campo edad no contiene un formato de edad válido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                        Intent dato = result.getData();
                        String estrellas = dato.getStringExtra("nota");

                        Toast.makeText(getApplicationContext(), "Se ha puntuado con " + estrellas + " estrellas.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}