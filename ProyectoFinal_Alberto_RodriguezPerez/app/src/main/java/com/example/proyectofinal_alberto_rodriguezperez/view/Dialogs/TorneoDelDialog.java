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
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.PartidaController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ControllersOfModels.TorneoController;
import com.example.proyectofinal_alberto_rodriguezperez.model.Partida;
import com.example.proyectofinal_alberto_rodriguezperez.model.Torneo;
import com.example.proyectofinal_alberto_rodriguezperez.view.DataActivity;

public class TorneoDelDialog extends DialogFragment implements DialogInterface.OnClickListener {
    Torneo torneo;
    TorneoController pc = new TorneoController();
    OnMyEvent dataActivity;

    public TorneoDelDialog(Torneo torneo){
        this.torneo = torneo;
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
        builder.setMessage("¿Está seguro de que desea eliminar el torneo seleccionado?");
        builder.setPositiveButton("Aceptar", this);
        builder.setNegativeButton("Cancelar", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1:

                pc.borrarTorneo(getContext(), torneo.getId());
                dataActivity.actualizaFragment();

                break;
            case -2:

                dismiss();

                break;
        }
    }
}
