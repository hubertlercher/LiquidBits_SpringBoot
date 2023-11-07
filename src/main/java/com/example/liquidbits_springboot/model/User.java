package com.example.liquidbits_springboot.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID")
    private int userId;
    @Basic
    @Column(name = "USERNAME")
    private String username;
    @Basic
    @Column(name = "MAIL")
    private String mail;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Drink> drinksByUserId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, mail);
    }

    public Collection<Drink> getDrinksByUserId() {
        return drinksByUserId;
    }

    public void setDrinksByUserId(Collection<Drink> drinksByUserId) {
        this.drinksByUserId = drinksByUserId;
    }
}
