package com.example.practica17_alberto_rodriguez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.practica17_alberto_rodriguez.Coche.Coche;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listado = findViewById(R.id.recycler);
        listado.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listado.setLayoutManager(linearLayoutManager);

        ArrayList<Coche> coches = rellenaLista();

        RecyclerAdapter miAdapter = new RecyclerAdapter(coches);
        listado.setAdapter(miAdapter);
    }

    public ArrayList<Coche> rellenaLista(){
        ArrayList<Coche> coches = new ArrayList<>();
        coches.add(new Coche("12345678901234567", "Toyota", "Corolla", "gasolina", "azul", 20000));
        coches.add(new Coche("23456789012345678", "Ford", "Focus", "diésel", "negro", 35000));
        coches.add(new Coche("34567890123456789", "Honda", "Civic", "híbrido", "verde", 15000));
        coches.add(new Coche("45678901234567890", "Chevrolet", "Cruze", "gasolina", "rojo", 25000));
        coches.add(new Coche("56789012345678901", "Volkswagen", "Golf", "diésel", "blanco", 30000));
        coches.add(new Coche("67890123456789012", "Nissan", "Sentra", "gasolina", "gris", 18000));
        coches.add(new Coche("78901234567890123", "Hyundai", "Elantra", "híbrido", "amarillo", 22000));
        coches.add(new Coche("89012345678901234", "Mazda", "3", "gasolina", "azul", 27000));
        coches.add(new Coche("90123456789012345", "Subaru", "Impreza", "diésel", "negro", 32000));
        coches.add(new Coche("01234567890123456", "Kia", "Forte", "híbrido", "verde", 19000));
        coches.add(new Coche("12345678901234567", "Toyota", "Camry", "gasolina", "rojo", 23000));
        coches.add(new Coche("23456789012345678", "Ford", "Mustang", "diésel", "gris", 28000));
        coches.add(new Coche("34567890123456789", "Honda", "Accord", "gasolina", "blanco", 24000));
        coches.add(new Coche("45678901234567890", "Chevrolet", "Malibu", "híbrido", "negro", 20000));
        coches.add(new Coche("56789012345678901", "Volkswagen", "Passat", "diésel", "azul", 26000));
        coches.add(new Coche("11111111111111111", "Audi", "A3", "gasolina", "negro", 25000));
        coches.add(new Coche("22222222222222222", "BMW", "X5", "diésel", "blanco", 40000));
        coches.add(new Coche("33333333333333333", "Mercedes", "C-Class", "híbrido", "rojo", 18000));
        coches.add(new Coche("44444444444444444", "Volkswagen", "Tiguan", "gasolina", "azul", 30000));
        coches.add(new Coche("55555555555555555", "Ford", "Explorer", "diésel", "gris", 35000));
        coches.add(new Coche("66666666666666666", "Hyundai", "Tucson", "gasolina", "verde", 22000));
        coches.add(new Coche("77777777777777777", "Toyota", "Rav4", "híbrido", "amarillo", 28000));
        coches.add(new Coche("88888888888888888", "Kia", "Sportage", "gasolina", "negro", 32000));
        coches.add(new Coche("99999999999999999", "Nissan", "Rogue", "diésel", "rojo", 27000));
        coches.add(new Coche("10101010101010101", "Chevrolet", "Equinox", "híbrido", "azul", 23000));
        coches.add(new Coche("12121212121212121", "Mazda", "CX-5", "gasolina", "blanco", 26000));
        coches.add(new Coche("13131313131313131", "Subaru", "Outback", "diésel", "gris", 29000));
        coches.add(new Coche("14141414141414141", "Honda", "HR-V", "gasolina", "verde", 21000));
        coches.add(new Coche("15151515151515151", "Ford", "Escape", "híbrido", "amarillo", 24000));
        coches.add(new Coche("16161616161616161", "Jeep", "Cherokee", "diésel", "rojo", 33000));
        coches.add(new Coche("17171717171717171", "Volkswagen", "Atlas", "gasolina", "azul", 36000));
        coches.add(new Coche("18181818181818181", "Audi", "Q5", "híbrido", "negro", 19000));
        coches.add(new Coche("19191919191919191", "Toyota", "Highlander", "diésel", "blanco", 31000));
        return coches;
    }
}