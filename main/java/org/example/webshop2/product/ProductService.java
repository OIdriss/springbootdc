package org.example.webshop2.product;

import org.apache.catalina.User;
import org.example.webshop2.address.AddressDTO;
import org.example.webshop2.models.Address;
import org.example.webshop2.models.CustomUser;
import org.example.webshop2.models.Product;
import org.example.webshop2.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductDTO convertToDTO(Product product){
        CustomUser customUser = product.getUser();
        ProductDTO productDTO = new ProductDTO(product.getSKU(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getDateAdded(),
                customUser.getId(),
                product.getStock(),
                product.isListed(),
                product.getImages()
        );
        return productDTO;
    }

    private List<ProductDTO> convertToDTOs(List<Product> products) {
        List<ProductDTO> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(convertToDTO(product));
        }
        return dtos;
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return convertToDTOs(products);
    }

    public ProductDTO getProduct(Long productId){
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return convertToDTO(product);
    }

    public void deleteProduct(Long productId) {
        boolean productExists = productRepository.existsById(productId);
        if (!productExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product with id not found");
        }
        productRepository.deleteById(productId);
    }

    public void updateProduct(Long productId, ProductDTO productDTO) {
        boolean productExists = productRepository.existsById(productId);
        if (!productExists) {
            throw new IllegalStateException("product with id doesn't exist, id: "+ productId);
        }
        else {
            Product product = productRepository.findById(productId).orElse(null);
            product.setName(productDTO.name());
            product.setSKU(productDTO.SKU());
            product.setStock(productDTO.stock());
            product.setCategory(productDTO.category());
            product.setDescription(productDTO.description());
            product.setListed(productDTO.listed());
            product.setPrice(productDTO.price());
            productRepository.save(product);
        }
    }
}
