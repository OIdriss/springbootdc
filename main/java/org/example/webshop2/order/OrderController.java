package org.example.webshop2.order;

import org.example.webshop2.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/{orderID}")
    public Optional<Order> getOrder(@PathVariable("orderID") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping
    public void addOrder(@RequestBody OrderDTO orderDTO){
        orderService.addOrder(orderDTO);
    }

    @DeleteMapping(path = "/delete/{orderID}")
    public void deleteOrder(@PathVariable("orderID") Long orderId){
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/update/{orderID}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderID, @RequestBody OrderDTO updatedOrder){
        orderService.updateOrder(orderID, updatedOrder);
        return ResponseEntity.ok("Updated order: " + orderID);
    }

    @PostMapping("/place")
    public void placeOrder(@RequestBody ResponseOrderDTO responseOrderDTO){
        orderService.placeOrder(responseOrderDTO);
    }
}
