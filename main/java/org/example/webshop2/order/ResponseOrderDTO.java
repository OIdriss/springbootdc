package org.example.webshop2.order;

import java.util.List;

public record ResponseOrderDTO(List<Long> productIds, List<Long> quantities,Long userId) {
}
