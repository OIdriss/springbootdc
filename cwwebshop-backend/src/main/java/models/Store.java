package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Product> products;

    public Store(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Store() {
    }
}
