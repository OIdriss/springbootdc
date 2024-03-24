package org.example.webshop2.product;

import org.example.webshop2.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable("productId") Long productId){
        return productService.getProduct(productId);
    }

    @DeleteMapping(path = "/delete/{productId}")
    public void deleteUser(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }
}
