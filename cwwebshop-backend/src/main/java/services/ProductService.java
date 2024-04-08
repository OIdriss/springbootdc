package services;

import org.springframework.stereotype.Service;
import repositories.ProductRepo;

@Service
public class ProductService {
    ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
}
