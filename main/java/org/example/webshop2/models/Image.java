package org.example.webshop2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "image_url")
    private String imageURL;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;

    public Image() {
    }

    public Image(String imageURL,Product product) {
        this.imageURL = imageURL;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", product=" + product +
                '}';
    }
}


