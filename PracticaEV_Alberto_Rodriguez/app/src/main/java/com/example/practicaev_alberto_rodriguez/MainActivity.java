package com.example.practicaev_alberto_rodriguez;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner catego;
    ListView listadoLibros;
    ArrayList<Libro> libros= Biblioteca.rellenar();
    MiListAdapter adapterListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catego = findViewById(R.id.categoria);
        catego.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adaptaSpinner = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        catego.setAdapter(adaptaSpinner);

        listadoLibros = findViewById(R.id.lista);
        recargaListLibros(libros);

        registerForContextMenu(listadoLibros);
    }

    public void recargaListLibros(ArrayList<Libro> libros){
        adapterListView = new MiListAdapter(this, libros);
        listadoLibros.setAdapter(adapterListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.addMenu){
            Intent intent = new Intent(this, AddActivity.class);

            intentResult.launch(intent);

        }else if(item.getItemId() == R.id.actualizaStockMenu){
            if(catego.getSelectedItemId() == 0){
                recargaListLibros(Biblioteca.rellenar());
                Toast.makeText(this, "Se ha recargado la lista", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Opción no válida. Selecciona TODO en las categorías", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.detallesMenu){

            Intent intent = new Intent(this, DetallesActivity.class);
            intent.putExtra("libro", (Libro)listadoLibros.getAdapter().getItem(info.position));

            startActivity(intent);
            onPause();

        } else if (item.getItemId() == R.id.prestarMenu) {
            if(catego.getSelectedItemId() != 0){
                Toast.makeText(this, "No se puede prestar si hay un filtro", Toast.LENGTH_SHORT).show();

            }else{
                libros.remove(libros.get(info.position));
                recargaListLibros(libros);

                Toast.makeText(this, "Libro prestado", Toast.LENGTH_SHORT).show();
            }

        }

        return super.onContextItemSelected(item);
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    catego.setSelection(0);
                    assert result.getData() != null;

                    if(result.getResultCode() == RESULT_OK)
                        libros.add((Libro) result.getData().getSerializableExtra("libro"));

                    recargaListLibros(libros);

                }
            }
    );

    @Override
    protected void onResume() {
        catego.setSelection(0);

        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Libro> subArray = new ArrayList<>();

        if(parent.getSelectedItemPosition() == 0){
            subArray = libros;

        }else if(parent.getSelectedItemPosition() == 1){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Thriller"))
                    subArray.add(libros.get(i));
            }

        }else if(parent.getSelectedItemPosition() == 2){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Romántica"))
                    subArray.add(libros.get(i));
            }

        }else if(parent.getSelectedItemPosition() == 3){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Fantasía"))
                    subArray.add(libros.get(i));
            }

        }else if(parent.getSelectedItemPosition() == 4){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Histórica"))
                    subArray.add(libros.get(i));
            }

        }else if(parent.getSelectedItemPosition() == 5){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Ciencia Ficción"))
                    subArray.add(libros.get(i));
            }

        }else if(parent.getSelectedItemPosition() == 6){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Aventura"))
                    subArray.add(libros.get(i));
            }

        }else if(parent.getSelectedItemPosition() == 7){
            for(int i = 0; i< libros.size(); i++){
                if(libros.get(i).getCategoria().equals("Terror"))
                    subArray.add(libros.get(i));
            }
        }

        recargaListLibros(subArray);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //NADA
    }
}