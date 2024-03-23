package org.example.webshop2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    @Column(name = "house_number")
    private String housenumber;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String country;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private CustomUser customUser;

    public Address() {
    }

    public Address(String street, String housenumber, String zipCode, String city, String country, CustomUser customUser) {
        this.street = street;
        this.housenumber = housenumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.customUser = customUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CustomUser getUser() {
        return customUser;
    }

    public void setUser(CustomUser customUser) {
        this.customUser = customUser;
    }
}
