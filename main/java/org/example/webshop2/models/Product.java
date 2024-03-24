package org.example.webshop2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String SKU;
    private String name;
    private String description;
    private String category;
    private double price;
    @Column(name = "date_added")
    private LocalDate dateAdded;
    @JoinColumn(name = "added_by")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private CustomUser customUser;
    private int stock;
    private boolean listed;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<OrderedProduct> orderedProducts;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Image> images;

    public Product() {
    }

    public Product(String SKU, String name, String description, String category,
                   double price, LocalDate dateAdded, CustomUser customUser, int stock, boolean listed) {
        this.SKU = SKU;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.dateAdded = dateAdded;
        this.customUser = customUser;
        this.stock = stock;
        this.listed = listed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public CustomUser getUser() {
        return customUser;
    }

    public void setUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isListed() {
        return listed;
    }

    public void setListed(boolean listed) {
        this.listed = listed;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
