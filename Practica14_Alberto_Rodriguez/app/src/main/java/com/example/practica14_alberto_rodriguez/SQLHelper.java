package com.example.practica14_alberto_rodriguez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.practica14_alberto_rodriguez.Modelo.Articulo;
import com.example.practica14_alberto_rodriguez.Modelo.ArticuloContract;
import com.example.practica14_alberto_rodriguez.Modelo.Carrito;
import com.example.practica14_alberto_rodriguez.Modelo.CarritoContract;
import com.example.practica14_alberto_rodriguez.Modelo.Usuario;
import com.example.practica14_alberto_rodriguez.Modelo.UsuarioContract;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {

    static final String DDBB_NAME = "Practica14.db";
    static final int DDBB_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DDBB_NAME, null, DDBB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ UsuarioContract.TABLE_NAME +" ("+
                    UsuarioContract.USER + " text primary key, "+
                    UsuarioContract.PASS + " text not null, "+
                    UsuarioContract.NAME + " text not null)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ ArticuloContract.TABLE_NAME +" ("+
                ArticuloContract.CODIGO +  " integer primary key autoincrement, "+
                ArticuloContract.NOMBRE +  " text not null, "+
                ArticuloContract.DESCRIP + " text not null, "+
                ArticuloContract.COLOR +   " text not null, "+
                ArticuloContract.PRECIO +  " real not null)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ CarritoContract.TABLE_NAME +" ("+
                CarritoContract.USUARIO +  " text not null, "+
                CarritoContract.ARTICULO + " int not null, "+
                CarritoContract.CANT +     " int not null, "+
                "FOREIGN KEY ("+CarritoContract.USUARIO+") REFERENCES "+UsuarioContract.TABLE_NAME+" ("+UsuarioContract.USER+") ON UPDATE CASCADE, "+
                "FOREIGN KEY ("+CarritoContract.ARTICULO+") REFERENCES "+ArticuloContract.TABLE_NAME+" ("+ArticuloContract.CODIGO+") ON UPDATE CASCADE"+
                ")");

        insertUser(new Usuario("user1", "pass1", "Alberto"), db);
        insertUser(new Usuario("user2", "pass2", "Rodriguez"), db);
        insertArticulo(new Articulo("Folio", "50 Folios A4", "Blanco", 2.3f), db);
        insertArticulo(new Articulo("Cuaderno de Dibujo", "Cuaderno de dibujo de 50 hojas", "Blanco", 4.5f), db);
        insertArticulo(new Articulo("Rotuladores", "Juego de 12 rotuladores de colores", "Multicolor", 8.0f), db);
        insertArticulo(new Articulo("Goma de Borrar", "Goma de borrar suave y duradera", "Rosa", 1.2f), db);
        insertArticulo(new Articulo("Regla", "Regla transparente de 30 cm", "Transparente", 1.0f), db);
        insertArticulo(new Articulo("Cinta Adhesiva", "Rollo de cinta adhesiva de doble cara", "Transparente", 2.5f), db);
        insertArticulo(new Articulo("Lápices de Colores", "Estuche de 24 lápices de colores", "Variados", 6.0f), db);
        insertArticulo(new Articulo("Calculadora", "Calculadora de bolsillo", "Negro", 12.5f), db);
        insertArticulo(new Articulo("Pegamento", "Tubo de pegamento líquido", "Transparente", 3.0f), db);
        insertArticulo(new Articulo("Post-its", "Bloc de notas adhesivas", "Amarillo", 1.8f), db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(Usuario a, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(UsuarioContract.USER, a.getUser());
        values.put(UsuarioContract.PASS, a.getPass());
        values.put(UsuarioContract.NAME, a.getNombre());


        db.insert(UsuarioContract.TABLE_NAME, UsuarioContract.USER + ", " + UsuarioContract.PASS + ", " + UsuarioContract.NAME, values);
    }

    public void insertArticulo(Articulo a, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(ArticuloContract.NOMBRE, a.getNombre());
        values.put(ArticuloContract.DESCRIP, a.getDescripcion());
        values.put(ArticuloContract.COLOR, a.getColor());
        values.put(ArticuloContract.PRECIO, a.getPrecio());

        db.insert(ArticuloContract.TABLE_NAME, ArticuloContract.NOMBRE + ", " + ArticuloContract.DESCRIP + ", " + ArticuloContract.COLOR + ", " + ArticuloContract.PRECIO, values);
    }

    public Usuario compruebaUser(Context context, String user, String pass){

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(UsuarioContract.TABLE_NAME, null, UsuarioContract.USER+" = ? and "+UsuarioContract.PASS+" = ?", new String[]{user, pass}, null, null, null);

        ArrayList<Usuario> usrValido = new ArrayList<>();

        while(c.moveToNext()){
            usrValido.add(new Usuario(
                    c.getString(c.getColumnIndexOrThrow(UsuarioContract.USER)),
                    c.getString(c.getColumnIndexOrThrow(UsuarioContract.PASS)),
                    c.getString(c.getColumnIndexOrThrow(UsuarioContract.NAME))
            ));
        }

        for(Usuario usr: usrValido){
            if(usr.getUser().equals(user) && usr.getPass().equals(pass))
                return usr;
        }

        return null;
    }

    public ArrayList<Articulo> listaArticulos(String[] columnas, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        ArrayList<Articulo> leidos = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(ArticuloContract.TABLE_NAME, columnas, selection, selectionArgs, groupBy, having, orderBy);

        while(c.moveToNext()){
            leidos.add(new Articulo(
                    c.getInt(c.getColumnIndexOrThrow(ArticuloContract.CODIGO)),
                    c.getString(c.getColumnIndexOrThrow(ArticuloContract.NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ArticuloContract.DESCRIP)),
                    c.getString(c.getColumnIndexOrThrow(ArticuloContract.COLOR)),
                    c.getFloat(c.getColumnIndexOrThrow(ArticuloContract.PRECIO))));
        }

        return leidos;
    }

    public String contCarrito(String[] selectionArgs){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(CarritoContract.TABLE_NAME, null, CarritoContract.USUARIO+" LIKE ?", selectionArgs, null, null, null);

        int cont = 0;
        while(c.moveToNext())
            cont += c.getInt(c.getColumnIndexOrThrow(CarritoContract.CANT));


        return ""+cont;
    }

    public int updateCarrito(String usr, int codeArt, boolean sumar){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        if(sumar)
            values.put(CarritoContract.CANT, CarritoContract.CANT+" + 1");
        else
            values.put(CarritoContract.CANT, CarritoContract.CANT+" - 1");

        return db.update(CarritoContract.TABLE_NAME,
                  values,
                  CarritoContract.USUARIO+" LIKE ? and "+CarritoContract.ARTICULO+" LIKE ?",
                  new String[]{usr, ""+codeArt});

    }

    public void actualizaCarrito(String usr, int codeArt){

        int devuelto = updateCarrito(usr, codeArt, true);

        if(devuelto == 0){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CarritoContract.USUARIO, usr);
            values.put(CarritoContract.ARTICULO, ""+codeArt);
            values.put(CarritoContract.CANT, ""+1);

            db.insert(CarritoContract.TABLE_NAME, null, values);
        }
    }

    public void eliminaCarrito(int codeArt){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CarritoContract.TABLE_NAME, CarritoContract.ARTICULO+" LIKE ?", new String[]{""+codeArt});
    }


    public ArrayList<Carrito> tablaCarrito(String user){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(CarritoContract.TABLE_NAME, null, CarritoContract.USUARIO+" LIKE ?", new String[]{user}, null, null, null);

        ArrayList<Carrito> crrValido = new ArrayList<>();

        while(c.moveToNext()){
            crrValido.add(new Carrito(
                    c.getString(c.getColumnIndexOrThrow(CarritoContract.USUARIO)),
                    c.getInt(c.getColumnIndexOrThrow(CarritoContract.ARTICULO)),
                    c.getInt(c.getColumnIndexOrThrow(CarritoContract.CANT))
            ));
        }

        return crrValido;
    }
}
