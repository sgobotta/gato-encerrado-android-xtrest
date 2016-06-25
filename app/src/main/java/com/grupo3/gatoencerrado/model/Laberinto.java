package com.grupo3.gatoencerrado.model;

public class Laberinto {
    private int idLaberinto;
    private String nombreLaberinto;
    private String imagePath;
    private boolean isPlaying;

    public Laberinto(int id, String nombre, String pathImagen, boolean isPlaying) {
        this.idLaberinto       = id;
        this.nombreLaberinto   = nombre;
        this.imagePath         = pathImagen;
        this.isPlaying         = isPlaying;
    }

    // solo para Json.fromObject (backend en play)
    public Laberinto() {
    }

    public int getIdLaberinto() {
        return idLaberinto;
    }

    public void setIdLaberinto(int idLaberinto) {
        this.idLaberinto = idLaberinto;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNombreLaberinto() {
        return nombreLaberinto;
    }

    public void setNombreLaberinto(String nombreLaberinto) {
        this.nombreLaberinto = nombreLaberinto;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    @Override
    public String toString() {
        return nombreLaberinto;
    }

}
