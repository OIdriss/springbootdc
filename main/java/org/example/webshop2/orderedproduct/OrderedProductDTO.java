package org.example.webshop2.orderedproduct;

import com.fasterxml.jackson.annotation.JsonAlias;

public class OrderedProductDTO {
    @JsonAlias("product_id")
    private Long productID;
    @JsonAlias("order_id")
    private Long orderID;
    private int quantity;

    public OrderedProductDTO() {
    }

    public OrderedProductDTO(Long productID, Long orderID, int quantity) {
        this.productID = productID;
        this.orderID = orderID;
        this.quantity = quantity;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
