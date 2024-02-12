package com.example.proyectofinal_alberto_rodriguezperez.view.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.proyectofinal_alberto_rodriguezperez.R;
import com.example.proyectofinal_alberto_rodriguezperez.controller.ImageController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.JugadorController;
import com.example.proyectofinal_alberto_rodriguezperez.controller.Security;
import com.example.proyectofinal_alberto_rodriguezperez.model.Jugador;

public class RegisterOrEditDialog extends DialogFragment implements DialogInterface.OnClickListener, View.OnClickListener {

    private final JugadorController jugadorCont = new JugadorController();
    Jugador recibido = null, recibidoModificado = new Jugador();
    String imagen = null;
    TextView nombre, pais, fechaNaci, correo, pass1, pass2, pass3;
    ImageView foto;

    public RegisterOrEditDialog(Jugador jugador){
        if(jugador != null){
            this.recibido = jugador;
            this.imagen = recibido.getImagen();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.dialog_perfil, null);
        builder.setView(vista);

        nombre = vista.findViewById(R.id.etNombre);
        pais = vista.findViewById(R.id.etPais);
        fechaNaci = vista.findViewById(R.id.etFechaNaci);
        correo = vista.findViewById(R.id.etCorreo);
        pass1 = vista.findViewById(R.id.etPasswd1);
        pass2 = vista.findViewById(R.id.etPasswd2);
        pass3 = vista.findViewById(R.id.etPasswd3);

        foto = vista.findViewById(R.id.fotoPerfil);
        foto.setOnClickListener(this);

        if(recibido != null)
        {
            nombre.setEnabled(false);
            nombre.setText(recibido.getNombre());
            pais.setText(recibido.getPais());
            fechaNaci.setText(recibido.getFechaNacimiento());
            correo.setText(recibido.getCorreoElectronico());
            pass1.setHint("Contraseña antigua");
            pass2.setHint("Contraseña nueva");

            pass3.setHint("Repite contraseña nueva");
            pass3.setVisibility(View.VISIBLE);

            builder.setTitle("Modificar "+ recibido.getNombre());
            builder.setPositiveButton("Modificar", this);
        }
        else
        {
            pass1.setHint("Contraseña");
            pass2.setHint("Repite contraseña");

            builder.setTitle("Registrar usuario");
            builder.setPositiveButton("Registrarse", this);
        }

        builder.setMessage("Campos posibles");
        builder.setNegativeButton("Cancelar", this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                if(recibido != null){
                    opcionModificar();
                }else{
                    opcionRegistrar();
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(getContext(), "Operación cancelada", Toast.LENGTH_SHORT).show();
        }
    }

    private void opcionModificar() {
        recibidoModificado = recibido;
        recibidoModificado.setImagen(imagen);

        if (nombre.getText().toString().isEmpty() ||
                fechaNaci.getText().toString().isEmpty() ||
                pass1.getText().toString().isEmpty())
        {
            Toast.makeText(getContext(), "Contraseña no insertada.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!recibido.getPasswd().equals(Security.getMD5(pass1.getText().toString()))){
            Toast.makeText(getContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass2.getText().toString().isEmpty()){
            if(pass2.getText().toString().equals(pass3.getText().toString()) &&
                !pass2.getText().toString().equals(pass1.getText().toString())){

                Toast.makeText(getContext(), "Contraseñas no coincidentes o igual a la anterior", Toast.LENGTH_SHORT).show();
            }
            else{
                recibido.setPasswd(Security.getMD5(pass2.getText().toString()));
            }
        }

        recibidoModificado.setCorreoElectronico(correo.getText().toString());
        recibidoModificado.setFechaNacimiento(fechaNaci.getText().toString());
        recibidoModificado.setPais(pais.getText().toString());


        jugadorCont.modificaPerfil(getContext(), recibidoModificado);
    }

    private void opcionRegistrar(){
        if (nombre.getText().toString().isEmpty() ||
                fechaNaci.getText().toString().isEmpty() ||
                pass1.getText().toString().isEmpty() ||
                pass2.getText().toString().isEmpty())
            Toast.makeText(getContext(), "Valores no insertados", Toast.LENGTH_SHORT).show();

        else if (!pass1.getText().toString().equals(pass2.getText().toString())) {
            Toast.makeText(getContext(), "Error en la contraseña, no son iguales", Toast.LENGTH_SHORT).show();
            pass1.setText("");
            pass2.setText("");

        } else {
            Jugador add = new Jugador(
                    imagen,
                    nombre.getText().toString(),
                    pais.getText().toString(),
                    1000,
                    "2003-04-01",
                    correo.getText().toString(),
                    Security.getMD5(pass1.getText().toString()),
                    0);

            jugadorCont.introduceJugador(getContext(), add);

        }
    }

    @Override
    public void onClick(View v) {
        //Security.compruebaOtorgaPermisos((Context) dataActivity);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentResult.launch(intent);
    }

    ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {

                if(o.getData() != null && o.getData().getData() != null) {
                    //Uri imagenUri = o.getData().getData();
                    //foto.setImageURI(imagenUri);
                    imagen = "huevo";
                    //imagen = ImageController.byteArrayDelUri(requireContext(), imagenUri);
                }
            }
        }
    );
}
