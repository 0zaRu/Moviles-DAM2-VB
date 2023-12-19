package com.example.examenfragments_albertorodriguez.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.examenfragments_albertorodriguez.Controlador.SQLHelper;
import com.example.examenfragments_albertorodriguez.Fragments.OnFragmentEventListener;
import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.R;

public class InsertDialog extends DialogFragment implements DialogInterface.OnClickListener {

    OnFragmentEventListener listener;
    SQLHelper db = new SQLHelper(getActivity());
    EditText nombre, autor, pags;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentEventListener && context != null)
            listener = (OnFragmentEventListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.dialog_item, null);
        builder.setView(vista);

        nombre = vista.findViewById(R.id.dInsertTitle);
        autor = vista.findViewById(R.id.dInsertAutor);
        pags = vista.findViewById(R.id.dInsertPags);

        builder.setTitle(R.string.a_adir_un_libro);
        builder.setMessage(R.string.rellena_los_campos_correctamnete);
        builder.setPositiveButton(getString(R.string.a_adir), this);
        builder.setNegativeButton(getString(R.string.cancelar), this);

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            if(nombre.getText().toString().isEmpty() ||
                    autor.getText().toString().isEmpty() ||
                    pags.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Alguno de los campos está vacío", Toast.LENGTH_SHORT).show();
            }

            db.insertarLibro(new Libro(
                    nombre.getText().toString(),
                    autor.getText().toString(),
                    Integer.parseInt(pags.getText().toString())
            ));

            Toast.makeText(getActivity(), "Libro insertado correctamente", Toast.LENGTH_SHORT).show();

            listener.actualizaListado();
        }
    }
}
