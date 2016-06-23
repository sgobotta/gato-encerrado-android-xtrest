package com.grupo3.gatoencerrado.model;


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
