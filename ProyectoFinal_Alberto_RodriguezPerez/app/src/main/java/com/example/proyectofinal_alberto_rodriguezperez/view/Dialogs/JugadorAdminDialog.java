package com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;
import com.example.proyectofinal_alberto_rodriguezperez.view.DataActivity;

public class JugadorAdminDialog extends DialogFragment implements DialogInterface.OnClickListener {
    Jugador jugador;
    JugadorController jc = new JugadorController();
    OnMyEvent dataActivity;

    public JugadorAdminDialog(Jugador jugador){
        this.jugador = jugador;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof DataActivity)
            dataActivity = (OnMyEvent) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataActivity = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmación de borrado");
        if(jugador.getEsAdmin() == 1)
            builder.setMessage("¿Está completamente seguro de que desea eliminar los permisos de administrador?");
        else
            builder.setMessage("¿Está completamente seguro de que desea dar permisos de administrador?");

        builder.setPositiveButton("Aceptar", this);
        builder.setNegativeButton("Cancelar", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1:

                jc.gestionaAdmin(getContext(), jugador);
                dataActivity.actualizaFragment();

                break;
            case -2:

                dismiss();

                break;
        }
    }
}
