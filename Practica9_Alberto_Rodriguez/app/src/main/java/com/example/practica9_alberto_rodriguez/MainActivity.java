package com.example.practica9_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    Spinner selMarcas, selTipos;
    ListView listaColores;
    ArrayAdapter<CharSequence> marcasAdapter, tiposAdapter;
    String marcaSelect, tipoSelect, colorSelect = null;
    String[] colores = new String[]{"rojo", "verde", "azul", "morado", "negro"};
    Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selMarcas = findViewById(R.id.selMarca);
        selTipos = findViewById(R.id.selTipo);
        listaColores = findViewById(R.id.listColores);
        enviar = findViewById(R.id.bMostrar);

        marcasAdapter = ArrayAdapter.createFromResource(this, R.array.marcas, R.layout.item);
        selMarcas.setAdapter(marcasAdapter);
        selMarcas.setOnItemSelectedListener(this);

        ArrayAdapter<String> coloresAdapter = new ArrayAdapter<>(this, R.layout.listview_item, colores);
        listaColores.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listaColores.setAdapter(coloresAdapter);
        listaColores.setOnItemClickListener(this);

        enviar.setOnClickListener(v -> Toast.makeText(this, "Marca: "+marcaSelect+" | Tipo: "+tipoSelect+" | Color: "+colorSelect+".", Toast.LENGTH_SHORT).show());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter().equals(marcasAdapter)) {
            selTipos.setVisibility(View.VISIBLE);
            if(position == 0){
                onNothingSelected(parent);
            }
            else if (parent.getAdapter().getItem(position).equals("Audi")) {
                marcaSelect = "Audi";
                tiposAdapter = ArrayAdapter.createFromResource(this, R.array.tiposAudi, R.layout.item);

            } else if (parent.getAdapter().getItem(position).equals("Peugeot")) {
                marcaSelect = "Peugeot";
                tiposAdapter = ArrayAdapter.createFromResource(this, R.array.tiposPeugeot, R.layout.item);

            } else if (parent.getAdapter().getItem(position).equals("Seat")) {
                marcaSelect = "Seat";
                tiposAdapter = ArrayAdapter.createFromResource(this, R.array.tiposSeat, R.layout.item);

            }else
                marcaSelect = null;

            selTipos.setAdapter(tiposAdapter);
            selTipos.setOnItemSelectedListener(this);

        }else if(parent.getAdapter().equals(tiposAdapter)){
            if(position == 0)
                tipoSelect = null;
            else
                tipoSelect = (String)parent.getAdapter().getItem(position);
        }
        actualizaBoton();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getAdapter().equals(marcasAdapter)){
            marcaSelect = null;
            selTipos.setVisibility(View.INVISIBLE);

        }else if(parent.getAdapter().equals(tiposAdapter))
            tipoSelect = null;

        actualizaBoton();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        colorSelect = (String)parent.getAdapter().getItem(position);

        actualizaBoton();
    }

    public void actualizaBoton(){
        enviar.setEnabled(marcaSelect != null && tipoSelect != null && colorSelect != null);
    }
}