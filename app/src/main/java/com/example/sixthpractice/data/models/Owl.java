package com.example.sixthpractice.data.models;


public class Owl {

    int image;
    String name;
    String type;

    public Owl(String name, String type, int image) {
        this.image = image;
        this.name = name;
        this.type = type;
    }

    public String getAuthor() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setOwl(String type) {
        this.type = type;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

}

