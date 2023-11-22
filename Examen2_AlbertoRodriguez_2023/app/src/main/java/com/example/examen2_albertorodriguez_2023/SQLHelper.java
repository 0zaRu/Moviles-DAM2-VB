package com.example.examen2_albertorodriguez_2023;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    private static final int DDBB_VERSION = 1;
    private static final String DDBB_NAME = "Mascotas.db";

    public SQLHelper(Context context){
        super(context, DDBB_NAME, null, DDBB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ MascotaContract.NOMBRE_TABLA + " ("
                    +MascotaContract.CODIGO+" integer primary key,"
                    +MascotaContract.NOMBRE+" text not null,"
                    +MascotaContract.PESO+" decimal not null,"
                    +MascotaContract.TIPO+" text not null)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertar(Animal a){
        ContentValues values = new ContentValues();
        values.put(MascotaContract.CODIGO, a.getCodigo());
        values.put(MascotaContract.NOMBRE, a.getNombre());
        values.put(MascotaContract.PESO, a.getPeso());
        values.put(MascotaContract.TIPO, a.getTipo());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(MascotaContract.NOMBRE_TABLA, null, values);
    }

    public ArrayList<Animal> selecciona(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        ArrayList<Animal> recogidos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(MascotaContract.NOMBRE_TABLA, columns, selection, selectionArgs, groupBy, having, orderBy);

        while(c.moveToNext()){
            recogidos.add(new Animal(
                c.getInt(c.getColumnIndexOrThrow(MascotaContract.CODIGO)),
                c.getString(c.getColumnIndexOrThrow(MascotaContract.NOMBRE)),
                c.getDouble(c.getColumnIndexOrThrow(MascotaContract.PESO)),
                c.getString(c.getColumnIndexOrThrow(MascotaContract.TIPO))
            ));
        }

        return recogidos;
    }

    public void borrar(int codigo){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MascotaContract.NOMBRE_TABLA,
                MascotaContract.CODIGO + " = ?",
                new String[]{Integer.toString(codigo)});
    }
}
