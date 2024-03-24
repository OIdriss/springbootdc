package org.example.webshop2.security;

public class AuthenticationDTO {
    public String name;
    public String lastName;

    public String email;
    public String password;

    public AuthenticationDTO(String name, String lastName ,String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
