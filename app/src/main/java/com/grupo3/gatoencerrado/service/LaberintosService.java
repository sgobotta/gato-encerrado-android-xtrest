package com.grupo3.gatoencerrado.service;

import com.grupo3.gatoencerrado.model.Elemento;
import com.grupo3.gatoencerrado.model.Laberinto;
import com.grupo3.gatoencerrado.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface LaberintosService {

//     @GET("/laberintos/1")
//     void getLaberintos(Callback<List<Laberinto>> callback);
//
//     @GET("/laberintos/{LaberintoId}")
//     void getLaberinto(@retrofit.http.Path("LaberintoId") String id, Callback<Laberinto> callback);

    @GET("/android/laberintos/{idUser}")
    void getLaberintosForUser(@Path("idUser") int idUser, Callback<List<Laberinto>> listCallback);

    @GET("/android/users/logged")
    void getUsersPlaying(Callback<List<User>> listCallback);

    @GET("/android/inventario/{idUser}/{idLab}")
    void getInventoryForUser(@Path("idUser") int idUser, @Path("idLab") int idLab, Callback<List<Elemento>> listCallback);
}
