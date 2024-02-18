package com.example.proyectofinal_alberto_rodriguezperez.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;
import com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs.PartidaDelDialog;
import com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs.RegisterOrEditDialog;
import com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs.TorneoDelDialog;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.modelInfo.PartidaInfoFragment;
import com.example.proyectofinal_alberto_rodriguezperez.view.Fragments.modelInfo.TorneoInfoFragment;

public class ContextMenuController {

    public static void partidasMenu(Partida partidaSelecet, Jugador jugador, MenuItem item, FragmentActivity actividad){
        FragmentManager fragmentManager = actividad.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;
        Bundle args = new Bundle();

        if(item != null &&(item.getItemId() == R.id.partVer || item.getItemId() == R.id.partSoloVer))
        {
            fragmentACalocar = new PartidaInfoFragment();
            args.putString("modo", "ver");
        }
        else if (item != null &&(item.getItemId() == R.id.partMod))
        {
            fragmentACalocar = new PartidaInfoFragment();
            args.putString("modo", "editar");
        }
        else if (item != null &&(item.getItemId() == R.id.partDel))
        {
            PartidaDelDialog dialog = new PartidaDelDialog(partidaSelecet);
            dialog.show(actividad.getSupportFragmentManager(), "llamadaBorrar");
        }
        else if (partidaSelecet == null)
        {
            fragmentACalocar = new PartidaInfoFragment();
            args.putString("modo", "add");
        }
        else
            Toast.makeText(actividad, "???????", Toast.LENGTH_SHORT).show();


        if(fragmentACalocar != null) {
            args.putSerializable("jugador", jugador);
            args.putSerializable("partida", partidaSelecet);
            fragmentACalocar.setArguments(args);

            transaction.add(R.id.principalFrame, fragmentACalocar);
        }

        transaction.commit();
    }

    public static void torneosMenu(Torneo torneoSelecet, Jugador jugador, MenuItem item, FragmentActivity actividad){
        FragmentManager fragmentManager = actividad.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragmentACalocar = null;
        Bundle args = new Bundle();

        if(item != null && (item.getItemId() == R.id.torneoVer || item.getItemId() == R.id.torneoSoloVer))
        {
            fragmentACalocar = new TorneoInfoFragment();
            args.putString("modo", "ver");

        }
        else if (item != null && (item.getItemId() == R.id.torneoMod))
        {
            fragmentACalocar = new TorneoInfoFragment();
            args.putString("modo", "editar");
        }
        else if (item != null && (item.getItemId() == R.id.torneoDel))
        {
            TorneoDelDialog dialog = new TorneoDelDialog(torneoSelecet);
            dialog.show(actividad.getSupportFragmentManager(), "llamadaBorrar");
        }
        else if (torneoSelecet == null)
        {
            fragmentACalocar = new TorneoInfoFragment();
            args.putString("modo", "add");
        }
        else
            Toast.makeText(actividad, "???????", Toast.LENGTH_SHORT).show();

        if(fragmentACalocar != null) {
            args.putSerializable("jugador", jugador);
            args.putSerializable("torneo", torneoSelecet);
            fragmentACalocar.setArguments(args);

            transaction.add(R.id.principalFrame, fragmentACalocar);
        }

        transaction.commit();
    }
}
