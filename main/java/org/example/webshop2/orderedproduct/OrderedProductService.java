package org.example.webshop2.orderedproduct;

import org.example.webshop2.address.AddressDTO;
import org.example.webshop2.models.*;
import org.example.webshop2.order.OrderDTO;
import org.example.webshop2.order.OrderRepository;
import org.example.webshop2.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderedProductService {
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderedProductService(OrderedProductRepository orderedProductRepository, ProductRepository productRepository,
                                 OrderRepository orderRepository) {
        this.orderedProductRepository = orderedProductRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderedProductDTO convertToDTO(OrderedProduct orderedProduct){
        Product product = orderedProduct.getProduct();
        Order order = orderedProduct.getOrder();
        OrderedProductDTO orderedProductDTO = new OrderedProductDTO(product.getId(), order.getId(), orderedProduct.getQuantity());
        return orderedProductDTO;
    }

    private List<OrderedProductDTO> convertToDTOs(List<OrderedProduct> orderedProducts) {
        List<OrderedProductDTO> dtos = new ArrayList<>();
        for (OrderedProduct orderedProduct : orderedProducts) {
            dtos.add(convertToDTO(orderedProduct));
        }
        return dtos;
    }

    public List<OrderedProductDTO> getAllOrderedProducts() {
        List<OrderedProduct> orderedProducts = orderedProductRepository.findAll();
        return convertToDTOs(orderedProducts);
    }

    public OrderedProductDTO getOrderedProduct(Long orderedproductID){
        OrderedProduct orderedProduct = orderedProductRepository.findById(orderedproductID).orElse(null);
        if (orderedProduct == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered product not found");
        }
        return convertToDTO(orderedProduct);
    }

    public void addOrderedProduct(OrderedProductDTO orderedProductDTO) {
        Optional<Product> optionalProduct = productRepository.findById(orderedProductDTO.getProductID());
        Optional<Order> optionalOrder = orderRepository.findById(orderedProductDTO.getOrderID());

        if (optionalProduct.isPresent() && optionalOrder.isPresent()){
            Product product = optionalProduct.get();
            if (product.getStock() >= orderedProductDTO.getQuantity()) {
                Order order = optionalOrder.get();
                OrderedProduct orderedProduct = new OrderedProduct(product, order, orderedProductDTO.getQuantity());
                orderedProductRepository.save(orderedProduct);
                int newStock = product.getStock() - orderedProductDTO.getQuantity();
                product.setStock(newStock);
                productRepository.save(product);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock too low");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resources not found");
        }
    }


    public void deleteOrderedProduct(Long orderedProductId) {
        boolean orderedProductExists = orderedProductRepository.existsById(orderedProductId);
        if (!orderedProductExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered product with id not found");
        }
        orderedProductRepository.deleteById(orderedProductId);
    }
}
