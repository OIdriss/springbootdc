package org.example.webshop2.orderedproduct;

import org.example.webshop2.models.OrderedProduct;
import org.example.webshop2.order.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orderedproduct")
public class OrderedProductController {
    private final OrderedProductService orderedProductService;

    @Autowired
    public OrderedProductController(OrderedProductService orderedProductService) {
        this.orderedProductService = orderedProductService;
    }

    @GetMapping
    public List<OrderedProduct> getAllOrderedProducts(){
        return orderedProductService.getAllOrderedProducts();
    }

    @GetMapping("/{orderedproductID}")
    public Optional<OrderedProduct> getOrderedProduct(@PathVariable("orderedproductID") Long orderedProductId){
        return orderedProductService.getOrderedProduct(orderedProductId);
    }

    @PostMapping("/order/{orderID}/product/{productID}")
    public void addOrderedProduct(@PathVariable Long orderID, @PathVariable Long productID, @RequestBody OrderedProductDTO orderedProductDTO){
        orderedProductDTO.setOrderID(orderID);
        orderedProductDTO.setProductID(productID);
        orderedProductService.addOrderedProduct(orderedProductDTO);
    }

    @DeleteMapping(path = "/delete/{orderedproductID}")
    public void deleteOrderedProduct(@PathVariable("orderedproductID") Long orderedProductID){
        orderedProductService.deleteOrderedProduct(orderedProductID);
    }

}
