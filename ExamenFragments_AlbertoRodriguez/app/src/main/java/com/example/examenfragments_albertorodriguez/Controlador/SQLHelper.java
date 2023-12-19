package com.example.examenfragments_albertorodriguez.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.examenfragments_albertorodriguez.Modelo.Libro;
import com.example.examenfragments_albertorodriguez.Modelo.LibroContract;
import com.example.examenfragments_albertorodriguez.Modelo.Usuario;
import com.example.examenfragments_albertorodriguez.Modelo.UsuarioContract;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    public SQLHelper(@Nullable Context context) {
        super(context, "Examen.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + LibroContract.TABLE_NAME + "( "
                + LibroContract.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LibroContract.TITULO + " TEXT NOT NULL,"
                + LibroContract.AUTOR + " TEXT NOT NULL,"
                + LibroContract.NUMPAGS + " INTEGER NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + UsuarioContract.TABLE_NAME + "( "
                + UsuarioContract.USER + " TEXT PRIMARY KEY,"
                + UsuarioContract.PASSWORD + " TEXT NOT NULL,"
                + UsuarioContract.ROL + " TEXT NOT NULL)");

        Usuario u = new Usuario("u1", "p1", "cliente");
        ContentValues values = new ContentValues();
        values.put(UsuarioContract.USER, u.getUser());
        values.put(UsuarioContract.PASSWORD, u.getPass());
        values.put(UsuarioContract.ROL, u.getRol());

        sqLiteDatabase.insert(UsuarioContract.TABLE_NAME, null, values);

        u = new Usuario("u2", "p2", "cliente");
        values = new ContentValues();
        values.put(UsuarioContract.USER, u.getUser());
        values.put(UsuarioContract.PASSWORD, u.getPass());
        values.put(UsuarioContract.ROL, u.getRol());

        sqLiteDatabase.insert(UsuarioContract.TABLE_NAME, null, values);

        u = new Usuario("admin", "admin", "administrador");
        values = new ContentValues();
        values.put(UsuarioContract.USER, u.getUser());
        values.put(UsuarioContract.PASSWORD, u.getPass());
        values.put(UsuarioContract.ROL, u.getRol());

        sqLiteDatabase.insert(UsuarioContract.TABLE_NAME, null, values);

        Libro l = new Libro("La sombra del viento", "Carlos Ruiz Zafon", 450);
        values = new ContentValues();
        values.put(LibroContract.TITULO, l.getTitulo());
        values.put(LibroContract.AUTOR, l.getAutor());
        values.put(LibroContract.NUMPAGS, l.getNumPags());

        sqLiteDatabase.insert(LibroContract.TABLE_NAME, LibroContract.ID, values);

        l = new Libro("El manuscrito de piedra", "Luis García Jambrina", 270);
        values = new ContentValues();
        values.put(LibroContract.TITULO, l.getTitulo());
        values.put(LibroContract.AUTOR, l.getAutor());
        values.put(LibroContract.NUMPAGS, l.getNumPags());

        sqLiteDatabase.insert(LibroContract.TABLE_NAME, LibroContract.ID, values);

        l = new Libro("Todas las criaturas oscuras", "Paula Gallego ", 630);
        values = new ContentValues();
        values.put(LibroContract.TITULO, l.getTitulo());
        values.put(LibroContract.AUTOR, l.getAutor());
        values.put(LibroContract.NUMPAGS, l.getNumPags());

        sqLiteDatabase.insert(LibroContract.TABLE_NAME, LibroContract.ID, values);

        l = new Libro("El infierno", "Carmen Mola", 320);
        values = new ContentValues();
        values.put(LibroContract.TITULO, l.getTitulo());
        values.put(LibroContract.AUTOR, l.getAutor());
        values.put(LibroContract.NUMPAGS, l.getNumPags());

        sqLiteDatabase.insert(LibroContract.TABLE_NAME, LibroContract.ID, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Usuario iniciarSesion (String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(UsuarioContract.TABLE_NAME, new String[]{UsuarioContract.ROL}, UsuarioContract.USER + " = ? AND " + UsuarioContract.PASSWORD + " = ?" , new String[]{user, pass}, null, null, null, null);

        if (c.moveToNext()) {
            return new Usuario(user, pass, c.getString(c.getColumnIndexOrThrow(UsuarioContract.ROL)));
        } else {
            return null;
        }
    }

    public void insertarLibro(Libro l) {
        SQLiteDatabase sql = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LibroContract.TITULO, l.getTitulo());
        values.put(LibroContract.AUTOR, l.getAutor());
        values.put(LibroContract.NUMPAGS, l.getNumPags());

        sql.insert(LibroContract.TABLE_NAME, LibroContract.ID, values);
    }

    public ArrayList<Libro> getLibros () {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(LibroContract.TABLE_NAME, null, null, null, null, null, LibroContract.TITULO, null);

        ArrayList<Libro> libros = new ArrayList<>();

        while (c.moveToNext()) {
            libros.add(new Libro(c.getInt(c.getColumnIndexOrThrow(LibroContract.ID)),
                    c.getString(c.getColumnIndexOrThrow(LibroContract.TITULO)),
                    c.getString(c.getColumnIndexOrThrow(LibroContract.AUTOR)),
                    c.getInt(c.getColumnIndexOrThrow(LibroContract.NUMPAGS))));
        }

        return libros;
    }
}
