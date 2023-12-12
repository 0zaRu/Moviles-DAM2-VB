package com.example.practica16_alberto_rodriguez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        listaDeCoches.add(new Coche("11111111111111111", "Audi", "A3", "gasolina", "negro", 25000));
        listaDeCoches.add(new Coche("22222222222222222", "BMW", "X5", "diésel", "blanco", 40000));
        listaDeCoches.add(new Coche("33333333333333333", "Mercedes", "C-Class", "híbrido", "rojo", 18000));
        listaDeCoches.add(new Coche("44444444444444444", "Volkswagen", "Tiguan", "gasolina", "azul", 30000));
        listaDeCoches.add(new Coche("55555555555555555", "Ford", "Explorer", "diésel", "gris", 35000));
        listaDeCoches.add(new Coche("66666666666666666", "Hyundai", "Tucson", "gasolina", "verde", 22000));
        listaDeCoches.add(new Coche("77777777777777777", "Toyota", "Rav4", "híbrido", "amarillo", 28000));
        listaDeCoches.add(new Coche("88888888888888888", "Kia", "Sportage", "gasolina", "negro", 32000));
        listaDeCoches.add(new Coche("99999999999999999", "Nissan", "Rogue", "diésel", "rojo", 27000));
        listaDeCoches.add(new Coche("10101010101010101", "Chevrolet", "Equinox", "híbrido", "azul", 23000));
        listaDeCoches.add(new Coche("12121212121212121", "Mazda", "CX-5", "gasolina", "blanco", 26000));
        listaDeCoches.add(new Coche("13131313131313131", "Subaru", "Outback", "diésel", "gris", 29000));
        listaDeCoches.add(new Coche("14141414141414141", "Honda", "HR-V", "gasolina", "verde", 21000));
        listaDeCoches.add(new Coche("15151515151515151", "Ford", "Escape", "híbrido", "amarillo", 24000));
        listaDeCoches.add(new Coche("16161616161616161", "Jeep", "Cherokee", "diésel", "rojo", 33000));
        listaDeCoches.add(new Coche("17171717171717171", "Volkswagen", "Atlas", "gasolina", "azul", 36000));
        listaDeCoches.add(new Coche("18181818181818181", "Audi", "Q5", "híbrido", "negro", 19000));
        listaDeCoches.add(new Coche("19191919191919191", "Toyota", "Highlander", "diésel", "blanco", 31000));
        listaDeCoches.add(new Coche("20202020202020202", "Mitsubishi", "Outlander", "gasolina", "gris", 27000));
        listaDeCoches.add(new Coche("21212121212121212", "Buick", "Enclave", "híbrido", "verde", 24000));

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

    public ArrayList<Coche> selectCoches(@Nullable String numBastidor){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        if(numBastidor == null || numBastidor.isEmpty())
            c = db.query(CocheContract.TABLE_NAME, null, null, null, null, null, null);

        else
            c = db.query(CocheContract.TABLE_NAME, null, CocheContract.NUM_BASTIDOR +" LIKE ?", new String[]{numBastidor}, null, null, null);


        ArrayList<Coche> coches = new ArrayList<>();

        while(c.moveToNext()){
            coches.add(new Coche(
                    c.getString(c.getColumnIndexOrThrow(CocheContract.NUM_BASTIDOR)),
                    c.getString(c.getColumnIndexOrThrow(CocheContract.MARCA)),
                    c.getString(c.getColumnIndexOrThrow(CocheContract.MODELO)),
                    c.getString(c.getColumnIndexOrThrow(CocheContract.COMBUSTIBLE)),
                    c.getString(c.getColumnIndexOrThrow(CocheContract.COLOR)),
                    c.getInt(c.getColumnIndexOrThrow(CocheContract.KILOMETRAJE))
            ));
        }

        return coches;
    }
}
