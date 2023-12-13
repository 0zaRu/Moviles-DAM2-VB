package com.example.practica16_alberto_rodriguez.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.practica16_alberto_rodriguez.Coche.Coche;
import com.example.practica16_alberto_rodriguez.R;

public class DialogAddOrModify extends DialogFragment implements DialogInterface.OnClickListener {

    OnDialogEvent listener;
    TextView numBastidor, marca, modelo, kilometraje;
    Spinner combustible, color;
    Coche c = null;

    public DialogAddOrModify(Coche coche){
        if(coche != null)
            this.c = coche;

    }

    public DialogAddOrModify(){

    }

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

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(vista);

        numBastidor = vista.findViewById(R.id.dNBastidor);
        marca = vista.findViewById(R.id.dMarca);
        modelo = vista.findViewById(R.id.dModelo);
        combustible = vista.findViewById(R.id.dCombustible);
        color = vista.findViewById(R.id.dColores);
        kilometraje = vista.findViewById(R.id.dKilometraje);

        if(c != null){
            numBastidor.setText(c.getNumBastidor());
            marca.setText(c.getMarca());
            modelo.setText(c.getModelo());
            kilometraje.setText(Integer.toString(c.getKilometraje()));

            String[] combustibles = getResources().getStringArray(R.array.combustibles);
            for(int i = 0; i < combustibles.length; i++)
                if(combustibles[i].equals(c.getCombustible()))
                   combustible.setSelection(i);

            String[] colores = getResources().getStringArray(R.array.colores);
            for(int i = 0; i < colores.length; i++)
                if(colores[i].equals(c.getColor()))
                    color.setSelection(i);

            numBastidor.setEnabled(false);
        }

        builder.setTitle("Añadir coche");
        builder.setMessage("Rellena todos los campos");
        builder.setPositiveButton("Aceptar", this);
        builder.setNegativeButton("Cancelar", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1:

                if(numBastidor.getText().length() != 17 ||
                   marca.getText().toString().isEmpty() ||
                   modelo.getText().toString().isEmpty() ||
                   (kilometraje.getText().toString().isEmpty() &&
                   (Integer.parseInt(kilometraje.getText().toString()) < 0 ||
                   Integer.parseInt(kilometraje.getText().toString()) > 500000))){
                    Toast.makeText(getContext(), "Alguno de los valores está vacío o no es correcto", Toast.LENGTH_SHORT).show();

                }else {
                    Coche enviar = new Coche(
                            numBastidor.getText().toString(),
                            marca.getText().toString(),
                            modelo.getText().toString(),
                            combustible.getSelectedItem().toString(),
                            color.getSelectedItem().toString(),
                            Integer.parseInt(kilometraje.getText().toString()));

                    if(c == null)
                        listener.addCoche(enviar);
                    else
                        listener.modificarCoche(enviar);

                    dismiss();
                }

                break;
            case -2:
                dismiss();
                break;

            case -3:
            break;
        }
    }
}
