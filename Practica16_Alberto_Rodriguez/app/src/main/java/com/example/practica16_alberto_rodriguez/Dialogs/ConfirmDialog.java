package com.example.practica16_alberto_rodriguez.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.practica16_alberto_rodriguez.Coche.Coche;

public class ConfirmDialog extends DialogFragment implements DialogInterface.OnClickListener {

    OnDialogEvent listener;
    Coche c = null;

    public ConfirmDialog(Coche c){
        this.c = c;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnDialogEvent) {
            listener = (OnDialogEvent) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmación");
        builder.setMessage("¿Está seguro de que desea eliminar el coche seleccionado?");
        builder.setPositiveButton("Aceptar", this);
        builder.setNegativeButton("Cancelar", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1:

                listener.eliminaCoche(c);

                break;
            case -2:

                dismiss();

                break;
        }
    }
}
