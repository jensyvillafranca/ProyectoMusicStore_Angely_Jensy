package com.example.proyectomusicstore_angely_jensy.Models;

public class vistaDeUsuario {
    private String text1;

    private int imageResource;

    public vistaDeUsuario(String text1, int imageResource) {
        this.text1 = text1;

        this.imageResource = imageResource;
    }

    public String getText1() {
        return text1;
    }


    public int getImageResource() {
        return imageResource;
    }
}
