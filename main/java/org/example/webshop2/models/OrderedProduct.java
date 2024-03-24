package org.example.webshop2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ordered_product")
public class OrderedProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Product product;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Order order;
    private int quantity;

    public OrderedProduct() {
    }

    public OrderedProduct(Product product, Order order, int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
