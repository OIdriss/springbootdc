package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private String infix;
    private String lastName;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String role;
    @OneToMany
    @JsonManagedReference
    private List<Order> orders;

    public CustomUser() {
    }
    public CustomUser(String email, String password, String name, String infix, String lastName, String streetName, String houseNumber, String zipCode, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.infix = infix;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.role = role;
    }


}


