package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;

public class ContextMenuController {

    public static void partidasMenu(Partida partidaSelecet, MenuItem item, Context context){

        if(item.getItemId() == R.id.partVer || item.getItemId() == R.id.partSoloVer)
        {

        }
        else if (item.getItemId() == R.id.partMod)
        {

        }
        else if (item.getItemId() == R.id.partDel)
        {

        }
        else
            Toast.makeText(context, "???????", Toast.LENGTH_SHORT).show();
    }

    public static void torneosMenu(Torneo torneoSelecet, MenuItem item, Context context){

        if(item.getItemId() == R.id.torneoVer || item.getItemId() == R.id.torneoSoloVer)
        {

        }
        else if (item.getItemId() == R.id.torneoMod)
        {

        }
        else if (item.getItemId() == R.id.torneoDel)
        {

        }
        else
            Toast.makeText(context, "???????", Toast.LENGTH_SHORT).show();
    }
}
