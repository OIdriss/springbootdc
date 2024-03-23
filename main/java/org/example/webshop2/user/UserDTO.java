package org.example.webshop2.user;

import com.fasterxml.jackson.annotation.JsonAlias;


public record UserDTO(String name, @JsonAlias("last_name")String lastName, @JsonAlias("email_address")String emailAddress, String password, boolean authority) {
}
