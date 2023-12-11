package com.example.practica16_alberto_rodriguez;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.practica16_alberto_rodriguez.Coche.Coche;
import com.example.practica16_alberto_rodriguez.Coche.CocheContract;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DDBB_NAME = "almCoches";
    static final int DDBB_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DDBB_NAME, null, DDBB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CocheContract.TABLE_NAME + " (" +
                CocheContract.NUM_BASTIDOR + " text PRIMARY KEY," +
                CocheContract.MARCA + " text not null," +
                CocheContract.MODELO + " text not null," +
                CocheContract.COMBUSTIBLE + " text not null," +
                CocheContract.COLOR + " text not null," +
                CocheContract.KILOMETRAJE + " integer not null)");

        insertaPrimerosCoches(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertaPrimerosCoches(SQLiteDatabase db){
        ArrayList<Coche> listaDeCoches = new ArrayList<>();

        listaDeCoches.add(new Coche("12345678901234567", "Toyota", "Corolla", "gasolina", "azul", 20000));
        listaDeCoches.add(new Coche("23456789012345678", "Ford", "Focus", "diésel", "negro", 35000));
        listaDeCoches.add(new Coche("34567890123456789", "Honda", "Civic", "híbrido", "verde", 15000));
        listaDeCoches.add(new Coche("45678901234567890", "Chevrolet", "Cruze", "gasolina", "rojo", 25000));
        listaDeCoches.add(new Coche("56789012345678901", "Volkswagen", "Golf", "diésel", "blanco", 30000));
        listaDeCoches.add(new Coche("67890123456789012", "Nissan", "Sentra", "gasolina", "gris", 18000));
        listaDeCoches.add(new Coche("78901234567890123", "Hyundai", "Elantra", "híbrido", "amarillo", 22000));
        listaDeCoches.add(new Coche("89012345678901234", "Mazda", "3", "gasolina", "azul", 27000));
        listaDeCoches.add(new Coche("90123456789012345", "Subaru", "Impreza", "diésel", "negro", 32000));
        listaDeCoches.add(new Coche("01234567890123456", "Kia", "Forte", "híbrido", "verde", 19000));
        listaDeCoches.add(new Coche("12345678901234567", "Toyota", "Camry", "gasolina", "rojo", 23000));
        listaDeCoches.add(new Coche("23456789012345678", "Ford", "Mustang", "diésel", "gris", 28000));
        listaDeCoches.add(new Coche("34567890123456789", "Honda", "Accord", "gasolina", "blanco", 24000));
        listaDeCoches.add(new Coche("45678901234567890", "Chevrolet", "Malibu", "híbrido", "negro", 20000));
        listaDeCoches.add(new Coche("56789012345678901", "Volkswagen", "Passat", "diésel", "azul", 26000));

        for(Coche c: listaDeCoches){
            insertaCoche(db, c);
        }
    }

    public void insertaCoche(SQLiteDatabase db, Coche c){
        ContentValues values = new ContentValues();
        values.put(CocheContract.NUM_BASTIDOR, c.getNumBastidor());
        values.put(CocheContract.MARCA, c.getMarca());
        values.put(CocheContract.MODELO, c.getModelo());
        values.put(CocheContract.COMBUSTIBLE, c.getCombustible());
        values.put(CocheContract.COLOR, c.getColor());
        values.put(CocheContract.KILOMETRAJE, c.getKilometraje());

        if(db == null){
            db = getWritableDatabase();
        }

        db.insert(CocheContract.TABLE_NAME, null, values);
    }
}
