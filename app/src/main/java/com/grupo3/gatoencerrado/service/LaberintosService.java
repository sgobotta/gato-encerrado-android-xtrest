package com.grupo3.gatoencerrado.service;

/**
 import retrofit.Callback;
 import retrofit.http.GET;
 */

public interface LaberintosService {
    /**
     @GET("/laberintos")
     void getLaberintos(Callback<List<Laberinto>> callback);

     @GET("/laberintos/{LaberintoId}")
     void getLaberinto(@retrofit.http.Path("LaberintoId") String id, Callback<Laberinto> callback);
     */

}
