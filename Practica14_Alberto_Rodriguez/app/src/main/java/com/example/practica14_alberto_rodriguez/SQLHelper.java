package com.example.practica14_alberto_rodriguez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.practica14_alberto_rodriguez.Modelo.ArticuloContract;
import com.example.practica14_alberto_rodriguez.Modelo.CarritoContract;
import com.example.practica14_alberto_rodriguez.Modelo.UsuarioContract;

public class SQLHelper extends SQLiteOpenHelper {

    static final String DDBB_NAME = "Practica14.db";
    static final int DDBB_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DDBB_NAME, null, DDBB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ UsuarioContract.TABLE_NAME +" ( "+
                    UsuarioContract.USER + " text primary key, "+
                    UsuarioContract.PASS + " text not null, "+
                    UsuarioContract.NAME + " text not null)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ ArticuloContract.TABLE_NAME +" ( "+
                ArticuloContract.CODIGO +  " int primary key autoincrement, "+
                ArticuloContract.NOMBRE +  " text not null, "+
                ArticuloContract.DESCRIP + " text not null, "+
                ArticuloContract.COLOR +   " text not null, "+
                ArticuloContract.PRECIO +  " real not null)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ CarritoContract.TABLE_NAME +" ( "+
                CarritoContract.USUARIO +  " text not null, "+
                CarritoContract.ARTICULO + " int not null, "+
                CarritoContract.CANT +     " int not null, "+
                "FOREIGN KEY ("+CarritoContract.USUARIO+") REFERENCES "+UsuarioContract.TABLE_NAME+" ("+UsuarioContract.USER+") ON UPDATE CASCADE, "+
                "FOREIGN KEY ("+CarritoContract.ARTICULO+") REFERENCES "+ArticuloContract.TABLE_NAME+" ("+ArticuloContract.CODIGO+") ON UPDATE CASCADE"+
                ")");

        db.execSQL("INSERT INTO "+UsuarioContract.TABLE_NAME+" ( "+
                   UsuarioContract.USER+", "+UsuarioContract.PASS+", "+UsuarioContract.NAME+") " +
                   "VALUES (user1, pass1, Alberto)");

        db.execSQL("INSERT INTO "+UsuarioContract.TABLE_NAME+" ( "+
                UsuarioContract.USER+", "+UsuarioContract.PASS+", "+UsuarioContract.NAME+") " +
                "VALUES (user2, pass2, Rodriguez)");

        db.execSQL("INSERT INTO "+ArticuloContract.TABLE_NAME+" ( "+
                ArticuloContract.NOMBRE +  ", "+ ArticuloContract.DESCRIP + ", "+ ArticuloContract.COLOR + ", "+ ArticuloContract.PRECIO +  ") "+
                "VALUES ('Folio', '50 Folios A4', 'Blanco', 2.3)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Cuaderno de Dibujo', 'Cuaderno de dibujo de 50 hojas', 'Blanco', 4.5)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Rotuladores', 'Juego de 12 rotuladores de colores', 'Multicolor', 8.0)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Goma de Borrar', 'Goma de borrar suave y duradera', 'Rosa', 1.2)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Regla', 'Regla transparente de 30 cm', 'Transparente', 1.0)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Cinta Adhesiva', 'Rollo de cinta adhesiva de doble cara', 'Transparente', 2.5)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Lápices de Colores', 'Estuche de 24 lápices de colores', 'Variados', 6.0)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Calculadora', 'Calculadora de bolsillo', 'Negro', 12.5)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Pegamento', 'Tubo de pegamento líquido', 'Transparente', 3.0)");

        db.execSQL("INSERT INTO " + ArticuloContract.TABLE_NAME + " (" +
                ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO + ") " +
                "VALUES ('Post-its', 'Bloc de notas adhesivas', 'Amarillo', 1.8)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
