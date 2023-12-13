package com.example.practica16_alberto_rodriguez.Dialogs;

import com.example.practica16_alberto_rodriguez.Coche.Coche;

public interface OnDialogEvent {
    public void addCoche(Coche coche);
    public void modificarCoche(Coche coche);
    public void eliminaCoche(Coche coche);
}
