package com.example.zapateria_alberto_rodriguezperez;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    String[] tipoCalzado = new String[]{"Selecciona un calzado ...", "Bota", "Zapato", "Sandalia", "Zapatilla"};
    Spinner spTipos;
    ArrayAdapter<CharSequence> tiposAdapter, descripListView;
    ArrayList<Calzado> listaCalzados = new ArrayList<Calzado>();
    ArrayList<Calzado> calzadoFiltrado = new ArrayList<Calzado>();
    ArrayList<CharSequence> descripFiltrada = new ArrayList<CharSequence>();
    Button registrar;
    ListView lista;
    boolean ignorarPrimerToast = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTipos = findViewById(R.id.spTipos);
        spTipos.setVisibility(View.INVISIBLE);

        tiposAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoCalzado);
        spTipos.setAdapter(tiposAdapter);
        spTipos.setOnItemSelectedListener(this);

        registrar = findViewById(R.id.registrar);
        registrar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);

            intentResult.launch(intent);
        });

        lista = findViewById(R.id.lista);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        descripFiltrada = new ArrayList<CharSequence>();
        calzadoFiltrado = new ArrayList<Calzado>();

        if(parent.getAdapter().getItem(position).equals("Selecciona un calzado ...")){
            onNothingSelected(parent);
            return;
        }else if(parent.getAdapter().getItem(position).equals("Bota")){
            for(int i = 0; i<listaCalzados.size(); i++){
                if(listaCalzados.get(i).getTipo().equals("Bota")) {
                    calzadoFiltrado.add(listaCalzados.get(i));
                    descripFiltrada.add(listaCalzados.get(i).getDescripción());
                }
            }

        }else if(parent.getAdapter().getItem(position).equals("Zapato")){
            for(int i = 0; i<listaCalzados.size(); i++){
                if(listaCalzados.get(i).getTipo().equals("Zapato")) {
                    calzadoFiltrado.add(listaCalzados.get(i));
                    descripFiltrada.add(listaCalzados.get(i).getDescripción());
                }
            }

        }else if(parent.getAdapter().getItem(position).equals("Sandalia")){
            for(int i = 0; i<listaCalzados.size(); i++){
                if(listaCalzados.get(i).getTipo().equals("Sandalia")) {
                    calzadoFiltrado.add(listaCalzados.get(i));
                    descripFiltrada.add(listaCalzados.get(i).getDescripción());
                }
            }

        }else if(parent.getAdapter().getItem(position).equals("Zapatilla")){
            for(int i = 0; i<listaCalzados.size(); i++){
                if(listaCalzados.get(i).getTipo().equals("Zapatilla")) {
                    calzadoFiltrado.add(listaCalzados.get(i));
                    descripFiltrada.add(listaCalzados.get(i).getDescripción());
                }
            }

        }else
            Toast.makeText(this, "No existen elementos del tipo seleccionado", Toast.LENGTH_SHORT).show();

        descripListView = new ArrayAdapter<>(this, R.layout.listviewmuestradescrip, descripFiltrada);
        lista.setAdapter(descripListView);

        lista.setOnItemClickListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(!ignorarPrimerToast)
            Toast.makeText(this, "No se ha seleccionado tipo de calzado", Toast.LENGTH_SHORT).show();
        else
            ignorarPrimerToast = false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Calzado seleccionado = calzadoFiltrado.get(position);

        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("calzado", seleccionado);

        startActivity(intent);
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    assert result.getData() != null;
                    listaCalzados.add((Calzado) result.getData().getSerializableExtra("calzado"));
                    spTipos.setVisibility(View.VISIBLE);
                }
            }
        }
    );
}