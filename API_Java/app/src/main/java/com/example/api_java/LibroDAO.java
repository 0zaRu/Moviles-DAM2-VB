package com.example.api_java;

import com.example.api_java.model.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Body;

public interface LibroDAO {
    // Método para insertar un libro
    @POST("crearLibro.php")
    public Call<Libro> createLibro(@Body Libro libro);

    // Método para obtener todas los libros
    @GET("getLibros.php")
    public Call<List<Libro>> getLibros();

    // Método para obtener un libro por su ID
    @GET("getLibro.php")
    public Call<Libro> getLibro(@Query("id") int id);
}
