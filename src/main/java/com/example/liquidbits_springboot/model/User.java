package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "MAIL")
    private String mail;
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Drink> drinks = new HashSet<>();


    @Column(name = "IMAGE")
    private String image;

    @Column(name = "AGE")
    private int age;

    @Column(name = "SEX")
    private char sex;

    //region Constructor

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    //endregion



    //region Getter and Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    //endregion


    //region haschCode equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(surname, user.surname) && Objects.equals(firstname, user.firstname) && Objects.equals(mail, user.mail) && Objects.equals(drinks, user.drinks) && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, surname, firstname, mail, drinks, image);
    }
    //endregion

    //region toString

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    //endregion
}
