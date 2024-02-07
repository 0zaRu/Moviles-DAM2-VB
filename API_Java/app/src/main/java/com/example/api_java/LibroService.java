package com.example.api_java;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.api_java.model.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LibroService {
    // Instancia de Retrofit
    //La url es la correspondiente a la localización del servidor.
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/app-clase/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Método para insertar un libro
    public void createLibro(Libro libro) {
        getRetrofit().create(LibroDAO.class).createLibro(libro).enqueue(
                new Callback<Libro>() {
                    @Override
                    public void onResponse(@NonNull Call<Libro> call, @NonNull Response<Libro> response) {
                        Log.d("TAG", "RESPONSE: " + response);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Libro> call, @NonNull Throwable t) {
                        Log.d("TAG", "Error");
                        Log.d("TAG", "ERROR: " + t.getMessage());
                    }
                }
        );
    }

    //Método para ver todos los libros
    public Call<List<Libro>> getLibros()  {
        return getRetrofit().create(LibroDAO.class).getLibros();
    }

    //Método para ver un libro por ID
    public Call<Libro> getLibro(int id) {
        return getRetrofit().create(LibroDAO.class).getLibro(id);
    }
}
