package org.example.webshop2.orderedproduct;

import org.example.webshop2.models.Order;
import org.example.webshop2.models.OrderedProduct;
import org.example.webshop2.models.Product;
import org.example.webshop2.order.OrderDTO;
import org.example.webshop2.order.OrderRepository;
import org.example.webshop2.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<OrderedProduct> getAllOrderedProducts() {
        return orderedProductRepository.findAll();
    }

    public Optional<OrderedProduct> getOrderedProduct(Long orderedproductID){
        boolean orderedProductExists = orderedProductRepository.existsById(orderedproductID);
        if (!orderedProductExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered product not found");
        }
        return orderedProductRepository.findById(orderedproductID);
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
