package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class OrderedProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Product product;
    @ManyToOne
    @JsonBackReference
    private Order order;
    private int quantity;

    public OrderedProduct(Product product, Order order, int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderedProduct() {
    }
}
