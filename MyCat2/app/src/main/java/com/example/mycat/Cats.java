package com.example.mycat;

import java.io.Serializable;

public class Cats implements Serializable {

    private String name;

    private String area;
    private String streetAndBlock;
    private String number;
    private String details;
    private String breed;
    private int image;

    public Cats(String name, String area, String streetAndBlock, String number, String details, String breed, int image) {
        this.name = name;
        this.area = area;
        this.streetAndBlock = streetAndBlock;
        this.number = number;
        this.details = details;
        this.breed = breed;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreetAndBlock() {
        return streetAndBlock;
    }

    public void setStreetAndBlock(String streetAndBlock) {
        this.streetAndBlock = streetAndBlock;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}