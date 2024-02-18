package com.example.proyectofinal_alberto_rodriguezperez.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Buscar.BuscarFormatoFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Buscar.BuscarResultadoFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Buscar.BuscarTopMenuFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio.InicioTopMenuFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio.PartidasFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.PerfilFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio.TodoFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.Inicio.TorneosFragment;

public class DataActivity extends AppCompatActivity implements View.OnClickListener, OnMyEvent {

    Button btBuscar, btInicio;
    static String modoActual = "inicio";
    Jugador jugador;
    String opcionBusqueda = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        assert getIntent() != null;
        jugador = (Jugador) getIntent().getSerializableExtra("jugador");

        ponerTopMenu();


        btBuscar = findViewById(R.id.menuButBuscar);
        btBuscar.setOnClickListener(this);

        btInicio = findViewById(R.id.menuButInicio);
        btInicio.setOnClickListener(this);

    }

    private void ponerTopMenu(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;
        View vistaFalsa = new View(this);

        if(modoActual.equals("inicio"))
        {
            fragmentACalocar = new InicioTopMenuFragment();

            //Ponemos fragment principial tod-o por defecto
            vistaFalsa.setId(R.id.InicioButTodo);
            botoneraInicio(vistaFalsa);

        }
        else if(modoActual.equals("buscar"))
        {
            fragmentACalocar = new BuscarTopMenuFragment();
            vistaFalsa.setId(R.id.layoutEstablecebusqueda);
            botoneraBuscar(vistaFalsa, "");
        }


        if(fragmentACalocar != null) {
            Bundle args = new Bundle();
            args.putSerializable("usuario", jugador);
            fragmentACalocar.setArguments(args);

            transaction.replace(R.id.topFrame, fragmentACalocar);
        }

        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        //botón Inicio
        if(v.getId() == R.id.menuButInicio)
            modoActual = "inicio";

        //Botón Búsqueda
        else if(v.getId() == R.id.menuButBuscar)
            modoActual = "buscar";

        ponerTopMenu();

    }

    @Override
    public void botoneraInicio(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;

        if(v.getId() == R.id.InicioButTodo)
            fragmentACalocar = new TodoFragment();

        else if(v.getId() == R.id.InicioButPartidas)
            fragmentACalocar = new PartidasFragment();

        else if(v.getId() == R.id.InicioButTorneos)
            fragmentACalocar = new TorneosFragment();

        else if(v.getId() == R.id.InicioButPerfil)
            fragmentACalocar = new PerfilFragment();



        if(fragmentACalocar != null){
            Bundle args = new Bundle();
            args.putSerializable("usuario", jugador);
            fragmentACalocar.setArguments(args);

            transaction.replace(R.id.principalFrame, fragmentACalocar);
        }

        transaction.commit();
    }

    @Override
    public void botoneraBuscar(View v, String txtBuscar) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;
        Bundle args = new Bundle();

        if(v.getId() == R.id.layoutEstablecebusqueda) {
            opcionBusqueda = "";
            fragmentACalocar = new BuscarFormatoFragment();
        }

        else if(v.getId() == R.id.BuscarButPerfil)
            fragmentACalocar = new PerfilFragment();

        else if(txtBuscar.isEmpty() && (opcionBusqueda.equals("Torneos") || opcionBusqueda.equals("Jugadores")))
            Toast.makeText(this, "Texto a buscar no establecido", Toast.LENGTH_SHORT).show();

        else if(opcionBusqueda.isEmpty())
            Toast.makeText(this, "Tipo de búsqueda no establecido", Toast.LENGTH_SHORT).show();

        else
        { //Es decir, hay formato de búsqueda y/o texto a buscar
            if(!txtBuscar.isEmpty())
            { //opción 1, es torneo o usuario, que se buscan por texto
                args.putString("estiloBusqueda", opcionBusqueda);
                args.putString("textoBusqueda", txtBuscar);

                fragmentACalocar = new BuscarResultadoFragment();

            }
            else
                Toast.makeText(this, "Huevo", Toast.LENGTH_SHORT).show();
        }



        if(fragmentACalocar != null) {
            args.putSerializable("usuario", jugador);
            fragmentACalocar.setArguments(args);

            transaction.replace(R.id.principalFrame, fragmentACalocar);
        }

        transaction.commit();
    }

    @Override
    public void tipoFiltradoBuscar(String tipo) {
        opcionBusqueda = tipo;
    }

    @Override
    public void actualizaFragment(){
        ponerTopMenu();
    }

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1234) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intentResult.launch(intent);

            } else {
                Toast.makeText(this, "No podrás cambiarte la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == 1234){
                        if(o.getData() != null && o.getData().getExtras() != null){
                            //Aquí va la imagen
                        }
                    }
                }
            }
    );
*/
}