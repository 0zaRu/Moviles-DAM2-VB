package com.example.practica13_alberto_rodriguez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DDBB_NAME = "Alumnos.db";
    static final int DDBB_VERSION = 4;
    public SQLHelper(@Nullable Context context) {
        super(context, DDBB_NAME, null, DDBB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + AlumnosContract.TABLE_NAME + " ("
                + AlumnosContract.DNI + " text primary key,"
                + AlumnosContract.NOMBRE + " text not null,"
                + AlumnosContract.APELLIDOS + " text not null,"
                + AlumnosContract.EDAD + " integer not null,"
                + AlumnosContract.TELEFONO + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if((oldVersion == 2 || oldVersion == 3 || oldVersion == 1) && newVersion == 4){
            //ALTER TABLE `nombreTabla` ADD `nuevaColumna` tipoDato [CONSTRAINT]
            db.execSQL("ALTER TABLE "+AlumnosContract.TABLE_NAME +
                        " ADD COLUMN "+ AlumnosContract.TELEFONO +" text");

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
        values.put(AlumnosContract.TELEFONO, a.getTelefono());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(AlumnosContract.TABLE_NAME, null, values);
    }

    public int updateAlumno(Alumno a){
        ContentValues values = new ContentValues();
        values.put(AlumnosContract.NOMBRE, a.getNombre());
        values.put(AlumnosContract.APELLIDOS, a.getApellidos());
        values.put(AlumnosContract.EDAD, a.getEdad());
        values.put(AlumnosContract.TELEFONO, a.getTelefono());

        SQLiteDatabase db = getWritableDatabase();

        return db.update(AlumnosContract.TABLE_NAME,
                values,
                AlumnosContract.DNI + " = ?",
                new String[]{a.getDni()});
    }

    public int deleteAlumno(Alumno a){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(AlumnosContract.TABLE_NAME,AlumnosContract.DNI + " = ?", new String[]{a.getDni()});
    }

    public Cursor select(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(AlumnosContract.TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public ArrayList<Alumno> extraerDB(String columns[], String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor alumnos = select(columns, selection, selectionArgs, groupBy, having, orderBy);
        ArrayList<Alumno> recogidos = new ArrayList<>();

        while(alumnos.moveToNext()){
            recogidos.add(new Alumno(
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.DNI)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.NOMBRE)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.APELLIDOS)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.EDAD)),
                    alumnos.getString(alumnos.getColumnIndexOrThrow(AlumnosContract.TELEFONO))
            ));
        }
        return recogidos;
    }
}
