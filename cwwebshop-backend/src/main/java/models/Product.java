package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
    @ManyToOne
    @JsonBackReference
    private Store store;

    public Product(String name, String description, String category, double price, int stock, Store store) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.store = store;
    }

    public Product() {
    }
}
