package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    private CustomUser customUser;
    private LocalDate orderDate;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderedProduct> orderedProducts;

    public Order() {
    }
    public Order(CustomUser customUser, LocalDate orderDate, List<OrderedProduct> orderedProducts) {
        this.customUser = customUser;
        this.orderDate = orderDate;
        this.orderedProducts = orderedProducts;
    }

}
