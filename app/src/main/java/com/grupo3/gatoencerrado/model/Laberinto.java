package com.grupo3.gatoencerrado.model;

public class Laberinto {
    private int id;
    private String nombre;
    private String pathImagen;

    public Laberinto(int id, String nombre, String pathImagen) {
        this.id         = id;
        this.nombre     = nombre;
        this.pathImagen = pathImagen;
    }

    // solo para Json.fromObject (backend en play)
    public Laberinto() {
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getPathImagen() {
        return this.pathImagen;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return nombre;
    }

}
