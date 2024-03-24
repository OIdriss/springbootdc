package org.example.webshop2.product;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.example.webshop2.models.Image;

import java.time.LocalDate;
import java.util.List;

public record ProductDTO(String SKU, String name, String description, String category,
                         double price, LocalDate dateAdded, @JsonAlias("user_id") Long userID, int stock, boolean listed, List<Image> images) {
}
