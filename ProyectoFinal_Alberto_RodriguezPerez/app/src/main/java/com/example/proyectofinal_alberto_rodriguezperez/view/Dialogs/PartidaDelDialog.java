package com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.proyectofinal_alberto_rodriguezperez.Interfaces.OnMyEvent;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.view.DataActivity;

public class PartidaDelDialog extends DialogFragment implements DialogInterface.OnClickListener {
    Partida partida;
    PartidaController pc = new PartidaController();
    OnMyEvent dataActivity;

    public PartidaDelDialog(Partida partida){
        this.partida = partida;
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
        builder.setMessage("¿Está seguro de que desea eliminar la partida seleccionada?");
        builder.setPositiveButton("Aceptar", this);
        builder.setNegativeButton("Cancelar", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1:

                pc.borrarPartida(getContext(), partida.getId());
                dataActivity.actualizaFragment();

                break;
            case -2:

                dismiss();

                break;
        }
    }
}
