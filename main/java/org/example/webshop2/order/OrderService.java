package org.example.webshop2.order;

import org.example.webshop2.models.*;
import org.example.webshop2.product.ProductRepository;
import org.example.webshop2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long orderId) {
        boolean orderExists = orderRepository.existsById(orderId);
        if (!orderExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order with id doesn't exist, id: "+ orderId);
        }
        return orderRepository.findById(orderId);
    }

    public void addOrder(OrderDTO orderDTO) {
        Optional<CustomUser> userOptional = userRepository.findById(orderDTO.userID());
        if (userOptional.isPresent()) {
            CustomUser customUser = userOptional.get();
            Order order = new Order(customUser, orderDTO.orderStatus(), orderDTO.orderDate());
            orderRepository.save(order);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void deleteOrder(Long orderId) {
        boolean orderExists = orderRepository.existsById(orderId);
        if (!orderExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id not found");
        }
        orderRepository.deleteById(orderId);
    }

    public void updateOrder(Long orderID, OrderDTO orderDTO) {
        boolean orderExists = orderRepository.existsById(orderID);
        if (!orderExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        } else {
            Order order = orderRepository.getById(orderID);
            order.setOrderDate(orderDTO.orderDate());
            order.setOrderStatus(orderDTO.orderStatus());
            orderRepository.save(order);
        }
    }

    public void placeOrder(ResponseOrderDTO responseOrderDTO){
        Optional<CustomUser> userOptional = userRepository.findById(responseOrderDTO.userId());
        if (userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        CustomUser customUser = userOptional.get();
        Order order = new Order(customUser, "Pending", LocalDate.now());
        order.setOrderedProducts(new ArrayList<>());

        List<Long> productIds = responseOrderDTO.productIds();
        List<Long> quantities = responseOrderDTO.quantities();

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + productId + " not found");
            }

            Product product = optionalProduct.get();
            Long quantity = quantities.get(i);
            if (product.getStock() < quantity) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough stock for product with id " + productId);
            }

            OrderedProduct orderedProduct = new OrderedProduct(product, order, quantity.intValue());
            int newStock = product.getStock() - quantity.intValue();
            product.setStock(newStock);
            productRepository.save(product);
            order.getOrderedProducts().add(orderedProduct);
        }
        orderRepository.save(order);
    }
}
