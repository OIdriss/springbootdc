package org.example.webshop2.product;

import org.example.webshop2.address.AddressDTO;
import org.example.webshop2.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProduct(@PathVariable("productId") Long productId){
        return productService.getProduct(productId);
    }

    @DeleteMapping(path = "/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId,
                                                @RequestBody ProductDTO productDTO){
        productService.updateProduct(productId,productDTO);
        return ResponseEntity.ok("Updated product:" + productId);
    }
}
