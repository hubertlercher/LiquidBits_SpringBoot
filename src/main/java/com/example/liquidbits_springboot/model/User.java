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
    @JsonIgnore
    @Column(name = "NAME")
    private String name;
    @JsonIgnore
    @Column(name = "MAIL")
    private String mail;
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Drink> drinks = new HashSet<>();

    @JsonIgnore
    @Column(name = "IMAGE")
    private byte[] image;

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

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, mail);
    }

    public Collection<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }
}
