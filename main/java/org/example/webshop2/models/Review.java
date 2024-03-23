package org.example.webshop2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String review;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private CustomUser customUser;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Product product;

    public Review() {
    }

    public Review(String title, String review, CustomUser customUser, Product product) {
        this.title = title;
        this.review = review;
        this.customUser = customUser;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public CustomUser getUser() {
        return customUser;
    }

    public void setUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
