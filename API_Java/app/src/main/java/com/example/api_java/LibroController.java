package com.example.api_java;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.api_java.model.Libro;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibroController {
    private final LibroService serviceLibro = new LibroService();

    public void createLibros(Libro libro) {
        serviceLibro.createLibro(libro);
    }

    public void getLibros() {
        serviceLibro.getLibros().enqueue(
                new Callback<List<Libro>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Libro>> call, @NonNull Response<List<Libro>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            for (Libro libro: response.body())
                                Log.d("TAG", libro.toString());
                        } else {
                            Log.d("TAG", "Error");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Libro>> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }

    public void getLibro(int id) {
        serviceLibro.getLibro(id).enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(@NonNull Call<Libro> call, @NonNull Response<Libro> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());
                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Libro> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
