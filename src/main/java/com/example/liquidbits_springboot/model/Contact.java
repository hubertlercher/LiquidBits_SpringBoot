package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "CONTACT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact implements Serializable {

    //region Properties
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    @Column(name = "CONTACT_ID")
    private int contactId;

    @NotNull
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Column(name = "MAIL")
    private String mail;

    @Column(name = "TELEPHONE")
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "contact",
    cascade = CascadeType.MERGE,
    orphanRemoval = false,
    fetch = FetchType.LAZY)
    private Collection<MaintenanceLog> logs;


    //endregion Properties

    //region Getter and Setter
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    //endregion

    //region hashCode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact that = (Contact) o;
        return contactId == that.contactId && Objects.equals(name, that.name) && Objects.equals(mail, that.mail) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, name, mail, telephone);
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        return "ContactBook{" +
                "contactId=" + contactId +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
    //endregion

}
