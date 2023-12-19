package com.example.examenfragments_albertorodriguez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.examenfragments_albertorodriguez.Dialogs.InsertDialog;
import com.example.examenfragments_albertorodriguez.Fragments.DetallesFragment;
import com.example.examenfragments_albertorodriguez.Fragments.InsertaLibroFragment;
import com.example.examenfragments_albertorodriguez.Fragments.ListaLibrosFragment;
import com.example.examenfragments_albertorodriguez.Fragments.OnFragmentEventListener;
import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.Modelo.Usuario;

public class LibrosActivity extends AppCompatActivity implements OnFragmentEventListener{

    Usuario usr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros);

        //if(getIntent().getExtras() != null)
            usr = (Usuario)getIntent().getSerializableExtra("usuario");


        estableceFrameList();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            estableceDetallesOForm(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(usr.getRol().equals("administrador") && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.options_menu, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menuInsert){
            InsertDialog insertar = new InsertDialog();
            insertar.show(getSupportFragmentManager(), "insertar");
        }

        return super.onOptionsItemSelected(item);
    }

    private void estableceFrameList(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ListaLibrosFragment fragmentLista = new ListaLibrosFragment();
        transaction.replace(R.id.frameLista, fragmentLista);

        transaction.commit();
    }

    private void estableceDetallesOForm(Libro recibido){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(usr.getRol().equals("administrador") && recibido == null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            InsertaLibroFragment fragmentInsertar = new InsertaLibroFragment();
            transaction.replace(R.id.frameDetalles, fragmentInsertar);

        }else if(usr.getRol().equals("cliente") && recibido != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            DetallesFragment fragmentDetalles = new DetallesFragment();
            Bundle args = new Bundle();
            args.putSerializable("libro", recibido);
            fragmentDetalles.setArguments(args);

            transaction.replace(R.id.frameDetalles, fragmentDetalles);
        }


        transaction.commit();
    }

    @Override
    public void libroSeleccionado(Libro libro) {
        estableceDetallesOForm(libro);
    }

    @Override
    public void actualizaListado() {
        estableceFrameList();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            estableceDetallesOForm(null);
    }
}