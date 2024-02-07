package com.example.proyectofinal_alberto_rodriguezperez.DAO;

import com.example.proyectofinal_alberto_rodriguezperez.model.Libro;

import java.util.List;

import retrofit2.*;
import retrofit2.http.*;

public interface LibroDAO {
    @POST("crearLibro.php")
    public Call<Libro> createLibro(@Body Libro libro);

    @GET("getLibros.php")
    public Call<List<Libro>> getLibros();

    @GET("getLibro.php")
    public Call<Libro> getLibro(@Query("id") int id);
}
