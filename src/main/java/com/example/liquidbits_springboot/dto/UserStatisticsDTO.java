package com.example.liquidbits_springboot.dto;

public class UserStatisticsDTO {

    //region Properties
    private int userId;
    private String surname;
    private String firstname;
    private String image;
    private double drinksServedL;
    //endregion Properties

    //region Getter and Setter

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getDrinksServedL() {
        return drinksServedL;
    }

    public void setDrinksServedL(double drinksServedL) {
        this.drinksServedL = drinksServedL;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //endregion Getter and Setter


}
