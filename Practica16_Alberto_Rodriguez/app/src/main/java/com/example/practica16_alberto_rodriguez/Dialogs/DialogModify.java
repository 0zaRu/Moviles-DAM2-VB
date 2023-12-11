package com.example.practica16_alberto_rodriguez.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.practica16_alberto_rodriguez.R;

public class DialogModify extends DialogFragment implements DialogInterface.OnClickListener {

    OnDialogEvent listener;
    TextView t1, t2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnDialogEvent)
            listener = (OnDialogEvent) context;
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

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(vista);

        t1 = vista.findViewById(R.id.edit1);
        t2 = vista.findViewById(R.id.edit2);

        builder.setTitle("Modificar coche");
        builder.setMessage("Modifica los campos que consideres necesarios");
        builder.setPositiveButton("Aceptar", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1:

                listener.modificar(t1.getText().toString(), t2.getText().toString());
                break;
            case -2:
                break;
            case -3:
            break;

        }
    }
}
