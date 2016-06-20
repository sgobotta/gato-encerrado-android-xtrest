package com.grupo3.gatoencerrado.service;

import com.grupo3.gatoencerrado.model.Laberinto;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface LaberintosService {

     @GET("/laberintos/1")
     void getLaberintos(Callback<List<Laberinto>> callback);

     @GET("/laberintos/{LaberintoId}")
     void getLaberinto(@retrofit.http.Path("LaberintoId") String id, Callback<Laberinto> callback);


}
