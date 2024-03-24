package org.example.webshop2.image;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ImageDTO(String imageURL, @JsonAlias("product_id") Long productID) {
}
