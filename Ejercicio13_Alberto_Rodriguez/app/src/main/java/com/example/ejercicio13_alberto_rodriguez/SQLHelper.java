package com.example.ejercicio13_alberto_rodriguez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {


    public SQLHelper(@Nullable Context context) {
        super(context, "Alumnos.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + AlumnosContract.TABLE_NAME + " ("
                + AlumnosContract.DNI + " text primary key,"
                + AlumnosContract.NOMBRE + " text not null,"
                + AlumnosContract.APELLIDOS + " text not null,"
                + AlumnosContract.EDAD + " integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion == 1 && newVersion == 2){
            //ALTER TABLE `nombreTabla` ADD `nuevaColumna` tipoDato [CONSTRAINT]
            //db.execSQL("ALTER TABLE "+AlumnosContract.TABLE_NAME+ " SET "+ AlumnosContract.TELEFONO);
        }else{
            db.execSQL("CREATE TABLE " + AlumnosContract.TABLE_NAME + " ("
                    + AlumnosContract.DNI + " text primary key,"
                    + AlumnosContract.NOMBRE + " text not null,"
                    + AlumnosContract.APELLIDOS + " text not null,"
                    + AlumnosContract.EDAD + " integer not null,"
                    + AlumnosContract.TELEFONO + " text)");
        }
    }

    public long insertAlumno(Alumno a){
        ContentValues values = new ContentValues();
        values.put(AlumnosContract.DNI, a.getDni());
        values.put(AlumnosContract.NOMBRE, a.getNombre());
        values.put(AlumnosContract.APELLIDOS, a.getApellidos());
        values.put(AlumnosContract.EDAD, a.getEdad());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(AlumnosContract.TABLE_NAME, null, values);
    }

    public int updateAlumno(Alumno a){
        ContentValues values = new ContentValues();
        values.put(AlumnosContract.NOMBRE, a.getNombre());
        values.put(AlumnosContract.APELLIDOS, a.getApellidos());
        values.put(AlumnosContract.EDAD, a.getEdad());

        SQLiteDatabase db = getWritableDatabase();
        return db.update(AlumnosContract.TABLE_NAME, values, AlumnosContract.DNI + " = ?", new String[]{a.getDni()});
    }

    public int deleteAlumno(Alumno a){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(AlumnosContract.TABLE_NAME,AlumnosContract.DNI + " = ?", new String[]{a.getDni()});
    }

    public Cursor select(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(AlumnosContract.TABLE_NAME, null, null, null, null, null, null);
    }
}
