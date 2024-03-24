package org.example.webshop2.order;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public record OrderDTO(@JsonAlias("user_id")Long userID, @JsonAlias("order_status")String orderStatus,
                       @JsonAlias("order_date")LocalDate orderDate) {
}
