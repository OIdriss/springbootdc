package org.example.webshop2.product;

import org.example.webshop2.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long productId){
        boolean productExists = productRepository.existsById(productId);
        if (!productExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return productRepository.findById(productId);
    }

    public void deleteProduct(Long productId) {
        boolean productExists = productRepository.existsById(productId);
        if (!productExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product with id not found");
        }
        productRepository.deleteById(productId);
    }
}
