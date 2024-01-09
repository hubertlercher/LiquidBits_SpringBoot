package com.example.liquidbits_springboot.dto;

public class UserStatisticsDTO {

    //region Properties
    private String name;
    private byte[] image;
    private double drinksServedL;
    //endregion Properties

    //region Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getDrinksServedL() {
        return drinksServedL;
    }

    public void setDrinksServedL(double drinksServedL) {
        this.drinksServedL = drinksServedL;
    }


    //endregion Getter and Setter


}
