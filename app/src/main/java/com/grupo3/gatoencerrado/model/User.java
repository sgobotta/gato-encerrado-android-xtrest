package com.grupo3.gatoencerrado.model;

/**
 * Created by Juan on 22/06/2016.
 */
public class User {
    private int id;
    private String nombre;

    @Override
    public String toString(){
        return nombre;
    }

    public int getID(){
        return id;
    }
}
